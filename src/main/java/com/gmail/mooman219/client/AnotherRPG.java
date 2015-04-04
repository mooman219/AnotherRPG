package com.gmail.mooman219.client;

import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.KeyReleaseEvent;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.MousePressEvent;
import com.gmail.mooman219.client.event.core.MouseReleaseEvent;
import com.gmail.mooman219.client.event.core.PreRenderEvent;
import com.gmail.mooman219.client.event.core.ShutdownEvent;
import com.gmail.mooman219.client.event.core.StartupEvent;
import com.gmail.mooman219.client.event.core.TickEvent;
import com.gmail.mooman219.client.event.core.game.MouseBlockMoveEvent;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.event.core.game.WorldRenderEvent;
import com.gmail.mooman219.client.geo.World;
import com.gmail.mooman219.client.handler.EventLogHandler;
import com.gmail.mooman219.client.handler.FPSHandler;
import com.gmail.mooman219.client.handler.TextFormatHandler;
import com.gmail.mooman219.client.handler.TranslationHandler;
import com.gmail.mooman219.client.handler.game.ChatHandler;
import com.gmail.mooman219.client.handler.game.ChunkRenderHandler;
import com.gmail.mooman219.client.handler.game.MainMenuHandler;
import com.gmail.mooman219.client.handler.game.SelectionHandler;
import com.gmail.mooman219.client.manager.EventManager;
import com.gmail.mooman219.client.manager.StateManager;
import com.gmail.mooman219.client.manager.TextureManager;
import com.gmail.mooman219.client.manager.WorldManager;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glViewport;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class AnotherRPG {

    public static final boolean VSYNC = false;
    public static final int WIDTH = 864;
    public static final int HEIGHT = 800;
    public static String name = "AnotherRPG";
    public static float translate_x = 0;
    public static float translate_y = 0;
    public static int maxFPS = 500;

    public static UnicodeFont font;

    public static void main(String[] args) {
        new AnotherRPG();
    }

    public AnotherRPG() {
        System.setProperty("org.lwjgl.librarypath", new File("./target/natives/").getAbsolutePath());
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle(name + " FPS: Init");
            Display.create();
            Display.setVSyncEnabled(VSYNC);
            System.out.println("Display: " + Display.getVersion());
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL();
        initFont();

        //Setup Managers (Not needed for all managers)
        TextureManager.init();
        WorldManager.init();
        //
        //Create Handlers
        EventManager.registerEvents(new EventLogHandler());
        EventManager.registerEvents(new TextFormatHandler());
        EventManager.registerEvents(new FPSHandler());
        EventManager.registerEvents(new MainMenuHandler());
        EventManager.registerEvents(new SelectionHandler());
        EventManager.registerEvents(new ChunkRenderHandler());
        EventManager.registerEvents(new ChatHandler());
        EventManager.registerEvents(new TranslationHandler());
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
            Display.destroy();
            System.exit(0);
        }
    }

    public void initGL() {
        glEnable(GL_TEXTURE_2D);
        glShadeModel(GL_SMOOTH);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glViewport(0, 0, WIDTH, HEIGHT);
        glMatrixMode(GL_MODELVIEW);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    public void loop() {
        long lastTickTime = 0;
        long deltaTime = 0;
        float mouseRealDX = 0f;
        float mouseRealDY = 0f;

        int lastMouseGameX = 0;
        int lastMouseGameY = 0;
        int mouseGameDX = 0;
        int mouseGameDY = 0;
        EventManager.callEvent(new StartupEvent());
        while (!Display.isCloseRequested()) {
            deltaTime = getTime() - lastTickTime;
            lastTickTime = getTime();
            //You can only get the Delta Once, so I stored it.
            mouseRealDX = Mouse.getDX();
            mouseRealDY = Mouse.getDY();
            //
            mouseGameDX = getMouseGameX() - lastMouseGameX;
            mouseGameDY = getMouseGameY() - lastMouseGameY;
            lastMouseGameX = getMouseGameX();
            lastMouseGameY = getMouseGameY();
            //
            EventManager.callEvent(new TickEvent(getTime(), deltaTime));
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
            if (mouseGameDX != 0 || mouseGameDY != 0) {
                EventManager.callEvent(new MouseBlockMoveEvent(getMouseGameX(), getMouseGameY(), mouseGameDX, mouseGameDY));
            }
            render();
            Display.update();
            Display.sync(maxFPS);
        }
        EventManager.callEvent(new ShutdownEvent());
        Display.destroy();
        System.exit(0);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.6f, 0.85f, 0.92f, 1.0f);
        EventManager.callEvent(new PreRenderEvent());
        glPushMatrix();
        glTranslatef(AnotherRPG.translate_x, AnotherRPG.translate_y, 0);
        EventManager.callEvent(new WorldRenderEvent());
        glPopMatrix();
        glPushMatrix();
        EventManager.callEvent(new UIRenderEvent());
        glPopMatrix();
    }

    public static int getMouseRealX() {
        return (int) (Mouse.getX() - (StateManager.currentState == GameState.MAINMENU ? 0 : translate_x));
    }

    public static int getMouseRealY() {
        return (int) ((HEIGHT - Mouse.getY() - 1) - (StateManager.currentState == GameState.MAINMENU ? 0 : translate_y));
    }

    public static int getMouseGameX() {
        return (int) (getMouseRealX() / ((float) World.BLOCK_SIZE));
    }

    public static int getMouseGameY() {
        return (int) (getMouseRealY() / ((float) World.BLOCK_SIZE));
    }

    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static File getResourceFile(String fileName) {
        return new File(getResourceURL(fileName).getFile());
    }

    public static URL getResourceURL(String fileName) {
        ClassLoader classLoader = AnotherRPG.class.getClassLoader();
        return classLoader.getResource(fileName);
    }
}
