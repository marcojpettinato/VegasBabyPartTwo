package vegasBaby;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Tile implements Serializable 
{
	private static final Color[] colors = {Color.YELLOW, Color.GREEN, Color.ORANGE, Color.RED, Color.BLUE};
	private static final String[] colorNames = {"yellow","green","orange","red","blue"};
	private static final String[] shapes = {"circle","square"};
	private int color; //0 - yellow, 1 - green, 2 - orange, 3 - red, 4 - blue
	private int shape; // 0 - circle, 1 - square, 2 - diamond
	
	/**
	 * Allows for creation of default tile setting color to red and shape to circle
	 */
	public Tile() 
	{
		color = 3;
		shape = 0;
	}
	
	/**
	 * @param int color allows for initialization with set color
	 * @param int color allows for initialization with set shape
	 * Method allows for the creation of tile with specialized color 
	 */
	public Tile(int color, int shape) 
	{
		setColor(color);
		setShape(shape);
	}
	
	/**
	 * @return returns the integer representing a certain color
	 */
	public int getColor() 
	{
		return color;
	}
	
	
	/**
	 * @param int color takes in an integer representing a color
	 * @return returns the integer representing a certain color
	 * The use of this allows for some range checking and making sure it is within our bounds
	 */
	public void setColor(int color) 
	{
		if (color < 0) 
		{
			this.color = 0;
		} 
		
		else if (color > 4) 
		{
			this.color = 4;
		} 
		
		else 
		{
			this.color = color;
		}
	}
	
	/**
	 * @param takes a random generator object in order to randomly generate numbers
	 * The method then sets color and shape to random numbers within the range of the colors and shapes arrays using .length method
	 */
	public void setRandomly(Random rnd) 
	{
		color = rnd.nextInt(colors.length);
		shape = rnd.nextInt(shapes.length);
	}
	
	/**
	 * @return this function returns the actual color object corresponding to its integer representative.
	 */
	public Color getActualColor() 
	{
		return colors[color];
	}
	
	/**
	 * @return this function returns the string of the colors name based on its integer representation.
	 */
	public String getColorName() 
	{
		return colorNames[color];
	}
	
	/**
	 * @return this function returns the integer value representing the shapes
	 */
	public int getShape() 
	{
		return shape; 
	}
	
	/**
	 * @param int shape takes in an integer representing a shape
	 * @return returns the integer representing a certain shape
	 * The use of this allows for some range checking and making sure it is within our bounds
	 */
	public void setShape(int shape)
	{
		if (shape < 0) 
		{
			this.shape = 0;
		} 
		else if (shape > 1) 
		{
			this.shape = 1;
		} 
		else 
		{
			this.shape = shape;
		}
	}
	
	/**
	 * @return this function returns the String name of the shape based on its number representative.
	 */
	public String getShapeAsString() 
	{
		return shapes[shape];
	}
	
	/**
	 * @return this function returns a formatted string with the name of the color and shape.
	 */
	public String toStringFancy() 
	{
		return String.format("%s %s", getColorName(), getShapeAsString());
	}
	
	@Override
	/**
	 * @return this function returns a formatted string with the numerical representations of both color and shape
	 */
	public String toString()
	{
		return String.format("%d %d",color,shape);
	}
	
	/**
	 * @return an integer equal to ten times the numerical representaation of color plus the shape numerical representative. 
	 */
	public int getCode() 
	{
		return color*10+shape;
	}
}


