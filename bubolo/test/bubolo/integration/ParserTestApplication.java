package bubolo.integration;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.AbstractGameApplication;
import bubolo.GameApplication;
import bubolo.audio.Audio;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.util.Parser;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;

public class ParserTestApplication extends AbstractGameApplication
{
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BUBOLO Tank Controller Integration";
		cfg.width = 1067;
		cfg.height = 600;
		new LwjglApplication(new ParserTestApplication(1067, 600), cfg);
	}

	private int windowWidth;
	private int windowHeight;	

	private Graphics graphics;

	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final long TICKS_PER_SECOND = 30;

	/**
	 * The number of milliseconds per game tick.
	 */
	public static final long MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;

	/**
	 * Constructs an instance of the game application. Only one instance should ever
	 * exist.
	 * 
	 * @param windowWidth
	 *            the width of the window.
	 * @param windowHeight
	 *            the height of the window.
	 */
	public ParserTestApplication(int windowWidth, int windowHeight)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	/**
	 * Create anything that relies on graphics, sound, windowing, or input devices here.
	 * 
	 * @see <a
	 *      href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void create()
	{
		Audio.initialize();
		graphics = new Graphics(windowWidth, windowHeight);
		
		Network net = NetworkSystem.getInstance();
		net.startDebug();
		
		Parser fileParser = Parser.getInstance();
		Path path = FileSystems.getDefault().getPath("res", "maps/ParserTestMap.json");
		try
		{
			world = fileParser.parseMap(path);
		}
		catch (ParseException | IOException e)
		{
			e.printStackTrace();
			// The test is cancelled if the map failed to load.
			return;
		}

		Tank tank = world.addEntity(Tank.class);
		tank.setParams(100, 100, 0);
		tank.setLocalPlayer(true);

		setReady(true);
	}

	/**
	 * Called automatically by the rendering library.
	 * 
	 * @see <a
	 *      href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void render()
	{
		graphics.draw(world);
		world.update();

		// (cdc - 4/3/2014): Commented out, b/c update was being called twice. Additionally,
		// the game is extremely jittery when this is used instead of calling update continuously.
		
		// Ensure that the world is only updated as frequently as MILLIS_PER_TICK.
//		long currentMillis = System.currentTimeMillis();
//		if (currentMillis > (lastUpdate + MILLIS_PER_TICK))
//		{
//			world.update();
//			lastUpdate = currentMillis;
//		}
	}

	/**
	 * Called when the application is destroyed.
	 * 
	 * @see <a
	 *      href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void dispose()
	{
		Audio.dispose();
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void resume()
	{
	}
}
