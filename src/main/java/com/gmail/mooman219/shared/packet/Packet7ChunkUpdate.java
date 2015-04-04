package com.gmail.mooman219.shared.packet;

import com.gmail.mooman219.shared.geo.GenericChunk;

public class Packet7ChunkUpdate extends Packet{
    public static final long serialVersionUID = 1;
    public GenericChunk chunk;

    public Packet7ChunkUpdate(GenericChunk chunk){
        super((short)7);
        this.chunk = chunk;
    }
}