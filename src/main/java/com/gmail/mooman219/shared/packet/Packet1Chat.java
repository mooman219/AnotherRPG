package com.gmail.mooman219.shared.packet;

public class Packet1Chat extends Packet{
    public static final long serialVersionUID = 1;
    public String message;

    public Packet1Chat(String message){
        super((short)1);
        this.message = message;
    }
}
