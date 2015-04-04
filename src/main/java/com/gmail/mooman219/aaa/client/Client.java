package com.gmail.mooman219.aaa.client;

import com.gmail.mooman219.aaa.packet.Packet;
import com.gmail.mooman219.aaa.packet.Packet2Text;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String args[]) {
        Connection server = new Connection();
        while(true) {
            server.sendText("HI");
            try {
                Thread.sleep(100l);
            } catch(InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static class Connection {

        private final Socket connection;
        private final ReceiveThread reciveThread;

        private ObjectOutputStream out;
        private ObjectInputStream in;

        public Connection() {
            this.reciveThread = new ReceiveThread(this);
            this.connection = new Socket();
            try {
                this.connection.connect(new InetSocketAddress("localhost", 12081), 1000);
                this.out = new ObjectOutputStream(connection.getOutputStream());
                this.in = new ObjectInputStream(connection.getInputStream());
                this.out.flush();
                this.reciveThread.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }

        public void sendText(String text) {
            Packet2Text test = new Packet2Text();
            test.text = text;
            Packet.send(out, test);
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
