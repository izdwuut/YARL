package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.items.WeaponComponent;

/**
 * An item. Anything that can be picked up from floor, basically.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 */
public class Item extends Entity {
	public Item(String name) {
		add(new NameComponent(name));
	}
	
	public Item() {
		
	}
	
	/**
	 * Tags an item as a weapon and sets it's damage.
	 * 
	 * @param dmg weapon's damage
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Item setWeapon(int dmg) {
		add(new WeaponComponent(dmg));
		
		return this;
	}
	
	/**
	 * Sets an item name.
	 * 
	 * @param name a name to set
	 * 
	 * @return a current instance of an object (chaining)
	 */
	public Item setName(String name) {
		add(new NameComponent(name));
		
		return this;
	}
}
