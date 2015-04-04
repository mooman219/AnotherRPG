package com.gmail.mooman219.test3D.handler;

import com.gmail.mooman219.test3D.event.EventHandler;
import com.gmail.mooman219.test3D.event.Listener;
import com.gmail.mooman219.test3D.event.core.render.RenderTex3DEvent;
import com.gmail.mooman219.test3D.geo.World;

public class TestRender implements Listener {

    //public ArrayList<BlockRenderer> test = new ArrayList<BlockRenderer>();

    public World world;

    public TestRender() {
        world = new World();
        //world = new World();
        world.gen();

        //test.add(new BlockRenderer(new Vec3f(0,0,0)));
        //test.get(0).texture[2] = 3;
        //test.add(new BlockRenderer(new Vec3f(0,0,1)));
    }

    @EventHandler
    public void onRender(RenderTex3DEvent e) {
        //world.tick();
        world.renderAll();
    }
}
