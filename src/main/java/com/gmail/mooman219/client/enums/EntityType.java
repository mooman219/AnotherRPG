package com.gmail.mooman219.client.enums;

import com.gmail.mooman219.client.texture.DirectionTextureLoc;

public enum EntityType {

    CHAR(0, new DirectionTextureLoc(Direction.UP, "0CHAR_TOP.png"), new DirectionTextureLoc(Direction.DOWN, "0CHAR_BOTTOM.png"), new DirectionTextureLoc(Direction.LEFT, "0CHAR_LEFT.png"), new DirectionTextureLoc(Direction.RIGHT, "0CHAR_RIGHT.png"));

    public static final String TEXTURE_ROOT = "image/entity/";

    public final int index;
    public final DirectionTextureLoc[] TEXTURE_LOCS;

    EntityType(int index, DirectionTextureLoc... textures) {
        for (int i = 0; i < textures.length; i++) {
            textures[i].setTexture(TEXTURE_ROOT + textures[i]);
            System.out.println("Entity Startup: " + textures[i].getType() + " " + textures[i]);
        }

        this.index = index;
        this.TEXTURE_LOCS = textures;
    }
}
