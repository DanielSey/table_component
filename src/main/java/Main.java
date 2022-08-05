import data.DataLoader;
import data.DataTable;
import export.CSVExporter;
import export.Exporter;
import export.HTMLExporter;

/**
 * Main class.
 *
 * @author Daniel Zboril
 * @version 1.0
 */
public class Main {

    /**
     * Main method for table component program.
     *
     * @param args Stores command line arguments for program.
     */
    public static void main(String[] args) {
        final String PATH = "data.csv";

        DataLoader ld = new DataLoader(PATH);
        DataTable dataTable = ld.getDataTable("Czech Republic", "2010 Q4");

        dataTable.printAll();
        dataTable.getVendorStats("Dell");
        dataTable.getVendorRow("Dell");

        //dataTable.sortByVendor(true);
        dataTable.sortByUnits(false);
        dataTable.printAll();

        Exporter exporter = new HTMLExporter();
        exporter.exportData(dataTable, "test.html");

        exporter = new CSVExporter();
        exporter.exportData(dataTable, "test.csv");
    }
}