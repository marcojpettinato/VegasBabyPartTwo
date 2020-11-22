package vegasBaby;

import java.util.ArrayList;

public class TileChecker 
{
	private static int colorCount = 0;
	private static int shapeCount = 0;
	private static int balanceMulti;
	
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
		
		else if(shapeCount == 4)
		{
			balanceMulti = 0; 
		}
		
		return balanceMulti;
	}
}
