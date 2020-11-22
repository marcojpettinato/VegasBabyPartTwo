package vegasBaby;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TileWriter
{
	/**
	 * Write tiles to a text file
	 * @param fname the name of the text file
	 * @param tiles list of tiles to write
	 * @return true if successfully written; false otherwise
	 */
	public static boolean writeToText(String fname, ArrayList<Tile> tiles) 
	{
		File f = new File(fname);
		return writeToText(f,tiles);  // delegation - lean on another function to do your task
	}
	
	/**
	 * writes tiles to a file
	 * @param f the File object to write to
	 * @param tiles the list of tiles
	 * @return true if successfully written, false otherwise
	 */
	
	public static boolean writeToText(File f, ArrayList<Tile> tiles) 
	{
		try 
		{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for (Tile tile : tiles) 
			{
				pw.println(tile);
			}
			pw.close();
			return true;
		} catch (Exception ex) 
		
		{
			return false;
		}
	}
	
	/**
	 * writes tiles to a file
	 * @param f the File object to write to
	 * @param tiles the list of tiles
	 * @return true if successfully written, false otherwise
	 */
	public static boolean writeToBinary(String fname, ArrayList<Tile> tiles) 
	{
		File f = new File(fname);
		return writeToBinary(f,tiles);
	}
	
	/**
	 * writes tiles to a file
	 * @param f the File object to write to
	 * @param tiles the list of tiles
	 * @return true if successfully written, false otherwise
	 */
	public static boolean writeToBinary(File f, ArrayList<Tile> tiles) 
	{
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(tiles);
			oos.close();
			return true;
		} catch (Exception ex) 
		{
			return false;
		}
	}
	
	/**
	 * writes tiles to a file
	 * @param f the File object to write to
	 * @param tiles the list of tiles
	 * @return true if successfully written, false otherwise
	 */
	public boolean write(String fname, ArrayList<Tile> tiles) 
	{
		File f = new File(fname);
		return write(f,tiles);
	}
	
	/**
	 * writes tiles to a file
	 * @param f the File object to write to
	 * @param tiles the list of tiles
	 * @return true if successfully written, false otherwise
	 */
	public boolean writeToXML(String fname, ArrayList<Tile> tiles) 
	{
		File f = new File(fname);
		return writeToXML(f,tiles);
	}
	
	/**
	 * writes tiles to a file
	 * @param f the File object to write to
	 * @param tiles the list of tiles
	 * @return true if successfully written, false otherwise
	 */
	public static boolean writeToXML(File f, ArrayList<Tile> tiles) 
	{
		try 
		{
			XMLEncoder enc = new XMLEncoder(new 
					BufferedOutputStream(new FileOutputStream(f)));
			enc.writeObject(tiles);
			enc.close();
			return true;
		} catch (Exception ex) 
		{
			return false;
		}
	}
	/**
	 * The write function serves as a hub that farms out to other write___ functions
	 * the responsibility of writing the data. It chooses which function to call
	 * based on the file extension.
	 * .txt - writeToText
	 * .bin - writeToBinary
	 * .xml - writeToXML
	 * @param f This is the file object
	 * @param dots The dots to write
	 * @return true if successful, false otherwise (including if unrecognized extension)
	 */
	public static boolean write(File f, ArrayList<Tile> tiles) {
		try {
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) 
			{
				return writeToText(f,tiles);
			} 
			else if (fname.endsWith(".BIN")) 
			{
				return writeToBinary(f,tiles);
			} 
			else if (fname.endsWith(".XML")) 
			{
				return writeToXML(f,tiles);
			} 
			else 
			{
				return false;  // unrecognized file format
			}
		} 
		catch (Exception ex) 
		{
			return false;
		}
	}
	
}