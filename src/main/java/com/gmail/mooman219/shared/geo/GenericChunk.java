package com.gmail.mooman219.shared.geo;

import java.io.Serializable;

import com.gmail.mooman219.shared.geo.cord.Cord;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;
import com.gmail.mooman219.shared.geo.cord.ShortCord;

public class GenericChunk implements Serializable{
    private static final long serialVersionUID = 7768542694742117141L;
    public GenericBlock[][] blockMap = new GenericBlock[GenericWorld.CHUNK_SIZE][GenericWorld.CHUNK_SIZE];
    public ShortCord cPos;

    public GenericChunk(ShortCord chunkCord){
        this.cPos = chunkCord;
    }

    public void fillChunk(int type){
        for(short x = 0; x < GenericWorld.CHUNK_SIZE; x++){
            for(short y = 0; y < GenericWorld.CHUNK_SIZE; y++){
                blockMap[x][y] = new GenericBlock(new IntegerCord(x+(GenericWorld.CHUNK_SIZE*cPos.getX()), y+(GenericWorld.CHUNK_SIZE*cPos.getY())), type, 1);
            }
        }
    }

    public GenericBlock getBlock(Cord cbPos){
        if(cbPos.isWithin(-1, GenericWorld.CHUNK_SIZE)){
            return blockMap[cbPos.getX()][cbPos.getY()];
        }
        return null;
    }

    public void setBlock(GenericBlock b){
        Cord cbPos = Cord.mod(b.bPos, GenericWorld.CHUNK_SIZE);
        if(cbPos.isWithin(-1, GenericWorld.CHUNK_SIZE)){
            blockMap[cbPos.getX()][cbPos.getY()] = b;
        }
    }
}
