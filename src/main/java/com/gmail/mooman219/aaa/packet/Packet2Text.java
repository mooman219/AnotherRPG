package com.gmail.mooman219.aaa.packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Packet2Text extends Packet {

    public static final byte id = 2;

    static {
        Packet.registerPacket(id);
    }

    public String text;

    public Packet2Text() {
        this.text = "";
    }

    @Override
    protected void write(ObjectOutputStream out) throws IOException {
        out.writeUTF(text);
    }

    @Override
    protected Packet read(ObjectInputStream in) throws IOException {
        text = in.readUTF();
        return this;
    }

    @Override
    public byte getId() {
        return Packet2Text.id;
    }
}
