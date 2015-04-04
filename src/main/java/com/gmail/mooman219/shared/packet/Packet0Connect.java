package com.gmail.mooman219.shared.packet;

public class Packet0Connect extends Packet {
    public static final long serialVersionUID = 1L;
    public String name;

    public Packet0Connect(String name){
        super((short)0);
        this.name = name;
    }
}
