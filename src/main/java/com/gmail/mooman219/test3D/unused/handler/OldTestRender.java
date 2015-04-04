package com.gmail.mooman219.test3D.unused.handler;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.gmail.mooman219.test3D.event.EventHandler;
import com.gmail.mooman219.test3D.event.Listener;
import com.gmail.mooman219.test3D.event.core.render.Render3DEvent;
import com.gmail.mooman219.test3D.event.core.render.RenderTex3DEvent;
import com.gmail.mooman219.test3D.shape.polygon.Rectangle;
import com.gmail.mooman219.test3D.shape.polyhedron.PolygonCone;
import com.gmail.mooman219.test3D.shape.polyhedron.RectangularPrism;
import com.gmail.mooman219.test3D.util.BufferHelper;


public class OldTestRender implements Listener{
    public RectangularPrism shapeA;
    public Rectangle shapeB;
    public RectangularPrism shapeC;
    public PolygonCone shapeD;

    public OldTestRender(){
        shapeA = new RectangularPrism(new Vector3f(2,2,1), new Vector3f(1,1,-1));
        shapeA.renderer.setColorData(BufferHelper.stack(new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 6))
        .setTextureData(BufferHelper.stack(new float[]{0f, 1f, 0f, 0f, 1f, 0f, 1f, 1f}, 6));
        //.setTexture(TextureType.FLOOR_F.getIdentifier());
        
        shapeB = new Rectangle(new Vector3f(2, 2, -3), new Vector3f(1, 1, -3));
        shapeB.renderer.setColorData(BufferHelper.toFloatBuffer(new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}))
        .setTextureData(BufferHelper.toRectangle(new Vector2f(0, 0), new Vector2f(1, 1)));
        //.setTexture(TextureType.FLOOR_F.getIdentifier());
        
        shapeC = new RectangularPrism(new Vector3f(-1, -1, -2), new Vector3f(-2, -2, -3));
        shapeC.renderer.setColorData(BufferHelper.stack(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1}, 6));

        shapeD = new PolygonCone(new Vector3f(0, .75f, -5), new Vector3f(-1, -.75f, -4),
                new Vector3f(1, -.75f, -4), new Vector3f(1, -.75f, -6),
                new Vector3f(-1, -.75f, -6), new Vector3f(-2, -.75f, -4.5f));
        shapeD.renderer.setColorData(BufferHelper.stack(new float[]{1, 0, 0, 0, 0, 1, 0, 0, 1}, 2));
    }

    @EventHandler
    public void onRender(Render3DEvent e){
        shapeC.onRender();
        shapeD.onRender();
    }

    @EventHandler
    public void onRender(RenderTex3DEvent e){
        shapeA.onRender();
        shapeB.onRender();
    }
}
