package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.items.WeaponComponent;
import io.github.izdwuut.yarl.model.entities.Item;

/**
 * A flyweight item factory. Separates common {@link io.github.izdwuut.yarl.model.entities.Item item}'s components.
 * Created to reduce boilerplate code. Relies on lazy initialization. 
 * It is separated from an {@link io.github.izdwuut.yarl.model.factories.ItemFactory} to unclog it.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 */
public abstract class ItemFlyweightFactory extends FlyweightFactory<String, Item> {
	/**
	 * Gets a weapon with flyweight components.
	 * 
	 * @param name an item's name
	 * @param dmg an item's damage
	 * 
	 * @return a weapon with flyweight components
	 */
	protected Item getWeapon(String name, int dmg) {	
		Item weapon = new Item();
		
		if(hasFlyweight(name)) {
			return mergeFlyweight(name, weapon);		
		}
		
		WeaponComponent weaponComp = new WeaponComponent(dmg);
		addCommon(name, weapon).add(weaponComp);
		weapon.add(weaponComp);
		
		return weapon;
	}
	
	/**
	 * Adds common components to an {@code item} and returns a flyweight 
	 * object that can be further expanded.
	 * 
	 * @param name an item's name
	 * @param item an item with no components
	 * 
	 * @return a flyweight object
	 */
	private Item addCommon(String name, Item item) {
		Item flyweight = new Item();
		NameComponent nameComp = new NameComponent(name);
		item.add(nameComp);
		flyweight.add(nameComp);
		addFlyweight(name, flyweight);
		
		return flyweight;
	}
}
