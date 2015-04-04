package com.gmail.mooman219.client.handler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.KeyReleaseEvent;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.MousePressEvent;
import com.gmail.mooman219.client.event.core.MouseReleaseEvent;
import com.gmail.mooman219.client.event.core.PreRenderEvent;
import com.gmail.mooman219.client.event.core.ShutdownEvent;
import com.gmail.mooman219.client.event.core.StartupEvent;
import com.gmail.mooman219.client.event.core.TextEvent;
import com.gmail.mooman219.client.event.core.TickEvent;
import com.gmail.mooman219.client.event.core.game.BlockChangeEvent;
import com.gmail.mooman219.client.event.core.game.ChatEvent;
import com.gmail.mooman219.client.event.core.game.ConnectEvent;
import com.gmail.mooman219.client.event.core.game.ConnectionInfoEvent;
import com.gmail.mooman219.client.event.core.game.MouseBlockMoveEvent;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.shared.Order;

public class EventLogHandler implements Listener{

    public EventLogHandler(){}

    /**
     * Called when a mouse button was released.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventMouseRelease(MouseReleaseEvent e){
        //System.out.println("MOUSE(R) ID: " + e.getId() + " X: " + e.getX() + " Y: " + e.getY());
    }

    /**
     * Called when a mouse button was pressed.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventMousePress(MousePressEvent e){
        //System.out.println("MOUSE(P) ID: " + e.getId() + " X: " + e.getX() + " Y: " + e.getY());
    }

    /**
     * Called when the mouse's position is changed.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventMouseMove(MouseMoveEvent e){
        //System.out.println("MOUSE(M) X: " + x + " Y: " + y + " DX: " + dy + " DY: " + dy);
    }

    /**
     * Called when the mouse's position is changed in relation to the game.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventMouseBlockMove(MouseBlockMoveEvent e){
        //System.out.println("MOUSE(M) X: " + x + " Y: " + y + " DX: " + dx + " DY: " + dy);
    }

    /**
     * Called when a key on the keyboard is pressed.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventKeyPress(KeyPressEvent e){
        //System.out.println("KEY(P) ID: " + e.getKeyID() + " N: " + Keyboard.getKeyName(e.getKeyID()));
    }

    /**
     * Called when a key on the keyboard is released.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventKeyRelease(KeyReleaseEvent e){
        //System.out.println("KEY(R) ID: " + e.getKeyID() + " N: " + Keyboard.getKeyName(e.getKeyID()));

        if(Keyboard.KEY_ESCAPE == e.getKeyID()){
            Display.destroy();
            System.exit(0);
        }
    }

    /**
     * Called by the TextInputManager. Is called when the user types a character that is visable.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventTextInput(TextEvent e){
        //System.out.println("TEXT: " + e.getInput());
    }

    /**
     * Called before the frame is rendered and before input is checked.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventTick(TickEvent e){
        //System.out.println("TICK: CURRENT: " + e.getCurrentTime() + " DELTA: " + e.getDeltaTime());
    }

    /**
     * Called before the frame is being rendered. Sets up the frame to be rendered.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventRender(PreRenderEvent e){
        //System.out.println("RENDER: PRE");
    }

    /**
     * Renders the UI. Does not translate.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventUIRender(UIRenderEvent e){
        //System.out.println("RENDER: UI");
    }

    /**
     * Renders the World. Does translate.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventWorldRender(WorldRenderEvent e){
        //System.out.println("RENDER: WORLD");
    }

    /**
     * Called when the game starts. Right before the main loop begins.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventStartup(StartupEvent e){
        //System.out.println("STARTUP");
    }

    /**
     * Called when the game shutsdown. Right after the main loop ends.
     */
    @EventHandler(order = Order.INITIAL)
    public void eventShutdown(ShutdownEvent e){
        //System.out.println("SHUTDOWN");
    }

    @EventHandler(order = Order.INITIAL)
    public void eventConnectionInfo(ConnectionInfoEvent e){
        System.out.println("INFO: " + e.getInfoMessage());
    }

    @EventHandler(order = Order.INITIAL)
    public void eventChat(ChatEvent e){
        //System.out.println("CHAT: " + e.getMessage());
    }

    @EventHandler(order = Order.INITIAL)
    public void eventConnect(ConnectEvent e){
        //System.out.println("CONNECTED");
    }

    @EventHandler(order = Order.INITIAL)
    public void eventBlockChange(BlockChangeEvent e){
        //System.out.println("BLOCK CHANGE: X: " + e.getX() + " Y: " + e.getY() + " ID: " + e.getTypeID());
    }
}
