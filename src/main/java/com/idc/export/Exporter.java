package com.idc.export;

import com.idc.model.DataTable;

/**
 * Interface for exporting data.
 *
 * @author Daniel Zboril
 * @version 1.0
 */
public interface Exporter {

    /**
     * Export DataTable object into different format.
     *
     * @param dataTable DataTable object for which we want to export data.
     * @param fileName The name of the file.
     */
    void exportData(DataTable dataTable, String fileName);
}
