package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	private Vector3f position;
	private Vector3f colour;
	public Light(Vector3f vector3f, Vector3f vector3f2) {
		this.position = vector3f;
		this.colour=vector3f2;
	}
	public Vector3f getPositions() {
		return position;
	}
	public void setPositions(Vector3f positions) {
		this.position = positions;
	}
	public Vector3f getColour() {
		return colour;
	}
	public void setColour(Vector3f colour) {
		this.colour = colour;
	}

}
