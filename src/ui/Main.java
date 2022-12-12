package ui;

import java.sql.SQLException;

import database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import parser.RecordTrackLibrary;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        
        DatabaseManager dbManager = new DatabaseManager("RecordTrackLibrary.db");
        RecordTrackLibrary recordTrackLibrary = new RecordTrackLibrary();
        
        try {
			dbManager.open();
			recordTrackLibrary = dbManager.getExistingLibrary();
			dbManager.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
    }
}
