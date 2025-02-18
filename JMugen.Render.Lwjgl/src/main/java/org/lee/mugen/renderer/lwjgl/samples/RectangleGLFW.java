package org.lee.mugen.renderer.lwjgl.samples;

import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

public class RectangleGLFW {

    private long window;
    private int vaoId, vboId, eboId, shaderProgram;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        if (!glfwInit()) {
            throw new IllegalStateException("Falha ao inicializar GLFW");
        }

        // Configuração da Janela
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(800, 600, "Retângulo com GLFW", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Falha ao criar janela GLFW");
        }

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1);
        glfwShowWindow(window);

        // Criar VAO/VBO e carregar shaders
        setupRectangle();
        setupShaders();
    }

    private void setupRectangle() {
        float[] vertices = {
                //  Posição (X, Y)      // Cor (R, G, B)
                -0.5f, -0.5f,  1.0f, 0.0f, 0.0f, // Vermelho
                0.5f, -0.5f,  0.0f, 1.0f, 0.0f, // Verde
                0.5f,  0.5f,  0.0f, 0.0f, 1.0f, // Azul
                -0.5f,  0.5f,  1.0f, 1.0f, 0.0f  // Amarelo
        };

        int[] indices = { 0, 1, 2, 2, 3, 0 };

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Criar VBO (Buffer de Vértices)
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        // Criar EBO (Buffer de Índices)
        eboId = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        // Configurar atributos dos vértices
        int stride = 5 * Float.BYTES;

        // Posição
        glVertexAttribPointer(0, 2, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(0);

        // Cor
        glVertexAttribPointer(1, 3, GL_FLOAT, false, stride, 2 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private void setupShaders() {
        String vertexShaderSource =
                "#version 330 core\n" +
                        "layout (location = 0) in vec2 aPos;\n" +
                        "layout (location = 1) in vec3 aColor;\n" +
                        "out vec3 vertexColor;\n" +
                        "void main() {\n" +
                        "    gl_Position = vec4(aPos, 0.0, 1.0);\n" +
                        "    vertexColor = aColor;\n" +
                        "}";

        String fragmentShaderSource =
                "#version 330 core\n" +
                        "in vec3 vertexColor;\n" +
                        "out vec4 FragColor;\n" +
                        "void main() {\n" +
                        "    FragColor = vec4(vertexColor, 1.0);\n" +
                        "}";


        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        checkShaderCompilation(vertexShader, "Vertex Shader");

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        checkShaderCompilation(fragmentShader, "Fragment Shader");

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        checkProgramLinking(shaderProgram);

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);

            glUseProgram(shaderProgram);
            glBindVertexArray(vaoId);
            glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void cleanup() {
        glDeleteVertexArrays(vaoId);
        glDeleteBuffers(vboId);
        glDeleteBuffers(eboId);
        glDeleteProgram(shaderProgram);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void checkShaderCompilation(int shader, String type) {
        int success = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            String log = glGetShaderInfoLog(shader);
            throw new RuntimeException("Erro ao compilar " + type + ":\n" + log);
        }
    }

    private void checkProgramLinking(int program) {
        int success = glGetProgrami(program, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            String log = glGetProgramInfoLog(program);
            throw new RuntimeException("Erro ao vincular shader program:\n" + log);
        }
    }

    public static void main(String[] args) {
        new RectangleGLFW().run();
    }
}

