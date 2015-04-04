package com.gmail.mooman219.shared.packet;

import com.gmail.mooman219.shared.geo.cord.ShortCord;

public class Packet8ChunkRequest extends Packet{
    public static final long serialVersionUID = 1;
    public ShortCord cPos;

    public Packet8ChunkRequest(ShortCord cPos){
        super((short)8);
        this.cPos = cPos;
    }

    public Packet8ChunkRequest(int x1, int y1) {
        this(new ShortCord(x1, y1));
    }
}