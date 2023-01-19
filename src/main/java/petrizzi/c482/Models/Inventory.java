package petrizzi.c482.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Part> allFilteredParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static ObservableList<Product> allFilteredProducts = FXCollections.observableArrayList();


    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Product lookupProductID(int productID) {
        for (int i = 0, productsSize = allProducts.size(); i < productsSize; i++) {
            Product product = allProducts.get(i);

            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    public static Part lookupPartID(int partID) {
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
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart, ObservableList<Part> partList) {
        for (Part part : partList) {
            if (part.getId() == selectedPart.getId()) {
                partList.remove(part);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct, ObservableList<Product> productList) {
        for (Product product : productList) {
            if (product.getId() == selectedProduct.getId()) {
                productList.remove(product);
                return true;
            }
        }
        return false;

    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Part> getAllFilteredParts(String query){
        allFilteredParts = lookupPart(query);
        try {
            Part p = lookupPartID(Integer.parseInt(query));
            allFilteredParts.add(p);
        } catch(NumberFormatException e) {}

        return allFilteredParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    public static ObservableList<Product> getAllFilteredProducts(String query){
        allFilteredProducts = lookupProduct(query);
        try {
            Product p = lookupProductID(Integer.parseInt(query));
            allFilteredProducts.add(p);
        } catch(NumberFormatException e) {}

        return allFilteredProducts;
    }



}
