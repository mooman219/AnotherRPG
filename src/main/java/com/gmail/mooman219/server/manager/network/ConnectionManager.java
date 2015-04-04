package com.gmail.mooman219.server.manager.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.gmail.mooman219.server.network.ClientConnection;
import com.gmail.mooman219.shared.packet.Packet1Chat;
import com.gmail.mooman219.shared.packet.Packet2Userlist;

public class ConnectionManager {
    private static ArrayList<ClientConnection> connections = new ArrayList<ClientConnection>();

    public ConnectionManager(){}

    public static void addConnection(ClientConnection user){
        if(!connections.contains(user)){
            connections.add(user);
        }
    }

    public static void removeConnection(ClientConnection user){
        try {
            if(user.isConnected()){
                ServerPacketManager.sendGlobalPacket(new Packet1Chat("Network: Connection lost from " + user.getUserName() + "."));
            }
            SettingsManager.serverGUI.addLine("Removing connection " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {}
        if(connections.contains(user)) {
            connections.remove(user);
        }
        ServerPacketManager.sendGlobalPacket(new Packet2Userlist(getNames()));
        SettingsManager.serverGUI.updateUserList(getNames());
    }

    public static ArrayList<String> getNames(){
        ArrayList<String> nameList = new ArrayList<String>();
        for(ClientConnection user : connections) {
            nameList.add(user.getUserName());
        }
        return nameList;
    }

    public static boolean doesNameExist(String name){
        for(String localNames : getNames()) {
            if(name.equalsIgnoreCase(localNames)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<ClientConnection> getConnections() {
        return connections;
    }
}
