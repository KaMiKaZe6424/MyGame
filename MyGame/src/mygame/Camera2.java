package mygame;

import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.input.Mouse.*;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

public class Camera2 {
	
	private final float ratio;
	private float x = 0, y = 0, z = 0;
	private float rx = 0, ry = 0;
	private final float rz = 0;
	private float drx, dry;
	private float dx, dy, dz;
	private float dPosZ, dPosX, dPosY;
	private float lookSpeed = 2.0f;
	private float walkSpeed = 0.01f;
	
	private boolean inverted = false;
	
	
	private long lastTime;
	
	public Camera2() {
		ratio = 16f/9f;
	}
	
	public Camera2(float ratio) {
		this.ratio = ratio;
	}
	
	public Camera2(float ratio, float x, float y, float z) {
		this.ratio = ratio;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Camera2(float ratio, float x, float y, float z, float rx, float ry) {
		this.ratio = ratio;
		this.rx = rx;
		this.ry = ry;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void update() {
		input();
		if (inverted) {
			glRotatef(drx, 0, 1, 0);
			glRotatef(dry, 1, 0, 0);
		} else {
			glRotatef(drx, 0, 1, 0);
			glRotatef(-dry, 1, 0, 0);
		}
		glTranslatef(dPosX, dPosY, dPosZ);
	}
	
	public void input() {
		drx = (float) toRadians(((float) getDX()) * lookSpeed);
		dry = (float) toRadians(((float) getDY()) * lookSpeed);
		dPosZ = (float)  ((isKeyDown(KEY_W)) ? (System.currentTimeMillis() - lastTime) * walkSpeed : 0);
		dPosZ = (float) -((isKeyDown(KEY_S)) ? (System.currentTimeMillis() - lastTime) * walkSpeed : 0);
		dPosX = (float)  ((isKeyDown(KEY_D)) ? (System.currentTimeMillis() - lastTime) * walkSpeed : 0);
		dPosX = (float) -((isKeyDown(KEY_A)) ? (System.currentTimeMillis() - lastTime) * walkSpeed : 0);
		lastTime = System.currentTimeMillis();
	}
	
	public void setRotation(float rx, float ry) {
		this.rx = rx;
		this.ry = ry;
	}
	
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
}
