package mygame;

import static mygame.util.OpenGLUtil.*;
import static org.lwjgl.opengl.GL11.*;
import mygame.io.ObjectLoader;
import mygame.log.Logger;
import mygame.model.Face;
import mygame.model.Model;
import mygame.window.Window;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class MyGame {
	
	//Initialize variables here_
	public static GameState state = GameState.PREINIT;
	
	private Thread rendering;
	private Thread logic;
	
	private static final long LOGIC_SPEED = 50L;
	private static int renderSpeed = 60;
	
	private static long lastLogic;
	private static long lastFrame;
	
	private boolean stop = false;
	

	Model m;
	
	
	private MyGame() {
		Logger.info("Starting MyGame...");
		Logger.debug("Entering the PREINIT state.");
		
		
		init();
	}

	private void init() {
		state = GameState.INIT;
		Logger.debug("Entering the INIT state.");
		rendering = new Thread(new Runnable() {@Override public void run() {render();}});
		logic = new Thread(new Runnable() {@Override public void run() {logic();}});
		
		try {
			m = ObjectLoader.loadOBJ("res/models/mokey.obj");
		} catch (Exception e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(-1);
		}
		

		Mouse.setGrabbed(true);
		
		lastLogic =  System.currentTimeMillis();
		rendering.start();

		state = GameState.MAIN_MENU;
	}
	
	private void logic() {
		while (!stop) {
			//Do the logic stuff
			{
				
			}
			syncLogic();
		}
	}
	
	int bunnyDisplayList;
	Camera2 c;
	
	private void render() {
		Window.buildDefault();
		setupOpenGLgluPerspective();
		logic.start();
		
		bunnyDisplayList = glGenLists(1);
        glNewList(bunnyDisplayList, GL_COMPILE);
		glBegin(GL_TRIANGLES);
        for (Face face : m.faces) {
            Vector3f n1 = m.norm.get((int) face.norm.x - 1);
            glNormal3f(n1.x, n1.y, n1.z);
            Vector3f v1 = m.vert.get((int) face.ind.x - 1);
            glVertex3f(v1.x, v1.y, v1.z);
            Vector3f n2 = m.norm.get((int) face.norm.y - 1);
            glNormal3f(n2.x, n2.y, n2.z);
            Vector3f v2 = m.vert.get((int) face.ind.y - 1);
            glVertex3f(v2.x, v2.y, v2.z);
            Vector3f n3 = m.norm.get((int) face.norm.z - 1);
            glNormal3f(n3.x, n3.y, n3.z);
            Vector3f v3 = m.vert.get((int) face.ind.z - 1);
            glVertex3f(v3.x, v3.y, v3.z);
        }
        glEnd();
        glEndList();

		c = new Camera2(((float) Display.getWidth() / (float) Display.getHeight()), 0, 0, 0);
        
		
		while (!Display.isCloseRequested()) {
	        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	        checkInput();
	        c.update();
	        
	        
			//Render whatever you want
				{
					glCallList(bunnyDisplayList);
				}
			Display.update();
			Display.sync(renderSpeed);
		}
		stop = true;
		terminate();
	}
	
	private void terminate() {
		Display.destroy();
		Mouse.setGrabbed(false);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new MyGame();
	}
	
	private void checkInput() {
		lastFrame = System.currentTimeMillis();
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) terminate();
	}
	
	private void syncLogic() {
		try {
			int timeout = (int) (LOGIC_SPEED - (System.currentTimeMillis() - lastLogic));
			Thread.sleep((timeout > 0) ? timeout : 0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lastLogic = System.currentTimeMillis();
	}
	
}
