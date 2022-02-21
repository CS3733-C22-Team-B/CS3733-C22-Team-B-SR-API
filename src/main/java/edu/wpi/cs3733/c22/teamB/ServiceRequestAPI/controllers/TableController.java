package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TableController {

    public void goToLocationTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/LocationTable.fxml"));
            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToEmployeeTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/EmployeeTable.fxml"));
            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToSRTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/ServiceRequestManager.fxml"));
            BorderHomeController.curBorderHomeController.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
