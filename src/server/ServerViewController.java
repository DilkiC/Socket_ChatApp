package server;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerViewController {
    public Label lblName;
    public TextArea txtMessageArea;
    public TextField txtMessage;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;


    public void initialize(){
        new Thread(()->{
            try {
                serverSocket=new ServerSocket(5000);
                socket=serverSocket.accept();

                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());

                String messageIn="";
                while (messageIn!="empty"){
                    messageIn=dataInputStream.readUTF();
                    txtMessageArea.appendText("\nClient : "+messageIn);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        String messageOut="";
        messageOut=txtMessage.getText();
        dataOutputStream.writeUTF(messageOut);
        txtMessageArea.appendText("\nServer : "+messageOut);
        txtMessage.setText("");

    }


}
