package io.github.izdwuut.yarl.model.components;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Item;

/**
 * A container for {@link io.github.izdwuut.yarl.model.entities.Item Items}.
 * It specifies it's {@link #items content} and {@link #volume volume} and can be used both as a 
 * {@link io.github.izdwuut.yarl.model.entities.Creature Creature} inventory
 * and a container on a map.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since 2017-11-13  
 */
public class BagComponent implements Component {
	/** Items list. */
	private List<Item> items;
	
	/** Bag volume.*/
	private int volume;
	
	/**
	 * Creates @link #items items list} based on provided volume.
	 * 
	 * @param volume amount of items a bag can hold
	 */
	public BagComponent(int volume) {
		this.items = new ArrayList<Item>();
		this.volume = volume;
	}
	
	/**
	 * Gets bag content.
	 * 
	 * @return items list
	 */
	public List<Item> getItems() {
		return items;
	}
	
	/**
	 * Adds {@link io.github.izdwuut.yarl.model.entities.Item an item} to a bag.
	 * 
	 * @param item an item to be added to the bag
	 */
	public void addItem(Item item) {
		if(items.size() < volume) {
			items.add(item);
		}
	}
	
	/**
	 * Removes {@link io.github.izdwuut.yarl.model.entities.Item an item} from a bag.
	 * 
	 * @param item an item to be removed from the bag
	 */
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	/**
	 * Gets bag volume.
	 * 
	 * @return bag volume
	 */
	public int getVolume() {
		return volume;
	}
	
	/**
	 * Sets bag volume.
	 * 
	 * @param volume new bag volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}
}
