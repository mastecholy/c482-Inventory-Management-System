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
import petrizzi.c482.Models.InHouse;
import petrizzi.c482.Models.Inventory;
import petrizzi.c482.Models.Outsourced;

import java.io.IOException;

public class AddPartController {

    public void initialize() {
        AddPartIDTextField.setText(String.valueOf(idCounter));
    }

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
    private int idCounter = 1;




    @FXML
    void OnAddPartCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private boolean isInHouse = true;
    @FXML
    void OnOutsourcedRadioButtonClick(ActionEvent event) {
            AddPartMachineIDLabel.setText("Company Name");
            isInHouse = false;
    }

    @FXML
    void OnInHouseRadioButtonClick(ActionEvent event) {
        AddPartMachineIDLabel.setText("Machine ID");
        isInHouse = true;
    }

    @FXML
    void OnAddPartSaveButtonClick(ActionEvent event) throws IOException {


        if (isInHouse = true) {
            int machineID = Integer.parseInt(AddPartMachineIDTextField.getText());

            InHouse addInHousePart = new InHouse(idCounter, AddPartNameTextField.getText(),
                    Double.parseDouble(AddPartPriceTextField.getText()),
                    Integer.parseInt(AddPartInvTextField.getText()),
                    Integer.parseInt(AddPartMinTextField.getText()),
                    Integer.parseInt(AddPartMaxTextField.getText()), machineID);

            Inventory.addPart(addInHousePart);
            idCounter++;
        }

        if (isInHouse = false) {
            String companyName = AddPartMachineIDTextField.getText();

            Outsourced addOutsourcedPart = new Outsourced(idCounter, AddPartNameTextField.getText(),
                    Double.parseDouble(AddPartPriceTextField.getText()),
                    Integer.parseInt(AddPartInvTextField.getText()),
                    Integer.parseInt(AddPartMinTextField.getText()),
                    Integer.parseInt(AddPartMaxTextField.getText()), companyName);

            Inventory.addPart(addOutsourcedPart);
            idCounter++;
        }


        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
