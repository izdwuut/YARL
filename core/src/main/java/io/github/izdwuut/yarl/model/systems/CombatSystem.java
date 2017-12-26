package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.izdwuut.yarl.model.Event;
import io.github.izdwuut.yarl.model.components.combat.AttackerComponent;
import io.github.izdwuut.yarl.model.components.combat.DefenderComponent;
import io.github.izdwuut.yarl.model.components.creatures.HPComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.utils.Mappers;

public class CombatSystem extends IteratingSystem implements Listenable<Event> {
	Engine engine;
	
	/** An {@link io.github.izdwuut.yarl.model.Event Event} dispatcher. */
	private Signal<Event> dispatcher;
	
	public CombatSystem(Engine engine) {
		super(Family.all(AttackerComponent.class, DefenderComponent.class).get());
		
		this.engine = engine;
		this.dispatcher = new Signal<Event>();
	}
	
	@Override
	protected void processEntity(Entity combat, float deltaTime) {
		cleanup(combat(Mappers.defender.get(combat)
				.getDefender()), combat);
	}
	
	private Creature combat(Creature defender) {
		HPComponent hp = Mappers.hp.get(defender);
		
		if(hp.getHP() - 20 > 0) {
			hp.addHP(-20);
		} else {
			return defender;
		}
		
		return null;
	}
	
	private void cleanup(Creature creature, Entity combat) {
		if(creature != null) {
			engine.getSystem(WorldSystem.class)
			.removeCreature(creature);
			dispatcher.dispatch(Event.CREATURE_KILL);
		}
		
		engine.removeEntity(combat);
	}
	
	@Override
	public void addListener(Listener<Event> listener) {
		dispatcher.add(listener);
	}
}
