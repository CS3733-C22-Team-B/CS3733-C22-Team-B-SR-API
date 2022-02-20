package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BorderHomeController implements Initializable{
    public static BorderHomeController curBorderHomeController;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox hamburger;

    @FXML
    private BorderPane borderPane;

    private Pane childPane;

    public BorderHomeController() {
        curBorderHomeController = this;
    }

    private String pageToFXMLPath(String pageName) {
        try {
            return "/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/" + pageName +".fxml";
        } catch(Exception e) {
            throw new RuntimeException("Page Does not exist");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));
            childPane = loader.load();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();


            AnchorPane.setRightAnchor(childPane, 0.0);
            AnchorPane.setLeftAnchor(childPane, 0.0);
            AnchorPane.setTopAnchor(childPane, 0.0);
            AnchorPane.setBottomAnchor(childPane, 0.0);


            childPane.setPrefHeight(anchorPane.getHeight());
            childPane.setPrefWidth(anchorPane.getWidth());


        } catch (IOException e) {
            e.printStackTrace();
        }

        JFXButton button1 = new JFXButton("Home");
        JFXButton button2 = new JFXButton("Map");
        JFXButton button3 = new JFXButton("Request");
        JFXButton button4 = new JFXButton("Dashboard");
        JFXButton button5 = new JFXButton("Settings");
        JFXButton button6 = new JFXButton("Tables");
        JFXButton button7 = new JFXButton("Exit");





        VBox leftDrawerPane = new VBox();
        VBox DrawerPane = new VBox();
        leftDrawerPane.getChildren().add(button1);
        leftDrawerPane.getChildren().add(button2);
        leftDrawerPane.getChildren().add(button3);
        leftDrawerPane.getChildren().add(button4);
        leftDrawerPane.getChildren().add(button6);
        leftDrawerPane.getChildren().add(button5);
        leftDrawerPane.getChildren().add(button7);



        leftDrawerPane.setAlignment(Pos.CENTER);
        leftDrawerPane.prefHeightProperty().bind(anchorPane.heightProperty());

        drawer.setSidePane(leftDrawerPane);
        drawer.setDefaultDrawerSize(150);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(true);

        hamburger.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            if (drawer.isClosed()) {
                hamburger.setVisible(false);
                drawer.open();
            }
        });

        drawer.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
            drawer.close();
            hamburger.setVisible(true);

        });

        button1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));

            try {
                childPane = loader.load();
                AnchorPane.setRightAnchor(childPane, 0.0);
                AnchorPane.setLeftAnchor(childPane, 0.0);
                AnchorPane.setTopAnchor(childPane, 0.0);
                AnchorPane.setBottomAnchor(childPane, 0.0);


                childPane.setPrefHeight(anchorPane.getHeight());
                childPane.setPrefWidth(anchorPane.getWidth());


                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        button2.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("MapEditor")));

            try {
                childPane = loader.load();

                AnchorPane.setRightAnchor(childPane, 0.0);
                AnchorPane.setLeftAnchor(childPane, 0.0);
                AnchorPane.setTopAnchor(childPane, 0.0);
                AnchorPane.setBottomAnchor(childPane, 0.0);


                childPane.setPrefHeight(borderPane.getHeight());
                childPane.setPrefWidth(borderPane.getWidth());

                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();



            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button3.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("ComputerSR")));


            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/MasterServiceRequest.fxml"));
                loader.setControllerFactory(
                        param -> new MasterServiceRequestController("ComputerSR"));

                BorderHomeController.curBorderHomeController.changeNode(loader);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            try {
//                childPane = loader.load();
//                anchorPane.getChildren().clear();
//                anchorPane.getChildren().add(childPane);
//                anchorPane.toBack();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
        });

        button4.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Dashboard")));

            try {
                childPane = loader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        



        button6.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Tables")));

            try {
                childPane = loader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button7.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            Platform.exit();
        });


    }

    void changeNode(FXMLLoader loader) throws IOException {
        try {
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

    public AnchorPane getAnchorPane() {
        return this.anchorPane;
    }
}

