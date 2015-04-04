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
import com.gmail.mooman219.client.event.core.TickEvent;
import com.gmail.mooman219.client.manager.TextureManager;



public class ChatInput extends Rectangle{

    private static final long serialVersionUID = 1L;

    private Color textC = new Color(1f, 1f, 1f, 1f);

    private String preLine = "> ";
    private String currentLine = "";

    private boolean isTicking = false;
    private String tickString = "|";
    private int tickInterval = 600;
    private long targetTick = 0l;

    private int maxCharacters = 60;
    private int padding = 4;

    private final int HEIGHT = AnotherRPG.HEIGHT - 16 - padding;

    public ChatInput(int padding, int maxCharacters){
        super(0, 0, 0, 0);
        this.height = 16;
        this.y = HEIGHT;
        this.maxCharacters = maxCharacters;
        this.padding = padding;
    }

    public void draw() {
        bind();
        glColor4f(0f, 0f, 0f, 0.3f);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(this.x + padding, this.y);
        glTexCoord2f(1, 0);
        glVertex2i(this.x + width + padding, this.y);
        glTexCoord2f(1, 1);
        glVertex2i(this.x + width + padding, this.y + this.height + padding);
        glTexCoord2f(0, 1);
        glVertex2i(this.x + padding, this.y + this.height + padding);
        glEnd();
        glColor4f(1f, 1f, 1f, 1f);
        renderText();
    }

    private void renderText(){
        int yPos = (this.y + this.height) - AnotherRPG.font.getHeight(preLine + currentLine) - padding;
        AnotherRPG.font.drawString(this.x + (padding * 2), yPos, preLine + currentLine + (isTicking ? tickString : ""), textC);
    }

    public void addText(String text){
        if(currentLine.length() + text.length() > maxCharacters){
            return;
        }
        currentLine = currentLine + text;

        this.width = AnotherRPG.font.getWidth(preLine + currentLine) + (padding * 2);

        this.y = AnotherRPG.HEIGHT - AnotherRPG.font.getHeight(preLine + currentLine);
        this.height = AnotherRPG.HEIGHT - this.y;
    }

    public void backspace(){
        if(currentLine.length() == 0){
            return;
        }
        currentLine = currentLine.substring(0, currentLine.length() - 1);
        addText("");
    }

    public String enter(){
        String temp = currentLine;
        currentLine = "";
        addText("");
        isTicking = false;
        return temp;
    }

    public void bind() {
        TextureManager.uiTextures[UIType.BLANK.index].getTexture().bind();
    }

    public void tick(TickEvent e){
        if(e.getCurrentTime() >= targetTick){
            targetTick = e.getCurrentTime() + tickInterval;
            isTicking = !isTicking;
        }
    }
}
