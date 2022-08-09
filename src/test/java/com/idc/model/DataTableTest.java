package com.idc.model;

import com.idc.loader.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DataTable class methods.
 *
 * @author Daniel Zboril
 * @version 1.0
 */
class DataTableTest {

    /**
     * DataTable object.
     */
    private DataTable dataTable;

    /**
     * Prepare data for each test.
     */
    @BeforeEach
    void setUp() {
        final String PATH = "data.csv";

        DataLoader dataLoader = new DataLoader(PATH);
        dataTable = new DataTable(dataLoader.getRawData());
        dataTable.selectData("Czech Republic", "2010 Q4", true);
    }

    /**
     * Testing the getVendorStats method for Dell.
     */
    @Test
    void getDellVendorStats() {
        assertEquals("Units: 11455, Share: 51.1", dataTable.getVendorStats("Dell"));
    }

    /**
     * Testing the getVendorStats method for wrong vendor.
     */
    @Test
    void getWrongVendorStats() {
        assertEquals("vendor not found", dataTable.getVendorStats("Delll"));
    }

    /**
     * Testing the correct sorting of units with descending order.
     */
    @Test
    void fistAndLastUnitAscendingOrder() {
        dataTable.sortByUnits(false);
        List<DataTableRow> dataTableRows = dataTable.getSelectedData();
        String firstUnit = dataTableRows.get(0).getUnits();
        String lastUnit = dataTableRows.get(dataTableRows.size()-1).getUnits();

        assertEquals("11455", firstUnit);
        assertEquals("266", lastUnit);
    }
}