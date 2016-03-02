package mygame.window;

import mygame.log.Logger;
import static mygame.error.ErrorCodes.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	public static void buildDefault() {
		try {
			Display.setDisplayMode(new DisplayMode(720, 640));
			Display.setResizable(false);
			Display.create();
		} catch (LWJGLException e) {
			Logger.fatal("LWJGL Display could not be created! The application cannot continue.");
			e.printStackTrace();
			Display.destroy();
			System.exit(LWJGL_DISPLAY_CREATE_FAILURE);
		}
	}
	
}
