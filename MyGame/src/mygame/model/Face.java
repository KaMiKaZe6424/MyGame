package mygame.model;

import org.lwjgl.util.vector.Vector3f;

public class Face {
	
	public Vector3f ind = new Vector3f();
	public Vector3f norm = new Vector3f();
	
	public Face(Vector3f ind, Vector3f norm) {
		this.ind = ind;
		this.norm = norm;
	}
	
}
