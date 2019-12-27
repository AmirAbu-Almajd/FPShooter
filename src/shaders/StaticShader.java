package shaders;

import org.lwjgl.util.vector.Matrix4f;

import toolbox.Maths;

import entities.Camera;
import entities.Entity;
import entities.Light;

public class StaticShader extends ShaderProgram{
	
	private static String VERTEX_FILE = "src/shaders/";
	private static String FRAGMENT_FILE = "src/shaders/";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;
	
	public StaticShader(String v,String f)
	{
		super(VERTEX_FILE.concat(v),FRAGMENT_FILE.concat(f));
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normals");
		super.bindAttribute(3, "flag");
	}

	@Override
	protected void getAllUniformLocations()
	{
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
	}
	public void loadShineVariables(float damper, float reflectivity) 
	{
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	public void loadLight(Light light) 
	{
		super.loadVector(location_lightPosition, light.getPositions());
		super.loadVector(location_lightColour, light.getColour()); 
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	public void loadViewMatrix2(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix2(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	public void loadProjectionMatrix(Matrix4f projection)
	{
		super.loadMatrix(location_projectionMatrix, projection);
	}
}
