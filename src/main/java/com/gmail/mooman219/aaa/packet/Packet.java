package com.gmail.mooman219.aaa.packet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Packet {

    private static final List<Byte> packets = new ArrayList<Byte>();

    public static Packet next(ObjectInputStream in) throws IOException {
        byte id = in.readByte();
        switch(id) {
            case 0:
                return new Packet0Equation().read(in);
            case 1:
                return new Packet1Answer().read(in);
            case 2:
                return new Packet2Text().read(in);
        }
        return null;
    }

    public static void send(ObjectOutputStream out, Packet p) {
        if(packets.contains(p.getId())) {
            try {
                out.writeByte(p.getId());
                p.write(out);
                out.flush();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        } else {
            throw new IllegalStateException("Packet ID does not exist..");
        }
    }

    public static void registerPacket(byte id) {
        if(packets.contains(id)) {
            throw new IllegalStateException("Packet ID already exists.");
        } else {
            packets.add(id);
        }
    }

    protected abstract void write(ObjectOutputStream out) throws IOException;

    protected abstract Packet read(ObjectInputStream in) throws IOException;

    public abstract byte getId();
}
