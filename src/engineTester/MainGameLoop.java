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
int count=0,count2=0,c=0,c2=0;

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
		int screen = 1;
		int health = 100;
		float globalPitch=0;
		float globalYaw=0;
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
		Entity alien2 = matrices.load3D("Alien Animal", "dp2_gold.jpg", "JPG");
		Entity can = matrices.load3D("can", "can_CM.jpg", "JPG");
		Entity can2 = matrices.load3D("can", "can_CM.jpg", "JPG");

		//Entities setup
		can.setPosition(new Vector3f(19, 0f, 19f));
		can.setScale(5);
		can2.setPosition(new Vector3f(-20, 0f, -13f));
		can2.setScale(5);
		entity.setPosition(new Vector3f(0,0,-5));
		cart.setPosition(new Vector3f(0,-0.5f,-10));
		car.setScale(0.012f);
		car3.setScale(0.012f);
		alien.setScale(0.2f);
		alien.increasePosition(50, 0, 50);
		alien2.setScale(0.2f);
		alien2.increasePosition(-50, 0, -50);
		car.setPosition(new Vector3f(0,-0.5f,-2.5f));	
		sniper.setPosition(new Vector3f(0.5f,-0.8f,-15.0f));
		Weapons.bullet.setPosition(new Vector3f(555f,555f,555f));
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

		Camera camera = new Camera();
		camera.setPosition(new Vector3f(0,2,0));
		while(!Display.isCloseRequested())
		{
			renderer.prepare();

			if(screen==1)
			{
				count=0;
				count2=0;
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
				System.out.println("Health : "+ health);
				shader2D.start();
				Vector3f cameraP2=camera.getPosition();
				Vector3f alienP2=alien.getPosition();
				Vector3f alien2P2=alien2.getPosition();

				double can11= Math.sqrt(Math.pow(cameraP2.x-19,2)+Math.pow(cameraP2.z-19,2));
				double can22= Math.sqrt(Math.pow(cameraP2.x+20,2)+Math.pow(cameraP2.z+13,2));
				double aliencoll= Math.sqrt(Math.pow(cameraP2.x-alienP2.x,2)+Math.pow(cameraP2.z-alienP2.z,2));
				double aliencoll2= Math.sqrt(Math.pow(cameraP2.x-alien2P2.x,2)+Math.pow(cameraP2.z-alien2P2.z,2));
				if(aliencoll2>4)
				{
					
					int tmpHealth = 24-(health/25)*6;
					renderer.render_loading(health_tex, shader2D,tmpHealth);
				}
				else
				{
					if(can22<=4|can11<=4)
					{
						health=100;
					}
					else
					health-=10;
					if(health<25) {
					screen=1;
					Vector3f v=new Vector3f(0,2,0);
					camera.setPosition(v);			
					alien2.setPosition(new Vector3f(50f,0f,50f));
					alien.setPosition(new Vector3f(-50f,0f,-50f));
					health=100;
					}
				}
				
				if(aliencoll>4)
				{
					int tmpHealth = 24-(health/25)*6;
					renderer.render_loading(health_tex, shader2D,tmpHealth);
				}
				else
				{
					if(can22<=4|can11<=4)
					{
						health=100;
					}
					else
					health-=5;
					if(health<25) {
					screen=1;
					Vector3f v=new Vector3f(0,2,0);
					camera.setPosition(v);			
					alien2.setPosition(new Vector3f(50f,0f,50f));
					alien.setPosition(new Vector3f(-50f,0f,-50f));
					health=100;
					}

				}


				shader.stop();
				shader2D.stop();
				shader.start();
				camera.move();
				shader.loadLight(light);

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
				renderer.render(car3, shader);
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
				{
					screen=1;
					Vector3f v=new Vector3f(0,2,0);
					camera.setPosition(v);			
					alien2.setPosition(new Vector3f(50f,0f,50f));
					alien.setPosition(new Vector3f(-50f,0f,-50f));
					health=100;				
					}
				if(Mouse.isButtonDown(0))
				{

					float v = 0.4f;
					float x2 = (float) Math.sin(Math.toRadians(-camera.getYaw())) * v;
					float z2 = (float) Math.cos(Math.toRadians(-camera.getYaw())) * v;
					Weapons.bullet.setPosition(new Vector3f(camera.getPosition().x-x2,camera.getPosition().y,camera.getPosition().z-z2));
					Vector3f bulletP = Weapons.bullet.getPosition();
					float xd1 = bulletP.x - camera.getPosition().x;
					float yd1 = bulletP.z - camera.getPosition().z;
					double angle = Math.toDegrees(Math.atan2(xd1,yd1));
					Weapons.bullet.setRotZ((float)angle+180);
					globalPitch=z2;
					globalYaw=x2;
					bulletOut=true;
				}
				if(bulletOut) 
				{
					Weapons.bullet.increasePosition(0, 0, -globalPitch);
					Weapons.bullet.increasePosition(-globalYaw, 0, 0);
				}
				if(bulletOut)
				renderer.render(Weapons.bullet, shader);
				renderer.render(skybox, shader);
				renderer.render(ground, shader);
				renderer.render(sniper, shader);
				renderer.render(tower, shader);
				renderer.render(can, shader);
				renderer.render(can2, shader);

				Vector3f bulletp=Weapons.bullet.getPosition();



				
				Vector3f cameraP = camera.getPosition();
				Vector3f alienP = alien.getPosition();
				double bulletcoll= Math.sqrt(Math.pow(alienP.x-bulletp.x,2)+Math.pow(alienP.z-bulletp.z,2));
				
				float xd1 = alienP.x-cameraP.x;
				float yd1 = alienP.z-cameraP.z;
				float jmpy=0.05f;
				float jmpx=0.05f;
				if(yd1<0)
					jmpy*=-1;
				if(xd1<0)
					jmpx*=-1;
				
				if(count==0)
				{
					if(bulletcoll>3)
					{
					double towerColl= Math.sqrt(Math.pow(0f-alienP.x,2)+Math.pow(-30f-alienP.z,2));
					double cartColl= Math.sqrt(Math.pow(0f-alienP.x,2)+Math.pow(-10f-alienP.z,2));
					double carColl= Math.sqrt(Math.pow(10f-alienP.x,2)+Math.pow(0f-alienP.z,2));
					if(towerColl>5&cartColl>5&carColl>5)
					{
					renderer.render(alien, shader);
					alien.increasePosition(0, 0, -jmpy);
					alien.increasePosition(-jmpx, 0, 0);
					double angle = Math.toDegrees(Math.atan2(xd1,yd1));
					alien.setRotY((float)angle+180);
					}
					else
					{
						renderer.render(alien, shader);
						alien.increasePosition(0, 0, -1);
						alien.increasePosition(-1, 0, 0);
						double angle = Math.toDegrees(Math.atan2(xd1,yd1));
						alien.setRotY((float)angle+180);
					}
					}
				else
				{
					alien.setPosition(new Vector3f(555,555,555));
					count=1;
				}
				
				}
				 cameraP = camera.getPosition();
				 System.out.println(cameraP.x);
				 System.out.println(cameraP.z);

				Vector3f alien2P = alien2.getPosition();
				float xd2 = alien2P.x-cameraP.x;
				float yd2 = alien2P.z-cameraP.z;
				bulletp=Weapons.bullet.getPosition();
				double bulletcoll2= Math.sqrt(Math.pow(alien2P.x-bulletp.x,2)+Math.pow(alien2P.z-bulletp.z,2));
		
				if(count2==0){
					if(bulletcoll2>3)
					{
						double towerColl= Math.sqrt(Math.pow(0f-alien2P.x,2)+Math.pow(-30f-alien2P.z,2));
						double cartColl= Math.sqrt(Math.pow(0f-alien2P.x,2)+Math.pow(-10f-alien2P.z,2));
						double carColl= Math.sqrt(Math.pow(10f-alien2P.x,2)+Math.pow(0f-alien2P.z,2));
						if(towerColl>5&cartColl>5&carColl>5)
						{
						renderer.render(alien2, shader);
						alien2.increasePosition(0, 0, -yd2*0.005f);
						alien2.increasePosition(-xd2*0.005f, 0, 0);
						double angle = Math.toDegrees(Math.atan2(xd2,yd2));
						alien2.setRotY((float)angle+180);
						}else
						{
							renderer.render(alien2, shader);
							alien2.increasePosition(0, 0, -1*0.005f);
							alien2.increasePosition(-1*0.005f, 0, 0);
							double angle = Math.toDegrees(Math.atan2(xd2,yd2));
							alien2.setRotY((float)angle+180);
						}
					}
					else
					{
						alien2.setPosition(new Vector3f(555,555,555));
						count2=1;
					}
					
					}
				
			}
				
			
			DisplayManager.updateDisplay();
		}

		shader2D.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
