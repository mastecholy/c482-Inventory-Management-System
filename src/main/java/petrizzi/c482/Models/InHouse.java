package petrizzi.c482.Models;

/**
 *
 * @author
 * Massimiliano Petrizzi
 * mpetriz@wgu.edu
 * Student ID: 001386173
 */

/** Class to create InHouse part objects that inherit the Part class. */
public class InHouse  extends Part{

    private int machineID;

    /** Constructor for InHouse class.*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** Machine ID getter.
     * @return machineID*/
    public int getMachineID() {
        return machineID;
    }

    /** Machine ID setter.
     * @param machineID InHouse part machine ID */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
