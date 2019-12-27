package entities;
import java.applet.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//import javax.print.DocFlavor.INPUT_STREAM;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import engineTester.Weapons;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.audio.*;
public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch=0;
	private float yaw=0;
//	public AudioClip gunShot = g
	private float roll;
	public float oldX=628,oldY=363,newX,newY;
	private Vector3f direction = new Vector3f((float)(-Math.cos(yaw) * Math.sin(pitch))
			, (float)(Math.sin(yaw))
			, (float)(-Math.cos(yaw) * Math.cos(pitch)));
	
	public void playAudio(String songName) {
		try {
			FileInputStream fileInputStream = new FileInputStream("Resources/"+songName+".mp3");
			Player player = new Player(fileInputStream);
			player.play();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Camera(){}
	
	public void move(){
		
		newX = Mouse.getX();
		newY = Mouse.getY();
		float jump = 0.06f;

//			direction = new Vector3f((float)(-Math.cos(pitch) * Math.sin(yaw))
//				, (float)(Math.sin(pitch))
//				, (float)(-Math.cos(pitch) * Math.cos(yaw)));
		int flag=0;
		double towerColl= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(-30f-position.z,2));
		double cartColl= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(-10f-position.z,2));
		double carColl= Math.sqrt(Math.pow(10f-position.x,2)+Math.pow(0f-position.z,2));
		
		float x = (float) Math.sin(Math.toRadians(-yaw)) * jump;
		float z = (float) Math.cos(Math.toRadians(-yaw)) * jump;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
//			Weapons.QBZ.increasePosition(0, 0, -jump);	
			//double towerColl= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(-35f-position.z,2));
			if((towerColl>4.9&cartColl>7&carColl>5)|flag==1) {
				position.x-=x;
				position.z-=z;
				//Weapons.QBZ.setPosition(new Vector3f(position.x+0.08f,1.95f,position.z-0.07f));
//				flag=0;
			}
			else
				flag=1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
//			Weapons.QBZ.increasePosition(jump, 0, 0);
			if((towerColl>4.9&cartColl>7&carColl>5)|flag==1) {
				position.x+=z;
				position.z-=x;
//				flag=0;
				//Weapons.QBZ.setPosition(new Vector3f(position.x+0.08f,1.95f,position.z-0.07f));
				
			}else
				flag=1;

			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
//			Weapons.QBZ.increasePosition(-jump, 0, 0);
			//double ff= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(-35f-position.z,2));
			if((towerColl>4.9&cartColl>7&carColl>5)|flag==1) {
				
				position.x-=z;
				position.z+=x;
//				flag=0;
				//Weapons.QBZ.setPosition(new Vector3f(position.x+0.08f,1.95f,position.z-0.07f));
				
				}else
				flag=1;
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
//			Weapons.QBZ.increasePosition(0, 0, jump);
			//double ff= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(-35f-position.z,2));
			if((towerColl>4.9&cartColl>7&carColl>5)|flag==1) {
				
				position.x+=x;
				position.z+=z;
				//Weapons.QBZ.setPosition(new Vector3f(position.x+0.08f,1.95f,position.z-0.07f));
//				flag=0;
				}else
				flag=1;
			
		}
//		if(Mouse.isButtonDown(0)) {
//			playAudio();
//		}
		float dx = Mouse.getX()-oldX;
		float dy = Mouse.getY()-oldY;
		yaw+=dx*jump;
		pitch-=dy*jump;
	//	Weapons.QBZ.increaseRotation(dy*jump, dx*jump, 0);
	//	Weapons.QBZ.increasePosition(dy*jump, 0, dx*jump);
		if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
		{
			Mouse.setCursorPosition(628, 363);
		}
	
		if(Mouse.isButtonDown(0))
		{
		Thread bulletSound=new Thread(new Runnable() 
		{   @Override 
		     public void run()
			{
//			playAudio("bew");
			}		 
		});
		bulletSound.start();
		}
		if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
		{
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
