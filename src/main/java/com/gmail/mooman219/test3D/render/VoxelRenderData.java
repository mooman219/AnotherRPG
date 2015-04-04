package com.gmail.mooman219.test3D.render;

public abstract class VoxelRenderData {
	public static final int[] TEXROT = {3, 0, 0, 3, 1, 0};
    public boolean[] renderSide;
    public float[][] vertexData;
    public float[][] colorData;
    public float[][] textureData;
    
    public VoxelRenderData(){
        this.renderSide = new boolean[6];
        this.vertexData = new float[6][];
        this.colorData = new float[6][];
        this.textureData = new float[6][];
    }
    
    public abstract void calculate();
}
