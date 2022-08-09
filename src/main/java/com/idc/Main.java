package com.idc;

import com.idc.loader.DataLoader;
import com.idc.model.DataTable;
import com.idc.export.CSVExporter;
import com.idc.export.Exporter;
import com.idc.export.HTMLExporter;

/**
 * Main class.
 *
 * @author Daniel Zboril
 * @version 2.0
 */
public class Main {

    /**
     * Main method for table component program.
     *
     * @param args Stores command line arguments for program.
     */
    public static void main(String[] args) {
        final String PATH = "data.csv";

        DataLoader dataLoader = new DataLoader(PATH);
        DataTable dataTable = new DataTable(dataLoader.getRawData());
        dataTable.selectData("Czech Republic", "2010 Q4", true);

        dataTable.printSelectedData();
        System.out.println(dataTable.getVendorStats("Dell"));
        System.out.println(dataTable.getVendorRow("Dell"));

        //dataTable.sortByVendor(true);
        dataTable.sortByUnits(false);

        dataTable.printSelectedData();

        Exporter exporter = new HTMLExporter();
        exporter.exportData(dataTable, "test.html");

        exporter = new CSVExporter();
        exporter.exportData(dataTable, "test.csv");
    }
}