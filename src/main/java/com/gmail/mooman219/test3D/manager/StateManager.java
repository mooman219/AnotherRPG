package com.gmail.mooman219.test3D.manager;

import com.gmail.mooman219.test3D.enums.GameState;

public class StateManager {
    public static GameState currentState = GameState.MAINMENU;

    public StateManager(){}

    public static GameState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(GameState currentState) {
        StateManager.currentState = currentState;
    }

    public static boolean isCurrentState(GameState state){
        return (state == currentState || state == GameState.GLOBAL);
    }
}