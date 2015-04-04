package com.gmail.mooman219.test3D.render;

import com.gmail.mooman219.shared.geo.vec.Vec3f;
import com.gmail.mooman219.test3D.geo.World;
import com.gmail.mooman219.test3D.manager.TextureManager;

public class CubeRenderData extends VoxelRenderData{
    public Vec3f rPos;

    public CubeRenderData(Vec3f rPos){
        this.rPos = rPos;
        for(int i = 0; i < 6; i++){
            renderSide[i] = true;
        }
        calculate();
    }

    public void calculate(){
        float shift = (-1f)/World.BLOCK_RATIO;
        Vec3f[] vert = {
                rPos, (Vec3f)new Vec3f(rPos).add(shift,0,0),
                (Vec3f)new Vec3f(rPos).add(shift,shift,0),(Vec3f)new Vec3f(rPos).add(0,shift,0),
                (Vec3f)new Vec3f(rPos).add(0,0,shift),(Vec3f)new Vec3f(rPos).add(shift,0,shift),
                (Vec3f)new Vec3f(rPos).add(0,shift,shift),(Vec3f)new Vec3f(rPos).add(shift,shift,shift)};
        vertexData[0] = new float[]{//[A]BCD // FROUNT
                vert[0].x, vert[0].y, vert[0].z,
                vert[1].x, vert[1].y, vert[1].z,
                vert[2].x, vert[2].y, vert[2].z,
                vert[3].x, vert[3].y, vert[3].z};
        vertexData[1] = new float[]{//[E]GHF // BACK
                vert[4].x, vert[4].y, vert[4].z,
                vert[6].x, vert[6].y, vert[6].z,
                vert[7].x, vert[7].y, vert[7].z,
                vert[5].x, vert[5].y, vert[5].z};
        vertexData[2] = new float[]{//[F]HCB // LEFT
                vert[5].x, vert[5].y, vert[5].z,
                vert[7].x, vert[7].y, vert[7].z,
                vert[2].x, vert[2].y, vert[2].z,
                vert[1].x, vert[1].y, vert[1].z};
        vertexData[3] = new float[]{//[E]ADG // RIGHT
                vert[4].x, vert[4].y, vert[4].z,
                vert[0].x, vert[0].y, vert[0].z,
                vert[3].x, vert[3].y, vert[3].z,
                vert[6].x, vert[6].y, vert[6].z};
        vertexData[4] = new float[]{//[E]FBA // TOP
                vert[4].x, vert[4].y, vert[4].z,
                vert[5].x, vert[5].y, vert[5].z,
                vert[1].x, vert[1].y, vert[1].z,
                vert[0].x, vert[0].y, vert[0].z};
        vertexData[5] = new float[]{//[G]DCH // BOTTOM
                vert[6].x, vert[6].y, vert[6].z,
                vert[3].x, vert[3].y, vert[3].z,
                vert[2].x, vert[2].y, vert[2].z,
                vert[7].x, vert[7].y, vert[7].z};
        int[] textData = {2, 1, 3, 1, 1, 1};
        for(int i = 0; i < 6; i++){
            colorData[i] = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            textureData[i] = TextureManager.getTexture(textData[i], 0, TEXROT[i]);
            
        }
    }
}
