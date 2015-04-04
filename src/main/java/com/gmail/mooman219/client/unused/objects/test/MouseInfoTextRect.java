package com.gmail.mooman219.client.unused.objects.test;


import org.newdawn.slick.Color;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.MousePressEvent;
import com.gmail.mooman219.client.event.core.MouseReleaseEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.unused.objects.TextRectangle;




public class MouseInfoTextRect extends TextRectangle{

    private static final long serialVersionUID = 1L;

    public Color textolor = new Color(255, 255, 255);
    public Color hoverColor = new Color(200, 200, 200);
    public Color renderColor;

    private boolean isSelected = false;

    public MouseInfoTextRect(int x, int y){
        super("", x, y);
        renderColor = textolor;
    }

    @EventHandler
    public void onRender(WorldRenderEvent e) {
        AnotherRPG.font.drawString(x, y, text, renderColor);
    }

    @EventHandler
    public void onMouseMove(MouseMoveEvent e) {
        if(contains(x, y)){
            renderColor = hoverColor;
        }else{
            renderColor = textolor;
        }

        if(isSelected){
            this.x += e.getRealDX();
            this.y -= e.getRealDY();
        }

        text = "MousePos: x: " + x + " y: " + y;
        width = AnotherRPG.font.getWidth(text);
        height = AnotherRPG.font.getHeight(text);
    }

    @EventHandler
    public void onMousePress(MousePressEvent e) {
        if(contains(x, y) && e.getId() == 0){
            isSelected = true;
        }
    }

    @EventHandler
    public void onMouseRelease(MouseReleaseEvent e) {
        if(isSelected && e.getId() == 0){
            isSelected = false;
        }
    }
}
