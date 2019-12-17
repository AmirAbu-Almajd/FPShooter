package engineTester;

import models.RawModel;
import models.TexturedModel;

import java.awt.event.MouseEvent;
import java.util.Random;

import javax.security.auth.x500.X500Principal;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

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
		Renderer renderer = new Renderer(shader);
		Renderer renderer2 = new Renderer(lightShader);
		int screen = 1;
		Light light = new Light(new Vector3f(0,-3,0),new Vector3f(1.0f,1.0f,1.0f));
		//raw models
		RawModel load_raw = loader.loadToVAO2D(matrices.loading_bar, matrices.load_tex, matrices.ind);
		RawModel cod_raw = loader.loadToVAO2D(matrices.cod_bg, matrices.tex, matrices.ind);
		RawModel start_raw = loader.loadToVAO2D(matrices.start_button, matrices.tex, matrices.ind);
		RawModel controls_raw = loader.loadToVAO2D(matrices.controls_button, matrices.tex, matrices.ind);
		RawModel controlsbg_raw = loader.loadToVAO2D(matrices.controls_bg, matrices.tex, matrices.ind);
		RawModel smgRaw = OBJLoader.loadObjModel("smg1", loader);
		//textured models
		TexturedModel smgModel = new TexturedModel(smgRaw, new ModelTexture(loader.loadTexture("M24R_C.jpg", "JPG")));
		TexturedModel load_tex = new TexturedModel(load_raw, new ModelTexture(loader.loadTexture("green.png", "PNG")));
		TexturedModel cod_tex = new TexturedModel(cod_raw, new ModelTexture(loader.loadTexture("cod.jpg", "JPG")));
		TexturedModel start_tex = new TexturedModel(start_raw, new ModelTexture(loader.loadTexture	("start.jpg", "JPG")));
		TexturedModel controls_tex = new TexturedModel(controls_raw, new ModelTexture(loader.loadTexture("controls.jpg", "JPG")));
		TexturedModel controlsbg_tex = new TexturedModel(controlsbg_raw, new ModelTexture(loader.loadTexture("controls background.jpg", "JPG")));
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
		Entity cart = matrices.load3D("stall", "stallTexture.png", "PNG");
		Entity smg = new Entity(smgModel, new Vector3f(0.09f,-0.02f,-0.09f), 0, 0, 0, 0.06f);
		//Entities setup
		entity.setPosition(new Vector3f(0,0,-5));
		cart.setPosition(new Vector3f(0,-0.5f,-10));
		car.setScale(0.008f);
		car.setPosition(new Vector3f(0,-0.5f,-2.5f));
		smg.setRotY(200);
		smg.setPosition(new Vector3f(0,0,-10));
//		ModelTexture tekModelTexture = marioModel.getTexture();
//		tekModelTexture.setShineDamper(10);
//		tekModelTexture.setReflectivity(1);
		//camera setup
		Camera camera = new Camera();
		camera.setPosition(new Vector3f(0,2,0));
		while(!Display.isCloseRequested()){
			renderer.prepare();
//			System.out.println("X: "+Mouse.getX());
//			System.out.println("Y: "+Mouse.getY());
			if(screen==1)
			{
				shader2D.start();


				renderer.render2D(cod_tex, shader2D);
				renderer.render2D(start_tex, shader2D);
			    renderer.render2D(controls_tex, shader2D);
				if(Mouse.isButtonDown(0)) {
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
			else if(screen==2) {
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
				shader.start();

//				lightShader.start();
//				lightShader.loadLight(light);
//				Random rand = new Random();
//				float float_random1=rand.nextFloat();
//				float float_random2=rand.nextFloat();
//				float float_random3=rand.nextFloat();
//				light = new Light(new Vector3f(0,100,0), new Vector3f(float_random1,float_random2,float_random3));
				camera.move();
				shader.loadViewMatrix(camera);
				car.increaseRotation(0, 1, 0);
				renderer.render(cart, shader);
				//renderer.render(entity, shader);
				renderer2.render(car, lightShader);
//				for(int y=0;y<45;y++) {
//					renderer.render(pillars[y], shader);
//				}
				renderer.render(skybox, shader);
				renderer.render(ground, shader);
				renderer.render(smg, shader);
//				lightShader.stop();
				shader.stop();
			}
			DisplayManager.updateDisplay();
		}

		shader2D.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
