package com.gmail.mooman219.client.unused.entity;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.gmail.mooman219.client.enums.Direction;
import com.gmail.mooman219.client.enums.EntityType;
import com.gmail.mooman219.client.geo.World;
import com.gmail.mooman219.client.manager.TextureManager;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;


public abstract class Entity{
    private EntityType type;

    private Direction faceing;
    private int currentTexture;

    private IntegerCord realPos;
    private IntegerCord blockPos;
    private IntegerCord lastBlockPos;

    public Entity(EntityType type, IntegerCord blockPos){
        this.type = type;
        this.faceing = Direction.DOWN;
        this.currentTexture = 0;
        this.blockPos = blockPos;
        //WorldManager.currentWorld.addEntity(this);
        setBlockPos(blockPos);
    }

    public void draw() {
        bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(getRealPos().x, getRealPos().y);
        glTexCoord2f(1, 0);
        glVertex2f(getRealPos().x + World.BLOCK_SIZE, getRealPos().y);
        glTexCoord2f(1, 1);
        glVertex2f(getRealPos().x + World.BLOCK_SIZE, getRealPos().y + World.BLOCK_SIZE);
        glTexCoord2f(0, 1);
        glVertex2f(getRealPos().x, getRealPos().y + World.BLOCK_SIZE);
        glEnd();
    }

    public void bind() {
        TextureManager.entityTextures[type.index].getTexture(faceing).bind();
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
        faceing = Direction.DOWN;
        currentTexture = 0;
    }

    public int getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(int currentTexture) {
        this.currentTexture = currentTexture;
    }

    public IntegerCord getRealPos() {
        return realPos;
    }

    public IntegerCord getBlockPos() {
        return blockPos;
    }

    public Direction getFaceing() {
        return faceing;
    }

    public void setFaceing(Direction faceing) {
        this.faceing = faceing;
    }

    public IntegerCord getLastBlockPos() {
        return lastBlockPos;
    }

    public void setBlockPos(IntegerCord blockPos) {
        this.lastBlockPos = this.blockPos;
        this.blockPos = blockPos;
        this.realPos = new IntegerCord(blockPos).multiply(World.BLOCK_SIZE);
        //if(IntegerCord.toChunkCord(this.blockPos) != IntegerCord.toChunkCord(this.lastBlockPos)){
        //WorldManager.currentWorld.getChunk(IntegerCord.toChunkCord(this.blockPos)).updateEntity(this);
        //WorldManager.currentWorld.getChunk(IntegerCord.toChunkCord(this.lastBlockPos)).updateEntity(this);
        //}
    }
}
