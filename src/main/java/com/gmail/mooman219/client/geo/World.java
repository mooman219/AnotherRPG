package com.gmail.mooman219.client.geo;

import java.util.ArrayList;

import com.gmail.mooman219.client.manager.ClientPacketManager;
import com.gmail.mooman219.shared.geo.cord.Cord;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;
import com.gmail.mooman219.shared.packet.Packet8ChunkRequest;


public class World {
    //LOL Max world size: 4,611,686,014,132,420,609 blocks.
    //EST Max world size:             2,147,483,647 blocks.
    //CUR World 32, Chunk 32:             1,048,576 blocks.
    public static final int WORLD_SIZE = 32;
    public static final int CHUNK_SIZE = 32;
    public static final int BLOCK_SIZE = 32;
    public static int viewRadius = 2;
    private Chunk[][] chunkMap;

    public World(){
        fillWorld();
    }

    public void fillWorld(){
        chunkMap = new Chunk[WORLD_SIZE][WORLD_SIZE];
        for(int x = 0; x < WORLD_SIZE; x++){
            for(int y = 0; y < WORLD_SIZE; y++){
                chunkMap[x][y] = new Chunk(new IntegerCord(x, y));
            }
        }
    }

    public Chunk getChunk(Cord cPos){
        if(cPos.isWithin(-1, WORLD_SIZE)){
            return chunkMap[cPos.getX()][cPos.getY()] == null ? chunkMap[cPos.getX()][cPos.getY()] = new Chunk(cPos) : chunkMap[cPos.getX()][cPos.getY()];
        }
        return null;
    }

    public void setChunk(Chunk newChunk){
        Cord cPos = newChunk.getChunkPos();
        if(cPos.isWithin(-1, WORLD_SIZE)){
            chunkMap[cPos.getX()][cPos.getY()] = newChunk;
        }
    }

    public Block getBlock(Cord bPos){
        if(!bPos.isWithin(-1, WORLD_SIZE*CHUNK_SIZE)){
            return null;
        }
        Cord cPos = Cord.divide(bPos, CHUNK_SIZE);
        Cord cbPos = Cord.mod(bPos, CHUNK_SIZE);
        if(chunkMap[cPos.getX()][cPos.getY()] == null){
            chunkMap[cPos.getX()][cPos.getY()] = new Chunk(cPos);
        }
        return chunkMap[cPos.getX()][cPos.getY()].getBlock(cbPos);
    }

    public void setBlock(Block b){
        this.getChunk(Cord.divide(b.getBlockPos(), CHUNK_SIZE)).setBlock(b);
    }

    public ArrayList<Chunk> getVisableChunks(Cord cPos){
        ArrayList<Chunk> temp = new ArrayList<Chunk>();
        for(int x = cPos.getX() - viewRadius; x <= cPos.getX() + viewRadius; x++){
            if(x < 0){
                x = 0;
            }else if(x >= WORLD_SIZE){
                return temp;
            }
            for(int y = cPos.getY() - viewRadius; y <= cPos.getY() + viewRadius; y++){
                if(y <= 0){
                    y = 0;
                }else if(y >= WORLD_SIZE){
                    break;
                }
                if(!temp.contains(chunkMap[x][y])){
                    temp.add(chunkMap[x][y] == null ? chunkMap[x][y] = new Chunk(new IntegerCord(x,y)) : chunkMap[x][y]);
                    if(!chunkMap[x][y].isLoaded){
                        ClientPacketManager.sendPacket(new Packet8ChunkRequest(x, y));
                        chunkMap[x][y].isLoaded = true;
                    }
                }
            }
        }
        return temp;
    }
}
