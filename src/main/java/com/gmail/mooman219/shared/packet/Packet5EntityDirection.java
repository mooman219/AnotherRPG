package com.gmail.mooman219.shared.packet;

public class Packet5EntityDirection extends Packet{
    public static final long serialVersionUID = 1L;
    public int uniqueID;
    public short direction;

    public Packet5EntityDirection(int uniqueID, short direction){
        super((short)5);
        this.uniqueID = uniqueID;
        this.direction = direction;
    }

    public Packet5EntityDirection(int uniqueID, int direction){
        this(uniqueID, (short)direction);
    }
}
