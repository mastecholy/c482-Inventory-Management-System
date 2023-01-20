package petrizzi.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import petrizzi.c482.Models.InHouse;
import petrizzi.c482.Models.Inventory;
import petrizzi.c482.Models.Outsourced;

import java.io.IOException;

/* author
 * Massimiliano Petrizzi
 * mpetriz@wgu.edu
 * Student ID: 001386173
 */

/** Controller for the Add Part screen.*/
public class AddPartController {

    /** Initialize function that populates the ID text field.*/
    public void initialize() {
        AddPartIDTextField.setText(String.valueOf(idCounter));
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField AddPartIDTextField;

    @FXML
    private RadioButton AddPartInHouseRadio;

    @FXML
    private TextField AddPartInvTextField;

    @FXML
    private Label AddPartMachineIDLabel;

    @FXML
    private TextField AddPartMachineIDTextField;

    @FXML
    private TextField AddPartMaxTextField;

    @FXML
    private TextField AddPartMinTextField;

    @FXML
    private TextField AddPartNameTextField;

    @FXML
    private RadioButton AddPartOutsourcedRadio;

    @FXML
    private TextField AddPartPriceTextField;

    @FXML
    public static int idCounter = 1;

    /** Function that sends the program to the Main Form screen.*/
    private void goToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /** Function that cancels the Add Part action and returns to Main Form.*/
    @FXML
    void OnAddPartCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** Event that toggles the Outsourced radio button and changes form text to match.*/
    @FXML
    void OnOutsourcedRadioButtonClick(ActionEvent event) {
            AddPartMachineIDLabel.setText("Company Name");
    }

    /** Event that toggles the InHouse radio button and changes form text to match.*/
    @FXML
    void OnInHouseRadioButtonClick(ActionEvent event) {
        AddPartMachineIDLabel.setText("Machine ID");
    }


    /** Function that saves the added part and returns to Main Form*/
    @FXML
    void OnAddPartSaveButtonClick(ActionEvent event) throws IOException {

        if (AddPartNameTextField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name.");
            alert.showAndWait();
            return;
        }
        try {
            int inv;
            try {
                inv = Integer.parseInt(AddPartInvTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be of type integer.");
                alert.showAndWait();
                return;
            }
            double price;
            try {
                price = Double.parseDouble(AddPartPriceTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price value must be of type double.");
                alert.showAndWait();
                return;
            }
            int max;
            try {
                max = Integer.parseInt(AddPartMaxTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value must be of type integer.");
                alert.showAndWait();
                return;
            }
            int min;
            try {
                min = Integer.parseInt(AddPartMinTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min value must be of type integer.");
                alert.showAndWait();
                return;
            }

            if(!(max >= inv && inv >= min)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be between the min and max values.");
                    alert.showAndWait();
                    return;
                }

            else if (AddPartInHouseRadio.isSelected()) {

                int machineID = 0;
                try {
                    machineID = Integer.parseInt(AddPartMachineIDTextField.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID value must be of type integer.");
                    alert.showAndWait();
                    return;
                }


                InHouse addInHousePart = new InHouse(idCounter, AddPartNameTextField.getText(),
                        price, inv, min, max, machineID);

                Inventory.addPart(addInHousePart);
                idCounter++;
                goToMain(event);

            }

            else if (AddPartOutsourcedRadio.isSelected()) {
                if (AddPartMachineIDTextField.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a Company Name.");
                    alert.showAndWait();
                    return;
                }
                String companyName = AddPartMachineIDTextField.getText();

                Outsourced addOutsourcedPart = new Outsourced(idCounter, AddPartNameTextField.getText(),
                        price, inv, min, max, companyName);

                Inventory.addPart(addOutsourcedPart);
                idCounter++;
                goToMain(event);
            }
            }
            catch(NumberFormatException e){
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please enter valid inputs for every text field.");
                alert.showAndWait();
            }
        }
}
