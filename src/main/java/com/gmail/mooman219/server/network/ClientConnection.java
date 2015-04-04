package com.gmail.mooman219.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.gmail.mooman219.shared.packet.Packet;

public class ClientConnection {
    private String userName;
    private Socket socketConnection;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private boolean isConnected;

    public ClientConnection(Socket connection){
        this.isConnected = false;
        this.socketConnection = connection;
        this.userName = "null";
        try {
            outStream = new ObjectOutputStream(connection.getOutputStream());
            inStream = new ObjectInputStream(connection.getInputStream());
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClientReceiveThread receive = new ClientReceiveThread(this);
        receive.start();
    }

    public void sendPacket(Packet packet){
        try{
            outStream.writeObject(packet);
            outStream.flush();
        }catch(SocketException e){
            // Seems Safe
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Socket getSocketConnection() {
        return socketConnection;
    }

    public void setSocketConnection(Socket socketConnection) {
        this.socketConnection = socketConnection;
    }

    public ObjectOutputStream getOutStream() {
        return outStream;
    }

    public void setOutStream(ObjectOutputStream outStream) {
        this.outStream = outStream;
    }

    public ObjectInputStream getInStream() {
        return inStream;
    }

    public void setInStream(ObjectInputStream inStream) {
        this.inStream = inStream;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
