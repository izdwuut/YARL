package io.github.izdwuut.yarl.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;

import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.utils.Mappers;
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
	World world;
	
	/** Tested system. */
	WorldSystem worldSystem;
	
	/** Coords for which tests should fail. */
	static List<Coord> invalid;
	
	/** Dungeon height. */
	static int height;
	
	/** Dungeon width. */
	static int width;
	
	DungeonComponent dungeon;
	
	static Engine engine;
	
	@BeforeAll
	static void initAll() {
		engine = new Engine();
		World world = new WorldFactory(new SettingsFactory().getSettings()).getWorld();
		SizeComponent size = Mappers.size.get(world);
		width = size.getWidth();
		height = size.getHeight();
		invalid = Arrays.asList(Coord.get(0, -1), Coord.get(width, 0), Coord.get(width, height), Coord.get(-1, 0));
	}
	
	@BeforeEach
	void init() {
		engine.removeAllEntities();
		Settings settings = new SettingsFactory().getSettings();
		world = new WorldFactory(settings).getWorld();
		worldSystem = new WorldSystem(world, settings, engine);
		dungeon = Mappers.dungeon.get(world);
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
		GreasedRegion floors = dungeon.getFloors();
		
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
	
	/**
	 * Tests {@link io.github.izdwuut.yarl.model.systems.WorldSystem#populate() populate}.
	 */
	@Test
	void populateTest() {
		List<Coord> positions = Arrays.asList(Coord.get(71, 10),  
				Coord.get(77, 12),  
				Coord.get(58, 21),  
				Coord.get(72, 13), 
				Coord.get(46, 22),  
				Coord.get(61, 21),  
				Coord.get(15, 21),  
				Coord.get(11, 3),  
				Coord.get(41, 16),  
				Coord.get(5, 20));
		GreasedRegion floors = new GreasedRegion(dungeon.getDungeon(), '.');
		
		assertEquals(dungeon.getCreatureMap()
				.getAll()
				.size(), positions.size());
		
		for(Coord pos : positions) {
			assertTrue(worldSystem.isCreature(pos));
			floors.remove(pos);
		}
		
		List<Coord> testedFloors = floors.getAll(), fetchedFloors = dungeon.getFloors().getAll();
		assertEquals(testedFloors.size(), fetchedFloors.size());
		for(int i = 0, j = 0; i < testedFloors.size(); i++, j++) {
			assertEquals(testedFloors.get(i), fetchedFloors.get(j));
		}
		
		ImmutableArray<Entity> entities = engine.getEntities();
		assertEquals(entities.size(), positions.size());
		for(Entity entity : entities) {
			Coord pos = Mappers.position.get(entity)
					.getPosition();
			assertTrue(positions.contains(pos));
		}
	}
	
	/**
	 * Tests {@link io.github.izdwuut.yarl.model.systems.WorldSystem#isCreature(Coord coord) isCreature}.
	 */
	@Test
	void isCreatureTest() {
		Coord pos = Coord.get(5, 5);
		Creature creature = new Creature("Test creature");
		PositionComponent posComp = new PositionComponent();
		posComp.setPosition(pos);
		creature.add(posComp);
		dungeon.setCreature(pos, creature);
		assertTrue(worldSystem.isCreature(pos));
		
		List<Coord> emptyPositions = Arrays.asList(Coord.get(1, 1),
				Coord.get(15, 1),
				Coord.get(10, 20),
				Coord.get(23, 3),
				Coord.get(5, 11));
		
		for(Coord emptyPos : emptyPositions) {
			assertFalse(worldSystem.isCreature(emptyPos));
		}
	}
}
