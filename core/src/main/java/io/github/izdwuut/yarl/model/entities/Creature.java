package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.BagComponent;
import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.creatures.PlayerComponent;

public class Creature extends Entity {
	public Creature(String name) {
		add(new NameComponent(name));
		add(new PositionComponent());
	}
	public Creature setPlayer() {
		add(new PlayerComponent());
		
		return this;
	}
	public Creature setInv(int volume) {
		add(new BagComponent(volume));
		
		return this;
	}
	public Creature setMov() {
		add(new MovementComponent());
		
		return this;
	}
}
