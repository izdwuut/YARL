package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Item;

public class WeaponComponent implements Component {
	private Item[] weapons;
	public WeaponComponent(Item[] weapons) {
		this.weapons = weapons;
	}
	public Item[] getWeapons() {
		return weapons;
	}
	public void setWeapons(Item[] weapons) {
		this.weapons = weapons;
	}
}
