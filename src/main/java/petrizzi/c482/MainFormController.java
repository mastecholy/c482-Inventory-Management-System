package petrizzi.c482;

import javafx.application.Platform;
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
import petrizzi.c482.Models.Inventory;
import petrizzi.c482.Models.Part;
import petrizzi.c482.Models.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static petrizzi.c482.Models.Inventory.*;

public class MainFormController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField MainFormPartsSearchTextField;

    @FXML
    private TextField MainFormProductsSearchTextField;

    @FXML
    private TableView<Part> MainFormPartsTable;

    @FXML
    private TableColumn MainPartsTablePartIDColumn;

    @FXML
    private TableColumn MainPartsTableNameColumn;

    @FXML
    private TableColumn MainPartsTableInvColumn;

    @FXML
    private TableColumn MainPartsTablePriceColumn;

    @FXML
    private TableView<Product> MainFormProductsTable;

    @FXML
    private TableColumn MainProductsTableIDColumn;

    @FXML
    private TableColumn MainProductsTableNameColumn;

    @FXML
    private TableColumn MainProductsTableInvColumn;

    @FXML
    private TableColumn MainProductsTablePriceColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        MainFormPartsTable.setItems(getAllParts());
        MainPartsTablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        MainPartsTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        MainPartsTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MainPartsTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        MainFormProductsTable.setItems(getAllProducts());
        MainProductsTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        MainProductsTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        MainProductsTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MainProductsTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void OnMainPartsAddButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-part-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OnMainPartsDeleteButtonClick(ActionEvent event) {
        Part selectedPart = (Part) MainFormPartsTable.getSelectionModel().getSelectedItem();
        for (Product product : getAllProducts()) {
            if (product.getAllAssociatedParts().contains(selectedPart)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Selected part is associated with a product.");
                alert.showAndWait();
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected part?");
        alert.setTitle("DELETE PART?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (selectedPart == null) return;
            Inventory.getAllParts().remove(selectedPart);
        }


    }

    @FXML
    public void partSearchKeyEvent(KeyEvent event){
        MainFormPartsTable.setItems(getAllFilteredParts(MainFormPartsSearchTextField.getText()));

    }

    @FXML
    public void productSearchKeyEvent(KeyEvent event){
        MainFormProductsTable.setItems(getAllFilteredProducts(MainFormProductsSearchTextField.getText()));

    }

    @FXML
    void OnMainPartsModifyButtonClick(ActionEvent event) throws IOException {
        Part selectedPart = MainFormPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-part-view.fxml"));
            root = loader.load();
            ModifyPartController modifyPartController = loader.getController();
            modifyPartController.setPart(selectedPart);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to modify.");
            alert.showAndWait();
        }

    }

    @FXML
    void OnMainProductsAddButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-product-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnMainProductsDeleteButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected product?");
        alert.setTitle("DELETE PRODUCT?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Product selectedProduct = MainFormProductsTable.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) return;
            Inventory.getAllProducts().remove(selectedProduct);
        }
    }

    @FXML
    void OnMainProductsModifyButtonClick(ActionEvent event) throws IOException {
        Product tempSelectedProduct = MainFormProductsTable.getSelectionModel().getSelectedItem();

        if (tempSelectedProduct != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-product-view.fxml"));
            root = loader.load();
            ModifyProductController modifyProductController = loader.getController();
            modifyProductController.setProduct(tempSelectedProduct);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to modify.");
            alert.showAndWait();
        }
    }

    @FXML
    void OnMainExitButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert.setTitle("Exit Program?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
