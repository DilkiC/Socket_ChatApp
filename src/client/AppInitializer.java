package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("LoginView.fxml"))));
        primaryStage.show();


    Scene scene = new Scene(new Group(), 500, 400);
    scene.getStylesheets().add("Style.css");
    }
}
