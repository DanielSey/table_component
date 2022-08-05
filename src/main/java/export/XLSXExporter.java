package export;

import data.DataTable;

/**
 * XLSXExporter class is for export DataTable object into XLSX format.
 *
 * @author Daniel Zboril
 * @version 1.0
 */
public class XLSXExporter implements Exporter {

    /**
     * Export DataTable object into XLSX format.
     *
     * @param dataTable DataTable object for which we want to export data.
     * @param fileName The name of the XLSX file.
     */
    @Override
    public void exportData(DataTable dataTable, String fileName) {
        // TODO
        // 1. create header with Columns enum
        // 2. write every row from components list
        // 1b. use Java API that works with Excel files such as Aspose.Cells
    }
}
