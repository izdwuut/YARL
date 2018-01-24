package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.world.FloorComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;

/**
 * A world entity.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
public class World extends Entity {
	/**
	 * Constructs a world using a provided ASCII array.
	 * It's dimensions are used to specify world {@link io.github.izdwuut.yarl.model.components.SizeComponent size}.
	 * 
	 * @param dungeon two-dimensional ASCII array representing a dungeon
	 */
	public World(char[][] dungeon) {
		int width = dungeon.length, height = dungeon[0].length;
		add(new DungeonComponent(dungeon, width, height));
		add(new SizeComponent(width, height));
		FloorComponent floorComp = new FloorComponent();
		floorComp.setFloor();
		add(floorComp);
	}
}
