package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.entities.Item;

/**
 * A factory that produces {@link io.github.izdwuut.yarl.model.entities.Item items}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 */
public class ItemFactory extends ItemFlyweightFactory {
	/**
	 * Gets a sword.
	 * 
	 * @return *drumroll* a sword
	 */
	public Item sword() {
		return getWeapon("Sword", 20);
	}
}
