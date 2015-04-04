package com.gmail.mooman219.shared.packet;

import com.gmail.mooman219.shared.geo.cord.IntegerCord;

public class Packet6EntityMove extends Packet{
    public static final long serialVersionUID = 1L;
    public int uniqueID;
    public IntegerCord bPos;

    public Packet6EntityMove(int uniqueID, IntegerCord bPos){
        super((short)6);
        this.bPos = bPos;
    }
}
