package bubolo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import bubolo.world.GameWorld;
import bubolo.world.Tile;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;

public class Parser
{
	
	public Parser()
	{

	}
	
	private Tile[][] parseMap(GameWorld world, Path mapPath) throws ParseException
	{ 
		int mapHeight = 0;
		int mapWidth = 0;
		Object obj = null;
		Entity newEntity; 
		JSONObject layerObject = null;
		JSONArray tileData = null;
		JSONArray layerArray = null;
		
		// TODO: we need a way to determine the size of the game map. Perhaps we can have a default constructor,
		// and then the map loader or creator could set the size.

		/*
		 *  TODO: Modify the path line to choose from an assortment of maps either randomly or user selected iff we add more maps. 
		 */
		
		Charset charset = Charset.forName("US-ASCII");
		BufferedReader reader = null;		
		
		try 
		{
			reader = Files.newBufferedReader(mapPath, charset);
			JSONParser parser = new JSONParser();
			obj = parser.parse(reader);
			reader.close();
			JSONObject jsonObject = (JSONObject) obj;		
			mapHeight = (int) ((long) jsonObject.get("height"));
			mapWidth = (int) ((long) jsonObject.get("width"));
			Tile[][] mapTiles = new Tile[mapHeight][mapWidth];
			layerArray = (JSONArray) jsonObject.get("layers");
			layerObject = (JSONObject) layerArray.get(0);
			tileData = (JSONArray) layerObject.get("data");
			
			world = new GameWorld(mapHeight, mapWidth);
			
			for(int i = 0; i < mapHeight; i++)
			{
				for(int j = 0; j < mapWidth; j++)
				{
					/*
					 * TODO: Add cases to interpret all of the tileset ID's from tiled.
					 * Convert string outputs to generate "tiles" within the map
					 */
					
					switch (tileData.get(i * mapWidth + j).toString())
					{
						case "1":
							mapTiles[i][j] = new Tile(i, j, world.addEntity(Grass.class));
							break;
							
						default:
							break;
					}
				}
			}
			return mapTiles;
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (org.json.simple.parser.ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
	}
	
	private Object layerOneSwitch(String input)
	{
		switch (input)
		{
		case "1":
			return Grass.class;
		default:
			return Grass.class;
		}
	}
}