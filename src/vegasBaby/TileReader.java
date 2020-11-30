package vegasBaby;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TileReader 
{
	/**
	 * Read tiles from a text file
	 * @param fname the name of the text file
	 * @return true if successfully read; false otherwise
	 */
	public ArrayList<Tile> readFromText(String fname) 
	{
		File f = new File(fname);
		return readFromText(f);
	}
	
	/**
	 * writes tiles to a file
	 * @param f the File object to write to
	 * @return ArrayList of tiles read in, null if there is an exception
	 */
	public static ArrayList<Tile> readFromText(File f) 
	{
		try 
		{
			ArrayList<Tile> tileRead = new ArrayList<Tile>();
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			int x, y;
			Tile tile;
			while (fsc.hasNextLine()) 
			{
				line = fsc.nextLine();
				parts = line.split(" ");
				x = Integer.parseInt(parts[0]);
				y = Integer.parseInt(parts[1]);
				tile = new Tile(x,y);
				tileRead.add(tile);
			}
			fsc.close();
			return tileRead;
		} catch (Exception ex) 
		{
			return null;  // null means I wasn't able to build 
						  // the required array list of dots
		}
	}
	
	/**
	 * Read tiles from a text file
	 * @param fname the name of the text file
	 * @return true if successfully read; false otherwise
	 */
	public ArrayList<Tile> readFromBinary(String fname) 
	{
		File f = new File(fname);
		return readFromBinary(f);
	}
	
	/**
	 * read tiles to a text file
	 * @param fname the name of the text file
	 * @return ArrayList tileRead if successfully read; false otherwise
	 */
	public static ArrayList<Tile> readFromBinary(File f) 
	{
		try 
		{
			ArrayList<Tile> tileRead;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			tileRead = (ArrayList<Tile>)ois.readObject();
			ois.close();
			return tileRead;
		} catch (Exception ex) 
		{
			return null;
		}
	}
	
	/**
	 * read tiles to a text file
	 * @param fname the name of the text file
	 * @return ArrayList tileRead if successfully read; false otherwise
	 */
	public ArrayList<Tile> readFromXML(String fname) 
	{
		File f = new File(fname);
		return readFromXML(f);
	}
	public static ArrayList<Tile> readFromXML(File f) 
	{
		try 
		{
			ArrayList<Tile> tilesRead;
			XMLDecoder dec = new XMLDecoder(
					new BufferedInputStream(new FileInputStream(f)));
			tilesRead = (ArrayList<Tile>)dec.readObject();
			dec.close();
			return tilesRead;
		} 
		catch (Exception ex) 
		{
			return null;
		}
	}
	public static ArrayList<Tile> read(String fname) 
	{
		File f = new File(fname);
		return read(f);
	}
	
	/**
	 * read tiles to a text file
	 * @param fname the name of the text file
	 * @return ArrayList tileRead if successfully read; false otherwise
	 */
	public static ArrayList<Tile> read(File f) 
	{
		try 
		{
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) 
			{
				return readFromText(f);
			} 
			
			else if (fname.endsWith(".BIN")) 
			{
				return readFromBinary(f);
			} 
			
			else if (fname.endsWith(".XML")) 
			{
				return readFromXML(f);
			}
			else 
			{
				return null;  // unrecognized extension
			}
		} 
		catch (Exception ex) 
		{
			return null;
		}
	
	}
}
