package com.gmail.mooman219.client.handler.game;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.input.Mouse;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.enums.BlockType;
import com.gmail.mooman219.client.enums.Direction;
import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.enums.UIType;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.MousePressEvent;
import com.gmail.mooman219.client.event.core.game.MouseBlockMoveEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.geo.Block;
import com.gmail.mooman219.client.geo.World;
import com.gmail.mooman219.client.manager.ClientPacketManager;
import com.gmail.mooman219.client.manager.TextureManager;
import com.gmail.mooman219.client.manager.WorldManager;
import com.gmail.mooman219.shared.Order;
import com.gmail.mooman219.shared.geo.GenericBlock;
import com.gmail.mooman219.shared.geo.cord.Cord;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;
import com.gmail.mooman219.shared.packet.Packet4BlockChange;



public class SelectionHandler implements Listener{

    private BlockType selection = BlockType.STONE;

    public SelectionHandler(){}

    @EventHandler(state = GameState.INGAME)
    public void onKeyPress(KeyPressEvent e) {
        switch(e.getKeyID()){
        case 2:
            selection = BlockType.AIR;
            break;
        case 3:
            selection = BlockType.DIRT;
            break;
        case 4:
            selection = BlockType.GRASS;
            break;
        case 5:
            selection = BlockType.STONE;
            break;
        case 6:
            selection = BlockType.WATER;
            break;
        case 7:
            break;
        case 8:
            break;
        case 9:
            break;
        case 10:
            break;
        case 11:
            break;
        default:
            break;
        }
    }

    @EventHandler(state = GameState.INGAME)
    public void onMousePress(MousePressEvent e) {
        if(e.getId() == 0){
            Cord bPos = new IntegerCord(AnotherRPG.getMouseGameX(), AnotherRPG.getMouseGameY());
            System.out.print("Try ["+bPos+"] ");
            if(WorldManager.currentWorld.getBlock(bPos) != null){
                GenericBlock b = new GenericBlock(bPos, selection.index, Direction.DOWN.index);
                WorldManager.currentWorld.setBlock(Block.deGeneric(b));
                ClientPacketManager.sendPacket(new Packet4BlockChange(b));
                System.out.print("[Sent] ["+selection+"]");
            }
            System.out.println("");
        }
    }

    @EventHandler(state = GameState.INGAME)
    public void onGameMouseMove(MouseBlockMoveEvent e) {
        if(Mouse.isButtonDown(0)){
            Cord bPos = new IntegerCord(AnotherRPG.getMouseGameX(), AnotherRPG.getMouseGameY());
            System.out.print("Try ["+bPos+"] ");
            if(WorldManager.currentWorld.getBlock(bPos) != null){
                GenericBlock b = new GenericBlock(bPos, selection.index, Direction.DOWN.index);
                WorldManager.currentWorld.setBlock(Block.deGeneric(b));
                ClientPacketManager.sendPacket(new Packet4BlockChange(b));
                System.out.print("[Sent] ["+selection+"]");
            }
            System.out.println("");
        }
    }

    @EventHandler(order = Order.LATE, state = GameState.INGAME)
    public void onWorldRender(WorldRenderEvent e) {
        bind();
        int x = AnotherRPG.getMouseGameX() * World.BLOCK_SIZE;
        int y = AnotherRPG.getMouseGameY() * World.BLOCK_SIZE;
        int x2 = x + World.BLOCK_SIZE;
        int y2 = y + World.BLOCK_SIZE;
        glColor4f(0f, 0f, 0f, 0.3f);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(x, y);
        glTexCoord2f(1, 0);
        glVertex2i(x2, y);
        glTexCoord2f(1, 1);
        glVertex2i(x2, y2);
        glTexCoord2f(0, 1);
        glVertex2i(x, y2);
        glEnd();
        glColor4f(1f, 1f, 1f, 1f);
    }

    public void bind() {
        TextureManager.uiTextures[UIType.BLANK.index].getTexture().bind();
    }
}
