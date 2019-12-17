package engineTester;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class matrices {
	public static float[] loading_bar= {
			//0
			-0.3f,-0.8f,-0.1f,	
			-0.15f,-0.8f,-0.1f,	
			-0.15f,-0.85f,-0.1f,	
			-0.15f,-0.85f,-0.1f,	
			-0.3f,-0.85f,-0.1f,
			-0.3f,-0.8f,-0.1f,		
			//6
			-0.3f,-0.8f,-0.1f,	
			-0.05f,-0.8f,-0.1f,	
			-0.05f,-0.85f,-0.1f,	
			-0.05f,-0.85f,-0.1f,	
			-0.3f,-0.85f,-0.1f,
			-0.3f,-0.8f,-0.1f,	
			//12
			-0.3f,-0.8f,-0.1f,	
			0.05f,-0.8f,-0.1f,	
			0.05f,-0.85f,-0.1f,	
			0.05f,-0.85f,-0.1f,	
			-0.3f,-0.85f,-0.1f,
			-0.3f,-0.8f,-0.1f,	
			//18
			-0.3f,-0.8f,-0.1f,	
			0.15f,-0.8f,-0.1f,	
			0.15f,-0.85f,-0.1f,	
			0.15f,-0.85f,-0.1f,	
			-0.3f,-0.85f,-0.1f,
			-0.3f,-0.8f,-0.1f,	
			//24
			-0.3f,-0.8f,-0.1f,	
			0.2f,-0.8f,-0.1f,	
			0.2f,-0.85f,-0.1f,	
			0.2f,-0.85f,-0.1f,	
			-0.3f,-0.85f,-0.1f,
			-0.3f,-0.8f,-0.1f,	
			//30
			-0.3f,-0.8f,-0.1f,	
			0.25f,-0.8f,-0.1f,	
			0.25f,-0.85f,-0.1f,	
			0.25f,-0.85f,-0.1f,	
			-0.3f,-0.85f,-0.1f,
			-0.3f,-0.8f,-0.1f,	
			//36
			-0.3f,-0.8f,-0.1f,	
			0.3f,-0.8f,-0.1f,	
			0.3f,-0.85f,-0.1f,	
			0.3f,-0.85f,-0.1f,	
			-0.3f,-0.85f,-0.1f,
			-0.3f,-0.8f,-0.1f,	
		};
	public static float[] cod_bg= {
			-1.0f,1.0f,0.0f,	
			1.0f,1.0f,0.0f,	
			1.0f,-1.0f,0.0f,	
			-1.0f,-1.0f,0.0f,	
		};
	public static float[] controls_bg= {
			-1.0f,1.0f,0.0f,	
			1.0f,1.0f,0.0f,	
			1.0f,-1.0f,0.0f,	
			-1.0f,-1.0f,0.0f,	
		};
	public static float[] start_button= {
			-0.3f,0.5f,-0.1f,	
			0.3f,0.5f,-0.1f,	
			0.3f,0.0f,-0.1f,	
			-0.3f,0.0f,-0.1f,	
		};
	public static float[] controls_button= {
			-0.3f,-0.3f,-0.1f,	
			0.3f,-0.3f,-0.1f,	
			0.3f,-0.8f,-0.1f,	
			-0.3f,-0.8f,-0.1f,	
		};
	public static float[] tex = {
			1,0,
			0,0,
			0,1,
			1,1,
	};
	public static float[] load_tex = {
			1,0,
			0,0,
			0,1,
			1,1,
	};
	public static int[] ind = {
			0,1,3,
			3,1,2,
	};
	public static float[] tempBox = {				
			-100.0f,-0.5f,100,
			-100.0f,-0.5f,-100,
			100.0f,-0.5f,-100,
			100.0f,-0.5f,100,
	};
	public static float[] vertices = {			
			-0.5f,0.5f,0,	
			-0.5f,-0.5f,0,	
			0.5f,-0.5f,0,	
			0.5f,0.5f,0,		
			
			-0.5f,0.5f,1,	
			-0.5f,-0.5f,1,	
			0.5f,-0.5f,1,	
			0.5f,0.5f,1,
			
			0.5f,0.5f,0,	
			0.5f,-0.5f,0,	
			0.5f,-0.5f,1,	
			0.5f,0.5f,1,
			
			-0.5f,0.5f,0,	
			-0.5f,-0.5f,0,	
			-0.5f,-0.5f,1,	
			-0.5f,0.5f,1,
			
			-0.5f,0.5f,1,
			-0.5f,0.5f,0,
			0.5f,0.5f,0,
			0.5f,0.5f,1,
			
			-0.5f,-0.5f,1,
			-0.5f,-0.5f,0,
			0.5f,-0.5f,0,
			0.5f,-0.5f,1
			
	};
	public static float[] pillar = {			
			-0.5f,30.0f,0,	
			-0.5f,-0.5f,0,	
			0.5f,-0.5f,0,	
			0.5f,30.0f,0,		
			
			-0.5f,30.0f,1,	
			-0.5f,-0.5f,1,	
			0.5f,-0.5f,1,	
			0.5f,30.0f,1,
			
			0.5f,30.0f,0,	
			0.5f,-0.5f,0,	
			0.5f,-0.5f,1,	
			0.5f,30.0f,1,
			
			-0.5f,30.0f,0,	
			-0.5f,-0.5f,0,	
			-0.5f,-0.5f,1,	
			-0.5f,30.0f,1,
			//roof
			-0.5f,30.0f,1,
			-0.5f,30.0f,0,
			0.5f,30.0f,0,
			0.5f,30.0f,1,
			//floor
			-0.5f,-0.5f,1,
			-0.5f,-0.5f,0,
			0.5f,-0.5f,0,
			0.5f,-0.5f,1
			
	};
	public static float[] skybox = {			
			-100.0f,100.0f,-100,	
			-100.0f,-100.0f,-100,	
			100.0f,-100.0f,-100,	
			100.0f,100.0f,-100,		
			
			-100.0f,100.0f,100,	
			-100.0f,-100.0f,100,	
			100.0f,-100.0f,100,	
			100.0f,100.0f,100,
			
			100.0f,100.0f,-100,	
			100.0f,-100.0f,-100,	
			100.0f,-100.0f,100,	
			100.0f,100.0f,100,
			
			-100.0f,100.0f,-100,	
			-100.0f,-100.0f,-100,	
			-100.0f,-100.0f,100,	
			-100.0f,100.0f,100,
			
			-100f,100.0f,100,
			-100f,100.0f,-100,
			100f,100.0f,-100,
			100f,100.0f,100,
			//floor
			-100.0f,-100.0f,100,
			-100.0f,-100.0f,-100,
			100.0f,-100.0f,-100,
			100.0f,-100.0f,100,
			
	};
	public static float[] ground= {
			-100.0f,-0.5f,100,
			-100.0f,-0.5f,-100,
			100.0f,-0.5f,-100,
			100.0f,-0.5f,100,
			
	};
	public static float[] temp= {
			-1.0f,0.0f,1,
			-1.0f,0.0f,-1,
			1.0f,0.0f,-1,
			1.0f,0.0f,1,
			
	};
	public static float[] temp2 = {
			0,0,
			0,1,
			1,1,
			1,0,
	};
	
	public static float[] textureCoords = {
//0
			0,0,
			0,1,
			1,1,
			1,0,			
//1
			0,0,
			0,1,
			1,1,
			1,0,
//2
			0,0,
			0,1,
			1,1,
			1,0,
//3
			0,0,
			0,1,
			1,1,
			1,0,
//4
			0,0,
			0,1,
			1,1,
			1,0,
//5	
			0,0,
			0,1,
			1,1,
			1,0

			
	};
	public static float[] temp3tex = {
			//0
						0,0,
						0,1,
						1,1,
						1,0,				
				};
	public static int[] indices = {
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22

	};
	public static int[] tempInd = {
			0,1,3,	
			3,1,2,	
	};
	public static Entity load3D(String objectFileName,String textureFileName,String textureType ) {
		Loader loader = new Loader();
		RawModel tempRaw = OBJLoader.loadObjModel(objectFileName,loader);
		TexturedModel tempTex = new TexturedModel(tempRaw, new ModelTexture(loader.loadTexture(textureFileName,textureType )));
		Entity temEntity = new Entity(tempTex, new Vector3f(0,0,0),0, 0, 0, 1);
		return temEntity;
	}
	public static Entity load2D(float[] vertices1,float[] texCoords1,int[] indices1,String textureFileName,String textureType ) {
		Loader loader = new Loader();
		RawModel tempRaw = loader.loadToVAO2D(vertices1, texCoords1, indices1);
		TexturedModel tempTex = new TexturedModel(tempRaw, new ModelTexture(loader.loadTexture(textureFileName,textureType )));
		Entity temEntity = new Entity(tempTex, new Vector3f(0,0,0),0, 0, 0, 1);
		return temEntity;
	}

}
