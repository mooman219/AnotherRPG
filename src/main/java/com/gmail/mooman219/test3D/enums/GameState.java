package com.gmail.mooman219.test3D.enums;

public enum GameState {
    GLOBAL(0),
    MAINMENU(1),
    INGAME(2);

    public final int index;

    GameState(int index){
        this.index = index;
    }
}
