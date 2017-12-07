package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.BagComponent;
import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.creatures.PlayerComponent;

/**
 * A creature entity. It covers every creature, they only differ in components.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
public class Creature extends Entity {
	/**
	 * Constructs a creature using a provided name.
	 * Base creature is composed of a name and a position.
	 * 
	 * @param name a creature name
	 */
	public Creature(String name) {
		add(new NameComponent(name));
		add(new PositionComponent());
	}
	
	/**
	 * Tags a player.
	 * 
	 * @return current instance of an object (chaining)
	 */
	public Creature setPlayer() {
		add(new PlayerComponent());
		
		return this;
	}
	
	/**
	 * Adds an inventory.
	 * 
	 * @param volume inventory volume
	 * 
	 * @return current instance of an object (chaining)
	 */
	public Creature setInv(int volume) {
		add(new BagComponent(volume));
		
		return this;
	}

	/**
	 * Makes a creature able to move.
	 * 
	 * @return current instance of an object (chaining)
	 */
	public Creature setMov() {
		add(new MovementComponent());
		
		return this;
	}
}
