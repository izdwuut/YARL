package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.NameComponent;

public class Item extends Entity {
	public Item(String name) {
		add(new NameComponent(name));
	}
}
