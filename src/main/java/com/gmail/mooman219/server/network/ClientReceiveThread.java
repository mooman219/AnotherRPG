package com.gmail.mooman219.server.network;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

import com.gmail.mooman219.server.manager.network.ConnectionManager;
import com.gmail.mooman219.server.manager.network.ServerPacketManager;
import com.gmail.mooman219.server.manager.network.SettingsManager;
import com.gmail.mooman219.shared.packet.Packet;

public class ClientReceiveThread extends Thread{
    ClientConnection client;

    public ClientReceiveThread(ClientConnection client){
        this.client = client;
    }

    public void run(){
        //System.out.println("Receive thread started.");
        while (true){
            try {
                Packet packet = (Packet)client.getInStream().readObject();
                ServerPacketManager.handlePacket(client, packet);
            } catch (SocketException e) {
                SettingsManager.serverGUI.addLine("Connection lost from " + client.getUserName() + ".");
                ConnectionManager.removeConnection(client);
                break;
            } catch (EOFException e) {
                SettingsManager.serverGUI.addLine("Connection lost from " + client.getUserName() + ".");
                ConnectionManager.removeConnection(client);
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
