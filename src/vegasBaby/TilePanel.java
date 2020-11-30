package vegasBaby;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class TilePanel extends JPanel implements MouseListener
{
	public static ArrayList<Tile> tiles;
	private static Random rnd;
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * @param this function takes an mouse event to allow the user to use the mouse in a way
	 * this function allows the user to change a shape by clicking on it
	 */
	public void mouseClicked(MouseEvent e) 
	{
		int whichTile = e.getX()/(this.getWidth()/4);
		Tile tile = tiles.get(whichTile);
		tile.setRandomly(rnd);
		repaint();
	}
	
	/**
	 *  This function initializes a set of 4 tiles and stores them in an arrayList
	 */
	public TilePanel() 
	{
		tiles = new ArrayList<Tile>();
		Tile tile;
		rnd = new Random();
		for (int i = 0; i < 4; i++) 
		{
			tile = new Tile();
			tile.setRandomly(rnd);
			tiles.add(tile);
		}
		addMouseListener(this);
	}
	
	/**
	 *  This function creates an illusion that the tiles are spinning by setting them randomly numerous times.
	 */
	public static void spin()
	{
		for(int i = 0; i<400; i++)
		{
			if(i < 100)
			{
				for(int j = 0; j<4; j++)
				{
					tiles.get(j).setRandomly(rnd);
				}	
			}
			else if(i<200)
			{
				for(int j = 0; j<3; j++)
				{
					tiles.get(j).setRandomly(rnd);
				}	
			}
			
			else if(i<300)
			{
				for(int j = 0; j<2; j++)
				{
					tiles.get(j).setRandomly(rnd);
				}	
			}
			
			else 
			{
					tiles.get(0).setRandomly(rnd);
			}
			
			try 
			{
				Thread.sleep(10);
			}
			catch(Exception ex)
			{
				
			}
			//repaint();
		}
	}
	
	/**
	 *  @return this funtcion returns the arrayList of tiles representing the four tiles in the panel. 
	 */
	public static ArrayList<Tile> getTiles()
	{
		return tiles;
	}
	
	/**
	 *  @param tiles, this funtcion takes in an ArrayList of tiles called tiles.
	 *  THe function then sets the arrayList passed in equal to the the tiles currently on the panel
	 */
	public void setTiles(ArrayList<Tile> tiles) 
	{
		this.tiles = tiles;
	}
	
	@Override
	/**
	 * @param this function takes an graphic value to allow the program to create the shapes using graphics
	 * this function uses the randomly generated values for the shapes and creates a panel of them.
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int cellWidth = this.getWidth() / 4;
		int tileSize = 4*cellWidth/5;
		int shape;
		Color color;
		Tile tile;
		for (int i = 0; i < tiles.size(); i++) 
		{
			tile = tiles.get(i);
			shape = tile.getShape();
			color = tile.getActualColor();
			g.setColor(color);
			if (shape == 0) 
			{
				g.fillOval(i*cellWidth + cellWidth/10, cellWidth/10, tileSize, tileSize); 
			} else if (shape == 1) 
			{
				g.fillRect(i*cellWidth + cellWidth/10, cellWidth/10, tileSize, tileSize);
			} 
		}
	}
}

