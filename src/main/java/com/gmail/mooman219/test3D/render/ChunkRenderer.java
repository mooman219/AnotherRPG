package com.gmail.mooman219.test3D.render;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawArrays;

import com.gmail.mooman219.shared.geo.vec.Vec2i;
import com.gmail.mooman219.shared.geo.vec.Vec3i;
import com.gmail.mooman219.test3D.enums.BufferType;
import com.gmail.mooman219.test3D.geo.Chunk;
import com.gmail.mooman219.test3D.geo.World;
import com.gmail.mooman219.test3D.manager.TextureManager;
import com.gmail.mooman219.test3D.util.ArrayUtil;
import com.gmail.mooman219.test3D.util.BufferHelper;

public class ChunkRenderer {
    public final Chunk parent;
    
    public VertexBuffer vertexBuffer = new VertexBuffer();
    public VertexBuffer colorBuffer = new VertexBuffer();
    public VertexBuffer texcoordBuffer = new VertexBuffer();
    
    public ChunkRenderer(Chunk parent){
        this.parent = parent;
    }
    
    public void refreshBlocks(){
        calculateVisability();
        calculateVertexBuffers();
    }
    
    public void calculateVisability(){
        Vec2i cPos = parent.cPos;
        Vec3i offset = new Vec3i(cPos.getX()*World.CHUNK_SIZE, 0, cPos.getY()*World.CHUNK_SIZE);
        for(int x = 0; x<World.CHUNK_SIZE; x++){
            for(int y = 0; y<World.CHUNK_HEIGHT; y++){
                for(int z = 0; z<World.CHUNK_SIZE; z++){
                    if(parent.blocks[x][y][z] == null){
                        continue;
                    }
                    parent.blocks[x][y][z].blockRenderer.renderSide = parent.world.getRenderableBlockFaces((Vec3i) new Vec3i(x,y,z).add(offset));
                }
            }
        }
    }
    
    public void calculateVertexBuffers(){
        float[] vbl = new float[0];
        float[] cbl = new float[0];
        float[] tbl = new float[0];
        for(int x = 0; x<World.CHUNK_SIZE; x++){
            for(int y = 0; y<World.CHUNK_HEIGHT; y++){
                for(int z = 0; z<World.CHUNK_SIZE; z++){
                    if(parent.blocks[x][y][z] == null){
                        continue;
                    }
                    for(int i = 0; i < 6; i++){
                        if(parent.blocks[x][y][z].blockRenderer.renderSide[i]){
                            vbl = ArrayUtil.combine(vbl, parent.blocks[x][y][z].blockRenderer.vertexData[i]);
                            cbl = ArrayUtil.combine(cbl, parent.blocks[x][y][z].blockRenderer.colorData[i]);
                            tbl = ArrayUtil.combine(tbl, parent.blocks[x][y][z].blockRenderer.textureData[i]);
                        }
                    }
                }
            }
        }
        vertexBuffer.flush(BufferType.VERTEX, BufferHelper.toFloatBuffer(vbl));
        colorBuffer.flush(BufferType.COLOR, BufferHelper.toFloatBuffer(cbl));
        texcoordBuffer.flush(BufferType.TEXCORD, BufferHelper.toFloatBuffer(tbl));
    }

    public void render(){
        glBindTexture(GL_TEXTURE_2D, TextureManager.sheetId);
        vertexBuffer.call();
        colorBuffer.call();
        texcoordBuffer.call();
        glDrawArrays(GL_QUADS, 0, vertexBuffer.getSize()/3);
    }
}
