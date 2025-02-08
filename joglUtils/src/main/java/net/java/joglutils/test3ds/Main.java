/*
 * Copyright (c) 2006 Greg Rodgers All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * The names of Greg Rodgers, Sun Microsystems, Inc. or the names of
 * contributors may not be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. GREG RODGERS,
 * SUN MICROSYSTEMS, INC. ("SUN"), AND SUN'S LICENSORS SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL GREG
 * RODGERS, SUN, OR SUN'S LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT
 * OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
 * ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF GREG
 * RODGERS OR SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed or intended for use
 * in the design, construction, operation or maintenance of any nuclear
 * facility.
 */

package net.java.joglutils.test3ds;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.Animator;

public class Main {
    /** Creates a new instance of Main */
    public Main() {
    }

    public static void main(String[] args)
    {
        Frame frame = new Frame();
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Renderer());
        frame.add(canvas);
        frame.setSize(600, 600);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              // Run this on another thread than the AWT event queue to
              // make sure the call to Animator.stop() completes before
              // exiting
              new Thread(new Runnable() {
                  public void run() {
                    animator.stop();
                    System.exit(0);
                  }
                }).start();
            }
          });
        frame.show();
        animator.start();
    }
    static float[] cameraPos = {0f,3f,35f};
    static float cameraRotation = 0f;
    static class Renderer implements GLEventListener
    {
        private MyModel model = new MyModel();

        public void display(GLAutoDrawable gLDrawable)
        {
            final GL gl = gLDrawable.getGL();
            final GLU glu = new GLU();
            int width = gLDrawable.getWidth();
            int height = gLDrawable.getHeight();
            
            if (height <= 0) // avoid a divide by zero error!
                height = 1;
            final float h = (float)width / (float)height;
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrtho(-10000, 10000, -10000, 10000, -10000, 10000);
            gl.glMatrixMode(GL.GL_MODELVIEW);
            gl.glLoadIdentity();
            
            
            
            
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            gl.glEnable(GL.GL_LIGHTING);
//            gl.glDisable(GL.GL_LIGHTING);

//            gl.glMatrixMode(GL.GL_PROJECTION);
//            gl.glLoadIdentity();
            /* 		fovy,aspect,zNear,zFar  */
//            gl.glRotatef(cameraRotation+=0.2f, -1f, 1f, 0); 
//            glu.gluPerspective(1,         // zoom in or out of view
//                               3, // shape of viewport rectangle
//                               10,        // Min Z: how far from eye position does view start
//                               10f); 
//            gl.glLoadIdentity();
//            gl.glRotatef(180, 1f, 0f, 0);   // first rotate around y axis
        	
            float faWhite[]      = { 0.01f, 1.0f, 1.0f, 1.0f };
            float faLightBlue[]  = { 5f, 10f, 10f, 0.5f };
            // Light direction: if last value is 0, then this describes light direction.  If 1, then light position.
            float lightDirection[]= { -0f, 5f, 2f, 0f };
            
//            gl.glLightModelf(GL.GL_LIGHT_MODEL_AMBIENT, 15f);
//            
            setLight(gl,  GL.GL_LIGHT1, faWhite, faLightBlue, lightDirection );
            gl.glPushMatrix();
            	gl.glTranslatef(3200, 240 + 190, 0);  // then move forward on x,z axis (staying on ground, so no Y motion)
            	gl.glScalef(0.00001f, 0.00001f, 0.00000004f);
                gl.glRotatef(180, 1f, 0f, 0);   // first rotate around y axis
            	gl.glTranslatef(-3200, -240 - 190, 0);  // then move forward on x,z axis (staying on ground, so no Y motion)
            	gl.glRotatef(cameraRotation+=0.2f, 0f, 1f, 0);
                model.render(gLDrawable);
            gl.glPopMatrix();

            gl.glFlush();
        }
        public FloatBuffer allocFloats(float[] floatarray) {
            FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            fb.put(floatarray).flip();
            return fb;
        }
        public void setLight(GL gl, int GLLightHandle,
                float[] diffuseLightColor, float[] ambientLightColor, float[] position )
            {
        	
                FloatBuffer ltDiffuse = allocFloats(diffuseLightColor);
                FloatBuffer ltAmbient = allocFloats(ambientLightColor);
                FloatBuffer ltPosition = allocFloats(position);
                gl.glLightfv(GLLightHandle, GL.GL_DIFFUSE, ltDiffuse);   // color of the direct illumination
                gl.glLightfv(GLLightHandle, GL.GL_SPECULAR, ltDiffuse);  // color of the highlight
                gl.glLightfv(GLLightHandle, GL.GL_AMBIENT, ltAmbient);   // color of the reflected light
                gl.glLightfv(GLLightHandle, GL.GL_POSITION, ltPosition);
                gl.glEnable(GLLightHandle);	// Enable the light (GL_LIGHT1 - 7)
                //GL11.glLightf(GLLightHandle, GL11.GL_QUADRATIC_ATTENUATION, .005F);    // how light beam drops off
            }

        /** Called when the display mode has been changed.  <B>!! CURRENTLY UNIMPLEMENTED IN JOGL !!</B>
         * @param gLDrawable The GLDrawable object.
         * @param modeChanged Indicates if the video mode has changed.
         * @param deviceChanged Indicates if the video device has changed.
         */
        public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

        /** Called by the drawable immediately after the OpenGL context is
         * initialized for the first time. Can be used to perform one-time OpenGL
         * initialization such as setup of lights and display lists.
         * @param gLDrawable The GLDrawable object.
         */
        public void init(GLAutoDrawable gLDrawable)
        {
            final GL gl = gLDrawable.getGL();

            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.3f);
            gl.glClearDepth(1.0f);
            gl.glEnable(GL.GL_DEPTH_TEST);
            gl.glDepthFunc(GL.GL_LEQUAL);
            gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

            if (!model.isLoaded())
                model.load(gLDrawable, "globe.3ds");
        }


        /** Called by the drawable during the first repaint after the component has
         * been resized. The client can update the viewport and view volume of the
         * window appropriately, for example by a call to
         * GL.glViewport(int, int, int, int); note that for convenience the component
         * has already called GL.glViewport(int, int, int, int)(x, y, width, height)
         * when this method is called, so the client may not have to do anything in
         * this method.
         * @param gLDrawable The GLDrawable object.
         * @param x The X Coordinate of the viewport rectangle.
         * @param y The Y coordinate of the viewport rectanble.
         * @param width The new width of the window.
         * @param height The new height of the window.
         */
        public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
        {
            final GL gl = gLDrawable.getGL();
            final GLU glu = new GLU();

            if (height <= 0) // avoid a divide by zero error!
                height = 1;
            final float h = (float)width / (float)height;
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrtho(-1000, 1000, -1000, 1000, -10000, 10000);
            gl.glMatrixMode(GL.GL_MODELVIEW);
            gl.glLoadIdentity();
        }
    }
}
