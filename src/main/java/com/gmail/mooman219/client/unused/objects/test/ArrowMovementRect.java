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

import java.awt.Color;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.KeyReleaseEvent;
import com.gmail.mooman219.client.event.core.TickEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.unused.objects.RenderableRectangle;



public class ArrowMovementRect extends RenderableRectangle{

    private static final long serialVersionUID = 1L;

    public Color rectcolor;
    public Color renderColor;

    private boolean isMoving = false;
    private long lastMove = 0;
    private int moveSpeed = 5;
    private int moveAmount = 10;

    private int xMove = 0;
    private int yMove = 0;

    public ArrowMovementRect(Color rectcolor, int x, int y, int width, int height){
        super(x, y, width, height);
        this.rectcolor = rectcolor;
        renderColor = rectcolor;
        lastMove = AnotherRPG.getTime();
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
    public void onKeyPress(KeyPressEvent e) {
        switch(e.getKeyID()){
        case 17: // W
            yMove -= 1;
            break;
        case 30: // A
            xMove -= 1;
            break;
        case 31: // S
            yMove += 1;
            break;
        case 32: // D
            xMove += 1;
            break;
        default:
            break;
        }
        isMoving = xMove != 0 || yMove != 0;
    }

    @EventHandler
    public void onKeyRelease(KeyReleaseEvent e) {
        switch(e.getKeyID()){
        case 17: // W
            yMove += 1;
            break;
        case 30: // A
            xMove += 1;
            break;
        case 31: // S
            yMove -= 1;
            break;
        case 32: // D
            xMove -= 1;
            break;
        default:
            break;
        }
        isMoving = xMove != 0 || yMove != 0;
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if(isMoving){
            long deltaMove = e.getCurrentTime() - lastMove;
            if(deltaMove >= moveSpeed){
                lastMove = e.getCurrentTime() + moveSpeed;
                this.x += xMove * moveAmount;
                this.y += yMove * moveAmount;
            }
        }
    }
}
