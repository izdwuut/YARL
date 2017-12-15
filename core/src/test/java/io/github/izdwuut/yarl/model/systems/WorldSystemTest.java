package io.github.izdwuut.yarl.test.model.systems;

import java.util.Arrays;
import java.util.Random;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import com.badlogic.ashley.core.Engine;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.utils.Mappers;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.systems.WorldSystem;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;

/**
 * Tests {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-15
 */
class WorldSystemTest {
	/** A world entity. */
	static World world;
	
	/** Tested system. */
	static WorldSystem worldSystem;
	
	/** Coords for which tests should fail. */
	static List<Coord> invalid;
	
	/** Dungeon height. */
	static int height;
	
	/** Dungeon width. */
	static int width;
	
	@BeforeAll
	static void initAll() {
		world = new WorldFactory(new SettingsFactory().getSettings()).getWorld();
		worldSystem = new WorldSystem(world);
		SizeComponent size = Mappers.size.get(world);
		width = size.getWidth();
		height = size.getHeight();
		invalid = Arrays.asList(Coord.get(0, -1), Coord.get(width, 0), Coord.get(width, height), Coord.get(-1, 0));
	}
	
	/**
	 * Tests {@link io.github.izdwuut.yarl.model.systems.WorldSystem#isBounds(Coord) isBounds}.
	 */
	@Test
	void isBoundsTest() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				assertTrue(worldSystem.isBounds(Coord.get(i, j)));
			}
		}
		
		for(Coord coord : invalid) {
			assertFalse(worldSystem.isBounds(coord));
		}
	}

	/**
	 * Tests {@link io.github.izdwuut.yarl.model.systems.WorldSystem#isFloor(Coord) isFloor}.
	 */
	@Test
	void isFloorTest() {
		GreasedRegion floors = Mappers.dungeon.get(world).getFloors();
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < width; j++) {
				Coord coord = Coord.get(i, j);
				boolean isFloor = worldSystem.isFloor(coord);
				if(floors.contains(coord)) {
					assertTrue(isFloor);
				} else {
					assertFalse(isFloor);
				}
			}
		}
		
		for(Coord coord : invalid) {
			assertFalse(worldSystem.isFloor(coord));
		}
	}
}
