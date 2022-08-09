package com.idc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The DataTable class is used for working with loaded data.
 *
 * @author Daniel Zboril
 * @version 2.0
 */
public class DataTable {

    /**
     * List of CSVRow objects.
     */
    private final List<CSVRow> csvRows;

    /**
     * List of selected rows by user with given params (country, timescale, formatUnits) as DataTableRow objects.
     */
    private final List<DataTableRow> selectedData;

    /**
     * Default DataTable constructor. Initialize list of CSVRow objects and selectedData list.
     *
     * @param csvRows List of CSVRow objects.
     */
    public DataTable(List<CSVRow> csvRows) {
        this.csvRows = csvRows;
        selectedData = new ArrayList<>();
    }

    /**
     * Get units and share value for given vendor.
     *
     * @param vendorName Name of the vendor for which we want to return data.
     * @return Stats for given vendor, if found.
     */
    public String getVendorStats(String vendorName) {
        if (checkSelectedData()) {
            for (DataTableRow dataTableRow : selectedData) {
                if (dataTableRow.getVendor().equals(vendorName)) {
                    //System.out.println("Units: " + dataTableRow.getUnits() + ", Share: " + dataTableRow.getShare());
                    return "Units: " + dataTableRow.getUnits() + ", Share: " + dataTableRow.getShare();
                }
            }
            return "vendor not found";
        }
        return "";
    }

    /**
     * Get row where the vendor is located.
     *
     * @param vendorName Name of the vendor for which we want to return the row.
     * @return Row for given vendor, if found.
     */
    public String getVendorRow(String vendorName) {
        if (checkSelectedData()) {
            for (int i = 0; i < selectedData.size(); i++) {
                if (selectedData.get(i).getVendor().equals(vendorName)) {
                    //System.out.println(vendorName + " is on the " + (i + 1) + ". row");
                    return vendorName + " is on the " + (i + 1) + ". row";
                }
            }
            return "vendor not found";
        }
        return "";
    }

    /**
     * Sort list of selected data by vendor.
     *
     * @param ascending Sort in ascending or descending order (true - ascending, false - descending).
     */
    public void sortByVendor(boolean ascending) {
        if (checkSelectedData()){
            if (ascending)
                selectedData.sort((firstDataTableRow, secondDataTableRow) -> firstDataTableRow.getVendor().toLowerCase().compareTo(secondDataTableRow.getVendor().toLowerCase()));
            else
                selectedData.sort((secondDataTableRow, firstDataTableRow) -> firstDataTableRow.getVendor().toLowerCase().compareTo(secondDataTableRow.getVendor().toLowerCase()));

            System.out.println("Sorting by vendor has been completed.");
        }
        else {
            System.out.println("Nothing to sort.");
        }
    }

    /**
     * Sort list of selected data by units.
     *
     * @param ascending Sort in ascending or descending order (true - ascending, false - descending).
     */
    public void sortByUnits(boolean ascending) {
        if (checkSelectedData()){
            if (ascending)
                selectedData.sort((firstDataTableRow, secondDataTableRow) -> Integer.valueOf(firstDataTableRow.getUnits()).compareTo(Integer.valueOf(secondDataTableRow.getUnits())));
            else
                selectedData.sort((secondDataTableRow, firstDataTableRow) -> Integer.valueOf(firstDataTableRow.getUnits()).compareTo(Integer.valueOf(secondDataTableRow.getUnits())));

            System.out.println("Sorting by units has been completed.");
        }
        else {
            System.out.println("Nothing to sort.");
        }
    }

    /**
     * Print all information about list of CSVRow objects.
     */
    public void printAllCsvRows() {
        for (CSVRow row : csvRows) {
            System.out.println(row.toString());
        }
    }

    /**
     * Print all information about list of selected data.
     */
    public void printSelectedData() {
        if (checkSelectedData()) {
            for (DataTableRow row : selectedData) {
                System.out.println(row.toString());
            }
        }
        else {
            System.out.println("Nothing to print.");
        }
    }

    /**
     * Check if selectedData is empty.
     *
     * @return True if list of selected data is not empty.
     */
    private boolean checkSelectedData() {
        if (!selectedData.isEmpty()){
            return true;
        }
        else {
            System.out.println("Error - no data had been selected. Use \"selectData\" method first.");
            return false;
        }
    }

    /**
     * Getter for csvRows.
     *
     * @return List of CSVRow objects.
     */
    public List<CSVRow> getCsvRows() {
        return csvRows;
    }

    /**
     * Getter for selectedData.
     *
     * @return List of DataTableRow objects.
     */
    public List<DataTableRow> getSelectedData() {
        return selectedData;
    }

    /**
     * Select data for the selected country and timescale. Share values are also computed.
     *
     * @param country Country for which we want to get data.
     * @param timescale Timescale for which we want to get data.
     * @param formatUnits If it is true, unit column will be formatted.
     */
    public void selectData(String country, String timescale, boolean formatUnits) {
        // get selected data and compute units sum
        double unitsSum = 0.0;
        for (CSVRow row : csvRows) {
            if (row.getCountry().equals(country) &&
                    row.getTimescale().equals(timescale)) {
                selectedData.add(new DataTableRow(row.getCountry(), row.getTimescale(), row.getVendor(), row.getUnits(), 0.0));
                unitsSum += Double.parseDouble(row.getUnits());
            }
        }

        computeShareValue(unitsSum, selectedData);

        if (formatUnits)
            formatUnitColumn(selectedData);
    }

    /**
     * Compute share values for given data.
     *
     * @param unitsSum Sum of values for which we want to calculate the share value.
     * @param dataTableRow List of DataTableRow objects for which we want to calculate the share value.
     */
    private void computeShareValue(double unitsSum, List<DataTableRow> dataTableRow) {
        for (DataTableRow row : dataTableRow) {
            double unit = Double.parseDouble(row.getUnits());
            double result = this.roundNumber((unit / unitsSum) * 100, 1);
            row.setShare(result);
        }
    }

    /**
     * Round given number with given precision.
     *
     * @param value number for rounding
     * @param precision number of decimal places
     * @return rounded number
     */
    private double roundNumber(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     * Format units in list of DataTableRow objects.
     *
     * @param dataTableRow List of DataTableRow objects in which we want to format units.
     */
    private void formatUnitColumn(List<DataTableRow> dataTableRow) {
        for (DataTableRow row : dataTableRow) {
            row.setUnits(row.getUnits().split("\\.")[0]);
        }
    }
}
