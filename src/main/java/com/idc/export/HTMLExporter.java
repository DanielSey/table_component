package com.idc.export;

import com.idc.model.DataTable;
import com.idc.model.DataTableRow;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * HTMLExporter class is for export DataTable object into HTML format.
 *
 * @author Daniel Zboril
 * @version 2.0
 */
public class HTMLExporter implements Exporter{

    /**
     * Data preparation for export.
     *
     * @param dataTable DataTable object for which we want to export data.
     * @return list of prepared data
     */
    private List<String> prepareData(DataTable dataTable) {
        String data = "";
        String summary = "";
        int unitsSum = 0;

        // create data rows
        for (DataTableRow dataTableRow : dataTable.getSelectedData()) {
            unitsSum += Integer.parseInt(dataTableRow.getUnits());
            data += "<tr>\n" +
                    "   <td>" + dataTableRow.getVendor() + "</td>\n" +
                    "   <td>" + formatNumber(Integer.parseInt(dataTableRow.getUnits())) + "</td>\n" +
                    "   <td>" + dataTableRow.getShare() + "%</td>\n" +
                    "</tr>";
        }

        // create summary row
        summary = "<tr class=\"summary\">\n" +
                  "   <td>Total</td>\n" +
                  "   <td>" + formatNumber(unitsSum) + "</td>\n" +
                  "   <td>100%</td>\n" +
                  "</tr>\n";

        return Arrays.asList(data, summary);
    }

    /**
     * Export DataTable object into HTML format.
     *
     * @param dataTable DataTable object for which we want to export data.
     * @param fileName The name of the HTML file.
     */
    @Override
    public void exportData(DataTable dataTable, String fileName) {
        String htmlString = null;
        try {
            htmlString = Files.readString(Paths.get("exportedData-template.html"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assign prepared data to html file
        List<String> data = prepareData(dataTable);
        htmlString = htmlString.replace("$data", data.get(0));
        htmlString = htmlString.replace("$summary", data.get(1));

        try {
            Files.writeString(Paths.get(fileName), htmlString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Successfully exported into: " + fileName);
    }

    /**
     * Format the number for better reading.
     *
     * @param number number to format
     * @return formatted number
     */
    private String formatNumber(int number) {
        return String.format("%,d", number);
    }
}
