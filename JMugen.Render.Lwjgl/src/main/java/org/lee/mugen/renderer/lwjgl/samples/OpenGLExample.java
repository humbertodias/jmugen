package org.lee.mugen.renderer.lwjgl.samples;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.system.MemoryUtil.NULL;

public class OpenGLExample {
    private long window;
    private ByteBuffer fontBuffer;
    private STBTTBakedChar.Buffer charData;
    private int textureID;

    public void run() {
        init();
        loadFont();
        loop();
        cleanup();
    }

    private void init() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Criação da janela GLFW com OpenGL
        window = GLFW.glfwCreateWindow(800, 600, "LWJGL 3 - OpenGL Text", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        // Configura o contexto OpenGL
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities(); // Habilita OpenGL no contexto
        GLFW.glfwSwapInterval(1);

        // Configura a projeção ortográfica para renderizar texto 2D
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        GL11.glOrtho(0, 800, 600, 0, -1, 1);  // Defina o sistema de coordenadas 2D
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 600, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

    }

    private void loadFont() {
        try {
            // Carrega a fonte (DejaVuSans.ttf)
            fontBuffer = loadFontFile("arial.ttf");

            // Criamos um buffer de 512x512 para armazenar a textura de caracteres
            charData = STBTTBakedChar.malloc(96);
            ByteBuffer bitmap = MemoryUtil.memAlloc(512 * 512);

            // Bake da fonte para o bitmap
            STBTruetype.stbtt_BakeFontBitmap(fontBuffer, 32, bitmap, 512, 512, 32, charData);

            // Criação da textura OpenGL
            textureID = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_ALPHA, 512, 512, 0, GL11.GL_ALPHA, GL11.GL_UNSIGNED_BYTE, bitmap);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RED, 512, 512, 0, GL11.GL_RED, GL11.GL_UNSIGNED_BYTE, bitmap);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);

            MemoryUtil.memFree(bitmap);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load font", e);
        }
    }

    private void renderText(String text, float x, float y) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer xpos = stack.floats(x);
            FloatBuffer ypos = stack.floats(y);

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            for (char c : text.toCharArray()) {
                if (c < 32 || c >= 128) continue; // Ignorar caracteres não suportados

                STBTTAlignedQuad quad = STBTTAlignedQuad.malloc(stack);
                STBTruetype.stbtt_GetBakedQuad(charData, 512, 512, c - 32, xpos, ypos, quad, true);

                // Aplique uma escala para tornar o texto legível
                float scale = 2.0f; // Ajuste para aumentar ou diminuir o tamanho do texto
                float x0 = quad.x0() * scale, y0 = quad.y0() * scale;
                float x1 = quad.x1() * scale, y1 = quad.y1() * scale;

                GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(quad.s0(), quad.t0()); GL11.glVertex2f(x0, y0);
                GL11.glTexCoord2f(quad.s1(), quad.t0()); GL11.glVertex2f(x1, y0);
                GL11.glTexCoord2f(quad.s1(), quad.t1()); GL11.glVertex2f(x1, y1);
                GL11.glTexCoord2f(quad.s0(), quad.t1()); GL11.glVertex2f(x0, y1);
                GL11.glEnd();
            }
        }
    }


    private void loop() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 600, 0, -1, 1); // Define a projeção ortográfica para 2D

        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Renderiza o texto "Hello, World!"
            renderText("Hello, World!", 50, 100);

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }


    private void cleanup() {
        GL11.glDeleteTextures(textureID);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
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

            return (ByteBuffer) buffer;
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

    public static void main(String[] args) {
        new OpenGLExample().run();
    }
}
