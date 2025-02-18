package org.lee.mugen.renderer.lwjgl.samples;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class VulkanExample {

    private long window;

    public void init() {
        // Inicializar o GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Falha ao inicializar o GLFW!");
        }

        // Configurar para não usar OpenGL (usar Vulkan)
        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);

        // Criar a janela GLFW
        window = glfwCreateWindow(800, 600, "Vulkan com GLFW", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Falha ao criar a janela GLFW!");
        }

        // Obter o monitor para determinar a resolução
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            // Obtém as dimensões da janela
            glfwGetFramebufferSize(window, pWidth, pHeight);

            // Posiciona a janela no centro
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(
                    window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
    }

    public void loop() {
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            // Renderização com Vulkan deve ser feita aqui
            // (configuração de Vulkan, criação de buffers, etc.)
        }
    }

    public void cleanup() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static void main(String[] args) {
        VulkanExample example = new VulkanExample();

        try {
            example.init();
            example.loop();
        } finally {
            example.cleanup();
        }
    }
}
