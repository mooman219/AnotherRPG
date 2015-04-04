package com.gmail.mooman219.client.handler;


import org.lwjgl.input.Keyboard;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.TextEvent;
import com.gmail.mooman219.client.manager.EventManager;
import com.gmail.mooman219.shared.Order;



public class TextFormatHandler implements Listener{

    public TextFormatHandler(){}

    @EventHandler(order = Order.MONITOR)
    public void onKeyPress(KeyPressEvent e){
        switch(e.getKeyID()){
        case 42:
            return;
        case 54:
            return;
        case 14:
            return;
        case 1:
            return;
        }
        if(Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)){
            switch(e.getKeyID()){
            case 2:
                EventManager.callEvent(new TextEvent("!"));
                return;
            case 3:
                EventManager.callEvent(new TextEvent("@"));
                return;
            case 4:
                EventManager.callEvent(new TextEvent("#"));
                return;
            case 5:
                EventManager.callEvent(new TextEvent("$"));
                return;
            case 6:
                EventManager.callEvent(new TextEvent("%"));
                return;
            case 7:
                EventManager.callEvent(new TextEvent("^"));
                return;
            case 8:
                EventManager.callEvent(new TextEvent("&"));
                return;
            case 9:
                EventManager.callEvent(new TextEvent("*"));
                return;
            case 10:
                EventManager.callEvent(new TextEvent("("));
                return;
            case 11:
                EventManager.callEvent(new TextEvent(")"));
                return;
            case 12:
                EventManager.callEvent(new TextEvent("_"));
                return;
            case 13:
                EventManager.callEvent(new TextEvent("+"));
                return;
            case 57:
                EventManager.callEvent(new TextEvent("  "));
                return;
            case 51:
                EventManager.callEvent(new TextEvent("<"));
                return;
            case 52:
                EventManager.callEvent(new TextEvent(">"));
                return;
            case 53:
                EventManager.callEvent(new TextEvent("?"));
                return;
            case 39:
                EventManager.callEvent(new TextEvent(":"));
                return;
            case 40:
                EventManager.callEvent(new TextEvent("" + '"')); //lol, pro workaround for '"'. Casting is to mainstream
                return;
            case 26:
                EventManager.callEvent(new TextEvent("{"));
                return;
            case 27:
                EventManager.callEvent(new TextEvent("}"));
                return;
            case 43:
                EventManager.callEvent(new TextEvent("|"));
                return;
            case 41:
                EventManager.callEvent(new TextEvent("~"));
                return;
            case 28:
                EventManager.callEvent(new TextEvent("\n"));
                return;
            case 79:
                EventManager.callEvent(new TextEvent("1"));
                return;
            case 80:
                EventManager.callEvent(new TextEvent("2"));
                return;
            case 81:
                EventManager.callEvent(new TextEvent("3"));
                return;
            case 75:
                EventManager.callEvent(new TextEvent("4"));
                return;
            case 76:
                EventManager.callEvent(new TextEvent("5"));
                return;
            case 77:
                EventManager.callEvent(new TextEvent("6"));
                return;
            case 71:
                EventManager.callEvent(new TextEvent("7"));
                return;
            case 72:
                EventManager.callEvent(new TextEvent("8"));
                return;
            case 73:
                EventManager.callEvent(new TextEvent("9"));
                return;
            default:
                EventManager.callEvent(new TextEvent(Keyboard.getKeyName(e.getKeyID())));
                return;
            }
        }else{
            switch(e.getKeyID()){
            case 12:
                EventManager.callEvent(new TextEvent("-"));
                return;
            case 13:
                EventManager.callEvent(new TextEvent("="));
                return;
            case 57:
                EventManager.callEvent(new TextEvent(" "));
                return;
            case 51:
                EventManager.callEvent(new TextEvent(","));
                return;
            case 52:
                EventManager.callEvent(new TextEvent("."));
                return;
            case 53:
                EventManager.callEvent(new TextEvent("/"));
                return;
            case 39:
                EventManager.callEvent(new TextEvent(";"));
                return;
            case 40:
                EventManager.callEvent(new TextEvent("'"));
                return;
            case 26:
                EventManager.callEvent(new TextEvent("["));
                return;
            case 27:
                EventManager.callEvent(new TextEvent("]"));
                return;
            case 43:
                EventManager.callEvent(new TextEvent("\\")); //Pro regex for '\'
                return;
            case 41:
                EventManager.callEvent(new TextEvent("`"));
                return;
            case 79:
                EventManager.callEvent(new TextEvent("1"));
                return;
            case 80:
                EventManager.callEvent(new TextEvent("2"));
                return;
            case 81:
                EventManager.callEvent(new TextEvent("3"));
                return;
            case 75:
                EventManager.callEvent(new TextEvent("4"));
                return;
            case 76:
                EventManager.callEvent(new TextEvent("5"));
                return;
            case 77:
                EventManager.callEvent(new TextEvent("6"));
                return;
            case 71:
                EventManager.callEvent(new TextEvent("7"));
                return;
            case 72:
                EventManager.callEvent(new TextEvent("8"));
                return;
            case 73:
                EventManager.callEvent(new TextEvent("9"));
                return;
            case 28:
                return;
            default:
                EventManager.callEvent(new TextEvent(Keyboard.getKeyName(e.getKeyID()).toLowerCase()));
                return;
            }
        }
    }
}
