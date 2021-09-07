package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AppInitializer /*extends Application*/ {

    private static ArrayList<Client>clients=new ArrayList<Client>();

   /* @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("ServerView.fxml"))));
        primaryStage.show();


    Scene scene = new Scene(new Group(), 500, 400);
    scene.getStylesheets().add("Style.css");
    }*/

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket=new ServerSocket(5000);
            while (true){
                System.out.println("waiting for a client..");
                socket=serverSocket.accept();
                System.out.println("connected......");
                Client client=new Client(socket,clients);
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
