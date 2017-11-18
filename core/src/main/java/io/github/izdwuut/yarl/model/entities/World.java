package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.DungeonComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;

public class World extends Entity {
	public World(char[][] dungeon) {
		add(new DungeonComponent(dungeon));
		add(new SizeComponent(dungeon.length, dungeon[0].length));
	}
}
