package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Screen;

import java.io.IOException;

public class HomeController {

    @FXML
    private void initialize(){
        Bapp.getPrimaryStage().setResizable(true);
        Bapp.getPrimaryStage().setX(0);
        Bapp.getPrimaryStage().setY(0);
        Bapp.getPrimaryStage().setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        Bapp.getPrimaryStage().setHeight(Screen.getPrimary().getVisualBounds().getHeight());
    }

    @FXML
    private void goToMedEquipDeliveryService(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(
                            getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MedicalEquipmentDeliveryService.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToLaundryScene(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/LaundryService.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToFoodDeliveryService(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/FoodDeliveryService.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToMedicineDeliveryService(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(
                            getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MedicineDeliveryService.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToExternalTransportService(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/ExternalTransport.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToGiftFloralService(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/GiftFloralService.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToServiceRequestPanel(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/ServiceRequestPanel.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goHome() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToEquipmentTable(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(
                            getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MedicalEquipmentTable.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToLocationTable(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/LocationTable.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToEmployeeTable(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/EmployeeTable.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void goToMapEditor(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MapEditor.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToCSVRestoreBackup(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Settings.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
/*
    @FXML
    private void goToCSVImport(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/CSV_ImportExport.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
*/
    @FXML
    private void shutDown() {
        Platform.exit();
    }

    public void goToServiceRequestMenu(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/ServiceRequestMenu.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToSRManager(ActionEvent actionEvent) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/ServiceRequestManager.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}