package petrizzi.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import petrizzi.c482.Models.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static petrizzi.c482.Models.Inventory.getAllFilteredParts;
import static petrizzi.c482.Models.Inventory.getAllParts;

public class ModifyProductController {

    ObservableList<Part> availPartListProduct = FXCollections.observableArrayList();
    ObservableList<Part> assocPartListProducts = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Product selectedProduct;

    private static int max;
    private static int min;
    private static int inv;
    private static double price;

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
    private Button ModifyProductAddButton;

    @FXML
    private Button ModifyProductCancelButton;

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
    private Button ModifyProductRemoveAssocPartButton;

    @FXML
    private Button ModifyProductSaveButton;

    @FXML
    private TextField ModifyProductSearchTextField;

    @FXML
    public void partSearchKeyEvent(KeyEvent event){
        ModifyProductTopTableView.setItems(getAllFilteredParts(ModifyProductSearchTextField.getText()));

    }

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

    @FXML
    void OnAddProductRemovePartButtonClick(ActionEvent event) {
        Part selectedPart = ModifyProductAssocTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an associated part to remove.");
            alert.showAndWait();}

        assocPartListProducts.remove(selectedPart);
    }

    @FXML
    void OnModifyProductSaveButtonClick(ActionEvent event) throws IOException {
        if (ModifyProductNameTextField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name.");
            alert.showAndWait();
            return;
        }
        try {
            try {
                inv = Integer.parseInt(ModifyProductInvTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be of type integer.");
                alert.showAndWait();
                return;
            }
            try {
                price = Double.parseDouble(ModifyProductPriceTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price value must be of type double.");
                alert.showAndWait();
                return;
            }
            try {
                max = Integer.parseInt(ModifyProductMaxTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value must be of type integer.");
                alert.showAndWait();
                return;
            }
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

    @FXML
    void OnModifyProductCancelButtonClick(ActionEvent event) throws IOException {

        assocPartListProducts.setAll(selectedProduct.getAllAssociatedParts());

        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void goToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
