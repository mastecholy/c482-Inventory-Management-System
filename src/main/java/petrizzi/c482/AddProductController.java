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
import petrizzi.c482.Models.Inventory;
import petrizzi.c482.Models.Part;
import petrizzi.c482.Models.Product;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static petrizzi.c482.Models.Inventory.getAllFilteredParts;
import static petrizzi.c482.Models.Inventory.getAllParts;

public class AddProductController implements Initializable {

    Product newProduct;
    ObservableList<Part> availPartList = FXCollections.observableArrayList();
    ObservableList<Part> assocPartList = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static int idCounter = 1;
    private static int max;
    private static int min;
    private static int inv;
    private static double price;

    @FXML
    public TableView<Part> AddPartTopTableView;

    @FXML
    public TableColumn AddPartTablePartIDColumn;

    @FXML
    public TableColumn AddPartTableNameColumn;

    @FXML
    public TableColumn AddPartTableInvColumn;

    @FXML
    public TableColumn AddPartTablePriceColumn;

    @FXML
    public TableView<Part> AddProductBottomTableView;

    @FXML
    public TableColumn AssocTablePartIDColumn;

    @FXML
    public TableColumn AssocPartTableNameColumn;

    @FXML
    public TableColumn AssocPartTableInvColumn;

    @FXML
    public TableColumn AssocPartTablePriceColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AddProductIDTextField.setText(String.valueOf(idCounter));
        availPartList = getAllParts();

        AddPartTopTableView.setItems(availPartList);

        AddPartTablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddPartTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddPartTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AddPartTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        Product newProduct = new Product(0, null, 0.0, 0, 0, 0);
        assocPartList = newProduct.getAllAssociatedParts();
        AddProductBottomTableView.setItems(assocPartList);

        AssocTablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssocPartTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssocPartTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssocPartTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    @FXML
    private Button AddProductAddButton;

    @FXML
    private Button AddProductCancelButton;

    @FXML
    private TextField AddProductIDTextField;

    @FXML
    private TextField AddProductInvTextField;

    @FXML
    private TextField AddProductMaxTextField;

    @FXML
    private TextField AddProductMinTextField;

    @FXML
    private TextField AddProductNameTextField;

    @FXML
    private TextField AddProductPriceTextField;

    @FXML
    private Button AddProductRemoveAssocPartButton;

    @FXML
    private Button AddProductSaveButton;

    @FXML
    private TextField AddProductSearchTextField;

    @FXML
    public void partSearchKeyEvent(KeyEvent event){
        AddPartTopTableView.setItems(getAllFilteredParts(AddProductSearchTextField.getText()));

    }

    @FXML
    void OnAddProductCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnAddProductAddPartButtonClick(ActionEvent event){
        Part selectedPart = AddPartTopTableView.getSelectionModel().getSelectedItem();

        if (assocPartList.contains(selectedPart)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Part already associated with product.");
            alert.showAndWait();
        }
        else if (selectedPart != null) {
            Part tempPart = selectedPart;
            assocPartList.add(tempPart);
        } else  if (selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an available part to add.");
            alert.showAndWait();
        }
    }

    @FXML
    void OnAddProductRemovePartButtonClick(ActionEvent event) {
        Part selectedPart = AddProductBottomTableView.getSelectionModel().getSelectedItem();
        assocPartList.remove(selectedPart);
    }

    @FXML
    void OnAddProductSaveButtonClick(ActionEvent event) throws IOException {
        if (AddProductNameTextField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name.");
            alert.showAndWait();
            return;
        }
        try {
            try {
                inv = Integer.parseInt(AddProductInvTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be of type integer.");
                alert.showAndWait();
                return;
            }
            try {
                price = Double.parseDouble(AddProductPriceTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price value must be of type double.");
                alert.showAndWait();
                return;
            }
            try {
                max = Integer.parseInt(AddProductMaxTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value must be of type integer.");
                alert.showAndWait();
                return;
            }
            try {
                min = Integer.parseInt(AddProductMinTextField.getText());
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


            Product addProduct = new Product(idCounter, AddProductNameTextField.getText(), price, inv, min, max);
            if (!(assocPartList.isEmpty())){
                for (Part part : assocPartList) {
                    addProduct.addAssociatedPart(part);
                }
            }
            Inventory.addProduct(addProduct);
            idCounter++;
            goToMain(event);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter valid inputs for every text field.");
            alert.showAndWait();
        }


    }

    private void goToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
