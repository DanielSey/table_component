package com.idc.loader;

import com.idc.model.CSVRow;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The DataLoader class is used to read data from the given file.
 *
 * @author Daniel Zboril
 * @version 2.0
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
     * Get raw/no changed data from given csv file.
     *
     * @return List of all csvRows without share value.
     */
    public List<CSVRow> getRawData(){
        FileReader file = null;
        try {
            file = new FileReader(pathToFile);

            List<CSVRow> csvRows = new CsvToBeanBuilder<CSVRow>(file)
                    .withType(CSVRow.class)
                    .build()
                    .parse();

            return csvRows;

        } catch (FileNotFoundException e) {
            System.out.println("Error - file not exists. Error message: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}
