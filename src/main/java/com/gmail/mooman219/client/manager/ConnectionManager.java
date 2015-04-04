package com.gmail.mooman219.client.manager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.event.core.game.ConnectionInfoEvent;
import com.gmail.mooman219.client.network.ServerConnection;
import com.gmail.mooman219.client.network.ServerReceiveThread;
import com.gmail.mooman219.shared.packet.Packet0Connect;

public class ConnectionManager {
    public static ServerConnection connection = new ServerConnection();

    public ConnectionManager(){}

    public static void resetConnection(){
        try {
            if(connection.isConnected){
                StateManager.currentState = GameState.MAINMENU;
                AnotherRPG.translate_x = 0;
                AnotherRPG.translate_y = 0;
                connection.isConnected = false;
                connection.receiveThread.toStop = true;
                connection.objectIn.close();
                connection.objectOut.close();
                connection.clientSocket.close();

                connection.receiveThread = null;
                connection.objectIn = null;
                connection.objectOut = null;
                connection.clientSocket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void establishConnection(){
        if(connection.isConnected){
            resetConnection();
        }

        System.out.println("Current name: " + connection.name);
        try {
            String address = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Current address: " + address);

            if(connection.autoConnect){
                autoConnect(address, ConnectionManager.connection.serverPort);
            }else{
                directConnect(ConnectionManager.connection.destination, connection.serverPort);
            }
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
    }

    private static void finalizeConnection(){
        try {
            EventManager.callEvent(new ConnectionInfoEvent("Connected to " + connection.clientSocket.getInetAddress() + " in port " + connection.serverPort));
            connection.objectOut = new ObjectOutputStream(connection.clientSocket.getOutputStream());
            connection.objectIn = new ObjectInputStream(connection.clientSocket.getInputStream());
            connection.objectOut.flush();

            connection.receiveThread = new ServerReceiveThread();
            connection.receiveThread.start();

            ClientPacketManager.sendPacket(new Packet0Connect(connection.name));
            connection.isConnected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void directConnect(String dest, int port) {
        try {
            EventManager.callEvent(new ConnectionInfoEvent("Attempting connection to " + dest));
            connection.clientSocket = new Socket();
            connection.clientSocket.connect(new InetSocketAddress(dest, port), 1000);
            finalizeConnection();
            return;
        } catch (SocketTimeoutException e) {
            // There is no server on the given address
            EventManager.callEvent(new ConnectionInfoEvent("Error: SocketTimeoutException"));
        } catch (ConnectException e) {
            e.printStackTrace();
            EventManager.callEvent(new ConnectionInfoEvent("Error: ConnectException"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            EventManager.callEvent(new ConnectionInfoEvent("Error: UnknownHostException"));
        } catch (Exception e) {
            e.printStackTrace();
            EventManager.callEvent(new ConnectionInfoEvent("Error: " + e.getClass().getSimpleName()));
        }
        try {
            connection.clientSocket.close();
            connection.clientSocket = null;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void autoConnect(String currentAddress, int port) {
        EventManager.callEvent(new ConnectionInfoEvent("Attempting to automatically obtain address."));
        int timeout = 15;
        String[] addressSections;
        int[] addressSectionsInt;

        addressSections = currentAddress.split("\\.");
        addressSectionsInt = new int[addressSections.length];
        for (int a = 0; a < addressSections.length; a++) {
            addressSectionsInt[a] = Integer.parseInt(addressSections[a]);
        }
        int iTemp = addressSectionsInt[addressSectionsInt.length - 2];
        for (int a = -1; a < 256; a++) {
            if (a == -1) {
                ;
            } else if (a == iTemp) {
                a++;
                addressSectionsInt[addressSectionsInt.length - 2] = a;
            } else {
                addressSectionsInt[addressSectionsInt.length - 2] = a;
            }
            for (int b = 0; b < 256; b++) {
                addressSectionsInt[addressSectionsInt.length - 1] = b;
                String temp = formAddress(addressSectionsInt);
                try {
                    //System.out.println("Attempting connection to " + temp);
                    connection.clientSocket = new Socket();
                    connection.clientSocket.connect(new InetSocketAddress(temp, port), timeout);
                    finalizeConnection();
                    return;
                } catch (SocketTimeoutException e) {
                    // Happens a LOT
                } catch (BindException e) {
                    EventManager.callEvent(new ConnectionInfoEvent("Error: BindException"));
                    return;
                } catch (UnknownHostException e) {
                    EventManager.callEvent(new ConnectionInfoEvent("Error: UnknownHostException"));
                    return;
                } catch (ConnectException e) {
                    EventManager.callEvent(new ConnectionInfoEvent("Error: ConnectException"));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    EventManager.callEvent(new ConnectionInfoEvent("Error: " + e.getClass().getSimpleName()));
                }
            }
        }
    }

    private static String formAddress(int[] input) {
        String result = "";
        for (int a = 0; a < input.length; a++) {
            result += input[a] + (a + 1 < input.length ? "." : "");
        }
        return result;
    }
}
