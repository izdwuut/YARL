package io.github.izdwuut.yarl;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import io.github.izdwuut.yarl.controllers.Controller;
import io.github.izdwuut.yarl.controllers.GameController;
import io.github.izdwuut.yarl.model.systems.InitSystem;

/**
 * Main game class. It stores a reference to a current {@link #controller controller} and 
 * {@link com.badlogic.gdx.Game#screen screen}. Every {@link com.badlogic.ashley.core.EntitySystem system} 
 * in a game is created and added to an Ashley {@link #engine engine} here, 
 * where {@link #controller controller} is only responsible for 
 * {@link io.github.izdwuut.yarl.controllers.Controller#pause() pausing} 
 * and {@link io.github.izdwuut.yarl.controllers.Controller#resume() resuming} 
 * them according to it's needs.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
//TODO: populate system
public class YARL extends Game {
	/** A current controller. */
	private Controller controller;
	
	/** An Ashley engine. */
	private Engine engine;
	
	/**
	 * Creates an Ashley {@link #engine engine}.
	 */
	public YARL() {
		super();
		this.engine = new Engine();
	}
	
	/**
	 * Sets non continuous rendering.
	 * Instantiates default {@link #controller controller} 
	 * and creates systems which are then added to an Ashley
	 * {@link #engine engine}. 
	 */
	public void create() {
		Gdx.graphics.setContinuousRendering(false);
		
		InitSystem init = new InitSystem(engine);
		init.setProcessing(false);
		engine.addSystem(init); //TODO: consider refactoring. potentially dangerous - acts as globals. 

		this.controller = new GameController(this, engine);
	}
	
	/**
	 * Renders a frame and passes control to a current {@link #controller controller}.
	 */
	@Override
	public void render() {
		super.render();
		controller.update();
	}
	
	/**
	 * Sets current {@link #controller controller}. 
	 * Exactly one {@link #controller controller} can exist in a game at a time.
	 * 
	 * @param controller currently active controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
