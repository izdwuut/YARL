package io.github.izdwuut.yarl.model.factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.items.WeaponComponent;
import io.github.izdwuut.yarl.model.entities.Item;

/**
 * A flyweight item factory. Separates common {@link io.github.izdwuut.yarl.model.entities.Item item}'s components.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 */
public class ItemFlyweightFactory {
	/**
	 * Common item names.
	 */
	List<NameComponent> names;
	
	/**
	 * Common weapon components.
	 */
	List<WeaponComponent> weapons;
	
	public ItemFlyweightFactory() {
		names = new ArrayList<NameComponent>();
		weapons = new ArrayList<WeaponComponent>();
	}
	
	public Item sword() {		
		return getFlyweight("Sword", 20);
	}
	
	/**
	 * Gets a flyweight item. Created to reduce boilerplate code.
	 * Make sure to operate on {@link #names names} and {@link #weapons weapons} from this method only,
	 * because both name and weapon have to be treated as one!
	 * 
	 * @param name an item's name
	 * @param dmg an item's damage
	 * 
	 * @return a flyweight item
	 */
	Item getFlyweight(String name, int dmg) {
		Iterator<NameComponent> nameIter = names.iterator();
		Iterator<WeaponComponent> weaponIter = weapons.iterator();
		Item item = new Item();
		
		while(nameIter.hasNext() && weaponIter.hasNext()) {
			NameComponent currName = nameIter.next();
			WeaponComponent currWeapon = weaponIter.next(); 
			if(currName.getName() == name && currWeapon.getDmg() == dmg) {
				item.add(currName)
				.add(currWeapon);
				
				return item;
			}
		}
		
		NameComponent nameComp = new NameComponent(name);
		WeaponComponent weaponComp = new WeaponComponent(dmg);
		item.add(nameComp)
		.add(weaponComp);
		names.add(nameComp);
		weapons.add(weaponComp);
		
		return item;
	}
}
