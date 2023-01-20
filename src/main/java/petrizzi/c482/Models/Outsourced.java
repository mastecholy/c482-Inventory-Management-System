package petrizzi.c482.Models;

/**
 *
 * @author
 * Massimiliano Petrizzi
 * mpetriz@wgu.edu
 * Student ID: 001386173
 */

/** Class to create Outsourced part objects that inherit the Part class.*/
public class Outsourced extends Part{

    private String companyName;

    /** Constructor for Outsourced class.*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** Company Name getter.
     * @return companyName*/
    public String getCompanyName() {
        return companyName;
    }

    /** Company Name setter.
     * @param companyName Outsourced part company name*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
