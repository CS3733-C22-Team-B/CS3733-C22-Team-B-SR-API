package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity.ComputerSR;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity.DatabaseWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

public class ComputerSRController implements IController {
    @FXML private JFXComboBox<String> helpTypeField;

    private ComputerSR sr = null;

    public ComputerSRController() {}

    public ComputerSRController(ComputerSR sr) {
        this.sr = sr;
    }

    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        if (this.sr == null)
            dw.addSR(new ComputerSR(sr, helpTypeField.getValue()));
        else
            dw.updateSR(new ComputerSR(sr, helpTypeField.getValue()));
    }

    @Override
    public void clear() {
        helpTypeField.setValue(null);
    }


    @FXML public void initialize() {
        String st[] = {"Hardware", "Software"};
        helpTypeField.setItems(FXCollections.observableArrayList(st));
        if (sr == null)
            clear();
        else
            helpTypeField.setValue(sr.getHelpType());
    }
}
