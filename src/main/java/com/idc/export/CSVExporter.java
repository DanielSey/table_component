package com.idc.export;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.idc.model.DataTable;

import java.io.FileWriter;
import java.io.Writer;

/**
 * CSVExporter class is for export DataTable object into CSV format.
 *
 * @author Daniel Zboril
 * @version 2.0
 */
public class CSVExporter implements Exporter {

    /**
     * Export DataTable object into CSV format.
     *
     * @param dataTable DataTable object for which we want to export data.
     * @param fileName The name of the CSV file.
     */
    @Override
    public void exportData(DataTable dataTable, String fileName) {
        try {
            Writer writer = new FileWriter(fileName);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(dataTable.getSelectedData());
            writer.close();
            System.out.println("Successfully exported into: " + fileName);
        }
        catch (Exception e) {
            System.out.println("Export failed!");
        }
    }
}
