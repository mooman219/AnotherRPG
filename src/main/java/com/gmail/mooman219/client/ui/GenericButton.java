package com.gmail.mooman219.client.ui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Rectangle;

import org.newdawn.slick.Color;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.enums.UIType;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.manager.TextureManager;



public class GenericButton extends Rectangle{

    private static final long serialVersionUID = 1L;

    private Color backgroundColor = new Color(1f, 1f, 1f, 0.8f);
    private Color textColor = new Color(0.7f, 0.7f, 0.7f, 1f);

    private String text = "";
    private int textPosX = 0;
    private int textPosY = 0;

    public GenericButton(int x, int y, int width, int height, String text){
        super(x, y, width, height);
        setText(text);
    }

    public GenericButton(int x, int y, int width, int height, String text, Color textColor, Color backgroundColor){
        super(x, y, width, height);
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        setText(text);
    }

    public void onMouseMove(MouseMoveEvent e){
        if(this.contains(e.getRealX(), e.getRealY())){
            backgroundColor.a = 1f;
        }else{
            backgroundColor.a = 0.4f;
        }
    }

    public void onRender(UIRenderEvent e) {
        bind();
        glColor4f(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(this.x, this.y);
        glTexCoord2f(1, 0);
        glVertex2i(this.x + width, this.y);
        glTexCoord2f(1, 1);
        glVertex2i(this.x + width, this.y + this.height);
        glTexCoord2f(0, 1);
        glVertex2i(this.x, this.y + this.height);
        glEnd();
        glColor4f(1f, 1f, 1f, 1f);
        renderText();
    }

    private void renderText(){
        AnotherRPG.font.drawString(textPosX, textPosY, text, textColor);
    }

    public void bind() {
        TextureManager.uiTextures[UIType.BLANK.index].getTexture().bind();
    }

    // Gen

    public String getText(){
        return text;
    }

    public void setText(String newText){
        text = newText;
        textPosX = ((x + (x + width)) / 2) - (AnotherRPG.font.getWidth(text) / 2);
        textPosY = ((y + (y + height)) / 2) - (AnotherRPG.font.getHeight(text) / 2);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
