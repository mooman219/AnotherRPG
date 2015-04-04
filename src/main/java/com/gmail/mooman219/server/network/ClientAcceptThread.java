package com.gmail.mooman219.server.network;

import java.io.IOException;
import java.net.Socket;

import com.gmail.mooman219.server.manager.network.ConnectionManager;
import com.gmail.mooman219.server.manager.network.SettingsManager;

public class ClientAcceptThread extends Thread{

    public ClientAcceptThread(){}

    public void run(){
        SettingsManager.serverGUI.addLine("Connection thread started.");
        SettingsManager.serverGUI.addLine("Accepting new connections.");
        while(true){
            try {
                Socket connection = SettingsManager.serverSocket.accept();
                SettingsManager.serverGUI.addLine("Connection received from " + connection.getInetAddress().getHostName());
                ConnectionManager.addConnection(new ClientConnection(connection));
            } catch (IOException e) {
                e.printStackTrace();
                SettingsManager.serverGUI.addLine("Retrying connection.");
            }
        }
    }
}
