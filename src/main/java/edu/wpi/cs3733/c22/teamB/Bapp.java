package edu.wpi.cs3733.c22.teamB;

import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.BServiceRequestAPI;
import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.ServiceException;
import edu.wpi.cs3733.c22.teamB.controllers.MasterServiceRequestController;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Bapp extends Application {
  private static Stage primaryStage;

  @Override
  public void init() throws IOException{
    log.info("Starting Up");

    DatabaseWrapper db = new DatabaseWrapper();
    db.isFirstRestore();
  }

  @Override
  public void start(Stage primaryStage) throws IOException, ServiceException {
    this.primaryStage = primaryStage;

    BServiceRequestAPI.getInstance().run(0, 0, 0, 0, "/edu/wpi/cs3733/c22/teamB/styles/default.css", null, null);
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

}
