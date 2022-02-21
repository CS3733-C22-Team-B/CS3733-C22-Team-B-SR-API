package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BServiceRequestAPI {
    private String destLocationID = null;
    private String originLocationID = null;

    private static BServiceRequestAPI instance;

    private BServiceRequestAPI() {}

    public static BServiceRequestAPI getInstance() {
        if (instance == null)
            instance = new BServiceRequestAPI();
        return instance;
    }

    public void run(int xCoord, int yCoord, int windowWidth, int windowHeight, String cssPath,
                    String destLocationID, String originLocationID) throws ServiceException {
        this.destLocationID = destLocationID;
        this.originLocationID = originLocationID;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/views/borderHome.fxml"));
        try {
            Parent root = loader.load();
            Stage srWindow = new Stage();
            srWindow.setTitle("Bapp - Home Page");
            Scene scene = new Scene(root,
                    windowWidth != 0 ? windowWidth : -1,
                    windowHeight != 0 ? windowHeight : -1);
            scene.getStylesheets().add(cssPath == null
                    ? "/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/styles/default.css"
                    : cssPath);
            srWindow.setScene(scene);

            srWindow.initModality(Modality.NONE);
            srWindow.initOwner(Bapp.getPrimaryStage());
            srWindow.setX(xCoord);
            srWindow.setY(yCoord);

            srWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("");
        }
    }

    public String getDestLocationID() {
        return destLocationID;
    }

    public String getOriginLocationID() {
        return originLocationID;
    }

}
