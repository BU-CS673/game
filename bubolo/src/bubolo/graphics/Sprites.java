package bubolo.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

/**
 * Contains static methods for adding new sprites.
 * @author BU CS673 - Clone Productions
 */
public class Sprites
{
	private Map<Class<? extends Entity>, SpriteFactory> spriteFactories;
	
	private List<Sprite<? extends Entity>> sprites = new ArrayList<Sprite<? extends Entity>>();
	
	private static Sprites instance;
	
	/**
	 * Returns the instance of this singleton.
	 * @return the instance of this singleton.
	 */
	public static Sprites getInstance()
	{
		if (instance == null)
		{
			instance = new Sprites();
		}
		return instance;
	}
	
	/**
	 * Private constructor to prevent this class from being instantiated.
	 */
	private Sprites()
	{
		spriteFactories = setSpriteFactories();
	}
	
	/**
	 * Returns a reference to the list of sprites. Package-private because 
	 * this method should not be accessed outside of the Graphics system.
	 * @return
	 */
	List<Sprite<? extends Entity>> getSprites()
	{
		return sprites;
	}
	
	
	/**
	 * Creates a new sprite based on the type of entity provided.
	 * @param entity reference to an entity.
	 * @return reference to the new sprite.
	 */
	public Sprite<?> createSprite(Entity entity)
	{
		if (!spriteFactories.containsKey(entity.getClass()))
		{
			throw new IllegalStateException("createSprite is unable to create a sprite from entity type " + 
					entity.getClass().getName());
		}
		
		Sprite<? extends Entity> sprite = spriteFactories.get(entity.getClass()).create(entity);
		sprites.add(sprite);
		return sprite;
	}
	
	/**
	 * Wrapper for sprite creation functions.
	 * @author BU CS673 - Clone Productions
	 */
	private interface SpriteFactory
	{
		/**
		 * Executes the sprite creation function.
		 * @param e reference to the entity that the sprite represents.
		 * @return reference to the new sprite.
		 */
		 Sprite<? extends Entity> create(Entity e);
	}
	
	/**
	 * Creates the sprite factory objects, which map concrete classes to sprite creation.
	 * @return map of the concrete classes to sprite creator classes.
	 */
	private static Map<Class<? extends Entity>, SpriteFactory> setSpriteFactories()
	{
		Map<Class<? extends Entity>, SpriteFactory> factories = new HashMap<>();
		
		factories.put(Grass.class, new SpriteFactory() {
			@Override
			public Sprite<? extends Entity> create(Entity e)
			{
				return new GrassSprite((Grass)e);
			}
		});
		
		factories.put(Road.class, new SpriteFactory() {
			@Override 
			public Sprite<? extends Entity> create(Entity e) 
			{
				return new RoadSprite((Road)e);
			}
		});
		
		factories.put(Tank.class, new SpriteFactory() {
			@Override 
			public Sprite<? extends Entity> create(Entity e) 
			{
				return new TankSprite((Tank)e);
			}
		});
		
		factories.put(Tree.class, new SpriteFactory() {
			@Override 
			public Sprite<? extends Entity> create(Entity e) 
			{
				return new TreeSprite((Tree)e);
			}
		});
		
		return factories;
	}
}