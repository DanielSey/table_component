package com.idc.model;

import com.opencsv.bean.CsvBindByName;

/**
 * CSVRow class is for mapping loaded data from CSV file.
 *
 * @author Daniel Zboril
 * @version 2.0
 */
public class CSVRow {

    /**
     * Column of country.
     */
    @CsvBindByName
    private String country;

    /**
     * Column of timescale.
     */
    @CsvBindByName
    private String timescale;

    /**
     * Column of vendor.
     */
    @CsvBindByName
    private String vendor;

    /**
     * Column of unit values.
     */
    @CsvBindByName
    private String units;

    /**
     * Get current country.
     *
     * @return current county
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set new country.
     *
     * @param country Name of the new country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get current timescale.
     *
     * @return current timescale
     */
    public String getTimescale() {
        return timescale;
    }

    /**
     * Set new timescale.
     *
     * @param timescale Name of the new timescale.
     */
    public void setTimescale(String timescale) {
        this.timescale = timescale;
    }

    /**
     * Get current vendor.
     *
     * @return current vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Set new vendor.
     *
     * @param vendor Name of the new vendor.
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Get current units.
     *
     * @return current units
     */
    public String getUnits() {
        return units;
    }

    /**
     * Set new units.
     *
     * @param units Name of the new units.
     */
    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "CSVRow{" +
                "country='" + country + '\'' +
                ", timescale='" + timescale + '\'' +
                ", vendor='" + vendor + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}
