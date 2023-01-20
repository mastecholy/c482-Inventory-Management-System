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
import petrizzi.c482.Models.Part;

import java.io.IOException;

import static petrizzi.c482.Models.Inventory.getAllParts;

/* author
 * Massimiliano Petrizzi
 * mpetriz@wgu.edu
 * Student ID: 001386173
 */

/** Controller for Modify Part form.*/
public class ModifyPartController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Part selectedPart;

    /** Called from Main Form to initialize Modify Part form and populate fields with Part info.
     * @param part Part selected from the Main Form parts table.*/
    void setPart (Part part) {
        selectedPart = part;
        System.out.println(selectedPart);
        ModifyPartIDTextField.setText(String.valueOf(selectedPart.getId()));
        ModifyPartNameTextField.setText(selectedPart.getName());
        ModifyPartPriceTextField.setText(String.valueOf(selectedPart.getPrice()));
        ModifyPartInvTextField.setText(String.valueOf(selectedPart.getStock()));
        ModifyPartMaxTextField.setText(String.valueOf(selectedPart.getMax()));
        ModifyPartMinTextField.setText(String.valueOf(selectedPart.getMin()));
        if (part instanceof InHouse) {
            ModifyPartMachineIDTextField.setText(String.valueOf(((InHouse) part).getMachineID()));
        }
        else if (part instanceof Outsourced) {
            ModifyPartMachineIDTextField.setText(((Outsourced) part).getCompanyName());
            ModifyPartOutsourcedRadio.setSelected(true);
            ModifyPartMachineIDLabel.setText("Company Name");
        }
    }

    @FXML
    private TextField ModifyPartIDTextField;

    @FXML
    private RadioButton ModifyPartInHouseRadio;

    @FXML
    private TextField ModifyPartInvTextField;

    @FXML
    private Label ModifyPartMachineIDLabel;

    @FXML
    private TextField ModifyPartMachineIDTextField;

    @FXML
    private TextField ModifyPartMaxTextField;

    @FXML
    private TextField ModifyPartMinTextField;

    @FXML
    private TextField ModifyPartNameTextField;

    @FXML
    private RadioButton ModifyPartOutsourcedRadio;

    @FXML
    private TextField ModifyPartPriceTextField;

    /** Radio button event that toggles Outsourced and modifies form text to match.*/
    @FXML
    void OnOutsourcedRadioButtonClick(ActionEvent event) {
        ModifyPartMachineIDLabel.setText("Company Name");
    }

    /** Radio button event that toggles InHouse and modifies form text to match.*/
    @FXML
    void OnInHouseRadioButtonClick(ActionEvent event) {
        ModifyPartMachineIDLabel.setText("Machine ID");
    }

    /** Cancel Button event that aborts Part modification and returns to Main Form.*/
    @FXML
    void OnModifyPartCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** Save Button event that finalizes Part modification and returns to Main Form.*/
    @FXML
    void OnModifyPartSaveButtonClick(ActionEvent event) throws IOException {
        int id = Integer.parseInt(ModifyPartIDTextField.getText());

        if (ModifyPartNameTextField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name.");
            alert.showAndWait();
            return;
        }
        try {
            int inv;
            try {
                inv = Integer.parseInt(ModifyPartInvTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be of type integer.");
                alert.showAndWait();
                return;
            }
            double price;
            try {
                price = Double.parseDouble(ModifyPartPriceTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price value must be of type double.");
                alert.showAndWait();
                return;
            }
            int max;
            try {
                max = Integer.parseInt(ModifyPartMaxTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value must be of type integer.");
                alert.showAndWait();
                return;
            }
            int min;
            try {
                min = Integer.parseInt(ModifyPartMinTextField.getText());}
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

            else if (ModifyPartInHouseRadio.isSelected()) {

                int machineID = 0;
                try {
                    machineID = Integer.parseInt(ModifyPartMachineIDTextField.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID value must be of type integer.");
                    alert.showAndWait();
                    return;
                }

                if (selectedPart instanceof InHouse) {
                    selectedPart.setId(id);
                    selectedPart.setMax(max);
                    selectedPart.setMin(min);
                    selectedPart.setPrice(price);
                    selectedPart.setStock(inv);
                    ((InHouse) selectedPart).setMachineID(machineID);
                } else if (selectedPart instanceof Outsourced) {
                    Inventory.deletePart(selectedPart, getAllParts());
                    InHouse addInHousePart = new InHouse(id, ModifyPartNameTextField.getText(),
                            price, inv, min, max, machineID);
                    Inventory.addPart(addInHousePart);


                }

            }

            else if (ModifyPartOutsourcedRadio.isSelected()) {
                if (ModifyPartMachineIDTextField.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a Company Name.");
                    alert.showAndWait();
                    return;
                }
                String companyName = ModifyPartMachineIDTextField.getText();

                if (selectedPart instanceof Outsourced) {
                    selectedPart.setId(id);
                    selectedPart.setMax(max);
                    selectedPart.setMin(min);
                    selectedPart.setPrice(price);
                    selectedPart.setStock(inv);
                    ((Outsourced) selectedPart).setCompanyName(companyName);
                } else if (selectedPart instanceof InHouse) {
                    Inventory.deletePart(selectedPart, Inventory.getAllParts());
                    Outsourced addOutsourcedPart = new Outsourced(id, ModifyPartNameTextField.getText(),
                            price, inv, min, max, companyName);
                    Inventory.addPart(addOutsourcedPart);
                }
            }
            goToMain(event);
        }

        catch(NumberFormatException e){
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter valid inputs for every text field.");
            alert.showAndWait();
        }


    }

    /** Method that returns application to Main Form.*/
    private void goToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
