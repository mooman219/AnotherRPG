package com.gmail.mooman219.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import com.gmail.mooman219.server.event.core.TickEvent;
import com.gmail.mooman219.server.handler.TPSHandler;
import com.gmail.mooman219.server.manager.EventManager;
import com.gmail.mooman219.server.manager.WorldManager;
import com.gmail.mooman219.server.manager.network.SettingsManager;
import com.gmail.mooman219.server.network.ClientAcceptThread;


public class AnotherServer {
    public static void main(String[] args) {
        new AnotherServer();
    }

    public AnotherServer(){
        SettingsManager.serverGUI.setTitle("AnotherServer TPS: init");
        SettingsManager.serverGUI.setVisible(true);
        SettingsManager.serverGUI.addLine("AnotherServer Started.");

        try {
            SettingsManager.serverSocket = new ServerSocket(SettingsManager.serverPort, 10);
            SettingsManager.serverGUI.addLine("ServerSocket created.");
            SettingsManager.serverGUI.addLine("Current address " + InetAddress.getLocalHost().getHostAddress() + ":" + SettingsManager.serverPort);
            new ClientAcceptThread().start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        WorldManager.init();
        //
        EventManager.registerEvents(new TPSHandler());
        //

        //loop();
    }

    public void loop(){
        long lastTime = 0l;
        long deltaTime = 0l;
        while(true){
            deltaTime = System.currentTimeMillis() - lastTime;
            EventManager.callEvent(new TickEvent(System.currentTimeMillis(), deltaTime));
            lastTime = System.currentTimeMillis();
        }
    }

    public static void message(String message){
        SettingsManager.serverGUI.addLine(message);
    }
}