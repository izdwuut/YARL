package io.github.izdwuut.yarl.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import squidpony.squidgrid.gui.gdx.SquidInput;

/**
 * A generic controller. Provides a default {@link #update() update} method
 * fired after every frame is {@link io.github.izdwuut.yarl.YARL#render() rendered}. 
 * It also specifies common fields used by every controller: {@link #engine engine} 
 * (passed either by {@link io.github.izdwuut.yarl.YARL YARL} or any controller) and 
 * {@link #input input}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-28
 */
public abstract class Controller {
	/** 
	 * An input handler. 
	 */
	protected SquidInput input;
	
	/** 
	 * An Ashley engine. 
	 */
	protected Engine engine;
	
	/** 
	 * A game class. 
	 */
	protected Game game;
	
	/**
	 * Constructs a controller using an {@link com.badlogic.ashley.core.Engine Engine} 
	 * passed either by {@link io.github.izdwuut.yarl.YARL YARL} or any controller.
	 * 
	 * @param engine an Ashley engine
	 * @param game a main {@link com.badlogic.gdx.Game Game} class
	 */
	public Controller(Engine engine, Game game) {
		this.engine = engine;
		this.game = game;
	}
	
	/**
	 * Fired after a frame is {@link io.github.izdwuut.yarl.YARL#render() rendered}. Reacts to user input 
	 * and tells the {@link #engine engine} to update entities accordingly.
	 */
	public void update() {
		if(input.hasNext()) {
            input.next();
            engine.update(Gdx.graphics.getDeltaTime());
        }
	}
	
	/**
	 * Pauses systems.
	 */
	abstract protected void pause();
	
	/**
	 * Resumes systems.
	 */
	abstract protected void resume();
}
