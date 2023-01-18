package petrizzi.c482;

import javafx.application.Platform;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import petrizzi.c482.Models.InHouse;
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
    private Button MainFormExitButton;

    @FXML
    private TextField MainFormPartsSearchTextField;

    @FXML
    private TextField MainFormProductsSearchTextField;

    @FXML
    private Button MainPartsAddButton;

    @FXML
    private Button MainPartsDeleteButton;

    @FXML
    private Button MainPartsModifyButton;

    @FXML
    private Button MainProductsAddButton;

    @FXML
    private Button MainProductsDeleteButton;

    @FXML
    private Button MainProductsModifyButton;

    @FXML
    public TableView<Part> MainFormPartsTable;

    @FXML
    public TableColumn MainPartsTablePartIDColumn;

    @FXML
    public TableColumn MainPartsTableNameColumn;

    @FXML
    public TableColumn MainPartsTableInvColumn;

    @FXML
    public TableColumn MainPartsTablePriceColumn;

    @FXML
    public TableView<Product> MainFormProductsTable;

    @FXML
    public TableColumn MainProductsTableIDColumn;

    @FXML
    public TableColumn MainProductsTableNameColumn;

    @FXML
    public TableColumn MainProductsTableInvColumn;

    @FXML
    public TableColumn MainProductsTablePriceColumn;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected part?");
        alert.setTitle("DELETE PART?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Part selectedPart = (Part) MainFormPartsTable.getSelectionModel().getSelectedItem();
            if (selectedPart == null) return;
            Inventory.getAllParts().remove(selectedPart);
        }


    }

    @FXML
    public void partSearchKeyEvent(KeyEvent event){
            MainFormPartsTable.setItems(getAllFilteredParts(MainFormPartsSearchTextField.getText()));

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
            Product selectedProduct = (Product) MainFormProductsTable.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) return;
            Inventory.getAllParts().remove(selectedProduct);
        }
    }

    @FXML
    void OnMainProductsModifyButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("modify-product-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OnMainExitButtonClick(ActionEvent event) {
        Platform.exit();
    }


}
