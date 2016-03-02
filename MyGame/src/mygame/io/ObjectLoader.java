package mygame.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mygame.log.Logger;
import mygame.model.Face;
import mygame.model.Model;

import org.lwjgl.util.vector.Vector3f;

public class ObjectLoader {
	
	public static Model loadOBJ(String s) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(s)));
		String line;
		Model m = new Model();
		int i = 0;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("v ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				m.vert.add(new Vector3f(x, y, z));
			} else if (line.startsWith("vn ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				m.norm.add(new Vector3f(x, y, z));
			} else if (line.startsWith("f ")) {
				Vector3f vertInd = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[0]),
											 Float.valueOf(line.split(" ")[2].split("/")[0]),
											 Float.valueOf(line.split(" ")[3].split("/")[0]));
				Vector3f normInd = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[2]),
						 Float.valueOf(line.split(" ")[2].split("/")[2]),
						 Float.valueOf(line.split(" ")[3].split("/")[2]));
				m.faces.add(new Face(vertInd, normInd));
			}
			i++;
		}
		Logger.info(m.vert.size() + ", " + m.norm.size() + ", " + m.faces.size());
		reader.close();
		return m;
	}
	
}
