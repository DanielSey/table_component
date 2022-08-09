package com.idc.export;

import com.idc.model.DataTable;

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
        // 1. create header with columns names
        // 2. write every row from selectedData list
        // 1b. use Java API that works with Excel files such as Aspose.Cells
    }
}
