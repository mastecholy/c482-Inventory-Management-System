package petrizzi.c482.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Product lookupProduct(int productID) {
        for (int i = 0, productsSize = allProducts.size(); i < productsSize; i++) {
            Product product = allProducts.get(i);

            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    public static Part lookupPart(int partID) {
        for (int i = 0, partSize = allParts.size(); i < partSize; i++) {
            Part part = allParts.get(i);

            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String Name) {
        ObservableList<Part> parts = FXCollections.observableArrayList();

        for (Part part : getAllParts()) {
            if (part.getName().contains(Name)){
                parts.add(part);
            }
        }
        return parts;
    }

    public static ObservableList<Product> lookupProduct(String Name) {
        ObservableList<Product> products = FXCollections.observableArrayList();

        for (Product product : getAllProducts()) {
            if (product.getName().contains(Name)){
                products.add(product);
            }
        }
        return products;
    }

    public static void updatePart(int index, Part selectedPart) {

    }

    public static void updateProduct(int index, Product newProduct) {

    }

    /*public static boolean deletePart(Part selectedPart) {

    }*/

    /*public static boolean deleteProduct(Product selectedProduct) {

    }*/

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
