package com.idc.model;

/**
 * DataTableRow class is for selected data by user.
 *
 * @author Daniel Zboril
 * @version 1.0
 */
public class DataTableRow {

    /**
     * Column of country.
     */
    private String country;

    /**
     * Column of timescale.
     */
    private String timescale;

    /**
     * Column of vendor.
     */
    private String vendor;

    /**
     * Column of unit values.
     */
    private String units;

    /**
     * Column of share values.
     */
    private double share;

    /**
     * Default DataTableRow constructor.
     *
     * @param country value of country
     * @param timescale value of timescale
     * @param vendor value of vendor
     * @param units value of units
     * @param share value of share
     */
    public DataTableRow(String country, String timescale, String vendor, String units, double share){
        this.country = country;
        this.timescale = timescale;
        this.vendor = vendor;
        this.units = units;
        this.share = share;
    }

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

    /**
     * Get current share.
     *
     * @return current share
     */
    public double getShare() {
        return share;
    }

    /**
     * Set new share.
     *
     * @param share Name of the new share.
     */
    public void setShare(double share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "DataRableRow{" +
                "country='" + country + '\'' +
                ", timescale='" + timescale + '\'' +
                ", vendor='" + vendor + '\'' +
                ", units=" + units +
                ", share=" + share +
                '}';
    }
}
