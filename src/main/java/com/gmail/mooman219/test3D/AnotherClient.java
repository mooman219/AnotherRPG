package com.gmail.mooman219.test3D;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.shared.geo.vec.Vec3f;
import com.gmail.mooman219.test3D.enums.GameState;
import com.gmail.mooman219.test3D.event.core.KeyPressEvent;
import com.gmail.mooman219.test3D.event.core.KeyReleaseEvent;
import com.gmail.mooman219.test3D.event.core.MouseMoveEvent;
import com.gmail.mooman219.test3D.event.core.MousePressEvent;
import com.gmail.mooman219.test3D.event.core.MouseReleaseEvent;
import com.gmail.mooman219.test3D.event.core.ShutdownEvent;
import com.gmail.mooman219.test3D.event.core.StartupEvent;
import com.gmail.mooman219.test3D.event.core.TickEvent;
import com.gmail.mooman219.test3D.event.core.WindowResizeEvent;
import com.gmail.mooman219.test3D.event.core.render.Render2DEvent;
import com.gmail.mooman219.test3D.event.core.render.Render3DEvent;
import com.gmail.mooman219.test3D.event.core.render.RenderFont;
import com.gmail.mooman219.test3D.event.core.render.RenderTex2DEvent;
import com.gmail.mooman219.test3D.event.core.render.RenderTex3DEvent;
import com.gmail.mooman219.test3D.handler.FPSHandler;
import com.gmail.mooman219.test3D.handler.TestRender;
import com.gmail.mooman219.test3D.handler.TextFormatHandler;
import com.gmail.mooman219.test3D.input.PlayerController;
import com.gmail.mooman219.test3D.manager.EventManager;
import com.gmail.mooman219.test3D.manager.StateManager;
import com.gmail.mooman219.test3D.manager.TextureManager;
import com.gmail.mooman219.test3D.render.VertexBuffer;
import com.gmail.mooman219.test3D.unused.render.DisplayList;
import com.gmail.mooman219.test3D.util.BufferHelper;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.FloatBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FOG;
import static org.lwjgl.opengl.GL11.GL_FOG_COLOR;
import static org.lwjgl.opengl.GL11.GL_FOG_DENSITY;
import static org.lwjgl.opengl.GL11.GL_FOG_END;
import static org.lwjgl.opengl.GL11.GL_FOG_HINT;
import static org.lwjgl.opengl.GL11.GL_FOG_MODE;
import static org.lwjgl.opengl.GL11.GL_FOG_START;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_VENDOR;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glFog;
import static org.lwjgl.opengl.GL11.glFogf;
import static org.lwjgl.opengl.GL11.glFogi;
import static org.lwjgl.opengl.GL11.glGetError;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glViewport;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class AnotherClient {

    //hash - fa3Aw2Qn5Gr9
    public static final boolean fullscreen = false;
    public static final boolean vsync = false;

    public static Vec3f rotation = new Vec3f(0, 0, 0);
    public static Vec3f position = new Vec3f(0, -2, 0);

    public static int width = 864;
    public static int height = 800;
    public static String name = "AnotherRPG";
    public static float translate_x = 0;
    public static float translate_y = 0;
    public static int maxFPS = 200;

    public static long lastFrame;
    public static float zNear = 0.01f;
    public static float zFar = 120f;
    public static float fogNear = 30f;
    public static float fogFar = 60f;
    public static Color fogColor = new Color(0.6f, 0.85f, 0.92f, 1.0f);
    public static int fov = 90;

    public static UnicodeFont font;

    public static void main(String[] args) {
        new AnotherClient();
    }

    public AnotherClient() {
        System.setProperty("org.lwjgl.librarypath", new File("./target/natives/").getAbsolutePath());
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(name + " FPS: Init");
            Display.setResizable(true);
            Display.setVSyncEnabled(vsync);
            Display.create();
            System.out.println("Display: " + Display.getVersion());
            System.out.println("Operating System: " + System.getProperty("os.name") + " " + System.getProperty("os.arch"));
            System.out.println("Operating System Version: " + System.getProperty("os.version"));
            System.out.println("Vendor: " + glGetString(GL_VENDOR));
            System.out.println("OpenGL Version: " + glGetString(GL11.GL_VERSION));
            System.out.println("GLSL Version: " + glGetString(GL20.GL_SHADING_LANGUAGE_VERSION) + "\n");
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        if (!GLContext.getCapabilities().OpenGL20) {
            System.err.println("Your OpenGL version doesn't support the required functionality.");
            Display.destroy();
            System.exit(1);
        }

        initFont();
        initGL();

        //Setup Managers (Not needed for all managers)
        //To follow my api, I should make them register and use the startup event,
        //but the other handlers might need the managers before startup.
        TextureManager.init();
        //WorldManager.init();
        //
        //Create Handlers
        EventManager.registerEvents(new PlayerController());
        EventManager.registerEvents(new TextFormatHandler());
        EventManager.registerEvents(new FPSHandler());
        EventManager.registerEvents(new TestRender());
        //EventManager.registerEvents(new MainMenuHandler());
        //EventManager.registerEvents(new SelectionHandler());
        //EventManager.registerEvents(new ChunkRenderHandler());
        //EventManager.registerEvents(new ChatHandler());
        //EventManager.registerEvents(new TranslationHandler());
        //

        loop();
    }

    @SuppressWarnings("unchecked")
    public void initFont() {
        java.awt.Font awtFont;
        try {
            awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, getResourceFile("font/kroe0655.ttf"));
            awtFont = awtFont.deriveFont(15f);
            font = new UnicodeFont(awtFont);
            font.getEffects().add(new ColorEffect(java.awt.Color.white));
            font.addAsciiGlyphs();
            font.loadGlyphs();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SlickException e) {
            e.printStackTrace();
            shutdown();
        }
    }

    public void initGL() {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glShadeModel(GL_SMOOTH);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        /**/
        {
            glEnable(GL_FOG);
            FloatBuffer fogColours = BufferHelper.toFloatBuffer(new float[]{fogColor.r, fogColor.g, fogColor.b, fogColor.a});
            glClearColor(fogColor.r, fogColor.g, fogColor.b, fogColor.a);
            glFog(GL_FOG_COLOR, fogColours);
            glFogi(GL_FOG_MODE, GL_LINEAR);
            glHint(GL_FOG_HINT, GL_NICEST);
            glFogf(GL_FOG_START, fogNear);
            glFogf(GL_FOG_END, fogFar);
            glFogf(GL_FOG_DENSITY, 0.0005f);
        }/**/

        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
    }

    public void loop() {
        long lastTickTime = 0;
        long deltaTime = 0;
        float mouseRealDX = 0f;
        float mouseRealDY = 0f;
        EventManager.callEvent(new StartupEvent());
        int errorCode = GL_NO_ERROR;
        try {
            while (!Display.isCloseRequested()) {
                deltaTime = getTime() - lastTickTime;
                lastTickTime = getTime();
                //You can only get the Delta Once, so I stored it.
                mouseRealDX = Mouse.getDX();
                mouseRealDY = Mouse.getDY();
                while (Mouse.next()) {
                    if (Mouse.getEventButtonState()) {
                        EventManager.callEvent(new MousePressEvent(Mouse.getEventButton(), getMouseRealX(), getMouseRealY()));
                    } else {
                        if (Mouse.getEventButton() != -1) {
                            EventManager.callEvent(new MouseReleaseEvent(Mouse.getEventButton(), getMouseRealX(), getMouseRealY()));
                        }
                    }
                }
                while (Keyboard.next()) {
                    if (Keyboard.getEventKeyState()) {
                        EventManager.callEvent(new KeyPressEvent(Keyboard.getEventKey()));
                    } else {
                        EventManager.callEvent(new KeyReleaseEvent(Keyboard.getEventKey()));
                    }
                }
                if (mouseRealDX != 0 || mouseRealDY != 0) {
                    EventManager.callEvent(new MouseMoveEvent(getMouseRealX(), getMouseRealY(), (int) mouseRealDX, (int) mouseRealDY));
                }
                EventManager.callEvent(new TickEvent(lastTickTime, deltaTime));
                render();
                Display.update();
                //Commented out of crazy fps
                //Display.sync(maxFPS);
            }
            errorCode = glGetError();
            if (errorCode != GL_NO_ERROR) {
                String error = glGetString(errorCode);
                System.out.println("OpenGL Error:\n" + error);
                errorCode = GL_NO_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            shutdown();
        }
        shutdown();
    }

    public void render() {
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        /**/// SCULPT, FASTERRR!
        render3D();
        /**/// DRAW, NAOW!
        render2D();
        /**/// Once size fit all screen
        if (Display.wasResized()) {
            resize();
        }
    }

    public static int getMouseRealX() {
        return (int) (Mouse.getX() - (StateManager.currentState == GameState.MAINMENU ? 0 : translate_x));
    }

    public static int getMouseRealY() {
        return (int) ((height - Mouse.getY() - 1) - (StateManager.currentState == GameState.MAINMENU ? 0 : translate_y));
    }

    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static void render3D() {
        glPushMatrix();
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        //glEnable(GL_LIGHTING);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, (float) Display.getWidth() / (float) Display.getHeight(), zNear, zFar);
        glRotatef(rotation.x, 1, 0, 0);
        glRotatef(rotation.y, 0, 1, 0);
        glRotatef(rotation.z, 0, 0, 1);
        glTranslatef(position.x, position.y, position.z);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.3f, 0.425f, 0.46f, 1.0f);
        EventManager.callEvent(new RenderTex3DEvent());
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glBindTexture(GL_TEXTURE_2D, 0);
        EventManager.callEvent(new Render3DEvent());
        glPopMatrix();
    }

    public static void render2D() {
        glPushMatrix();
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        //glDisable(GL_LIGHTING);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, Display.getWidth(), Display.getHeight(), 0.0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        EventManager.callEvent(new RenderTex2DEvent());
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glBindTexture(GL_TEXTURE_2D, 0);
        EventManager.callEvent(new Render2DEvent());
        EventManager.callEvent(new RenderFont());
        glPopMatrix();
    }

    public static void resize() {
        EventManager.callEvent(new WindowResizeEvent(Display.getWidth(), Display.getHeight()));
        glViewport(0, 0, Display.getWidth(), Display.getHeight());
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, (float) Display.getWidth() / (float) Display.getHeight(), zNear, zFar);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public static void shutdown() {
        System.out.println("Shutdown");
        glDisableClientState(GL_COLOR_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
        EventManager.callEvent(new ShutdownEvent());
        VertexBuffer.disposeAll();
        DisplayList.disposeAll();
        TextureManager.unloadTextures();
        Display.destroy();
        System.exit(0);
    }

    public static File getResourceFile(String fileName) {
        return new File(getResourceURL(fileName).getFile());
    }

    public static URL getResourceURL(String fileName) {
        ClassLoader classLoader = AnotherRPG.class.getClassLoader();
        return classLoader.getResource(fileName);
    }
}
