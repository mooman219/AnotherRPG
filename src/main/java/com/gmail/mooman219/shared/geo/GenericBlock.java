package com.gmail.mooman219.shared.geo;

import java.io.Serializable;

import com.gmail.mooman219.shared.geo.cord.Cord;

public class GenericBlock implements Serializable{
    private static final long serialVersionUID = -90805474447304740L;
    public short blockType;
    public short direction;
    public Cord bPos;

    public GenericBlock(Cord blockCord, short blockType, short direction){
        this.blockType = blockType;
        this.direction = direction;
        this.bPos = blockCord;
    }

    public GenericBlock(Cord blockCord, int blockType, int direction){
        this(blockCord, (short) blockType, (short) direction);
    }
}