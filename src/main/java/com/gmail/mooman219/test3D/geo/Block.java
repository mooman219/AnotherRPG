package com.gmail.mooman219.test3D.geo;

import com.gmail.mooman219.shared.geo.vec.Vec3f;
import com.gmail.mooman219.shared.geo.vec.Vec3i;
import com.gmail.mooman219.test3D.render.CubeRenderData;

public class Block {
    public final Chunk chunk;
    
    public CubeRenderData blockRenderer;
    public Vec3f rPos;
    public Vec3i bPos;
    
    public Block(Chunk chunk, Vec3i bPos){
        this.chunk = chunk;
        this.bPos = bPos;
        this.rPos = (Vec3f) new Vec3f(bPos).divide(World.BLOCK_RATIO,World.BLOCK_RATIO,World.BLOCK_RATIO);
        this.blockRenderer = new CubeRenderData(rPos);
    }
}