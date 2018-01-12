package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.CreatureComponent;
import io.github.izdwuut.yarl.model.components.creatures.ExpComponent;

/**
 * An experience entity. It gets processed by an entity engine
 * when a creature gains experience.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-12 
 */
public class Exp extends Entity {
	public Exp(Creature creature, int exp) {
		add(new CreatureComponent(creature));
		add(new ExpComponent(exp));
	}
}
