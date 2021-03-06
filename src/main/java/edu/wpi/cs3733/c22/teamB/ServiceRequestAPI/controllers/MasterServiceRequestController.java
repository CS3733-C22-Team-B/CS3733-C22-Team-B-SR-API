package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.BServiceRequestAPI;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MasterServiceRequestController{
    @FXML private JFXButton submitButton;
    @FXML private JFXButton clearButton;
    @FXML private JFXButton backButton;
    @FXML private TextField idField;
    @FXML private JFXComboBox<String> statusField;
    @FXML private JFXComboBox<String> assignedEmployeeField;
    @FXML private TextArea notesField;
    @FXML private JFXComboBox<String> floorField;
    @FXML private JFXComboBox<String> locationField;
    @FXML private AnchorPane srPane;
    @FXML private Label srLabel;
    @FXML private Pane contentPane;
    @FXML private AnchorPane anchorPane;

    private Pane childPane;
    private IController childController;

    private String childSRType;
    private AbstractSR childSR = null;

    private List<Location> locList;
    private Map<String, Location> locMap;
    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;

    public MasterServiceRequestController() {}

    public MasterServiceRequestController(String srType) {
        try {
            childSRType = srType;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(srTypeToFXMLPath(srType)));
            childPane = loader.load();
            System.out.println(childPane);
            childController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MasterServiceRequestController(String srType, AbstractSR sr) {
        try {
            childSR = sr;
            childSRType = srType;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(srTypeToFXMLPath(srType)));
            loader.setControllerFactory(param -> {
                // Important: add your controller below in an else if
                switch (srType) {
                    case "ComputerSR":
                        return new ComputerSRController((ComputerSR) sr);
                }
                return null;

            });
            childPane = loader.load();
            childController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DO NOT TOUCH THIS
    @FXML private void initialize() {

        DatabaseWrapper dw = new DatabaseWrapper();
        // idField

        srLabel.setText(getLabel());


        // statusField
        statusField.getItems().addAll(AbstractSR.SRstatus);

        // assignedEmployeeField
        employeeList = dw.getAllEmployee();
        employeeMap =
                IntStream.range(0, employeeList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i ->
                                                (employeeList.get(i).getEmployeeID() + ' ' + employeeList.get(i).getName()),
                                        i -> employeeList.get(i)));
        assignedEmployeeField.getItems().addAll(employeeMap.keySet());

        // floorField init
        floorField.getItems().addAll("ALL", "L1", "L2", "1", "2", "3"); // all floors

        // locationField init
        locList = dw.getAllLocation();
        locMap =
                IntStream.range(0, locList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i -> (locList.get(i).getNodeID() + ' ' + locList.get(i).getLongName()), // assuming no dup in long name
                                        i -> locList.get(i)));

        // notesField init

        // srLabel (Page title)
//        srLabel.setText(childSRType); // change this to be more correct

        if (childSR == null) {
            clear(null);
            if (BServiceRequestAPI.getInstance().getDestLocationID() != null)
                locationField.setDisable(true);
            idField.setDisable(true);
        }
        else {
            idField.setText(childSR.getSrID());
            statusField.setValue(childSR.getStatus());
            assignedEmployeeField.setValue(childSR.getAssignedEmployee().getEmployeeID() + ' ' + childSR.getAssignedEmployee().getName());
            floorField.setValue(childSR.getLocation().getFloor());
            locationField.setValue(childSR.getLocation().getNodeID() + ' ' + childSR.getLocation().getLongName());
            notesField.setText(childSR.getNotes());

            idField.setDisable(false);
            statusField.setDisable(false);
            assignedEmployeeField.setDisable(false);
        }
        locationField.getItems().addAll(locMap.keySet()
                .stream()
                .filter(
                        lstr -> floorField.getValue().equals("ALL")
                                || locMap.get(lstr).getFloor().equals(floorField.getValue()))
                .collect(Collectors.toList()));

        // load specific SR fxml
        srPane.getChildren().add(childPane);

    }

    private String getLabel() {
        String name = "Computer Service Request";
        if (childSRType.equals("ExternalTransportSR")){
            name =  "External Patient Transport Service Request";
        }
        if (childSRType.equals("ComputerServiceSR")){
            return "Computer Service Request";
        }
        if (childSRType.equals("FoodDeliverySR")){
            return "Food Delivery Service Request";
        }
        if (childSRType.equals("GiftFloralSR")){
            return "Gift and Floral Service Request";
        }
        if (childSRType.equals("LaundrySR")){
            return "Laundry Service Request";
        }
        if (childSRType.equals("MedicalEquipmentSR")){
            return "Medical Equipment Service Request";
        }
        if (childSRType.equals("MedicineDeliverySr")){
            return "Medicine Delivery Request";
        }
        if (childSRType.equals("SanitationSR")){
            return "Sanitation Service Request";
        }

        return name;
    }

    // DO NOT TOUCH THIS
    @FXML private void submit(ActionEvent actionEvent) {
        childSR = new MainSR(
                idField.getText(),
                childSRType,
                statusField.getValue(),
                locMap.get(locationField.getValue()),
                employeeMap.get(assignedEmployeeField.getValue()),
                employeeMap.get(assignedEmployeeField.getValue()),
                LocalDate.now(),
                notesField.getText());
        childController.submit(childSR);
        this.clear(null);
    }

    // DO NOT TOUCH THIS
    @FXML private void clear(ActionEvent actionEvent) {
        idField.setText(SRIDGenerator.generateID());
        statusField.setValue("WAITING");
        assignedEmployeeField.setValue(employeeList.get(0).getEmployeeID() + ' ' + employeeList.get(0).getName());
        floorField.setValue("ALL");

        if (BServiceRequestAPI.getInstance().getDestLocationID() != null) {
            Location loc = (new DatabaseWrapper()).getLocation(BServiceRequestAPI.getInstance().getDestLocationID());
            locationField.setValue(loc.getNodeID() + ' ' + loc.getLongName());
        } else
            locationField.setValue(null);

        notesField.clear();

        childController.clear();
    }

    @FXML private void onFloorFieldChange(ActionEvent actionEvent) {
        // change locationField accordingly
        locationField.setValue(null);
        locationField.getItems().removeAll();
        locationField.getItems().clear();
        locationField.getItems().addAll(locMap.keySet()
                .stream()
                .filter(
                        lstr -> floorField.getValue().equals("ALL")
                                || locMap.get(lstr).getFloor().equals(floorField.getValue()))
                .collect(Collectors.toList()));
    }
    // Important: add your path here
    public static String srTypeToFXMLPath(String srType) {
        switch (srType) {

            case "ComputerSR":
                return "/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/ComputerSR.fxml";

            default:
                throw new RuntimeException("srType invalid");
        }
    }

    public void goToEmployeeTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/EmployeeTable.fxml"));
            BServiceRequestAPI.getInstance().getSRWindow().getScene().setRoot(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
