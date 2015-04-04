package com.gmail.mooman219.client.handler;

import org.lwjgl.input.Mouse;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.game.MouseBlockMoveEvent;
import com.gmail.mooman219.shared.Order;

public class TranslationHandler implements Listener{

    public TranslationHandler(){}

    @EventHandler(order = Order.INITIAL)
    public void onMouseMove(MouseMoveEvent e){
        //This if statement makes sure the dx and dy don't 'freakout' when the mouse moves out of the frame.
        //It causes some issues but overall improves stability.
        if(!(Mouse.getX() > 0 && Mouse.getX() < (AnotherRPG.WIDTH - 1)) || !(Mouse.getY() > 0 && Mouse.getY() < (AnotherRPG.HEIGHT - 1))){
            e.setCancelled(true);
            return;
        }
        /**/
        if(Mouse.isButtonDown(1)){
            AnotherRPG.translate_x += e.getRealDX();
            AnotherRPG.translate_y -= e.getRealDY();
            e.setRealX(e.getRealX());
            e.setRealY(e.getRealY());
            e.setRealDX(0);
            e.setRealDY(0);
        }
        /**/
    }

    @EventHandler(order = Order.INITIAL)
    public void onMouseBlockMove(MouseBlockMoveEvent e){
        //This if statement makes sure the dx and dy don't 'freakout' when the mouse moves out of the frame.
        //It causes some issues but overall improves stability.
        if(!(Mouse.getX() > 0 && Mouse.getX() < (AnotherRPG.WIDTH - 1)) || !(Mouse.getY() > 0 && Mouse.getY() < (AnotherRPG.HEIGHT - 1))){
            e.setCancelled(true);
            return;
        }
    }
}
