package com.gmail.mooman219.client.texture;

import com.gmail.mooman219.client.enums.Direction;

public class DirectionTextureLoc {
    private Direction type;
    private String texture;

    public DirectionTextureLoc(Direction t, String loc){
        type = t;
        texture = loc;
    }

    public Direction getType() {
        return type;
    }

    public void setType(Direction type) {
        this.type = type;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String toString(){
        return texture;
    }
}
