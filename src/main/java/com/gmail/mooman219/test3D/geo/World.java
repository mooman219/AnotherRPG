package com.gmail.mooman219.test3D.geo;

import com.gmail.mooman219.shared.geo.vec.Vec2i;
import com.gmail.mooman219.shared.geo.vec.Vec3;
import com.gmail.mooman219.shared.geo.vec.Vec3i;

public class World {
    public static final int WORLD_SIZE = 8;
    public static final int CHUNK_SIZE = 8;
    public static final int CHUNK_HEIGHT = 2;
    public static final int BLOCK_RATIO = 2;
    
    public Chunk[][] chunks = new Chunk[WORLD_SIZE][WORLD_SIZE];
    
    public void gen(){
        int blocks = (WORLD_SIZE*WORLD_SIZE)*(CHUNK_SIZE*CHUNK_SIZE*CHUNK_HEIGHT);
        System.out.println("World gen: "+blocks+" blocks / "+(blocks*6)+" faces");
        for(int x = 0; x < WORLD_SIZE; x++){
            for(int z = 0; z < WORLD_SIZE; z++){
                chunks[x][z] = new Chunk(this, new Vec2i(x,z));
                chunks[x][z].gen();
            }
        }
        refreshBlocks();
    }
    
    public void refreshBlocks(){
        for(int x = 0; x < WORLD_SIZE; x++){
            for(int z = 0; z < WORLD_SIZE; z++){
                chunks[x][z].chunkRenderer.refreshBlocks();
            }
        }
    }
    
    public void renderAll(){
        for(int x = 0; x < WORLD_SIZE; x++){
            for(int z = 0; z < WORLD_SIZE; z++){
                chunks[x][z].chunkRenderer.render();
            }
        }
    }
    
    public boolean[] getRenderableBlockFaces(Vec3i bPos){
        boolean[] ret = new boolean[6];
        Block[] blocks = getAdjacent(bPos);
        for(int i = 0; i < 6; i++){
            ret[i] = blocks[i] == null;
        }
        return ret;
    }
    
    public Block getBlock(Vec3i bPos){
        Vec2i cPos = new Vec2i(bPos.x/CHUNK_SIZE, bPos.z/CHUNK_SIZE);
        Vec3i cbPos = (Vec3i) Vec3.mod(bPos, CHUNK_SIZE, -1, CHUNK_SIZE);
        if(cPos.isWithin(-1, WORLD_SIZE) && (cbPos.x > -1 && cbPos.y > -1 && cbPos.z > -1 && cbPos.x < CHUNK_SIZE && cbPos.y < CHUNK_HEIGHT && cbPos.z < CHUNK_SIZE) ){
            return chunks[cPos.x][cPos.y].blocks[cbPos.x][cbPos.y][cbPos.z];
        }else{
        	return null;
        }
    }
    
    public Block[] getAdjacent(Vec3i bPos){
        Block[] ret = new Block[6];
        ret[0] = getBlock((Vec3i) Vec3.add(bPos, 0, 0, 1));
        ret[1] = getBlock((Vec3i) Vec3.add(bPos, 0, 0, -1));
        ret[2] = getBlock((Vec3i) Vec3.add(bPos, -1, 0, 0));
        ret[3] = getBlock((Vec3i) Vec3.add(bPos, 1, 0, 0));
        ret[4] = getBlock((Vec3i) Vec3.add(bPos, 0, 1, 0));
        ret[5] = getBlock((Vec3i) Vec3.add(bPos, 0, -1, 0));
        return ret;
    }
}
