package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Item;

/**
 * An arms component. Stores a weapon that is equipped by a creature.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 */
public class ArmsComponent implements Component {
	/**
	 * An equipped weapon.
	 */
	Item weapon;
	
	public ArmsComponent(Item weapon) {
		this.weapon = weapon;
	}
	
	/**
	 * Gets {@link #weapon an equipped weapon}.
	 * 
	 * @return an equipped weapon
	 */
	public Item getWeapon() {
		return weapon;
	}
	
	/**
	 * Sets {@link #weapon an equipped weapon} to a provided value.
	 * 
	 * @param weapon {@link #weapon an equipped weapon}
	 */
	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}
}
