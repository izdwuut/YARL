package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * A creature component. It has various usages.
 * {@link io.github.izdwuut.yarl.model.entities.Exp An Exp entity}
 * uses it to denote a creature that has just gained 
 * experience points.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-12
 */
public class CreatureComponent implements Component {
	/**
	 * {@link io.github.izdwuut.yarl.model.entities.Creature A Creature} entity.
	 */
	Creature creature;
	
	public CreatureComponent(Creature creature) {
		this.creature = creature;
	}

	/**
	 * Gets {@link #creature a creature}
	 * 
	 * @return {@link #creature a creature}
	 */
	public Creature getCreature() {
		return creature;
	}

	/**
	 * Sets {@link #creature a creature} to a given value.
	 * 
	 * @param creature {@link io.github.izdwuut.yarl.model.entities.Creature a Creature} to set
	 */
	public void setCreature(Creature creature) {
		this.creature = creature;
	}
}
