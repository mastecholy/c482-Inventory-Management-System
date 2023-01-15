package petrizzi.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button AddPartCancelButton;

    @FXML
    private Label AddPartCompanyNameLabel;

    @FXML
    private TextField AddPartCompanyNameTextField;

    @FXML
    private Label AddPartFormAddPartLabel;
    @FXML
    private TextField AddPartIDTextField;

    @FXML
    private RadioButton AddPartInHouseRadio;

    @FXML
    private Label AddPartInvLabel;

    @FXML
    private TextField AddPartInvTextField;

    @FXML
    private Label AddPartMachineIDLabel;

    @FXML
    private TextField AddPartMachineIDTextField;

    @FXML
    private Label AddPartMaxLabel;

    @FXML
    private TextField AddPartMaxTextField;

    @FXML
    private Label AddPartMinLabel;

    @FXML
    private TextField AddPartMinTextField;

    @FXML
    private Label AddPartNameLabel;

    @FXML
    private TextField AddPartNameTextField;

    @FXML
    private RadioButton AddPartOutsourcedRadio;

    @FXML
    private Label AddPartPriceLabel;

    @FXML
    private TextField AddPartPriceTextField;

    @FXML
    private ToggleGroup AddPartRadioGroup;

    @FXML
    private Button AddPartSaveButton;

    @FXML
    void OnAddPartCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
