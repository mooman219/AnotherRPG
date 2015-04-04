package com.gmail.mooman219.aaa.packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Packet0Equation extends Packet{
    public static final byte id = 0;

    static {
        Packet.registerPacket(id);
    }

    public int numA, demA, numB, demB;
    public char op;

    public Packet0Equation() {
        this.numA = 0;
        this.demA = 0;
        this.op = '+';
        this.numB = 0;
        this.demB = 0;
    }

    @Override
    protected void write(ObjectOutputStream out) throws IOException {
        out.writeInt(numA);
        out.writeInt(demA);
        out.writeChar(op);
        out.writeInt(numB);
        out.writeInt(demB);;
    }

    @Override
    protected Packet read(ObjectInputStream in) throws IOException {
        numA = in.readInt();
        demA = in.readInt();
        op = in.readChar();
        numB = in.readInt();
        demB = in.readInt();
        return this;
    }

    @Override
    public byte getId() {
        return Packet0Equation.id;
    }
}
