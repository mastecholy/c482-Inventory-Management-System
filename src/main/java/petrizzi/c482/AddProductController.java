package petrizzi.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class AddProductController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<?> AddPartTopTableView;

    @FXML
    private Button AddProductAddButton;

    @FXML
    private TableView<?> AddProductBottomTableView;

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
    void OnAddProductCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
