package com.gmail.mooman219.server.manager.network;

import com.gmail.mooman219.server.AnotherServer;
import com.gmail.mooman219.server.event.core.BlockChangeEvent;
import com.gmail.mooman219.server.manager.EventManager;
import com.gmail.mooman219.server.manager.WorldManager;
import com.gmail.mooman219.server.network.ClientConnection;
import com.gmail.mooman219.shared.packet.Packet;
import com.gmail.mooman219.shared.packet.Packet0Connect;
import com.gmail.mooman219.shared.packet.Packet1Chat;
import com.gmail.mooman219.shared.packet.Packet2Userlist;
import com.gmail.mooman219.shared.packet.Packet3InvalidName;
import com.gmail.mooman219.shared.packet.Packet4BlockChange;
import com.gmail.mooman219.shared.packet.Packet5EntityDirection;
import com.gmail.mooman219.shared.packet.Packet6EntityMove;
import com.gmail.mooman219.shared.packet.Packet7ChunkUpdate;
import com.gmail.mooman219.shared.packet.Packet8ChunkRequest;

public class ServerPacketManager {

    public ServerPacketManager(){}

    public static void handlePacket(ClientConnection from, Packet packet){
        if(packet.id == 0) {
            handlePacket0Connect(from, (Packet0Connect)packet);
        } else if(!from.isConnected()) {
            return;
        } else if(packet.id == 1) {
            handlePacket1Chat(from, (Packet1Chat)packet);
        } else if(packet.id == 2) {
            return;
        } else if(packet.id == 3) {
            handlePacket3InvalidName(from, (Packet3InvalidName)packet);
        } else if(packet.id == 4) {
            handlePacket4BlockChange(from, (Packet4BlockChange)packet);
        } else if(packet.id == 5) {
            handlePacket5EntityDirection(from, (Packet5EntityDirection)packet);
        } else if(packet.id == 6) {
            handlePacket6EntityMove(from, (Packet6EntityMove)packet);
        } else if(packet.id == 7) {
            handlePacket7ChunkUpdate(from, (Packet7ChunkUpdate)packet);
        } else if(packet.id == 8) {
            handlePacket8ChunkRequest(from, (Packet8ChunkRequest)packet);
        }
    }

    public static void handlePacket0Connect(ClientConnection from, Packet0Connect packet){
        if(ConnectionManager.doesNameExist(packet.name) || packet.name.contains(" ") || packet.name.length() < SettingsManager.minNameLength || packet.name.length() > SettingsManager.maxNameLength){
            from.sendPacket(new Packet3InvalidName());
            SettingsManager.serverGUI.addLine("Invalid name from " + from.getSocketConnection().getInetAddress().getHostName());
            return;
        }
        from.sendPacket(new Packet0Connect(""));
        from.setUserName(packet.name);
        String message = from.getUserName() + " connected to the server.";
        SettingsManager.serverGUI.addLine(message);
        sendGlobalPacket(new Packet2Userlist(ConnectionManager.getNames()));
        SettingsManager.serverGUI.updateUserList(ConnectionManager.getNames());
        from.setConnected(true);
        sendGlobalPacket(new Packet1Chat("Network: " + message));
    }

    public static void handlePacket1Chat(ClientConnection from, Packet1Chat packet){
        SettingsManager.serverGUI.addLine(from.getUserName() + ": " + packet.message);
        sendGlobalPacket(new Packet1Chat(from.getUserName() + ": " + packet.message));
    }

    public static void handlePacket2Userlist(ClientConnection from, Packet2Userlist packet){
        // Nothing
    }

    public static void handlePacket3InvalidName(ClientConnection from, Packet3InvalidName packet){
        // Nothing
    }

    public static void handlePacket4BlockChange(ClientConnection from, Packet4BlockChange packet){
        BlockChangeEvent e = new BlockChangeEvent(packet.genericBlock);
        EventManager.callEvent(e);
        if(!e.isCancelled()){
            WorldManager.currentWorld.setBlock(e.getNewBlock());
            sendGlobalPacket(packet);
        }
    }

    public static void handlePacket5EntityDirection(ClientConnection from, Packet5EntityDirection packet){
        // Nothing
    }

    public static void handlePacket6EntityMove(ClientConnection from, Packet6EntityMove packet){
        // Nothing
    }

    public static void handlePacket7ChunkUpdate(ClientConnection from, Packet7ChunkUpdate packet){
        // Nothing
    }

    public static void handlePacket8ChunkRequest(ClientConnection from, Packet8ChunkRequest packet){
        AnotherServer.message(from.getUserName()+" Requests X"+packet.cPos.x+" Y"+packet.cPos.y);
        from.sendPacket(new Packet7ChunkUpdate(WorldManager.currentWorld.getChunk(packet.cPos)));
    }

    public static void sendGlobalPacket(Packet packet){
        for(int i = 0; i < ConnectionManager.getConnections().size(); i++) {
            ConnectionManager.getConnections().get(i).sendPacket(packet);
        }
    }

    public static void sendGlobalPacketBut(ClientConnection skip, Packet packet){
        for(int i = 0; i < ConnectionManager.getConnections().size(); i++){
            if(!ConnectionManager.getConnections().get(i).equals(skip)){
                ConnectionManager.getConnections().get(i).sendPacket(packet);
            }
        }
    }
}
