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

/**
 * A system that process {@link com.badlogic.ashley.core.Entity.Combat Combat} entities,
 * i.e. a battle between two creatures - an attacker and a defender.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 *
 */
public class CombatSystem extends IteratingSystem implements Listenable<Event> {
	/** 
	 * An Ashley engine needed to retrieve {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}. 
	 */
	Engine engine;
	
	/** An {@link io.github.izdwuut.yarl.model.Event Event} dispatcher. */
	Signal<Event> dispatcher;
	
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
	
	/**
	 * Resolves a combat.
	 * 
	 * @param defender a defender side
	 * 
	 * @return a defender creature if hit points are above 0, null otherwise
	 */
	private Creature combat(Creature defender) {
		HPComponent hp = Mappers.hp.get(defender);
		
		if(hp.getHP() - 20 > 0) {
			hp.addHP(-20);
		} else {
			return defender;
		}
		
		return null;
	}
	
	/**
	 * Cleans up after combat. Imagine Viscera Cleanup Detail.
	 * 
	 * @param creature a creature if any died in a combat, null otherwise
	 * 
	 * @param combat {@link com.badlogic.ashley.core.Entity.Combat a Combat} entity
	 */
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
