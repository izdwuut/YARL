package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.signals.Listener;

/**
 * Describes behaviour of a class that can be listened to.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-04
 */
public interface Listenable<T> {
	/**
	 * Registers {@link com.badlogic.ashley.signals.Listener a Listener}.
	 * 
	 * @param listener a listener to register
	 * @param event an event that you want to listen to
	 */
	public void addListener(Listener<T> listener, T event);
	
	
	/**
	 * Registers {@link com.badlogic.ashley.signals.Listener a Listener} to multiple events.
	 * 
	 * @param listener a listener to register
	 * 
	 * @param events events to register
	 */
	//TODO: temporary. when every system will have it's own dispatcher, 
	//it should be replaced with registering to to all signals a system issues
	default public void addListener(Listener<T> listener, T...events) {
		for(int i = 0; i < events.length; i++) {			
			addListener(listener, events[i]);
		}
	}
}