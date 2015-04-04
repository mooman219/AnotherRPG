package com.gmail.mooman219.client.ui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Rectangle;

import com.gmail.mooman219.client.enums.UIType;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.manager.TextureManager;



public class GenericCheckBox extends Rectangle{

    private static final long serialVersionUID = 1L;

    private float alpha = 0.6f;

    private boolean isSelected = true;

    public GenericCheckBox(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    public void onMouseMove(MouseMoveEvent e){
        if(this.contains(e.getRealX(), e.getRealY())){
            alpha = 1f;
        }else{
            alpha = 0.6f;
        }
    }

    public void onRender(UIRenderEvent e) {
        bind();
        glColor4f(1f, 1f, 1f, alpha);
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
        //TextureManager.blockTextures[isSelected ? 1 : 2].getTexture().bind();
        TextureManager.uiTextures[UIType.CHECKBOX.index].getTexture(isSelected ? 1 : 0).bind();
    }

    // Gen

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
