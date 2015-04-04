package com.gmail.mooman219.client.handler.game;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.game.MouseBlockMoveEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.geo.Chunk;
import com.gmail.mooman219.client.geo.World;
import com.gmail.mooman219.client.manager.WorldManager;
import com.gmail.mooman219.shared.Order;
import com.gmail.mooman219.shared.geo.cord.Cord;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;


public class ChunkRenderHandler implements Listener{

    private Cord mousePos;

    public ChunkRenderHandler(){
        mousePos = new IntegerCord(0, 0);
    }

    @EventHandler(order = Order.EARLY, state = GameState.INGAME)
    public void onGameMouseMove(MouseBlockMoveEvent e) {
        mousePos.setX(e.getBlockX());
        mousePos.setY(e.getBlockY());
        mousePos.divide(World.CHUNK_SIZE);
    }

    @EventHandler(state = GameState.INGAME)
    public void onRender(WorldRenderEvent e) {
        for(Chunk c:WorldManager.currentWorld.getVisableChunks(mousePos)){
            c.draw();
        }
    }
}
