package com.gmail.mooman219.aaa.server;

import com.gmail.mooman219.aaa.packet.Packet;
import com.gmail.mooman219.aaa.packet.Packet2Text;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    public static ServerSocket serverSocket;
    public static CopyOnWriteArrayList<Connection> connections = new CopyOnWriteArrayList<Connection>();

    public static void main(String args[]) {
        try {
            serverSocket = new ServerSocket(12081, 10);
            AcceptThread acceptThread = new AcceptThread();
            acceptThread.start();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static class AcceptThread extends Thread {

        public void run() {
            while(true) {
                try {
                    Socket connection = serverSocket.accept();
                    System.out.println("Connection received from " + connection.getInetAddress().getHostName());
                    connections.add(new Connection(connection));
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static class Connection {

        private final Socket connection;
        private final ReceiveThread reciveThread;

        private ObjectOutputStream out;
        private ObjectInputStream in;

        public Connection(Socket connection) {
            this.connection = connection;
            this.reciveThread = new ReceiveThread(this);
            try {
                this.out = new ObjectOutputStream(connection.getOutputStream());
                this.in = new ObjectInputStream(connection.getInputStream());
                this.out.flush();
                this.reciveThread.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }

        public static class ReceiveThread extends Thread {

            private final Connection connection;

            public ReceiveThread(Connection connection) {
                this.connection = connection;
            }

            public void run() {
                while(true) {
                    try {
                        Packet packet = Packet.next(connection.in);
                        if(packet != null) {
                            System.out.println("Got " + packet.getId());
                            switch(packet.getId()) {
                                case 0:
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    System.out.println(((Packet2Text)packet).text);
                                    break;
                            }
                        }
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
