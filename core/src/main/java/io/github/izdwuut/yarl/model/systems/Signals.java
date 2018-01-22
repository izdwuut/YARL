package io.github.izdwuut.yarl.model.systems;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

/**
 * A class that aggregates event dispatchers.
 * Implements a simplified strategy pattern.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-12
 */
//TODO: unit testing
//TODO: implement dispatcher interface (maybe rename to Dispatcher. it declares a dispatch method)
//TODO: generics + abstract
public class Signals implements Listenable<Event> {
	/**
	 * Event dispatchers.
	 */
	Map<Event, Signal<Event>> signals;
	
	public Signals() {
		signals = new HashMap<Event, Signal<Event>>();
	}
	
	@Override
	public void addListener(Listener<Event> listener, Event event) {
		getSignal(event).add(listener);
	}
	
	/**
	 * Dispatches an event.
	 * 
	 * @param event the event that will be dispatched
	 */
	public void dispatch(Event event) {
		getSignal(event).dispatch(event);
	}
	
	/**
	 * Gets an event dispatcher related to a provided event.
	 * 
	 * @param event an event related to a underlying event dispatcher
	 * 
	 * @return the event dispatcher
	 */
	Signal<Event> getSignal(Event event) {
		if(signals.containsKey(event)) {
			return signals.get(event);
		}
		
		Signal<Event> signal = new Signal<Event>();
		signals.put(event, signal);
		
		return signal;
	}
}
