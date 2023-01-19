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

import static petrizzi.c482.Models.Inventory.deletePart;
import static petrizzi.c482.Models.Inventory.getAllParts;

public class ModifyPartController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Part selectedPart;

    private static double price;
    private static int max;
    private static int min;
    private static int inv;

    void setPart (Part part) {
        selectedPart = part;
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
    void OnOutsourcedRadioButtonClick(ActionEvent event) {
        ModifyPartMachineIDLabel.setText("Company Name");
    }

    @FXML
    void OnInHouseRadioButtonClick(ActionEvent event) {
        ModifyPartMachineIDLabel.setText("Machine ID");
    }

    @FXML
    void OnModifyPartCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnModifyPartSaveButtonClick(ActionEvent event) throws IOException {
        int id = Integer.parseInt(ModifyPartIDTextField.getText());

        if (ModifyPartNameTextField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name.");
            alert.showAndWait();
            return;
        }
        try {
            try {
                inv = Integer.parseInt(ModifyPartInvTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be of type integer.");
                alert.showAndWait();
                return;
            }
            try {
                price = Double.parseDouble(ModifyPartPriceTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price value must be of type double.");
                alert.showAndWait();
                return;
            }
            try {
                max = Integer.parseInt(ModifyPartMaxTextField.getText());}
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value must be of type integer.");
                alert.showAndWait();
                return;
            }
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

    private void goToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
