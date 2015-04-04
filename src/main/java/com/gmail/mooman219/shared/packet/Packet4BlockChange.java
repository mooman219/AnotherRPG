package com.gmail.mooman219.shared.packet;

import com.gmail.mooman219.shared.geo.GenericBlock;

public class Packet4BlockChange extends Packet{
    public static final long serialVersionUID = 1L;
    public GenericBlock genericBlock;

    public Packet4BlockChange(GenericBlock genericBlock){
        super((short)4);
        this.genericBlock = genericBlock;
    }
}
