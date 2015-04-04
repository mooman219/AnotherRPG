package com.gmail.mooman219.client.unused.entity;

import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.game.MouseBlockMoveEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.geo.World;
import com.gmail.mooman219.shared.Order;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;


public class EntityRenderHandler implements Listener{
    private IntegerCord mouseChunkPos = new IntegerCord(0,0);

    public EntityRenderHandler(){}

    @EventHandler(order = Order.DEFAULT, state = GameState.INGAME)
    public void onRender(WorldRenderEvent e){
        //for(Entity ent:  WorldManager.currentWorld.getVisableEntities(mouseChunkPos)){
        //ent.draw();
        //}
    }

    @EventHandler(state = GameState.INGAME)
    public void onGameMouseMove(MouseBlockMoveEvent e) {
        mouseChunkPos.x = e.getBlockX();
        mouseChunkPos.y = e.getBlockY();
        mouseChunkPos.divide(World.CHUNK_SIZE);
    }
}