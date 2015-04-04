package com.gmail.mooman219.aaa.packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Packet1Answer extends Packet {

    public static final byte id = 1;

    static {
        Packet.registerPacket(id);
    }

    public int num, dem;

    public Packet1Answer() {
        this.num = 0;
        this.dem = 0;
    }

    @Override
    protected void write(ObjectOutputStream out) throws IOException {
        out.writeInt(num);
        out.writeInt(dem);
    }

    @Override
    protected Packet read(ObjectInputStream in) throws IOException {
        num = in.readInt();
        dem = in.readInt();
        return this;
    }

    @Override
    public byte getId() {
        return Packet1Answer.id;
    }
}
