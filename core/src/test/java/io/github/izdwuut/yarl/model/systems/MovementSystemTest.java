package io.github.izdwuut.yarl.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import squidpony.squidgrid.Direction;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;

/**
 * Tests {@link io.github.izdwuut.yarl.model.systems.MovementSystem MovementSystem}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-14
 */
class MovementSystemTest {
	/** An Ashley engine. */
	static Engine engine;
	
	/** Moved entity. */
	Entity entity;
	/** Pre and post movement position. */
	PositionComponent prePosition, postPosition;
	
	/** Denotes pre and post movement direction. */
	MovementComponent preMovement, postMovement;
	
	/**
	 * Entity engine tick.
	 */
	float deltaTime;
	
	/** Clockwise {@link #entity Entity} positions, starting from top right. */ 
	static List<Coord> positions;
	
	/** Clockwise directions starting from {@link squidpony.squidgrid.Direction.UP UP}. */ 
	static List<Direction> directions;
	
	/** Tracks currently processed direction. */
	static Iterator<Direction> iterator;
	
	/**
	 * Tested system.
	 */
	static WorldSystem worldSystem;
	
	/** Dungeon floors. */
	static GreasedRegion floors;
	
	@BeforeAll
	static void initAll() {
		engine = new Engine();
		//use known seed, self-populate world (known entities)
		World world = new WorldFactory(new SettingsFactory().getSettings()).getWorld();
		floors = world.getComponent(DungeonComponent.class).getFloors();
		worldSystem = new WorldSystem(world);
		worldSystem.setProcessing(false);
		engine.addSystem(worldSystem);
		engine.addSystem(new MovementSystem(engine));
		SizeComponent size = world.getComponent(SizeComponent.class);
		int width = size.getWidth() - 1, height = size.getHeight() - 1;
		positions = Arrays.asList(Coord.get(width, 0), Coord.get(width, height), Coord.get(0, height), Coord.get(0, 0));
		directions = Arrays.asList(Direction.CLOCKWISE);
		iterator = directions.iterator();
	}
	
	@BeforeEach
	void init() {
		engine.removeAllEntities();
		entity = new Entity();
		prePosition = new PositionComponent();
		postPosition = new PositionComponent();
		preMovement = new MovementComponent();
		postMovement = new MovementComponent();
		entity.add(prePosition)
		.add(preMovement);
		engine.addEntity(entity);
	}
	
	/**
	 * Gets next direction.
	 * 
	 * @return next direction
	 */
	Direction next() {
		if(!iterator.hasNext()) {
			iterator = directions.iterator();
		}
		return iterator.next();
	}
	
	/**
	 * Tests movement, specifically {@link io.github.izdwuut.yarl.model.systems.MovementSystem MovementSystem} {@link io.github.izdwuut.yarl.model.systems.MovementSystem#processEntity(Entity entity, float deltaTime) processEntity} method.
	 * Assures that only floors are traversable and an {@link com.badlogic.ashley.core.Entity Entity} can't move out of map bounds.
	 */
	@Test
	void movementTest() {				
		for(Coord position : positions) {
			prePosition.setPosition(position);
			postPosition.setPosition(position);
			
			for(int i = 0; i < 2; i++) {
				preMovement.setDirection(next());
				engine.update(deltaTime);
				assertEquals(prePosition.getPosition(), postPosition.getPosition());
				assertEquals(preMovement.getDirection(), postMovement.getDirection());
			}
		}
		
		for(Coord start : floors.asCoords()) {
			for(Direction direction : directions) {
				prePosition.setPosition(start);
				Coord target = start.translate(direction);
				preMovement.setDirection(direction);
				engine.update(deltaTime);
				if(worldSystem.isFloor(target)) {
					assertEquals(prePosition.getPosition(), target);
				} else {
					assertEquals(prePosition.getPosition(), start);
				}
				assertEquals(preMovement.getDirection(), postMovement.getDirection());
			}
		}
	}
}
