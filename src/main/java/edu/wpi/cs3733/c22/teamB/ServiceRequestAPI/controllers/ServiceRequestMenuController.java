package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.controllers;

import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.BServiceRequestAPI;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class ServiceRequestMenuController {
    @FXML private JFXToggleButton toggleName;
    @FXML private Label name2;

    @FXML
    private void toggleName(ActionEvent event) {
        name2.setVisible(toggleName.isSelected());
    }

    @FXML
    private void goToComputerServiceSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("ComputerSR"));
            Parent root = loader.load();
            BServiceRequestAPI.getInstance().getSRWindow().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        toggleName.setSelected(false);
        name2.setVisible(false);
    }

    @FXML
    private void onMouseComputerServiceSR(MouseEvent mouseEvent) {
    }
}
