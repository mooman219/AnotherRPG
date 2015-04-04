package com.gmail.mooman219.client.ui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.enums.UIType;
import com.gmail.mooman219.client.manager.TextureManager;



public class ChatBox extends Rectangle{

    private static final long serialVersionUID = 1L;

    private Color textC = new Color(1f, 1f, 1f, 1f);

    private ArrayList<String> messages = new ArrayList<String>();
    private int messageMax = 10;
    private int padding = 4;

    private final int HEIGHT = AnotherRPG.HEIGHT - 22 - padding;

    public ChatBox(int padding, int messageMax){
        super(0, 0, 0, 0);
        this.y = HEIGHT;
        this.messageMax = messageMax;
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
        int currentSum = 0;
        for(int i = 0; i < messages.size(); i++){
            currentSum += AnotherRPG.font.getHeight(messages.get(i));
            int yPos = (this.y + this.height) - currentSum;
            AnotherRPG.font.drawString(this.x + (padding * 2), yPos, messages.get(i), textC);
        }
    }

    public void addLine(String message){
        int messageWidth = AnotherRPG.font.getWidth(message) + (padding * 2);
        if(messages.size() >= messageMax){
            int oldWidth = AnotherRPG.font.getWidth(messages.get(messageMax - 1)) + (padding * 2);
            messages.remove(messageMax - 1);
            if(oldWidth >= this.width){
                this.width = 0;
                for(String t : messages){
                    int tempWidth = AnotherRPG.font.getWidth(t) + (padding * 2);
                    if(tempWidth > this.width){
                        this.width = tempWidth;
                    }
                }
            }
        }
        if(messageWidth > this.width){
            this.width = messageWidth;
        }
        messages.add(0, message);
        int yPos = HEIGHT;
        for(String t : messages){
            yPos -= (AnotherRPG.font.getHeight(t));
        }
        this.y = yPos;
        this.height = HEIGHT - yPos;
    }

    public void bind() {
        TextureManager.uiTextures[UIType.BLANK.index].getTexture().bind();
    }
}
