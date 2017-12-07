package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.components.world.FloorComponent;

/**
 * A world entity.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
public class World extends Entity {
	/**
	 * Constructs a world using a provided ASCII array.
	 * It's dimensions are used to specify world size.
	 * 
	 * @param dungeon two-dimensional ASCII array representing a dungeon
	 */
	public World(char[][] dungeon) {
		add(new DungeonComponent(dungeon));
		add(new SizeComponent(dungeon.length, dungeon[0].length));
		add(new FloorComponent(dungeon));
	}
}
