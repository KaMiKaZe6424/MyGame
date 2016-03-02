package mygame.util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import mygame.log.Logger;

import org.lwjgl.opengl.Display;

public class OpenGLUtil {
	
	public static void setupOpenGLgluPerspective() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective((float) 30, (float) Display.getWidth() / (float) Display.getHeight(), 0.001f, 250f);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);
        Logger.info("OpenGL " + glGetString(GL_VERSION));
	}
	
}
