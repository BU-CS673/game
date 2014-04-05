package bubolo.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.GameWorld;
import bubolo.world.Tile;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Water;

/**
 * @author BU CS673 - Clone Productions
 */
public class TileUtilTest
{
	static GameWorld world;
	static Tile[][] tiles;

	@BeforeClass
	public static void setup()
	{
		world = new GameWorld(32 * 4, 32 * 4);
		tiles = new Tile[2][2];
		tiles[0][0] = new Tile(0, 0, new Grass());
		tiles[0][1] = new Tile(0, 1, new Grass());
		tiles[1][0] = new Tile(1, 0, new Grass());
		tiles[1][1] = new Tile(0, 1, new Water());
		world.setMapTiles(tiles);
	}

	@Test
	public void getCornerStates()
	{
		boolean[] corners = TileUtil.getCornerMatches(tiles[0][0], world, new Class[] { Water.class });
		assertEquals("Tile (0,0) has wrong top-right corner state!", true, corners[1]);
		assertEquals("Tile (1,0) has wrong top-right corner state!", false, corners[0]);
	}

	@Test
	public void getEdgeMatches()
	{
		boolean[] edges = TileUtil.getEdgeMatches(tiles[1][0], world, new Class[] { Grass.class });
		assertEquals("Tile (1,0) has wrong top state!", false, edges[0]);
		assertEquals("Tile (1,0) has wrong bottom state!", true, edges[2]);
	}

	@Test
	public void getTilingState()
	{
		assertEquals("Tile (1,0) has the wrong tiling state!", 9, TileUtil.getTilingState(tiles[0][0], world, new Class[] { Grass.class }));
	}
	
	@Test
	public void getLocalEntites(){
		List l = TileUtil.getLocalEntities(0, 0, world);
		assertEquals("List of local Entities does not contain correct objects!", true,  l.contains(tiles[0][0].getTerrain()));
		l = TileUtil.getLocalEntities(3, 3, world);
		assertEquals("List of local Entities does not contain correct objects!", true,  l.contains(tiles[1][1].getTerrain()));
		assertEquals("List of local Entities contains incorrect objects!", false,  l.contains(tiles[0][0].getTerrain()));

	}

}