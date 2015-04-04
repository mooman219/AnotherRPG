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
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.manager.TextureManager;



public class GenericInputField extends Rectangle{

    private static final long serialVersionUID = 1L;

    private Color backgroundColor = new Color(1f, 1f, 1f, 0.8f);
    private Color textColor = new Color(0.7f, 0.7f, 0.7f, 1f);

    private String preLine = "";
    private String currentLine = "";
    private String description = "";

    private boolean isTicking = false;
    private String tickString = "|";
    private int tickInterval = 600;
    private long targetTick = 0l;

    private boolean isEnabled = true;
    private boolean isSelected = false;

    private int padding = 4;

    public GenericInputField(int x, int y, int width, int height, int padding){
        super(x, y, width, height);
        this.padding = padding;
    }

    public GenericInputField(int x, int y, int width, int height, int padding, Color textColor, Color backgroundColor){
        super(x, y, width, height);
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.padding = padding;
    }

    public void tick(TickEvent e){
        if(e.getCurrentTime() >= targetTick){
            targetTick = e.getCurrentTime() + tickInterval;
            isTicking = !isTicking;
        }
    }

    public void onRender(UIRenderEvent e) {
        bind();
        glColor4f(backgroundColor.r, backgroundColor.g, backgroundColor.b, isSelected ? backgroundColor.a : 0.5f);
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
        String finalRender = preLine + currentLine;
        if(isSelected){
            finalRender += isTicking ? tickString : "";
        }
        if(currentLine.length() == 0){
            finalRender += description;
        }
        AnotherRPG.font.drawString(this.x + (padding * 2), this.y + padding, finalRender, textColor);
    }

    public void bind() {
        TextureManager.uiTextures[isEnabled ? UIType.BLANK.index : UIType.GREYBORDER.index].getTexture().bind();
    }

    public void addText(String text){
        if(!isEnabled){
            return;
        }
        if(AnotherRPG.font.getWidth(currentLine + text) + (padding * 2) > this.width){
            return;
        }
        currentLine = currentLine + text;
    }

    public void backspace(){
        if(!isEnabled){
            return;
        }
        if(currentLine.length() == 0){
            return;
        }
        currentLine = currentLine.substring(0, currentLine.length() - 1);
    }

    // Gen

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    public String getText(){
        return currentLine;
    }

    public void setText(String newText){
        currentLine = "";
        addText(newText);
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

    public String getPreLine() {
        return preLine;
    }

    public void setPreLine(String preLine) {
        this.preLine = preLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTickString() {
        return tickString;
    }

    public void setTickString(String tickString) {
        this.tickString = tickString;
    }

    public int getTickInterval() {
        return tickInterval;
    }

    public void setTickInterval(int tickInterval) {
        this.tickInterval = tickInterval;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public boolean isTicking() {
        return isTicking;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}