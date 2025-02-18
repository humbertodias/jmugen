package org.lee.mugen.renderer.lwjgl;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.system.MemoryUtil.*;

public class FontRenderer {
    private long window;
    private int textureID;
    private ByteBuffer fontBuffer;
    private ByteBuffer bitmap;
    private STBTTBakedChar.Buffer charData;

    private static final int FONT_TEXTURE_SIZE = 512;

    public static void main(String[] args) {
        new FontRenderer().run();
    }

    public void run() {
        init();
        loadFont("DejaVuSans.ttf"); // Caminho da fonte
        loop();

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        if (!GLFW.glfwInit()) throw new IllegalStateException("GLFW não pôde ser inicializado!");

        window = GLFW.glfwCreateWindow(800, 600, "Texto LWJGL", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Falha ao criar janela!");

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }

    private ByteBuffer loadFontFile(String resourcePath) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Font resource not found: " + resourcePath);
            }

            byte[] bytes = toByteArray(is);
            ByteBuffer buffer = MemoryUtil.memAlloc(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            return buffer;
        }
    }

    private byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    private void loadFont(String fontPath) {
        try {
//            fontBuffer = ByteBuffer.wrap(Files.readAllBytes(Paths.get(fontPath)));
        fontBuffer = loadFontFile(fontPath);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar fonte!", e);
        }

        ByteBuffer bitmap = BufferUtils.createByteBuffer(FONT_TEXTURE_SIZE * FONT_TEXTURE_SIZE);

        charData = STBTTBakedChar.malloc(96); // Correção aqui

        // Bake da fonte para o bitmap
        STBTruetype.stbtt_BakeFontBitmap(fontBuffer, 32, bitmap, 512, 512, 32, charData);

        textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_ALPHA, FONT_TEXTURE_SIZE, FONT_TEXTURE_SIZE, 0, GL11.GL_ALPHA, GL11.GL_UNSIGNED_BYTE, bitmap);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    }

    private void renderText(String text, float x, float y, float scale) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer xpos = stack.floats(x);
            FloatBuffer ypos = stack.floats(y);

            GL11.glBegin(GL11.GL_QUADS);
            for (char c : text.toCharArray()) {
                if (c < 32 || c >= 128) continue;

                STBTTAlignedQuad quad = STBTTAlignedQuad.malloc(stack);
                STBTruetype.stbtt_GetBakedQuad(charData, FONT_TEXTURE_SIZE, FONT_TEXTURE_SIZE, c - 32, xpos, ypos, quad, true);

                float x0 = quad.x0() * scale, y0 = quad.y0() * scale;
                float x1 = quad.x1() * scale, y1 = quad.y1() * scale;

                GL11.glTexCoord2f(quad.s0(), quad.t0()); GL11.glVertex2f(x0, y0);
                GL11.glTexCoord2f(quad.s1(), quad.t0()); GL11.glVertex2f(x1, y0);
                GL11.glTexCoord2f(quad.s1(), quad.t1()); GL11.glVertex2f(x1, y1);
                GL11.glTexCoord2f(quad.s0(), quad.t1()); GL11.glVertex2f(x0, y1);
            }
            GL11.glEnd();
        }
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    private void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, 800, 600, 0, -1, 1);

            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();

            renderText("Hello, World!", 100, 300, 2.0f);

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }
}
