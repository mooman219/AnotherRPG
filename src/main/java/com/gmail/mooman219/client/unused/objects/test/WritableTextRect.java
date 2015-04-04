package com.gmail.mooman219.client.unused.objects.test;



import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.MousePressEvent;
import com.gmail.mooman219.client.event.core.TextEvent;
import com.gmail.mooman219.client.event.core.TickEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.unused.objects.TextRectangle;



public class WritableTextRect extends TextRectangle{

    private static final long serialVersionUID = 1L;

    public Color textColor = new Color(255, 255, 255);
    public Color selectedColor = new Color(150, 150, 255);
    public Color renderColor = textColor;

    private boolean isSelected = false;
    private boolean isFlashOn = false;
    private long lastMove = 0;

    public WritableTextRect(String text, int x, int y){
        super(text, x, y);
        lastMove = AnotherRPG.getTime();
    }

    @EventHandler
    public void onRender(WorldRenderEvent e) {
        AnotherRPG.font.drawString(x, y, text, renderColor);
    }

    @EventHandler
    public void onMousePress(MousePressEvent e) {
        if(e.getId() == 0 && contains(x, y)){
            isSelected = !isSelected;
            if(isSelected){
                lastMove = AnotherRPG.getTime();
                renderColor = selectedColor;
            }else{
                if(isFlashOn){
                    setText(getText().substring(0, getText().length() - 1));
                    isFlashOn = false;
                }
                if(getText().length() == 0){
                    setText("EMPTY");
                }
                renderColor = textColor;
            }
        }
    }

    @EventHandler
    public void onKeyPress(KeyPressEvent e) {
        if(isSelected){
            if(e.getKeyID() == 14 && getText().length() > 0){
                if(isFlashOn){
                    setText(getText().substring(0, getText().length() - 2) + "|");
                }else{
                    setText(getText().substring(0, getText().length() - 1));
                }
            }
            if(e.getKeyID() == 28 && !(Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54))){
                isSelected = false;
                if(isFlashOn){
                    setText(getText().substring(0, getText().length() - 1));
                    isFlashOn = false;
                }
                if(getText().length() == 0){
                    setText("EMPTY");
                }
                renderColor = textColor;
            }
        }
    }

    @EventHandler
    public void onTextInput(TextEvent e) {
        if(isSelected){
            if(isFlashOn){
                setText(getText().substring(0, getText().length() - 1) + e.getInput() + "|");
            }else{
                setText(getText() + e.getInput());
            }
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if(isSelected && (e.getCurrentTime() - lastMove > 500)){
            lastMove += 500;
            if(isFlashOn){
                setText(getText().substring(0, getText().length() - 1));
                isFlashOn = false;
            }else{
                setText(getText() + "|");
                isFlashOn = true;
            }
        }
    }
}
