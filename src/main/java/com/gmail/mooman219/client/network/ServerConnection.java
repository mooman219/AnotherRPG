package com.gmail.mooman219.client.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {
    public boolean isConnected = false;
    public ServerReceiveThread receiveThread;
    public ObjectOutputStream objectOut;
    public ObjectInputStream objectIn;
    public Socket clientSocket;

    public boolean autoConnect = true;
    public int serverPort = 32001;
    public String name = "Joe";
    public String destination = "192.168.1.1";
}
