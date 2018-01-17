package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.BagComponent;
import io.github.izdwuut.yarl.model.components.GlyphComponent;
import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.creatures.ArmsComponent;
import io.github.izdwuut.yarl.model.components.creatures.ExpComponent;
import io.github.izdwuut.yarl.model.components.creatures.HPComponent;
import io.github.izdwuut.yarl.model.components.creatures.LvlComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.creatures.PlayerComponent;
import squidpony.squidmath.Coord;

/**
 * A creature {@link com.badlogic.ashley.core.Entity Entity}. It covers both 
 * monsters and player.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
public class Creature extends Entity {
	public Creature(String name) {
		add(new NameComponent(name));
		add(new PositionComponent());
	}
	
	public Creature() {
		
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
	
	/**
	 * Sets a creature's position.
	 * 
	 * @param pos a position to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setPos(Coord pos) {
		add(new PositionComponent(pos));
		
		return this;
	}
	
	/**
	 * Sets a wielded weapon.
	 * 
	 * @param arms a wielded weapon to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setArms(Item arms) {
		add(new ArmsComponent(arms));
		
		return this;
	}
	
	/**
	 * Sets number of experience points.
	 * 
	 * @param exp number of exp points to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setExp(int exp) {
		add(new ExpComponent(exp));
		
		return this;
	}
	
	/**
	 * Sets an experience level.
	 * 
	 * @param lvl a level to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setLvl(int lvl) {
		LvlComponent comp = new LvlComponent();
		comp.setLvl(lvl);
		add(comp);
		
		return this;
	}
	
	/**
	 * Sets an experience level to 1 - a default value.
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Creature setLvl() {
		return setLvl(1);
	}
}
