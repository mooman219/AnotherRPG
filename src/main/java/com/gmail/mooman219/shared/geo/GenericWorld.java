package com.gmail.mooman219.shared.geo;

import java.io.Serializable;
import java.util.ArrayList;

import com.gmail.mooman219.shared.geo.cord.Cord;
import com.gmail.mooman219.shared.geo.cord.ShortCord;

public class GenericWorld implements Serializable{
    private static final long serialVersionUID = -2615207340833575213L;
    public static final short WORLD_SIZE = 32;
    public static final short CHUNK_SIZE = 32;
    public static final short BLOCK_SIZE = 32;
    public static short viewRadius = 1;

    public GenericChunk[][] chunkMap = new GenericChunk[WORLD_SIZE][WORLD_SIZE];

    public GenericWorld(){
        fillWorld();
    }

    public void fillWorld(){
        chunkMap = new GenericChunk[WORLD_SIZE][WORLD_SIZE];
        for(int x = 0; x < WORLD_SIZE; x++){
            for(int y = 0; y < WORLD_SIZE; y++){
                chunkMap[x][y] = new GenericChunk(new ShortCord(x,y));
                chunkMap[x][y].fillChunk(2);
            }
        }
    }

    public GenericBlock getBlock(Cord bPos){
        Cord cPos = Cord.divide(bPos, CHUNK_SIZE);
        Cord cbPos = Cord.mod(bPos, CHUNK_SIZE);
        if(cPos.isWithin(-1, WORLD_SIZE)){
            return chunkMap[cPos.getX()][cPos.getY()].getBlock(cbPos);
        }
        return null;
    }

    public void setBlock(GenericBlock b){
        Cord cPos = Cord.divide(b.bPos, CHUNK_SIZE);
        if(cPos.isWithin(-1, WORLD_SIZE)){
            chunkMap[cPos.getX()][cPos.getY()].setBlock(b);
        }
    }

    public GenericChunk getChunk(Cord cPos){
        if(cPos.isWithin(-1, WORLD_SIZE)){
            return chunkMap[cPos.getX()][cPos.getY()];
        }
        return null;
    }

    public void setChunk(GenericChunk c){
        if(c.cPos.isWithin(-1, WORLD_SIZE)){
            chunkMap[c.cPos.x][c.cPos.y] = c;
        }
    }

    public ArrayList<GenericChunk> getVisableChunks(Cord cPos){
        ArrayList<GenericChunk> ret = new ArrayList<GenericChunk>();
        for(int x = cPos.getX() - viewRadius; x <= cPos.getX() + viewRadius; x++){
            if(x < 0){
                x = 0;
                continue;
            }else if(x >= WORLD_SIZE){
                return ret;
            }
            for(int y = cPos.getY() - viewRadius; y <= cPos.getY() + viewRadius; y++){
                if(y < 0){
                    y = 0;
                    continue;
                }else if(y >= WORLD_SIZE){
                    break;
                }else if(!ret.contains(chunkMap[x][y])){
                    ret.add(chunkMap[x][y] == null ? chunkMap[x][y] = new GenericChunk(new ShortCord(x, y)) : chunkMap[x][y]);
                }
            }
        }
        return ret;
    }
}
