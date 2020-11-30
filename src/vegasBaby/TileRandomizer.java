package vegasBaby;
import java.util.ArrayList;
import java.util.Random;

public class TileRandomizer 
{	
	/**
	 * @param random, this function takes in an arrayList of tiles called random.
	 * The function then sets each tile in the arrayList to a knew random combination of color and shape.
	 */
	public static void tileRandomizer(ArrayList <Tile> random)
	{
		Random rnd = new Random();
		for (int i = 0; i < 4; i++) 
		{
			random.get(i).setRandomly(rnd);
		}
	}
}
