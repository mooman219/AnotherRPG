package com.gmail.mooman219.test3D.manager;

import com.gmail.mooman219.test3D.AnotherClient;
import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

public class TextureManager {

    public static final int TOTAL_SIZE = 128;
    public static final int SINGLE_SIZE = 16;
    public static final String SHEET_LOC = "image/tile/SpriteSheet.png";
    public static int sheetId = -1;

    //Top left to bottom right
    public static float[] getTexture(int x, int y) {
        return getTexture(x, y, 0);
    }

    public static float[] getTexture(int x, int y, int rotation) {
        if (rotation == 0) {
            return new float[]{(x + 0) * (16f / 128), (y + 0) * (16f / 128), (x + 0) * (16f / 128), (y + 1) * (16f / 128),
                (x + 1) * (16f / 128), (y + 1) * (16f / 128), (x + 1) * (16f / 128), (y + 0) * (16f / 128)};
        } else if (rotation == 1) {
            return new float[]{(x + 0) * (16f / 128), (y + 1) * (16f / 128), (x + 1) * (16f / 128), (y + 1) * (16f / 128),
                (x + 1) * (16f / 128), (y + 0) * (16f / 128), (x + 0) * (16f / 128), (y + 0) * (16f / 128)};
        } else if (rotation == 2) {
            return new float[]{(x + 1) * (16f / 128), (y + 1) * (16f / 128), (x + 1) * (16f / 128), (y + 0) * (16f / 128),
                (x + 0) * (16f / 128), (y + 0) * (16f / 128), (x + 0) * (16f / 128), (y + 1) * (16f / 128)};
        } else {
            return new float[]{(x + 1) * (16f / 128), (y + 0) * (16f / 128), (x + 0) * (16f / 128), (y + 0) * (16f / 128),
                (x + 0) * (16f / 128), (y + 1) * (16f / 128), (x + 1) * (16f / 128), (y + 1) * (16f / 128)};
        }
    }

    public static void init() {
        sheetId = loadPNG(SHEET_LOC);
    }

    public static void unloadTextures() {
        glDeleteTextures(sheetId);
    }

    public static int loadPNG(String loc) {
        InputStream in = null;
        try {
            in = new FileInputStream(AnotherClient.getResourceFile(loc));
            PNGDecoder decoder = new PNGDecoder(in);
            ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * 4, Format.RGBA);
            buffer.flip();

            int id = glGenTextures();

            glBindTexture(GL_TEXTURE_2D, id);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            glBindTexture(GL_TEXTURE_2D, 0);
            System.out.println("LoadPNG: (" + loc + ") Identifier: " + id);
            return id;
        } catch (Exception ex) {
            System.err.println("Failed to load the texture files. Shutting down.");
            ex.printStackTrace();
            AnotherClient.shutdown();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
}
