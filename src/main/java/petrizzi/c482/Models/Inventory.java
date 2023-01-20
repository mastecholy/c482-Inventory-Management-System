package petrizzi.c482.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class that controls information of saved Parts and Products.*/
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Part> allFilteredParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static ObservableList<Product> allFilteredProducts = FXCollections.observableArrayList();


    /** Adds part to allParts list.
     * @param part part to be added*/
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /** Adds product to allProducts list.
     * @param product part to be added*/
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /** Gets product from allProducts list by ID
     * @param productID product ID
     * @return returns product with matching ID*/
    public static Product lookupProductID(int productID) {
        for (int i = 0, productsSize = allProducts.size(); i < productsSize; i++) {
            Product product = allProducts.get(i);

            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    /** Gets part from allParts list by ID
     * @param partID part ID
     * @return returns part with matching ID*/
    public static Part lookupPartID(int partID) {
        for (int i = 0, partSize = allParts.size(); i < partSize; i++) {
            Part part = allParts.get(i);

            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    /** Gets parts from allParts by name
     * @param Name part name
     * @return returns parts that contain Name parameter
     * */
    public static ObservableList<Part> lookupPart(String Name) {
        ObservableList<Part> parts = FXCollections.observableArrayList();

        for (Part part : getAllParts()) {
            if (part.getName().contains(Name)){
                parts.add(part);
            }
        }
        return parts;
    }

    /** Gets products from allProducts by name
     * @param Name product name
     * @return returns products that contain Name parameter*/
    public static ObservableList<Product> lookupProduct(String Name) {
        ObservableList<Product> products = FXCollections.observableArrayList();

        for (Product product : getAllProducts()) {
            if (product.getName().contains(Name)){
                products.add(product);
            }
        }
        return products;
    }

    /** Updates selected Part ID
     * @param selectedPart part to be updated*/
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /** Updates selected Product ID
     * @param selectedProduct product to be updated */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /** Deletes selected Part from partList
     * @param selectedPart part to be removed
     * @param partList list to remove part from*/
    public static boolean deletePart(Part selectedPart, ObservableList<Part> partList) {
        for (Part part : partList) {
            if (part.getId() == selectedPart.getId()) {
                partList.remove(part);
                return true;
            }
        }
        return false;
    }

    /** Deletes selected Product from partList
     * @param selectedProduct product to be removed
     * @param productList list to remove product from*/
    public static boolean deleteProduct(Product selectedProduct, ObservableList<Product> productList) {
        for (Product product : productList) {
            if (product.getId() == selectedProduct.getId()) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }

    /** Getter for allParts list
     * @return returns allParts list*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** Filters all parts by calling lookupPart method.
     * @param query part name to be searched
     * @return returns all parts matching search*/
    public static ObservableList<Part> getAllFilteredParts(String query){
        allFilteredParts = lookupPart(query);
        try {
            Part p = lookupPartID(Integer.parseInt(query));
            allFilteredParts.add(p);
        } catch(NumberFormatException e) {}

        return allFilteredParts;
    }

    /** Getter for allProducts list.
     * @return returns allProducts list.*/
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /** Filters all products by calling lookupProduct method.
     * @param query product name to be searched
     * @return returns all products matching search*/
    public static ObservableList<Product> getAllFilteredProducts(String query){
        allFilteredProducts = lookupProduct(query);
        try {
            Product p = lookupProductID(Integer.parseInt(query));
            allFilteredProducts.add(p);
        } catch(NumberFormatException e) {}

        return allFilteredProducts;
    }



}
