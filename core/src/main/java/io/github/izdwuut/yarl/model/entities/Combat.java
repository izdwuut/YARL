package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.combat.AttackerComponent;
import io.github.izdwuut.yarl.model.components.combat.DefenderComponent;

/**
 * A combat entity. It indicates an initiated fight.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-25
 */
public class Combat extends Entity {
	public Combat() {
		
	}
	
	public Combat(Creature attacker, Creature defender) {
		setAttacker(attacker);
		setDefender(defender);
	}
	
	/**
	 * Sets an active side.
	 * 
	 * @param attacker {@link io.github.izdwuut.yarl.model.entities.Creature a Creature} that initiated combat
	 */
	public void setAttacker(Creature attacker) {
		add(new AttackerComponent(attacker));
	}
	
	/**
	 * Sets a passive side.
	 * 
	 * @param defender {@link io.github.izdwuut.yarl.model.entities.Creature a Creature} that defends
	 */
	public void setDefender(Creature defender) {
		add(new DefenderComponent(defender));
	}
}
