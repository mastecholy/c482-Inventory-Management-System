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
    private static int idCounter = 1;

    private void goToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void OnAddPartCancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public boolean isInHouse = true;
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


        try {

            if(!Integer.class.isInstance(Integer.parseInt((AddPartInvTextField.getText())))){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be of type integer.");
                alert.showAndWait();
            } if (!Double.class.isInstance((Double.parseDouble(AddPartPriceTextField.getText())))){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price value must be of type double.");
                alert.showAndWait();
            } if(!Integer.class.isInstance(Integer.parseInt((AddPartMaxTextField.getText())))) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value must be of type integer.");
                alert.showAndWait();
            } if(!Integer.class.isInstance(Integer.parseInt((AddPartMinTextField.getText())))) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min value must be of type integer.");
                alert.showAndWait();
            }  if(!(Integer.parseInt(AddPartMaxTextField.getText()) >= Integer.parseInt(AddPartInvTextField.getText()) &&
                    Integer.parseInt(AddPartInvTextField.getText()) >= Integer.parseInt(AddPartMinTextField.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value must be between the min and max values.");
                alert.showAndWait();
            }

            else if (isInHouse = true) {

                if(!Integer.class.isInstance(Integer.parseInt((AddPartMachineIDTextField.getText())))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID value must be of type integer.");

                } else {
                    int machineID = Integer.parseInt(AddPartMachineIDTextField.getText());

                    InHouse addInHousePart = new InHouse(idCounter, AddPartNameTextField.getText(),
                            Double.parseDouble(AddPartPriceTextField.getText()),
                            Integer.parseInt(AddPartInvTextField.getText()),
                            Integer.parseInt(AddPartMinTextField.getText()),
                            Integer.parseInt(AddPartMaxTextField.getText()), machineID);

                    Inventory.addPart(addInHousePart);
                    idCounter++;
                    goToMain(event);
                }
            }

            else if (isInHouse = false) {
                String companyName = AddPartMachineIDTextField.getText();


                Outsourced addOutsourcedPart = new Outsourced(idCounter, AddPartNameTextField.getText(),
                        Double.parseDouble(AddPartPriceTextField.getText()),
                        Integer.parseInt(AddPartInvTextField.getText()),
                        Integer.parseInt(AddPartMinTextField.getText()),
                        Integer.parseInt(AddPartMaxTextField.getText()), companyName);

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
