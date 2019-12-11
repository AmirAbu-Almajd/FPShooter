package engineTester;

import models.RawModel;
import models.TexturedModel;

import java.awt.event.MouseEvent;

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

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader("vertexShader.txt","fragmentShader.txt");
		StaticShader shader2D = new StaticShader("2DvertexShader.txt","2DfragmentShader.txt");
		Renderer renderer = new Renderer(shader);
		int screen = 1;
		//raw models
		RawModel model = loader.loadToVAO(matrices.vertices,matrices.textureCoords,matrices.indices);
		RawModel load_raw = loader.loadToVAO(matrices.loading_bar, matrices.load_tex, matrices.ind);
		RawModel cod_raw = loader.loadToVAO(matrices.cod_bg, matrices.tex, matrices.ind);
		RawModel start_raw = loader.loadToVAO(matrices.start_button, matrices.tex, matrices.ind);
		RawModel controls_raw = loader.loadToVAO(matrices.controls_button, matrices.tex, matrices.ind);
		RawModel controlsbg_raw = loader.loadToVAO(matrices.controls_bg, matrices.tex, matrices.ind);
		RawModel marioRaw = OBJLoader.loadObjModel("dpv", loader);
		//textured models
		TexturedModel marioModel = new TexturedModel(marioRaw, new ModelTexture(loader.loadTexture("dp2.png", "PNG")));
		TexturedModel load_tex = new TexturedModel(load_raw, new ModelTexture(loader.loadTexture("green.png", "PNG")));
		TexturedModel cod_tex = new TexturedModel(cod_raw, new ModelTexture(loader.loadTexture("cod.jpg", "JPG")));
		TexturedModel start_tex = new TexturedModel(start_raw, new ModelTexture(loader.loadTexture	("start.jpg", "JPG")));
		TexturedModel controls_tex = new TexturedModel(controls_raw, new ModelTexture(loader.loadTexture("controls.jpg", "JPG")));
		TexturedModel controlsbg_tex = new TexturedModel(controlsbg_raw, new ModelTexture(loader.loadTexture("controls background.jpg", "JPG")));
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white2.png","PNG")));
		//entities setup
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-5),0,0,0,1);
		Entity mario = new Entity(marioModel, new Vector3f(0,-3,0),0,0,0,0.5f);
		mario.increasePosition(0, 0, -40);
		mario.setScale(0.08f);
//		Entity load_model = new Entity(load_tex, new Vector3f(0,0,0),0,0,0,1);

		//camera setup
		Camera camera = new Camera();
		
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
				camera.move();
				shader.loadViewMatrix(camera);
				//mario.increaseRotation(0, 1, 0);
				renderer.render(entity, shader);
				renderer.render(mario, shader);
				
				shader.stop();
			}
			DisplayManager.updateDisplay();
		}

		shader2D.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
