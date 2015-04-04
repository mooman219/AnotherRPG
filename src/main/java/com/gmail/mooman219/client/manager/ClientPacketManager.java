package com.gmail.mooman219.client.manager;

import java.io.IOException;

import com.gmail.mooman219.client.enums.BlockType;
import com.gmail.mooman219.client.event.core.game.BlockChangeEvent;
import com.gmail.mooman219.client.event.core.game.ChatEvent;
import com.gmail.mooman219.client.event.core.game.ConnectEvent;
import com.gmail.mooman219.client.event.core.game.ConnectionInfoEvent;
import com.gmail.mooman219.client.geo.Block;
import com.gmail.mooman219.client.geo.Chunk;
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

public class ClientPacketManager {
    public ClientPacketManager(){}

    public static void handlePacket(Packet packet){
        if(packet.id == 0) {
            handlePacket0Connect((Packet0Connect)packet);
        } else if(!ConnectionManager.connection.isConnected) {
            return;
        } else if(packet.id == 1) {
            handlePacket1Chat((Packet1Chat)packet);
        } else if(packet.id == 2) {
            handlePacket2Userlist((Packet2Userlist)packet);
        } else if(packet.id == 3) {
            handlePacket3InvalidName((Packet3InvalidName)packet);
        } else if(packet.id == 4) {
            handlePacket4BlockChange((Packet4BlockChange)packet);
        } else if(packet.id == 5) {
            handlePacket5EntityDirection((Packet5EntityDirection)packet);
        } else if(packet.id == 6) {
            handlePacket6EntityMove((Packet6EntityMove)packet);
        } else if(packet.id == 7) {
            handlePacket7ChunkUpdate((Packet7ChunkUpdate)packet);
        } else if(packet.id == 8) {
            handlePacket8ChunkRequest((Packet8ChunkRequest)packet);
        }
    }

    public static void handlePacket0Connect(Packet0Connect packet){
        EventManager.callEvent(new ConnectEvent());
        // Nothing
    }

    public static void handlePacket1Chat(Packet1Chat packet){
        EventManager.callEvent(new ChatEvent(packet.message));
    }

    public static void handlePacket2Userlist(Packet2Userlist packet){
        //NetSettingsManager.clientGUI.updateUserList(packet.userlist);
    }

    public static void handlePacket3InvalidName(Packet3InvalidName packet){
        System.out.println("Error: Invalid name");
        EventManager.callEvent(new ConnectionInfoEvent("Error: Invalid name"));
    }

    public static void handlePacket4BlockChange(Packet4BlockChange packet){
        System.out.print("Got ["+packet.genericBlock.bPos+"] ["+BlockType.values()[packet.genericBlock.blockType]+"]");
        BlockChangeEvent e = new BlockChangeEvent(Block.deGeneric(packet.genericBlock));
        EventManager.callEvent(e);
        if(!e.isCancelled()){
            System.out.print(" Replace ["+WorldManager.currentWorld.getBlock(e.getBlock().getBlockPos()).getType()+"]");
            WorldManager.currentWorld.setBlock(Block.deGeneric(packet.genericBlock));
        }
        System.out.println("");
    }

    public static void handlePacket5EntityDirection(Packet5EntityDirection packet){
        // Nothing
    }

    public static void handlePacket6EntityMove(Packet6EntityMove packet){
        // Nothing
    }

    public static void handlePacket7ChunkUpdate(Packet7ChunkUpdate packet){
        WorldManager.currentWorld.setChunk(Chunk.deGeneric(packet.chunk));
    }

    public static void handlePacket8ChunkRequest(Packet8ChunkRequest packet){
        // Nothing
    }

    public static void sendPacket(Packet packet){
        try{
            ConnectionManager.connection.objectOut.writeObject(packet);
            ConnectionManager.connection.objectOut.flush();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}

