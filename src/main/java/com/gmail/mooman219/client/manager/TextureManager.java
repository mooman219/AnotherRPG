package com.gmail.mooman219.client.manager;

import com.gmail.mooman219.client.enums.BlockType;
import com.gmail.mooman219.client.enums.Direction;
import com.gmail.mooman219.client.enums.EntityType;
import com.gmail.mooman219.client.enums.UIType;
import com.gmail.mooman219.client.texture.ComplexTexture;
import com.gmail.mooman219.client.texture.DirectionTexture;
import com.gmail.mooman219.client.texture.DirectionTextureLoc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager {

    public static DirectionTexture[] blockTextures = new DirectionTexture[BlockType.values().length];
    public static DirectionTexture[] entityTextures = new DirectionTexture[EntityType.values().length];
    public static ComplexTexture[] uiTextures = new ComplexTexture[UIType.values().length];

    public TextureManager() {
    }

    public static void init() {
        loadUITextures();
        loadBlockTextures();
        loadEntityTextures();
    }

    public static void loadBlockTextures() {
        System.out.println("\nLoading Block Textures");
        EnumMap<Direction, ArrayList<Texture>> temp;

        for (int i = 0; i < BlockType.values().length; i++) {
            temp = new EnumMap<Direction, ArrayList<Texture>>(Direction.class);
            for (Direction d : Direction.values()) {
                temp.put(d, new ArrayList<Texture>());
            }
            for (DirectionTextureLoc t : BlockType.values()[i].TEXTURE_LOCS) {
                try {
                    Texture tempTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(t.getTexture()));
                    temp.get(t.getType()).add(tempTexture);
                    //
                    System.out.println("Loaded: " + BlockType.values()[i].toString() + " " + i + " ID: " + tempTexture.getTextureID() + " " + t.getType() + " " + t.getTexture());
                    //
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            blockTextures[i] = new DirectionTexture(temp);
        }
    }

    public static void loadEntityTextures() {
        System.out.println("\nLoading Entity Textures");
        EnumMap<Direction, ArrayList<Texture>> temp;

        for (int i = 0; i < EntityType.values().length; i++) {
            temp = new EnumMap<Direction, ArrayList<Texture>>(Direction.class);
            for (Direction d : Direction.values()) {
                temp.put(d, new ArrayList<Texture>());
            }
            for (DirectionTextureLoc t : EntityType.values()[i].TEXTURE_LOCS) {
                try {
                    Texture tempTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(t.getTexture()));
                    temp.get(t.getType()).add(tempTexture);
                    //
                    System.out.println("Loaded: " + EntityType.values()[i].toString() + " " + i + " ID: " + tempTexture.getTextureID() + " " + t.getType() + " " + t.getTexture());
                    //
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            entityTextures[i] = new DirectionTexture(temp);
        }
    }

    public static void loadUITextures() {
        System.out.println("\nLoading UI Textures");
        ArrayList<Texture> temp;

        for (int i = 0; i < UIType.values().length; i++) {
            temp = new ArrayList<Texture>();
            for (String t : UIType.values()[i].TEXTURE_LOCS) {
                try {
                    Texture tempTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(t));
                    temp.add(tempTexture);
                    //
                    System.out.println("Loaded: " + UIType.values()[i].toString() + " " + i + " ID: " + tempTexture.getTextureID() + " " + t);
                    //
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            uiTextures[i] = new ComplexTexture(temp);
        }
    }

    public static DirectionTexture getBlockTexture(BlockType type) {
        return blockTextures[type.index];
    }

    public static DirectionTexture getEntityTexture(EntityType type) {
        return entityTextures[type.index];
    }
}
