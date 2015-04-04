package com.gmail.mooman219.client.enums;

import com.gmail.mooman219.client.texture.DirectionTextureLoc;

public enum BlockType {

    AIR(0, "0AIR.png"),
    DIRT(1, "1DIRT.png"),
    GRASS(2, "2GRASS.png"),
    STONE(3, "3STONE.png"),
    WATER(4, "4WATER.png");

    public static final String TEXTURE_ROOT = "image/tile/";

    public final int index;
    public final DirectionTextureLoc[] TEXTURE_LOCS;

    BlockType(int index, DirectionTextureLoc... textures) {
        for (int i = 0; i < textures.length; i++) {
            textures[i].setTexture(TEXTURE_ROOT + textures[i]);
            System.out.println("Block Startup: " + textures[i].getType() + " " + textures[i]);
        }

        this.index = index;
        this.TEXTURE_LOCS = textures;
    }

    BlockType(int index, String... textures) {
        TEXTURE_LOCS = new DirectionTextureLoc[textures.length];
        for (int i = 0; i < textures.length; i++) {
            TEXTURE_LOCS[i] = new DirectionTextureLoc(Direction.DOWN, TEXTURE_ROOT + textures[i]);
            System.out.println("Block Startup: " + TEXTURE_LOCS[i].getType() + " " + TEXTURE_LOCS[i].getTexture());
        }

        this.index = index;
    }
}
