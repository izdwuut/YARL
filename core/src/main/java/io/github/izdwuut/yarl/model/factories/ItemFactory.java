package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.items.WeaponComponent;
import io.github.izdwuut.yarl.model.entities.Item;

/**
 * A factory that produces {@link io.github.izdwuut.yarl.model.entities.Item items}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 */
public class ItemFactory extends FlyweightFactory<String, Item> {
	public ItemFactory() {
		super(Item.class);
	}
	
	/**
	 * Gets a sword.
	 * 
	 * @return *drumroll* a sword
	 */
	public Item sword() {
		return getWeapon("Sword", "1d4", new Item());
	}
	
	
	Item getWeapon(String name, String dmg, Item weapon) {
		if(hasFlyweight(name)) {
			return getEntity(name, weapon);
		}
		
		return getEntity(name, weapon, new NameComponent(name), new WeaponComponent(dmg));
	}
}