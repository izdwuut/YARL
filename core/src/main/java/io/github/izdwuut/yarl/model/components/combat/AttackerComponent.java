package io.github.izdwuut.yarl.model.components.combat;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * An attacker component. Stores a creature that initiates 
 * {@link io.github.izdwuut.yarl.model.entities.Combat Combat}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-22
 */
public class AttackerComponent implements Component {
	/** 
	 * A creature that initiates combat. 
	 */
	Creature attacker;
	
	public AttackerComponent(Creature attacker) {
		this.attacker = attacker;
	}

	/**
	 * Gets {@link #attacker an attacker}.
	 * 
	 * @return {@link #attacker an attacker} 
	 */
	public Creature getAttacker() {
		return attacker;
	}

	/**
	 * Sets {@link #attacker an attacker} to a provided value.
	 * 
	 * @param attacker {@link io.github.izdwuut.yarl.model.entities.Creature a Creature} to set
	 */
	public void setAttacker(Creature attacker) {
		this.attacker = attacker;
	}
}
