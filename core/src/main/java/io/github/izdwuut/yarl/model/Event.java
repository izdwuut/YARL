package io.github.izdwuut.yarl.model;

/**
 * An enum that holds every event that an Ashley 
 * {@link com.badlogic.ashley.core.EntitySystem system} can dispatch.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-29
 */
public enum Event {
	/**
	 * Dispatches after {@link io.github.izdwuut.yarl.model.systems.MovementSystem} 
	 * {@link io.github.izdwuut.yarl.model.systems.MovementSystem#update(float deltaTime) update}. 
	 */
	MOVEMENT_END;
}
