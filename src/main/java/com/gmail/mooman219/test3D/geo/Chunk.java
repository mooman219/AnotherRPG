package com.gmail.mooman219.test3D.geo;

import com.gmail.mooman219.shared.geo.vec.Vec2i;
import com.gmail.mooman219.shared.geo.vec.Vec3i;
import com.gmail.mooman219.test3D.render.ChunkRenderer;

public class Chunk {
    public final World world;

    public ChunkRenderer chunkRenderer;
    public Block[][][] blocks;
    public Vec2i cPos;

    public Chunk(World world, Vec2i cPos){
        this.world = world;
        this.cPos = cPos;

        this.blocks = new Block[World.CHUNK_SIZE][World.CHUNK_HEIGHT][World.CHUNK_SIZE];
        this.chunkRenderer = new ChunkRenderer(this);
    }

    public void gen(){
        Vec3i offset = new Vec3i(cPos.getX()*World.CHUNK_SIZE, 0, cPos.getY()*World.CHUNK_SIZE);
        for(int x = 0; x < World.CHUNK_SIZE; x++){
            for(int y = 0; y < World.CHUNK_HEIGHT; y++){
                for(int z = 0; z < World.CHUNK_SIZE; z++){
                    blocks[x][y][z] = new Block(this, (Vec3i) new Vec3i(x,y,z).add(offset));
                }
            }
        }
    }
}
