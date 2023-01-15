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

public class ModifyProductController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<?> ModifyPartTopTableView;

    @FXML
    private Button ModifyProductAddButton;

    @FXML
    private TableView<?> ModifyProductBottomTableView;

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
