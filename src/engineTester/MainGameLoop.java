package engineTester;

import models.RawModel;
import models.TexturedModel;

import java.awt.event.MouseEvent;
import java.util.Random;

import javax.security.auth.x500.X500Principal;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import org.omg.CORBA.PUBLIC_MEMBER;
import java.applet.*;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MainGameLoop {
	public static void main(String[] args) {


		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader("vertexShader.txt","fragmentShader.txt");
		StaticShader lightShader = new StaticShader("vertexShader - light.txt","fragmentShader - light.txt");
		StaticShader shader2D = new StaticShader("2DvertexShader.txt","2DfragmentShader.txt");
		StaticShader wShader = new StaticShader("weaponVertex.txt","weaponFragment.txt");
		Renderer renderer = new Renderer(shader);
		Renderer wRenderer = new Renderer(wShader);
		boolean weaponSwitch = false;
		boolean bulletOut = false;
		float bulletYaw=0;
		float bulletPitch=0;
		int screen = 1;
		int health = 100;
		Vector3f bulletTarget= new Vector3f(0,0,0);
		Light light = new Light(new Vector3f(0,100,0),new Vector3f(1.0f,1.0f,1.0f));
		//raw models
		RawModel load_raw = loader.loadToVAO2D(matrices.loading_bar, matrices.load_tex, matrices.ind);
		RawModel cod_raw = loader.loadToVAO2D(matrices.cod_bg, matrices.tex, matrices.ind);
		RawModel start_raw = loader.loadToVAO2D(matrices.start_button, matrices.tex, matrices.ind);
		RawModel controls_raw = loader.loadToVAO2D(matrices.controls_button, matrices.tex, matrices.ind);
		RawModel controlsbg_raw = loader.loadToVAO2D(matrices.controls_bg, matrices.tex, matrices.ind);
		RawModel health_raw = loader.loadToVAO2D(matrices.health_bar, matrices.load_tex, matrices.ind);
		RawModel car2 = OBJLoader.loadObjModel("dpv", loader);
		//textured models
		TexturedModel load_tex = new TexturedModel(load_raw, new ModelTexture(loader.loadTexture("green.png", "PNG")));
		TexturedModel cod_tex = new TexturedModel(cod_raw, new ModelTexture(loader.loadTexture("cod.jpg", "JPG")));
		TexturedModel start_tex = new TexturedModel(start_raw, new ModelTexture(loader.loadTexture	("start.jpg", "JPG")));
		TexturedModel controls_tex = new TexturedModel(controls_raw, new ModelTexture(loader.loadTexture("controls.jpg", "JPG")));
		TexturedModel controlsbg_tex = new TexturedModel(controlsbg_raw, new ModelTexture(loader.loadTexture("controls background.jpg", "JPG")));
		TexturedModel carTEx = new TexturedModel(car2, new ModelTexture(loader.loadTexture("dp2.png", "PNG")));
		TexturedModel health_tex = new TexturedModel(health_raw, new ModelTexture(loader.loadTexture("green.png", "PNG")));
		//entities initialization
		
		Entity entity = matrices.load2D(matrices.vertices,matrices.textureCoords , matrices.indices, "white2.png", "PNG");
		Entity pillars[]= new Entity[45];
		int f=-50;
		int r=0;
//		for(int j=0;j<45;j++)
//		{
//			pillars[j]=matrices.load2D(matrices.pillar,matrices.textureCoords , matrices.indices, "Wall.jpg", "JPG");
//			pillars[j].setPosition(new Vector3f(f,-0.5f,r));
//			f+=5;
//			if(f==25)
//			{
//				f= -50;
//				r= -15;
//			}
//			
//		}
		System.out.println(f);
		Entity skybox = matrices.load2D(matrices.skybox,matrices.textureCoords , matrices.indices, "sky.jpg", "JPG");
		Entity ground = matrices.load2D(matrices.ground,matrices.tex , matrices.ind, "Ground2.jpg", "JPG");		
		Entity car = matrices.load3D("dpv", "dp2.png", "PNG");
		Entity car3 = new Entity(carTEx, new Vector3f(10,-0.5f,0),0, 0, 0, 1);
		Entity sniper = matrices.load3D("KSR-29 sniper rifle new_obj", "Sniper_KSR_29_Col.jpg", "JPG");
		Entity tower=matrices.load3D("wooden watch tower2", "Wood_Tower_Col.JPG", "JPG");
		Entity cart = matrices.load3D("stall", "stallTexture.png", "PNG");
		Entity alien = matrices.load3D("Alien Animal", "dp2.png", "PNG");
		//Entities setup
		entity.setPosition(new Vector3f(0,0,-5));
		cart.setPosition(new Vector3f(0,-0.5f,-10));
		car.setScale(0.012f);
		car3.setScale(0.012f);
		alien.setScale(0.2f);
		alien.increasePosition(0, 0, 10);
		car.setPosition(new Vector3f(0,-0.5f,-2.5f));	
		sniper.setPosition(new Vector3f(0.5f,-0.8f,-15.0f));
		Weapons.bullet.setPosition(new Vector3f(0.0f,2.0f,15.0f));
		Weapons.bullet.setRotX(-90);
		Weapons.bullet .setScale(0.1f);
		sniper.setScale(0.5f);
		tower.setPosition(new Vector3f(0,-0.5f,-30f));
		Weapons.smg.setRotY(200);
		Weapons.QBZ.setPosition(new Vector3f(0.08f,-0.05f,-0.07f));
		Weapons.QBZ.setScale(0.55f);
		Weapons.QBZ.setRotX(180);
		Weapons.QBZ.setRotZ(270);
		Weapons.QBZ.setRotY(-15);
		Weapons.smg.setPosition(new Vector3f(0.2f,-0.08f,-0.2f));
		Weapons.smg.setScale(0.1f);
//		ModelTexture tekModelTexture = carTEx.getTexture();
//		tekModelTexture.setShineDamper(10);
//		tekModelTexture.setReflectivity(1);
		//camera setup
		Camera camera = new Camera();
		camera.setPosition(new Vector3f(0,2,0));
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
//			System.out.println("X: "+Mouse.getX());
//			System.out.println("Y: "+Mouse.getY());
			if(screen==1)
			{
				shader2D.start();
				renderer.render2D(cod_tex, shader2D);
				renderer.render2D(start_tex, shader2D);
			    renderer.render2D(controls_tex, shader2D);
				if(Mouse.isButtonDown(0)) 
				{
					if(Mouse.getX()>=448&&Mouse.getX()<=832&&Mouse.getY()>=77&&Mouse.getY()<=249) {
						screen=2;
						shader2D.stop();
						continue;
					}
					if(Mouse.getX()>=448&&Mouse.getX()<=832&&Mouse.getY()>=361&&Mouse.getY()<=537) 
					{
						try {
							for(int h=0;h<=36;h+=6)
							{
							DisplayManager.updateDisplay();
							renderer.render_loading(load_tex, shader2D,h);
							DisplayManager.updateDisplay();
							Thread.sleep(50);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						screen=3;
						shader2D.stop();
						continue;
					}
					
				}

				shader2D.stop();

			}
			else if(screen==2) 
			{
				shader2D.start();
				renderer.render2D(controlsbg_tex, shader2D);
				if(Mouse.isButtonDown(0))
				{
					if(Mouse.getX()>=938&&Mouse.getX()<=1279&&Mouse.getY()>=0&&Mouse.getY()<=182)
					{
						screen=1;
						shader2D.stop();
						continue;
					}
					
				}
				shader2D.stop();
			}
			else if(screen==3) {
				shader2D.start();
				int tmpHealth = 24-(health/25)*6;
				renderer.render_loading(health_tex, shader2D,tmpHealth);
				shader2D.stop();
				shader.start();
				shader.loadLight(light);
				camera.move();
				System.out.println(camera.getPosition());
				System.out.println("Yaw : "+camera.getYaw()%180+"\nPitch : "+camera.getPitch()%180);
				shader.loadViewMatrix2(camera);
				if(Keyboard.isKeyDown(Keyboard.KEY_1))
				{
					if(weaponSwitch)
					{
						renderer.render(Weapons.QBZ, shader);						
						weaponSwitch=false;
					}

					else
					{
						renderer.render(Weapons.smg, shader);
						weaponSwitch=true;
					}

				}
				if(!weaponSwitch) 
				{
					renderer.render(Weapons.QBZ, shader);					
				}
				else
				{
					renderer.render(Weapons.smg, shader);
				}

				shader.loadViewMatrix(camera);

				car.increaseRotation(0, 1, 0);
				renderer.render(cart, shader);
				//renderer.render(entity, shader);
				renderer.render(car3, shader);
//				for(int y=0;y<45;y++) {
//					renderer.render(pillars[y], shader);
//				}
				if(Mouse.isButtonDown(0))
				{
					float v = 0.004f;
					float x2 = (float) Math.sin(Math.toRadians(-camera.getYaw())) * v;
					float z2 = (float) Math.cos(Math.toRadians(-camera.getYaw())) * v;
					Weapons.bullet.setPosition(new Vector3f(camera.getPosition().x-x2,camera.getPosition().y,camera.getPosition().z-z2));
					bulletTarget = new Vector3f(Weapons.bullet.getPosition().x*10,Weapons.bullet.getPosition().y,Weapons.bullet.getPosition().z*10);
//					if(bulletTarget.x<0)
//						bulletTarget= new Vector3f(bulletTarget.x-15,bulletTarget.y,bulletTarget.z);
//					else
//						bulletTarget= new Vector3f(bulletTarget.x+15,bulletTarget.y,bulletTarget.z);
//					if(bulletTarget.y<0)
//						bulletTarget= new Vector3f(bulletTarget.x,bulletTarget.y,bulletTarget.z-15);
//					else
//						bulletTarget= new Vector3f(bulletTarget.x,bulletTarget.y,bulletTarget.z+15);
//					Weapons.bullet.setPosition(bulletTarget);
					Vector3f bulletP = Weapons.bullet.getPosition();
					float xd1 = bulletP.x - camera.getPosition().x;
					float yd1 = bulletP.z - camera.getPosition().z;
					double angle = Math.toDegrees(Math.atan2(xd1,yd1));
					Weapons.bullet.setRotZ((float)angle+180);
					bulletPitch=z2;
					bulletYaw=x2;
					bulletOut=true;
					
				}
				if(bulletOut) 
				{

//					Vector3f bulletP = Weapons.bullet.getPosition();
//					float xd1 = bulletP.x - bulletTarget.x;
//					float jmpy = 0.005f;
//					float jmpx = 0.005f;
//					float yd1 = bulletP.z - bulletTarget.z;
					Weapons.bullet.increasePosition(0, 0, -bulletPitch);
					Weapons.bullet.increasePosition(-bulletYaw, 0, 0);
				}
				renderer.render(skybox, shader);
				renderer.render(ground, shader);
				renderer.render(Weapons.bullet, shader);
				renderer.render(sniper, shader);
				renderer.render(tower, shader);
				renderer.render(alien, shader);
				//renderer.render(car2, shader);
				//Enemy motion
				Vector3f alienP = alien.getPosition();
				Vector3f cameraP = camera.getPosition();
				float xd1 = alienP.x-cameraP.x;
				float yd1 = alienP.z-cameraP.z;
//				alien.increasePosition(0, 0, -yd1*0.005f);
//				alien.increasePosition(-xd1*0.005f, 0, 0);
				double angle = Math.toDegrees(Math.atan2(xd1,yd1));
				alien.setRotY((float)angle+180);
				shader.stop();
			}
			DisplayManager.updateDisplay();
		}

		shader2D.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
