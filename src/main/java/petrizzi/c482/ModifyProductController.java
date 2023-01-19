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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import petrizzi.c482.Models.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static petrizzi.c482.Models.Inventory.getAllParts;

public class ModifyProductController implements Initializable {

    ObservableList<Part> availPartList = FXCollections.observableArrayList();
    ObservableList<Part> assocPartList = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Product selectedProduct;

    @FXML
    public TableView<Part> AddProductTopTableView;

    @FXML
    public TableColumn AddProductTablePartIDColumn;

    @FXML
    public TableColumn AddProductTableNameColumn;

    @FXML
    public TableColumn AddProductTableInvColumn;

    @FXML
    public TableColumn AddProductTablePriceColumn;

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
        availPartList = getAllParts();

        AddProductTopTableView.setItems(availPartList);

        AddProductTablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddProductTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddProductTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AddProductTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        Product newProduct = new Product(0, null, 0.0, 0, 0, 0);
        assocPartList = newProduct.getAllAssociatedParts();
        AddProductBottomTableView.setItems(assocPartList);

        AssocTablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssocPartTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssocPartTableInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssocPartTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    void setProduct (Product product) {
        selectedProduct = product;
        ModifyProductIDTextField.setText(String.valueOf(selectedProduct.getId()));
        ModifyProductNameTextField.setText(selectedProduct.getName());
        ModifyProductPriceTextField.setText(String.valueOf(selectedProduct.getPrice()));
        ModifyProductInvTextField.setText(String.valueOf(selectedProduct.getStock()));
        ModifyProductMaxTextField.setText(String.valueOf(selectedProduct.getMax()));
        ModifyProductMinTextField.setText(String.valueOf(selectedProduct.getMin()));
    }

    @FXML
    private TableView<Part> ModifyPartTopTableView;

    @FXML
    private Button ModifyProductAddButton;

    @FXML
    private TableView<Product> ModifyProductBottomTableView;

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
    void OnModifyProductCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
