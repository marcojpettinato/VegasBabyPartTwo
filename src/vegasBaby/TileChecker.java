package vegasBaby;

import java.util.ArrayList;

public class TileChecker 
{
	private static int colorCount = 0;
	private static int shapeCount = 0;
	private static int balanceMulti;

	/**
	 * @param this function takes in ArrayList of tiles called currentTiles
	 * @param this function takes in ArrayList of tiles called tileChecks
	 * The function then goes and checks each tile in both arrayLists and compares the two,
	 * It checks to see if the color of each shape at the same spot is the same as well as the shape.
	 * @return this function then returns a number based on the matching proccess to represent if,
	 * all the shapes and colors matched, if only the colors, or none. 
	 */
	public static int TileChecker(ArrayList<Tile> currentTiles, ArrayList<Tile> tileChecks)
	{
		for(int i = 0; i<currentTiles.size(); i++)
		{
			Tile currentTile = currentTiles.get(i);
			Tile checkingTile = tileChecks.get(i);
			
			if(currentTile.getColorName().equals(checkingTile.getColorName()))
			{
				colorCount++;
			}
			
			if(currentTile.getShape() == checkingTile.getShape())
			{
				shapeCount++;
			}
		}
		
		if(colorCount == 4 && shapeCount == 4)
		{
			balanceMulti = 2; 
		}
		
		else if(colorCount == 4)
		{
			balanceMulti = 1; 
		}
		
		else
		{
			balanceMulti = 0; 
		}
		
		return balanceMulti;
	}
}
