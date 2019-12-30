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
	
	int flag_WandS=0;
	int flag_AandD=0;

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

		double towerColl= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(-30f-position.z,2));
		double cartColl= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(-10f-position.z,2));
		double carColl= Math.sqrt(Math.pow(10f-position.x,2)+Math.pow(0f-position.z,2));
//		double skyBoxColl= Math.sqrt(Math.pow(0f-5.6,2)+Math.pow(0f-98,2));
//		double skyBoxColl2= Math.sqrt(Math.pow(0f-position.x,2)+Math.pow(0f-position.z,2));
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) 
		{
			jump=0.1f;
		}
		System.out.println("x : "+position.x+"\ny : "+position.z+"\n");
		float x = (float) Math.sin(Math.toRadians(-yaw)) * jump;
		float z = (float) Math.cos(Math.toRadians(-yaw)) * jump;

	
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){

			if((towerColl>4.9&cartColl>7&carColl>5&position.x<98&position.x>-98&position.z<98&position.z>-98)|flag_WandS==1) {
				position.x-=x;
				position.z-=z;
				flag_WandS=0;
			}
			else {
				flag_WandS=1;
				position.x+=2.5*x;
				position.z+=2.5*z;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			if((towerColl>4.9&cartColl>7&carColl>5&position.x<98&position.x>-98&position.z<98&position.z>-98)|flag_AandD==1) {
				position.x+=z;
				position.z-=x;
				flag_AandD=0;

				
			}else {
				flag_AandD=1;
				position.x-=2.5*z;
				position.z+=2.5*x;
			}
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){

			if((towerColl>4.9&cartColl>7&carColl>5&position.x<98&position.x>-98&position.z<98&position.z>-98)|flag_WandS==1) {
				
				position.x-=z;
				position.z+=x;
				flag_AandD=0;

				
				}else
				{
					flag_AandD=1;
					position.x+=2.5*z;
					position.z-=2.5*x;
				}
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			if((towerColl>5&cartColl>7&carColl>5&position.x<98&position.x>-98&position.z<98&position.z>-98)|flag_WandS==1) {
				position.x+=x;
				position.z+=z;
				flag_WandS=0;

				}else
				{			
					position.x-=2.5*x;
					position.z-=2.5*z;
					flag_WandS=1;

				}
		}
		
		float dx = Mouse.getX()-oldX;
		float dy = Mouse.getY()-oldY;
		yaw+=dx*jump;
		pitch-=dy*jump;
	
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
			playAudio("bew");
			}		 
		});
		bulletSound.start();
		}
//		if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
//		{
//			Mouse.setCursorPosition(628, 363);
//		}
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
