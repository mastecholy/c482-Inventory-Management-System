package petrizzi.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import petrizzi.c482.Models.Part;
import petrizzi.c482.Models.Product;

import java.io.IOException;

import static petrizzi.c482.Models.Inventory.getAllFilteredParts;
import static petrizzi.c482.Models.Inventory.getAllParts;

/**
 *
 * @author
 * Massimiliano Petrizzi
 * mpetriz@wgu.edu
 * Student ID: 001386173
 */

/** Controller for Modify Product form.*/
public class ModifyProductController {

    ObservableList<Part> availPartListProduct = FXCollections.observableArrayList();
    ObservableList<Part> assocPartListProducts = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Product selectedProduct;

    /** Called from Main Form to initialize Modify Product form and populate fields with selected Product info.
     * @param tempSelectedProduct Product selected from Main Form products table.*/
    void setProduct (Product tempSelectedProduct) {
        selectedProduct = tempSelectedProduct;
        availPartListProduct = getAllParts();
        ModifyProductTopTableView.setItems(availPartListProduct);

        ModifyProductIDTextField.setText(String.valueOf(selectedProduct.getId()));
        ModifyProductNameTextField.setText(selectedProduct.getName());
        ModifyProductPriceTextField.setText(String.valueOf(selectedProduct.getPrice()));
        ModifyProductInvTextField.setText(String.valueOf(selectedProduct.getStock()));
        ModifyProductMaxTextField.setText(String.valueOf(selectedProduct.getMax()));
        ModifyProductMinTextField.setText(String.valueOf(selectedProduct.getMin()));

        ModifyProductTopTablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductTopTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductTopTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyProductTopTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        assocPartListProducts.setAll(selectedProduct.getAllAssociatedParts());
        ModifyProductAssocTableView.setItems(assocPartListProducts);

        AssocTablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssocPartTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssocPartTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssocPartTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @FXML
    public TableView<Part> ModifyProductTopTableView;

    @FXML
    public TableColumn<Object, Object> ModifyProductTopTablePartIDColumn;

    @FXML
    public TableColumn<Object, Object> ModifyProductTopTableNameColumn;

    @FXML
    public TableColumn<Object, Object> ModifyProductTopTableInvColumn;

    @FXML
    public TableColumn<Object, Object> ModifyProductTopTablePriceColumn;

    @FXML
    private TableView<Part> ModifyProductAssocTableView;

    @FXML
    public TableColumn<Object, Object> AssocTablePartIDColumn;

    @FXML
    public TableColumn<Object, Object> AssocPartTableNameColumn;

    @FXML
    public TableColumn<Object, Object> AssocPartTableInvColumn;

    @FXML
    public TableColumn<Object, Object> AssocPartTablePriceColumn;

    @FXML
    private TextField ModifyProductIDTextField;

    @FXML
    private TextField ModifyProductInvTextField;

    @FXML
    private TextField ModifyProductMaxTextField;

    @FXML
    private TextField ModifyProductMinTextField;

    @FXML
    private TextField ModifyProductNameTextField;

    @FXML
    private TextField ModifyProductPriceTextField;

    @FXML
    private TextField ModifyProductSearchTextField;

    /** Search KeyEvent that filters the All Parts view table.*/
    @FXML
    public void partSearchKeyEvent(KeyEvent event){
        ModifyProductTopTableView.setItems(getAllFilteredParts(ModifyProductSearchTextField.getText()));

    }

    /** Add Button event that adds selected Part to Product associated parts.*/
    @FXML
    void OnAddProductAddPartButtonClick(ActionEvent event){
        Part selectedPart = ModifyProductTopTableView.getSelectionModel().getSelectedItem();

        if (assocPartListProducts.contains(selectedPart)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Part already associated with product.");
            alert.showAndWait();
        }
        else if (selectedPart != null) {
            assocPartListProducts.add(selectedPart);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an available part to add.");
            alert.showAndWait();
        }
    }

    /** Remove Button event that removes selected Part from Product associated parts.*/
    @FXML
    void OnAddProductRemovePartButtonClick(ActionEvent event) {
        Part selectedPart = ModifyProductAssocTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an associated part to remove.");
            alert.showAndWait();}

        assocPartListProducts.remove(selectedPart);
    }

    /** RUNTIME ERROR
     * An error I encountered with this program was that the Product's associated parts
     * were not correctly updating on saving or cancelling the modifications. This ended up
     * being cause by me improperly setting Observable Array lists as equal to each other
     * rather than using "setAll" to ensure the lists had the same components.
     *
     * Save Button event that finalizes Product modification and returns to Main Form.*/
    @FXML
    void OnModifyProductSaveButtonClick(ActionEvent event) throws IOException {
        if (ModifyProductNameTextField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name.");
            alert.showAndWait();
            return;
        }
        try {
            int inv;
            try {
                inv = Integer.parseInt(ModifyProductInvTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be of type integer.");
                alert.showAndWait();
                return;
            }
            double price;
            try {
                price = Double.parseDouble(ModifyProductPriceTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price value must be of type double.");
                alert.showAndWait();
                return;
            }
            int max;
            try {
                max = Integer.parseInt(ModifyProductMaxTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value must be of type integer.");
                alert.showAndWait();
                return;
            }
            int min;
            try {
                min = Integer.parseInt(ModifyProductMinTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min value must be of type integer.");
                alert.showAndWait();
                return;
            }
            if (!(max >= inv && inv >= min)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be between the min and max values.");
                alert.showAndWait();
                return;
            }

            selectedProduct.setId(Integer.parseInt(ModifyProductIDTextField.getText()));
            selectedProduct.setMax(max);
            selectedProduct.setMin(min);
            selectedProduct.setPrice(price);
            selectedProduct.setStock(inv);
            selectedProduct.setName(ModifyProductNameTextField.getText());

            selectedProduct.getAllAssociatedParts().setAll(assocPartListProducts);
            goToMain(event);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter valid inputs for every text field.");
            alert.showAndWait();
        }
    }

    /** Cancel Button event that aborts Product modification.*/
    @FXML
    void OnModifyProductCancelButtonClick(ActionEvent event) throws IOException {

        assocPartListProducts.setAll(selectedProduct.getAllAssociatedParts());

        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** Brings application to Main Form.*/
    private void goToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
