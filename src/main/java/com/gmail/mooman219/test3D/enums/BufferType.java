package com.gmail.mooman219.test3D.enums;

public enum BufferType {
    VERTEX(0),
    COLOR(1),
    TEXCORD(2);

    public final int index;

    BufferType(int index){
        this.index = index;
    }
}
