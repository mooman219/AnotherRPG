package com.gmail.mooman219.client.texture;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

public class ComplexTexture {
    private Texture[] textures; 
    
    public ComplexTexture(Texture... textures){
        this.textures = textures;
    }
    
    public ComplexTexture(Map<?, ArrayList<Texture>> textures){
        List<Texture> temp = new ArrayList<Texture>();
        for(ArrayList<Texture> t : textures.values()){
            temp.addAll(t);
        }
        
        this.textures = new Texture[temp.size()];
        for(int i = 0; i < this.textures.length; i++){
            this.textures[i] = temp.get(i);
        }
    }
    
    public ComplexTexture(List<Texture> textures){
        this.textures = new Texture[textures.size()];
        for(int i = 0; i < this.textures.length; i++){
            this.textures[i] = textures.get(i);
        }
    }
    
    public Texture[] getTextures(){
        return textures;
    }
    
    public Texture getTexture(){
        return textures[0];
    }
    
    public Texture getTexture(int position){
        return textures[position];
    }
}
