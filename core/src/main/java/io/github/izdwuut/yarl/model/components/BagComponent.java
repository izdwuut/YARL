package io.github.izdwuut.yarl.model.components;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Item;

public class BagComponent implements Component {
	private List<Item> items;
	private int volume;
	public BagComponent(int volume) {
		this.items = new ArrayList<Item>();
		this.volume = volume;
	}
	public List<Item> getItems() {
		return items;
	}
	public void addItem(Item item) {
		if(items.size() < volume) {
			items.add(item);
		}
	}
	public void removeItem(Item item) {
		items.remove(item);
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
}
