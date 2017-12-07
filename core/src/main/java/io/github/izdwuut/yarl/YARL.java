package io.github.izdwuut.yarl;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;

import io.github.izdwuut.yarl.controllers.Controller;
import io.github.izdwuut.yarl.controllers.GameController;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.systems.MovementSystem;

/**
 * Main game class. It stores a reference to a current controller and screen.
 * Every system in a game is created and added to an Ashley engine here, where controller is
 * only responsible for pausing and resuming them according to it's needs.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
//TODO: populate system
public class YARL extends Game {
	/** A current controller */
	private Controller controller;
	
	/** An Ashley engine */
	private Engine engine;
	
	/**
	 * Creates an Ashley engine.
	 */
	public YARL() {
		super();
		this.engine = new Engine();
	}
	
	/**
	 * Instantiates default controller and creates systems, 
	 * which are then added to an Ashley engine.
	 */
	//TODO: put factories in systems
	public void create() {
		Settings settings = new SettingsFactory().getSettings();
		World world = new WorldFactory(settings).getWorld();
		
		MovementSystem system = new MovementSystem(world);
		engine.addSystem(system);		

		this.controller = new GameController(this, engine, world, settings);
	}
	
	/**
	 * Renders a frame and passes control to a current controller.
	 */
	//TODO: non-continuous rendering
	@Override
	public void render() {
		super.render();
		controller.update();
	}
	
	/**
	 * Sets current controller. Exactly one controller can exist in a game at a time.
	 * 
	 * @param controller currently active controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
