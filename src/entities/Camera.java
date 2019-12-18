package entities;

import java.awt.event.MouseEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;


public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch=0;
	private float yaw=0;
	private float roll;
	public float oldX=628,oldY=363,newX,newY;
	private Vector3f direction = new Vector3f((float)(-Math.cos(yaw) * Math.sin(pitch))
			, (float)(Math.sin(yaw))
			, (float)(-Math.cos(yaw) * Math.cos(pitch)));
	
	public Camera(){}
	
	public void move(){
		newX = Mouse.getX();
		newY = Mouse.getY();
		float jump = 0.06f;

//			direction = new Vector3f((float)(-Math.cos(pitch) * Math.sin(yaw))
//				, (float)(Math.sin(pitch))
//				, (float)(-Math.cos(pitch) * Math.cos(yaw)));
		
		float x = (float) Math.sin(Math.toRadians(-yaw)) * jump;
		float z = (float) Math.cos(Math.toRadians(-yaw)) * jump;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.x-=x;
			position.z-=z;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=z;
			position.z-=x;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=z;
			position.z+=x;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.x+=x;
			position.z+=z;
		}
		float dx = Mouse.getX()-oldX;
		float dy = Mouse.getY()-oldY;
		yaw+=dx*jump;
		pitch-=dy*jump;
		if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Mouse.setCursorPosition(628, 363);
		}
	}
	



	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
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

	public void setPitch(float pitch) {
		this.pitch += pitch;
	}

	public void setYaw(float yaw) {
		this.yaw += yaw;
	}
	
	

}
