package com.gmail.mooman219.server.manager.network;

import java.net.ServerSocket;

import com.gmail.mooman219.server.gui.ServerInterface;

public class SettingsManager {
    public static ServerInterface serverGUI = new ServerInterface();
    public static ServerSocket serverSocket;
    public static int serverPort = 32001;

    public static int maxNameLength = 16;
    public static int minNameLength = 3;

    public SettingsManager(){}

}
