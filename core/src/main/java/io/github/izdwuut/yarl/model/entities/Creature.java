package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.BagComponent;
import io.github.izdwuut.yarl.model.components.GlyphComponent;
import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.creatures.HPComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.creatures.PlayerComponent;

/**
 * A creature {@link com.badlogic.ashley.core.Entity Entity}. It covers every creature, they only 
 * differ in {@link com.badlogic.ashley.core.Component components}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
public class Creature extends Entity {
	/**
	 * Constructs a creature using a provided name.
	 * Base creature is composed of {@link io.github.izdwuut.yarl.model.components.NameComponent name} 
	 * and {@link io.github.izdwuut.yarl.model.components.PositionComponent a position}.
	 * 
	 * @param name a creature name
	 */
	public Creature(String name) {
		this();
		add(new NameComponent(name));
	}
	
	public Creature() {		
		add(new PositionComponent());
	}
	
	/**
	 * Tags a player.
	 * 
	 * @return a current instance of an object (chaining)
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
	 * @return a current instance of an object (chaining)
	 */
	public Creature setInv(int volume) {
		add(new BagComponent(volume));
		
		return this;
	}

	/**
	 * Makes a creature able to move.
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setMov() {
		add(new MovementComponent());
		
		return this;
	}
	
	/**
	 * Sets a glyph (display character).
	 * 
	 * @param glyph a glyph to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setGlyph(char glyph) {
		add(new GlyphComponent(glyph));
		
		return this;
	}
	
	/**
	 * Sets hit points.
	 * 
	 * @param hp hit points to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setHP(int hp) {
		add(new HPComponent(hp));
		
		return this;
	}
	
	/**
	 * Sets a creature's name;
	 * 
	 * @param name a name to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setName(String name) {
		add(new NameComponent(name));
		
		return this;
	}
}
