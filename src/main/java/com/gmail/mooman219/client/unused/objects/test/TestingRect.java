package com.gmail.mooman219.client.unused.objects.test;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.newdawn.slick.Color;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.MousePressEvent;
import com.gmail.mooman219.client.event.core.MouseReleaseEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.unused.objects.RenderableRectangle;




public class TestingRect extends RenderableRectangle{

    private static final long serialVersionUID = 1L;

    public Color rectcolor;
    public Color hoverColor = new Color(200, 200, 200);
    public Color renderColor;

    private boolean isSelected = false;

    public TestingRect(Color rectcolor, int x, int y, int width, int height){
        super(x, y, width, height);
        this.rectcolor = rectcolor;
        renderColor = rectcolor;
    }

    @EventHandler
    public void onRender(WorldRenderEvent e){
        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);

        glBegin(GL_QUADS);
        glColor3f(renderColor.getRed() / 255f, renderColor.getGreen() / 255f, renderColor.getBlue() / 255f);
        glVertex2i(this.x, this.y);
        glVertex2i(this.x + width, this.y);
        glVertex2i(this.x + width, this.y + this.height);
        glVertex2i(this.x, this.y + this.height);
        glEnd();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
    }

    @EventHandler
    public void onMouseMove(MouseMoveEvent e){
        if(this.contains(x, y)){
            renderColor = hoverColor;
        }else{
            renderColor = rectcolor;
        }

        if(isSelected){
            this.x += e.getRealDX();
            this.y -= e.getRealDY();
        }
    }

    @EventHandler
    public void onMousePress(MousePressEvent e) {
        if(e.getId() == 0 && contains(x, y)){
            rectcolor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
            renderColor = rectcolor;

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
