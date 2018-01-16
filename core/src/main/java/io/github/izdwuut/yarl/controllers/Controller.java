package io.github.izdwuut.yarl.controllers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
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
	 * Every entity system related to a controller.
	 */
	protected List<EntitySystem> systems;
	
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
		systems = new ArrayList<EntitySystem>();
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
	 * Declares systems related to this controller.
	 */
	protected abstract void declareSystems();
	
	
	/**
	 * Pauses systems.
	 */
	protected void pause() {
		for(EntitySystem sys : systems) {
			sys.setProcessing(false);
		}
	}
	
	/**
	 * Resumes systems.
	 */
	protected void resume() {
		for(EntitySystem sys : systems) {
			sys.setProcessing(true);
		}
	}
}
