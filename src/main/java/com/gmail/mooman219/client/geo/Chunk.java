package com.gmail.mooman219.client.geo;

import com.gmail.mooman219.client.enums.BlockType;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.shared.geo.GenericChunk;
import com.gmail.mooman219.shared.geo.cord.Cord;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;


public class Chunk implements Listener{
    private Block[][] blockMap;
    private Cord cPos;
    public boolean isLoaded = false;

    public Chunk(Cord cPos){
        this(cPos, false);
    }

    public Chunk(Cord cPos, boolean isServerChunk){
        this.cPos = cPos;
        this.isLoaded = isServerChunk;
        blockMap = new Block[World.CHUNK_SIZE][World.CHUNK_SIZE];
        fillChunk(BlockType.GRASS);
    }

    public static Chunk deGeneric(GenericChunk chunk){
        Chunk ret = new Chunk(chunk.cPos, true);
        for(int x = 0; x < World.CHUNK_SIZE; x++){
            for(int y = 0; y < World.CHUNK_SIZE; y++){
                ret.blockMap[x][y] = Block.deGeneric(chunk.blockMap[x][y]);
            }
        }
        return ret;
    }

    /**
     *Makes every block in the chunk a new block of 'type'.
     *@param type - The type of block to fill the chunk with.
     */
    public void fillChunk(BlockType type){
        Cord tPos = Cord.multiply(cPos, World.CHUNK_SIZE);
        for(int x = 0; x < World.CHUNK_SIZE; x++){
            for(int y = 0; y < World.CHUNK_SIZE; y++){
                blockMap[x][y] = new Block(new IntegerCord(x+tPos.getX(), y+tPos.getY()), type);
            }
        }
    }

    /**
     *Gets the block at position (x,y). If the position is out of bounds,
     *it will return null.
     *@param chunkPoint - Position of the block in the chunk. (0 < (x, y) < World.CHUNK_SIZE)
     *@return The block at position (x,y).
     */
    public Block getBlock(Cord cbPos){
        if(cbPos.isWithin(-1, World.CHUNK_SIZE)){
            return blockMap[cbPos.getX()][cbPos.getY()];
        }
        return null;
    }

    /**
     *Sets the block at position (x,y) to 'type'. This will make a new block
     *and remove the old one. If the position is out of bounds, this method
     *will just return.
     *@param b - Block to replace the old one.
     */
    public void setBlock(Block b){
        Cord cbPos = Cord.mod(b.getBlockPos(), World.CHUNK_SIZE);
        if(cbPos.isWithin(-1, World.CHUNK_SIZE)){
            blockMap[cbPos.getX()][cbPos.getY()] = b;
        }
    }

    public Block[][] getBlockMap(){
        return blockMap;
    }

    public void draw() {
        for (int x = 0; x < World.CHUNK_SIZE; x++) {
            for (int y = 0; y < World.CHUNK_SIZE; y++) {
                blockMap[x][y].draw();
            }
        }
    }

    public Cord getChunkPos() {
        return cPos;
    }
}
