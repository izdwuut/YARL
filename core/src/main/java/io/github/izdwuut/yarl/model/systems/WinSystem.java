package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;

import io.github.izdwuut.yarl.model.components.Families;

/**
 * A win system. A game is won when every monster is killed.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-01
 */
//TODO: remove class. dispatch event in a CombatSystem
public class WinSystem extends EntitySystem implements Listenable<Event> {
	/** 
	 * An {@link io.github.izdwuut.yarl.model.systems.Event Event} dispatcher. 
	 */
	Signals dispatcher;
	
	/** 
	 * An Ashley engine needed to retrieve {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}. 
	 */
	Engine engine;
	
	public WinSystem(Engine engine) {
		this.engine = engine;
		this.dispatcher = new Signals();
	}
	
	//TODO: refator families
	@Override
	public void update(float deltaTime) {
		if(engine.getEntitiesFor(new Families().getCreatures()).size() == 0) {
			dispatcher.dispatch(Event.FLOOR_CLEAR);
		}
	}
	
	@Override
	public void addListener(Listener<Event> listener, Event event) {
		dispatcher.addListener(listener, event);
	}
}
