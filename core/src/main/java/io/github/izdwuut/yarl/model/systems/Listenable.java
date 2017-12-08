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
	 * Registers {@link com.badlogic.ashley.signals.Listener<T> a Listener}.
	 * 
	 * @param listener a listener to register
	 */
	default void addListener(Listener<T> listener) {
	}
}
