package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.izdwuut.yarl.model.components.combat.AttackerComponent;
import io.github.izdwuut.yarl.model.components.combat.DefenderComponent;
import io.github.izdwuut.yarl.model.components.creatures.HPComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Exp;
import io.github.izdwuut.yarl.model.utils.Mappers;

/**
 * A system that process {@link io.github.izdwuut.yarl.model.entities.Combat Combat} {@link com.badlogic.ashley.core.Entity Entities},
 * i.e. a battle between two creatures - an attacker and a defender.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-26
 */
public class CombatSystem extends IteratingSystem implements Listenable<Event> {
	/** 
	 * An Ashley engine needed to retrieve {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}. 
	 */
	Engine engine;
	
	/** 
	 * An {@link io.github.izdwuut.yarl.model.systems.Event Event} dispatcher. 
	 */
	Signals dispatcher;
	
	public CombatSystem(Engine engine) {
		super(Family.all(AttackerComponent.class, DefenderComponent.class).get());
		
		this.engine = engine;
		this.dispatcher = new Signals();
	}
	
	@Override
	protected void processEntity(Entity combat, float deltaTime) {
		Creature attacker = Mappers.attacker.get(combat).getAttacker(), 
				defender = Mappers.defender.get(combat)
				.getDefender();
		Creature defeated = resolve(attacker, defender);
		
		if(defeated != null) {
			addExp(attacker, Mappers.exp.get(defender).getExp());
			cleanUp(defeated, combat);
		}
	}
	
	/**
	 * Resolves a combat.
	 * 
	 * @param attacker an attacker side
	 * @param defender a defender side
	 * 
	 * @return null if hit points are above 0, a defender creature otherwise
	 */
	Creature resolve(Creature attacker, Creature defender) {
		HPComponent hp = Mappers.hp.get(defender);
		int dmg = Mappers.weapon.get(Mappers.arms.get(attacker).getWeapon()).getDmg(); 
		
		if(hp.getHP() - dmg > 0) {
			hp.addHP(-dmg);
		} else {
			return defender;
		}
		
		return null;
	}
	
	/**
	 * Cleans up casualties. Imagine Viscera Cleanup Detail.
	 * 
	 * @param creature a creature if any died in a combat, null otherwise
	 * @param combat {@link io.github.izdwuut.yarl.model.entities.Combat a Combat} {@link com.badlogic.ashley.core.Entity Entity}
	 */
	void cleanUp(Creature creature, Entity combat) {
		engine.getSystem(WorldSystem.class)
		.removeCreature(creature);
		dispatcher.dispatch(Event.CREATURE_KILL);
		
		engine.removeEntity(combat);
	}
	
	@Override
	public void addListener(Listener<Event> listener, Event event) {
		dispatcher.addListener(listener, event);
	}
	
	/**
	 * Adds {@link io.github.izdwuut.yarl.model.entities.Exp an Exp} entity to an Ashley engine.
	 * 
	 * @param creature a creature that gains experience
	 * 
	 * @param exp gained experience points
	 */
	void addExp(Creature creature, int exp) {
		engine.addEntity(new Exp(creature, exp));
	}
}
