package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Item;

public class ArmorComponent implements Component {
	private Item armor;
	public ArmorComponent(Item armor) {
		this.armor = armor;
	}
	public Item getArmor() {
		return armor;
	}
	public void setArmor(Item armor) {
		this.armor = armor;
	}
}
