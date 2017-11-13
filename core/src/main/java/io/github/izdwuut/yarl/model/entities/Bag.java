package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.BagComponent;

public class Bag extends Entity {
	public Bag(int volume) {
		add(new BagComponent(volume));
	}
}
