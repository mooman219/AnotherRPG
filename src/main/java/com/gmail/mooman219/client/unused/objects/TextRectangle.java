package com.gmail.mooman219.client.unused.objects;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;


public class TextRectangle extends RenderableRectangle{

    private static final long serialVersionUID = 1L;

    public String text;

    public TextRectangle(String text, int x, int y){
        super(x, y, AnotherRPG.font.getWidth(text), AnotherRPG.font.getHeight(text));
        setText(text);
    }

    public String getText(){
        return text;
    }

    public void setText(String newText){
        text = newText;
        width = AnotherRPG.font.getWidth(text);
        for(String a:text.split("\n")){
            if(width < AnotherRPG.font.getWidth(a)){
                width = AnotherRPG.font.getWidth(a);
            }
        }
        height = AnotherRPG.font.getHeight(text);
    }

    @EventHandler
    public void onRender(WorldRenderEvent e) {
        AnotherRPG.font.drawString(x, y, text);
    }
}
