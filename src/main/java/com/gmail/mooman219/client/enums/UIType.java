package com.gmail.mooman219.client.enums;

public enum UIType {

    BLANK(0, "0BLANK.png"),
    CHECKBOX(1, "1CHECKBOX0.png", "1CHECKBOX1.png"),
    GREYBORDER(2, "2GREYBORDER.png");

    public static final String TEXTURE_ROOT = "image/ui/";

    public final int index;
    public final String[] TEXTURE_LOCS;

    UIType(int index, String... textures) {
        for (int i = 0; i < textures.length; i++) {
            textures[i] = TEXTURE_ROOT + textures[i];
            System.out.println("UI Startup: " + textures[i]);
        }

        this.index = index;
        this.TEXTURE_LOCS = textures;
    }
}
