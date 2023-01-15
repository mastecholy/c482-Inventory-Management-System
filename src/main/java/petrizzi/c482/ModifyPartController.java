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

public class ModifyPartController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button ModifyPartCancelButton;

    @FXML
    private Label ModifyPartCompanyNameLabel;

    @FXML
    private TextField ModifyPartCompanyNameTextField;

    @FXML
    private Label ModifyPartFormModifyPartLabel;

    @FXML
    private TextField ModifyPartIDTextField;

    @FXML
    private RadioButton ModifyPartInHouseRadio;

    @FXML
    private Label ModifyPartInvLabel;

    @FXML
    private TextField ModifyPartInvTextField;

    @FXML
    private Label ModifyPartMachineIDLabel;

    @FXML
    private TextField ModifyPartMachineIDTextField;

    @FXML
    private Label ModifyPartMaxLabel;

    @FXML
    private TextField ModifyPartMaxTextField;

    @FXML
    private Label ModifyPartMinLabel;

    @FXML
    private TextField ModifyPartMinTextField;

    @FXML
    private Label ModifyPartNameLabel;

    @FXML
    private TextField ModifyPartNameTextField;

    @FXML
    private RadioButton ModifyPartOutsourcedRadio;

    @FXML
    private Label ModifyPartPriceLabel;

    @FXML
    private TextField ModifyPartPriceTextField;

    @FXML
    private ToggleGroup ModifyPartRadioGroup;

    @FXML
    private Button ModifyPartSaveButton;

    @FXML
    void OnModifyPartCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
