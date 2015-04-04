package com.gmail.mooman219.client.unused.objects;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Rectangle;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;



public class RenderableRectangle extends Rectangle implements Listener{

    private static final long serialVersionUID = 1L;

    public RenderableRectangle(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    @EventHandler
    public void onRender(WorldRenderEvent e){
        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);

        glBegin(GL_QUADS);
        glColor3f(1f, 1f, 1f);
        glVertex2i(this.x, this.y);
        glVertex2i(this.x + width, this.y);
        glVertex2i(this.x + width, this.y + this.height);
        glVertex2i(this.x, this.y + this.height);
        glEnd();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
    }
}
