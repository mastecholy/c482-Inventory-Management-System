package petrizzi.c482;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

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
    void OnMainPartsAddButtonClick(ActionEvent event) {

    }

    @FXML
    void OnMainPartsDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void OnMainPartsModifyButtonClick(ActionEvent event) {

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
