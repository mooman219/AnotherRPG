package com.gmail.mooman219.shared.packet;

import java.io.Serializable;

public class Packet implements Serializable{
    private static final long serialVersionUID = 1L;
    public short id;

    public Packet(short id){
        this.id = id;
    }
}
