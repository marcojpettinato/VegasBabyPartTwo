package vegasBaby;
import java.util.ArrayList;
import java.util.Random;

public class TileRandomizer 
{
	private static ArrayList<Tile> random;
	
	public static ArrayList<Tile> tileRandomizer()
	{
		Tile tile;
		Random rnd = new Random();
		for (int i = 0; i < 4; i++) 
		{
			tile = new Tile();
			tile.setRandomly(rnd);
			random.add(tile);
		}
		return random;
	}
}
