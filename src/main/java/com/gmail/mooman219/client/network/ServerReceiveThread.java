package com.gmail.mooman219.client.network;

import java.io.EOFException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.SocketException;

import com.gmail.mooman219.client.manager.ClientPacketManager;
import com.gmail.mooman219.client.manager.ConnectionManager;
import com.gmail.mooman219.shared.packet.Packet;

public class ServerReceiveThread extends Thread{
    public boolean toStop = false;

    public ServerReceiveThread(){}

    public void run(){
        System.out.println("Receive thread started.");
        while (true){
            if(toStop){
                return;
            }
            try {
                Packet packet = (Packet)ConnectionManager.connection.objectIn.readObject();
                while (packet == null){
                    packet = (Packet)ConnectionManager.connection.objectIn.readObject();
                }
                ClientPacketManager.handlePacket(packet);
            } catch (SocketException e) {
                System.out.println("Connection lost to server.");
                System.out.println("Shutting down Receive Thread.");
                ConnectionManager.resetConnection();
                return;
            } catch (EOFException e) {
                System.out.println("Connection lost to server.");
                System.out.println("Shutting down Receive Thread.");
                ConnectionManager.resetConnection();
                return;
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException: Version error.");
            } catch (StreamCorruptedException e) {
                System.out.println("Connection lost to server.");
                System.out.println("Shutting down Receive Thread.");
                ConnectionManager.resetConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}