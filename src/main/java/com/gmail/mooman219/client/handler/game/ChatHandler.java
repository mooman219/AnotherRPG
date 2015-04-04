package com.gmail.mooman219.client.handler.game;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.TextEvent;
import com.gmail.mooman219.client.event.core.TickEvent;
import com.gmail.mooman219.client.event.core.game.ChatEvent;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.manager.ClientPacketManager;
import com.gmail.mooman219.client.ui.ChatBox;
import com.gmail.mooman219.client.ui.ChatInput;
import com.gmail.mooman219.shared.Order;
import com.gmail.mooman219.shared.packet.Packet1Chat;

public class ChatHandler implements Listener{
    private ChatBox chatBox = new ChatBox(4, 11);
    private ChatInput chatInput = new ChatInput(4, 80);

    private boolean isTyping = false;

    public ChatHandler(){}

    @EventHandler(state = GameState.INGAME)
    public void onRender(UIRenderEvent e){
        chatBox.draw();
        if(isTyping){
            chatInput.draw();
        }
    }

    @EventHandler(state = GameState.INGAME)
    public void onText(TextEvent e){
        if(isTyping){
            chatInput.addText(e.getInput());
        }
    }

    @EventHandler(order = Order.EARLY, state = GameState.INGAME)
    public void onKeyPress(KeyPressEvent e){
        if(isTyping){
            if(e.getKeyID() == 28){
                String message = chatInput.enter();
                if(message.length() > 0){
                    ClientPacketManager.sendPacket(new Packet1Chat(message));
                }
            }
            if(e.getKeyID() == 14){
                chatInput.backspace();
            }
            e.setCancelled(true);
        }
        if(e.getKeyID() == 28){
            isTyping = !isTyping;
        }
    }

    @EventHandler(state = GameState.INGAME)
    public void onTick(TickEvent e){
        if(isTyping){
            chatInput.tick(e);
        }
    }

    @EventHandler(state = GameState.INGAME)
    public void onChat(ChatEvent e){
        chatBox.addLine(e.getMessage());
    }
}
