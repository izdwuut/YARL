package io.github.izdwuut.yarl.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;

import squidpony.squidgrid.gui.gdx.SquidInput;

/**
 * A generic controller. Provides a default {@link #update() update} method
 * fired after every frame is rendered. It also specifies common fields used
 * by every controller: {@link com.badlogic.ashley.core.Engine engine} (passed either by
 * {@link io.github.izdwuut.yarl.YARL YARL} or any controller) and {@link squidpony.squidgrid.gui.gdx.SquidInput input}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-28
 */
public abstract class Controller {
	protected SquidInput input;
	protected Engine engine;
	
	/**
	 * Stores an {@link com.badlogic.ashley.core.Engine engine} passed either by
	 * {@link io.github.izdwuut.yarl.YARL YARL} or any controller.
	 * 
	 * @param engine an Ashley engine
	 */
	public Controller(Engine engine) {
		this.engine = engine;
	}
	
	/**
	 * Fired after a frame is rendered. Reacts to user input 
	 * and tells the {@link com.badlogic.ashley.core.Engine engine} to update entities accordingly.
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
