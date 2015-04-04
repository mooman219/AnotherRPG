package com.gmail.mooman219.client.ui;

import static org.lwjgl.opengl.GL11.glColor4f;

import org.newdawn.slick.Color;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;

public class GenericLabel{

    private int x = 0;
    private int y = 0;

    private Color textColor = new Color(1f, 1f, 1f, 1f);

    private String text = "";

    public GenericLabel(int x, int y, String text){
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void onRender(UIRenderEvent e) {
        glColor4f(1f, 1f, 1f, 1f);
        AnotherRPG.font.drawString(x, y, text, textColor);
        glColor4f(1f, 1f, 1f, 1f);
    }

    // Gen

    public String getText(){
        return text;
    }

    public void setText(String newText){
        text = newText;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
