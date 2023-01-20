package petrizzi.c482.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author
 * Massimiliano Petrizzi
 * mpetriz@wgu.edu
 * Student ID: 001386173
 */

/** Class to create Product objects.*/
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    /** Constructor for Product class.*/
   public Product(int id, String name, double price, int stock, int min, int max) {
       this.id = id;
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.min = min;
       this.max = max;
   }

    /** Product ID setter
     * @param id Product id*/
   public void setId(int id) {this.id = id;}


    /** Product name setter
     * @param name Product name*/
   public void setName(String name) {
       this.name = name;
   }

    /** Product price setter
     * @param price Product price*/
    public void setPrice(double price) {
        this.price = price;
    }

    /** Product stock setter
     * @param stock Product inventory count*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Product min setter
     * @param min Product minimum inventory count*/
    public void setMin(int min) {
        this.min = min;
    }

    /** Product max setter
     * @param max Product maximum inventory count*/
    public void setMax(int max) {
        this.max = max;
    }

    /** Product ID getter
     * @return id*/
    public int getId() {
       return id;
    }

    /** Product name getter
     * @return id*/
    public String getName() {
       return name;
    }

    /** Product price getter
     * @return price*/
    public double getPrice() {
        return price;
    }

    /** Product stock getter
     * @return price*/
    public int getStock() {
        return stock;
    }

    /** Product min getter
     * @return min*/
    public int getMin() {
        return min;
    }

    /** Product max getter
     * @return max*/
    public int getMax() {
        return max;
    }

    /** Adds a part to Product associated parts list
     * @param part Part to be associated with the Product*/
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** Deletes a part from the Product's associated parts list.
     * @param selectedAssociatedPart Part selected from table*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for (Part part : associatedParts) {
            if (part.getId() == selectedAssociatedPart.getId()) {
                associatedParts.remove(part);
                return true;
            }
        }
        return false;
    }

    /** Product associated parts list getter.
     * @return associatedParts*/
    public ObservableList<Part> getAllAssociatedParts() {
       return associatedParts;
    }
}
