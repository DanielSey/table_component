package export;

import data.CSVRow;
import data.DataTable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * HTMLExporter class is for export DataTable object into HTML format.
 *
 * @author Daniel Zboril
 * @version 1.0
 */
public class HTMLExporter implements Exporter{

    /**
     * Export DataTable object into HTML format.
     *
     * @param dataTable DataTable object for which we want to export data.
     * @param fileName The name of the HTML file.
     */
    @Override
    public void exportData(DataTable dataTable, String fileName) {
        // Prepare fileWriter values
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String start = "";
        String data = "";
        String end = "";
        int totalSum = 0;

        // create data part
        for (CSVRow c : dataTable.getCsvRows()) {
            totalSum += Integer.parseInt(c.getUnits());
            data += "<tr>\n" +
                    "    <td>" + c.getVendor() + "</td>\n" +
                    "    <td>" + formatNumber(Integer.parseInt(c.getUnits())) + "</td>\n" +
                    "    <td>" + c.getShare() + "%</td>\n" +
                    "</tr>";
        }

        // create html structure
        start = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Table component</title>\n" +
                "\n" +
                "    <style>\n" +
                "        table, td, th {\n" +
                "            border: 1px solid black;\n" +
                "        }\n" +
                "        \n" +
                "        table {\n" +
                "            border-collapse: collapse;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        th, td {\n" +
                "            padding: 0 50px;\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            font-weight: normal;\n" +
                "            background-color: rgb(217, 217, 217);\n" +
                "        }\n" +
                "\n" +
                "        .summary {\n" +
                "            background-color: rgb(255, 255, 153);\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <th>Vendor</th>\n" +
                "            <th>Units</th>\n" +
                "            <th>Share</th>\n" +
                "        </tr>";

        end = "        <tr class=\"summary\">\n" +
                "            <td>Total</td>\n" +
                "            <td>" + formatNumber(totalSum) + "</td>\n" +
                "            <td>100%</td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";

        // write to file
        writer.println(start);
        writer.println(data);
        writer.println(end);
        writer.close();

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
