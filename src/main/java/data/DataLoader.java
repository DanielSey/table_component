package data;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The DataLoader class is used to read data from the given file.
 *
 * @author Daniel Zboril
 * @version 1.0
 */
public class DataLoader {

    /**
     * Represents path to given file.
     */
    private final String pathToFile;

    /**
     * Default LoadData constructor.
     *
     * @param pathToFile path to given file
     */
    public DataLoader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    /**
     * Gets DataTable object for the selected country and timescale. Share value is also computed.
     *
     * @param country Country for which we want to get data.
     * @param timescale Timescale for which we want to get data.
     * @return DataTable object for given country and timescale with computed share value
     */
    public DataTable getDataTable(String country, String timescale) {
        List<CSVRow> csvRows = this.getRawData();
        List<CSVRow> newData = new ArrayList<>();

        // get selected data and compute units sum
        double unitsSum = 0; // 100%
        for (CSVRow c : csvRows) {
            if (c.getCountry().equals(country) &&
                c.getTimescale().equals(timescale)) {
                newData.add(c);
                unitsSum += Double.parseDouble(c.getUnits());
            }
        }

        // compute share values
        for (CSVRow c : newData) {
            double unit = Double.parseDouble(c.getUnits());
            double result = this.roundNumber((unit / unitsSum) * 100, 1);
            c.setShare(result);
        }

        formatUnitColumn(newData);

        return new DataTable(newData);
    }

    /**
     * Get raw/no changed data.
     *
     * @return List of all csvRows without share value.
     */
    public List<CSVRow> getRawData(){
        FileReader file = null;
        try {
            file = new FileReader(pathToFile);

            List<CSVRow> CSVRows = new CsvToBeanBuilder<CSVRow>(file)
                    .withType(CSVRow.class)
                    .build()
                    .parse();

            return CSVRows;

        } catch (FileNotFoundException e) {
            System.out.println("Error - file not exists. Error message: " + e.getMessage());
        }

        return new ArrayList<>();
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
     * Format units in list of CSVRow objects.
     *
     * @param csvRows List of CSVRow objects in which we want to format units.
     */
    private void formatUnitColumn(List<CSVRow> csvRows) {
        for (CSVRow c : csvRows) {
            c.setUnits(c.getUnits().split("\\.")[0]);
        }
    }
}
