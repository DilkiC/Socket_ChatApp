package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientViewController {

    public TextArea txtMessageArea;
    public TextField txtMessage;
    public Label lblName;

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void initialize(){
        new Thread(()->{
            try {
                socket=new Socket("127.0.0.1",5000);
                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                dataInputStream=new DataInputStream(socket.getInputStream());

                String messageIn="";
                while (messageIn!="empty"){
                    messageIn=dataInputStream.readUTF();
                    txtMessageArea.appendText("\nServer : "+messageIn);
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
        txtMessageArea.appendText("\nClient : "+messageOut);
        txtMessage.setText("");

    }
}

