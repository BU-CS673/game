package bubolo;

import com.badlogic.gdx.ApplicationListener;

/**
 * Defines the interface for the main game class.
 * @author BU CS673 - Clone Productions
 */
public interface GameApplication extends ApplicationListener
{
	/**
	 * The application's state.
	 * @author BU CS673 - Clone Productions
	 */
	public enum State
	{
		/** The main menu state. **/
		MAIN_MENU,
		
		/** The game lobby state. **/
		GAME_LOBBY,
		
		/** The game state. **/
		GAME
	}
	
	/**
	 * Returns true if the game's subsystems have been set up, or false otherwise.
	 * @return true if the game's subsystems have been set up.
	 */
	boolean isReady();
	
	/**
	 * Specifies whether the game has started.
	 * @return true if the game has started.
	 */
	boolean isGameStarted();
	
	/**
	 * Sets the application's state.
	 * @param state the application's state.
	 */
	void setState(State state);
}
