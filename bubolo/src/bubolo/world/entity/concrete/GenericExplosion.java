package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Effect;

/**
 * Generic explosion Entity, intended to be scalable for use in multiple contexts, such as tank and bullet explosions.
 * 
 * @author BU CS673 - Clone Productions
 */
public class GenericExplosion extends Effect
{

	/**
	 * Used when serializing and de-serializing.
	 */

	private static final long serialVersionUID = 5884325200144350446L;

	/**
	 * Construct a new Bullet with a random UUID.
	 */
	public GenericExplosion()
	{
		super();
		setWidth(26);
		setHeight(26);
		setXOffset(0);
		setYOffset(0);
	}

	/**
	 * Construct a new Bullet with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public GenericExplosion(UUID id)
	{
		super(id);
		setWidth(26);
		setHeight(26);
		setXOffset(0);
		setYOffset(0);
	}

	// TODO: Add Bullet functionality!
}
