package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread{
    private ArrayList<Client> clients;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Client(Socket socket,ArrayList<Client>clients){

        try {
            this.socket=socket;
            this.clients=clients;
            this.dataInputStream=new DataInputStream(socket.getInputStream());
            this.dataOutputStream=new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run(){
        try {
            String message;
            while ((message=dataInputStream.readUTF())!=null){
                if(message.equalsIgnoreCase("exit")){
                    break;
                }
                for (Client cl:clients){
                    cl.dataOutputStream.writeUTF(message);
                }

        }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dataOutputStream.close();
                dataInputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
