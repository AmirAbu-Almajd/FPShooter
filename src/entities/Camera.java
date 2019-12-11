package entities;

import java.awt.event.MouseEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(){}
	
	public void move(){
		float jump = 0.02f;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=jump;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=jump;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=jump;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z+=jump;
		}

	}



	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	

}
