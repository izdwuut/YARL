package io.github.izdwuut.yarl.model.components.combat;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * A defender component. Stores a creature that is .
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-22
 */
public class DefenderComponent implements Component{
	/** 
	 * A {@link io.github.izdwuut.yarl.model.entities.Combat Combat}'s passive side. 
	 */
	Creature defender;
	
	public DefenderComponent(Creature defender) {
		this.defender = defender;
	}
	
	/**
	 * Gets {@link #defender a defender}.
	 * 
	 * @return a defender side of combat
	 */
	public Creature getDefender() {
		return defender;
	}

	/**
	 * Sets {@link #defender a defender} to a provided value.
	 * 
	 * @param defender {@link io.github.izdwuut.yarl.model.entities.Creature a Creature} to set
	 */
	public void setDefender(Creature defender) {
		this.defender = defender;
	}
}
