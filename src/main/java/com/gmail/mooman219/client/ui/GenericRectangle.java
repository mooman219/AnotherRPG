package com.gmail.mooman219.client.ui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Rectangle;

import org.newdawn.slick.Color;

import com.gmail.mooman219.client.enums.UIType;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.manager.TextureManager;



public class GenericRectangle extends Rectangle{

    private static final long serialVersionUID = 1L;

    private Color backgroundColor = new Color(0.1f, 0.1f, 0.1f, 0.2f);

    public GenericRectangle(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    public GenericRectangle(int x, int y, int width, int height, Color backgroundColor){
        super(x, y, width, height);
        this.backgroundColor = backgroundColor;
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
    }

    public void bind() {
        TextureManager.uiTextures[UIType.BLANK.index].getTexture().bind();
    }

    // Gen

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
