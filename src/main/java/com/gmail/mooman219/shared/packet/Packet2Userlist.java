package com.gmail.mooman219.shared.packet;

import java.util.ArrayList;

public class Packet2Userlist extends Packet{
    public static final long serialVersionUID = 1L;
    public ArrayList<String> userlist = new ArrayList<String>();

    public Packet2Userlist(ArrayList<String> userNameList){
        super((short)2);
        this.userlist = userNameList;
    }
}
