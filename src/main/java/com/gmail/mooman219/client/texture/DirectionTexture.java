package com.gmail.mooman219.client.texture;

import java.util.ArrayList;
import java.util.EnumMap;

import org.newdawn.slick.opengl.Texture;

import com.gmail.mooman219.client.enums.Direction;


public class DirectionTexture extends ComplexTexture{
    private EnumMap<Direction, ArrayList<Texture>> enumTextures;

    public DirectionTexture(EnumMap<Direction, ArrayList<Texture>> textures){
        super(textures);
        enumTextures = textures;
    }

    public Texture[] getTextures(Direction d){
        Texture[] temp = new Texture[enumTextures.get(d).size()];
        return enumTextures.get(d).toArray(temp);
    }

    public Texture getTexture(Direction d){
        if(enumTextures.get(d).size() == 0){
            return getTexture();
        }else{
            return enumTextures.get(d).get(0);
        }
    }
}
