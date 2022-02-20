package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI;

import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity.DatabaseWrapper;
import javafx.application.Application;
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

    BServiceRequestAPI.getInstance().run(0, 0, 0, 0, "/edu/wpi/cs3733/c22/teamB/ServiceRequestAPI/styles/default.css", null, null);
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

}
