package data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The DataTable class is used for working with loaded data (CSVRow objects).
 *
 * @author Daniel Zboril
 * @version 1.0
 */
public class DataTable {

    /**
     * List of CSVRow objects.
     */
    private final List<CSVRow> csvRows;

    /**
     * Default DataTable constructor.
     *
     * @param csvRows list of CSVRow objects
     */
    public DataTable(List<CSVRow> csvRows) {
        this.csvRows = csvRows;
    }

    /**
     * Get units and share value for given vendor.
     *
     * @param vendorName Name of the vendor for which we want to return data.
     */
    public void getVendorStats(String vendorName) {
        for (CSVRow c : csvRows) {
            if (c.getVendor().equals(vendorName)) {
                System.out.println("Units: " + c.getUnits() + ", Share: " + c.getShare());
                break;
            }
        }
    }

    /**
     * Get row where the vendor is located.
     *
     * @param vendorName Name of the vendor for which we want to return the row.
     */
    public void getVendorRow(String vendorName) {
        for (int i = 0; i < csvRows.size(); i++) {
            if (csvRows.get(i).getVendor().equals(vendorName)) {
                System.out.println(vendorName + " is on the" + (i + 1) + ". row");
                break;
            }
        }
    }

    /**
     * Sort list of CSVRow objects by vendor.
     *
     * @param direction Sort in ascending or descending order (true - ascending, false - descending).
     */
    public void sortByVendor(boolean direction) {
        Collections.sort(csvRows, new Comparator<CSVRow>() {
            @Override
            public int compare(CSVRow o1, CSVRow o2) {
                if (direction)
                    return o1.getVendor().toLowerCase().compareTo(o2.getVendor().toLowerCase());
                else
                    return o2.getVendor().toLowerCase().compareTo(o1.getVendor().toLowerCase());
            }
        });
        System.out.println("Sorting by vendor has been completed.");
    }

    /**
     * Sort list of CSVRow objects by units.
     *
     * @param direction Sort in ascending or descending order (true - ascending, false - descending).
     */
    public void sortByUnits(boolean direction) {
        Collections.sort(csvRows, new Comparator<CSVRow>() {
            @Override
            public int compare(CSVRow o1, CSVRow o2) {
                if (direction)
                    return Integer.valueOf(o1.getUnits()).compareTo(Integer.valueOf(o2.getUnits()));
                else
                    return Integer.valueOf(o2.getUnits()).compareTo(Integer.valueOf(o1.getUnits()));
            }
        });
        System.out.println("Sorting by units has been completed.");
    }

    /**
     * Print all information about list of CSVRow objects.
     */
    public void printAll() {
        for (CSVRow c : csvRows) {
            System.out.println(c.toString());
        }
    }

    /**
     * Getter function for csvRows.
     *
     * @return list of CSVRow objects.
     */
    public List<CSVRow> getCsvRows() {
        return csvRows;
    }
}
