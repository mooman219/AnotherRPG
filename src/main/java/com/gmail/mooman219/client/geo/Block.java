package com.gmail.mooman219.client.geo;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.gmail.mooman219.client.enums.BlockType;
import com.gmail.mooman219.client.enums.Direction;
import com.gmail.mooman219.client.manager.TextureManager;
import com.gmail.mooman219.shared.geo.GenericBlock;
import com.gmail.mooman219.shared.geo.cord.Cord;



public class Block {
    private BlockType type;

    private Direction direction;
    private int currentTexture;

    private Cord rPos;
    private Cord bPos;

    public Block(Cord bPos, BlockType type){
        this.bPos = bPos.clone();
        this.rPos = Cord.multiply(bPos, World.BLOCK_SIZE);
        this.direction = Direction.DOWN;
        this.type = type;
        this.currentTexture = 0;
    }

    public static Block deGeneric(GenericBlock block){
        Block ret = new Block(block.bPos, BlockType.values()[block.blockType]);
        //ret.setDirection(Direction.values()[block.direction]);
        return ret;
    }

    public void draw() {
        bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(rPos.getX(),rPos.getY());
        glTexCoord2f(1, 0);
        glVertex2f(rPos.getX()+World.BLOCK_SIZE, rPos.getY());
        glTexCoord2f(1, 1);
        glVertex2f(rPos.getX()+World.BLOCK_SIZE, rPos.getY()+World.BLOCK_SIZE);
        glTexCoord2f(0, 1);
        glVertex2f(rPos.getX(),rPos.getY()+World.BLOCK_SIZE);
        glEnd();
    }

    public void bind() {
        TextureManager.blockTextures[type.index].getTexture(direction).bind();
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
        currentTexture = 0;
        direction = Direction.DOWN;
    }

    public Cord getRealPos() {
        return rPos;
    }

    public Cord getBlockPos() {
        return bPos;
    }

    public int getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(int currentTexture) {
        this.currentTexture = currentTexture;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
