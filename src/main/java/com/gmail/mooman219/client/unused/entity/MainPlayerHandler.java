package com.gmail.mooman219.client.unused.entity;

import java.util.ArrayList;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.enums.Direction;
import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.KeyReleaseEvent;
import com.gmail.mooman219.client.event.core.TickEvent;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;


public class MainPlayerHandler implements Listener{
    public Player mainPlayer;

    private int tickInterval = 100;
    private long targetTick = 0l;
    private ArrayList<Direction> keyList = new ArrayList<Direction>();

    public MainPlayerHandler(){
        mainPlayer = new Player(new IntegerCord(1,1));
    }

    /**
    KEY(P) ID: 203 N: LEFT
    KEY(P) ID: 200 N: UP
    KEY(P) ID: 205 N: RIGHT
    KEY(P) ID: 208 N: DOWN

    "0CHAR_TOP.png", "0CHAR_BOTTOM.png", "0CHAR_LEFT.png", "0CHAR_RIGHT.png"

    setCurrentTexture(int X);
    TOP: 0
    BOTTOM: 1
    LEFT: 2
    RIGHT: 3
    /**/
    @EventHandler(state = GameState.INGAME)
    public void onTickEvent(TickEvent e){
        if(e.getCurrentTime() >= targetTick){
            if(!(keyList.size() > 0)){
                return;
            }
            if(e.getCurrentTime() - targetTick > 150){
                targetTick = e.getCurrentTime() + tickInterval;
                return;
            }
            targetTick = e.getCurrentTime() + tickInterval;
            if(mainPlayer.getFaceing() != keyList.get(0)){
                mainPlayer.setFaceing(keyList.get(0));
            }
            mainPlayer.move();
        }
    }

    @EventHandler(state = GameState.INGAME)
    public void onKeyPress(KeyPressEvent e){
        switch(e.getKeyID()){
        case 200: // UP
            keyList.add(0, Direction.UP);
            if(mainPlayer.getFaceing() != Direction.UP){
                mainPlayer.setFaceing(Direction.UP);
            }
            break;
        case 203: // LEFT
            keyList.add(0, Direction.LEFT);
            if(mainPlayer.getFaceing() != Direction.LEFT){
                mainPlayer.setFaceing(Direction.LEFT);
            }
            break;
        case 205: // RIGHT
            keyList.add(0, Direction.RIGHT);
            if(mainPlayer.getFaceing() != Direction.RIGHT){
                mainPlayer.setFaceing(Direction.RIGHT);
            }
            break;
        case 208: // DOWN
            keyList.add(0, Direction.DOWN);
            if(mainPlayer.getFaceing() != Direction.DOWN){
                mainPlayer.setFaceing(Direction.DOWN);
            }
            break;
        }
    }

    @EventHandler(state = GameState.INGAME)
    public void onKeyKRelease(KeyReleaseEvent e){
        switch(e.getKeyID()){
        case 200: // UP
            keyList.remove(Direction.UP);
            break;
        case 203: // LEFT
            keyList.remove(Direction.LEFT);
            break;
        case 205: // RIGHT
            keyList.remove(Direction.RIGHT);
            break;
        case 208: // DOWN
            keyList.remove(Direction.DOWN);
            break;
        }
    }
}