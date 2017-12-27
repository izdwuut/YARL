package io.github.izdwuut.yarl;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import io.github.izdwuut.yarl.controllers.Controller;
import io.github.izdwuut.yarl.controllers.GameController;

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
	Controller controller;
	
	/** An Ashley engine. */
	Engine engine;
	
	/**
	 * Creates an Ashley {@link #engine engine}.
	 */
	public YARL() {
		super();
		this.engine = new Engine();
	}
	
	/**
	 * Sets non continuous rendering.
	 * Instantiates a default {@link #controller controller}.
	 * {@link #engine engine}. 
	 */
	public void create() {
		Gdx.graphics.setContinuousRendering(false);

		this.controller = new GameController(engine, this);
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
