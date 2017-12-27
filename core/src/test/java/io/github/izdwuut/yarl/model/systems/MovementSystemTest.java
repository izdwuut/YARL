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
import com.badlogic.ashley.core.Family;

import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.combat.AttackerComponent;
import io.github.izdwuut.yarl.model.components.combat.DefenderComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.entities.Combat;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.utils.Mappers;
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
	
	/** A creature that moves. */
	Creature creature;
	
	/** Pre movement position. */
	PositionComponent prePosition;
	
	/** Post movement position. */
	PositionComponent postPosition;
	
	/** Denotes pre movement direction. */
	MovementComponent preMovement;
	
	/** Post movement direction. */
	MovementComponent postMovement;
	
	/**
	 * Entity engine tick.
	 */
	float deltaTime;
	
	/** Clockwise {@link #creature Creature} positions, starting from top right. */ 
	static List<Coord> positions;
	
	/** Clockwise directions starting from {@link squidpony.squidgrid.Direction UP}. */ 
	static List<Direction> directions;
	
	/** Tracks currently processed direction. */
	static Iterator<Direction> iterator;
	
	/**
	 * A system that is needed to query {@link #world a world} entity.
	 */
	static WorldSystem worldSystem;
	
	/** Dungeon floors. */
	static GreasedRegion floors;
	
	/** A world entity. */
	static World world;
	
	
	static Settings settings;
	
	@BeforeAll
	static void initAll() {
		engine = new Engine();
		//use known seed, self-populate world (known entities)
		settings = new SettingsFactory().getSettings();
		world = new WorldFactory(settings).getWorld();
		floors = world.getComponent(DungeonComponent.class).getFloors();
		worldSystem = new WorldSystem(world, settings, engine);
		System.out.println(world.getComponent(DungeonComponent.class).getCreatureMap().toString());
		System.out.println(world.getComponent(DungeonComponent.class).getFloors().toString());
		
		worldSystem.setProcessing(false);
		engine.addSystem(worldSystem);
		
		MovementSystem movSystem = new MovementSystem(engine, world);
		movSystem.priority = 1;
		engine.addSystem(movSystem);
		
		SizeComponent size = world.getComponent(SizeComponent.class);
		int width = size.getWidth() - 1, height = size.getHeight() - 1;
		positions = Arrays.asList(Coord.get(width, 0), Coord.get(width, height), Coord.get(0, height), Coord.get(0, 0));
		directions = Arrays.asList(Direction.CLOCKWISE);
		iterator = directions.iterator();
	}
	
	@BeforeEach
	void init() {
		engine.removeAllEntities();
		creature = new Creature("Test creature");
		prePosition = Mappers.position.get(creature);
		postPosition = new PositionComponent();
		preMovement = new MovementComponent();
		postMovement = new MovementComponent();
		creature.add(preMovement);
		engine.addEntity(creature);
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
	 * Tests movement, specifically {@link io.github.izdwuut.yarl.model.systems.MovementSystem MovementSystem's} {@link io.github.izdwuut.yarl.model.systems.MovementSystem#processEntity(Entity entity, float deltaTime) processEntity} method.
	 * Assures that only floors are traversable and an {@link com.badlogic.ashley.core.Entity Entity} can't move out of map bounds. 
	 * Initiates combat if possible.
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
					initCombatTest(target);
				}
				assertEquals(preMovement.getDirection(), postMovement.getDirection());
			}
		}
		for(Entity entity: engine.getEntities()) {
			System.out.println(entity);
		}
		
		//TODO: put here:
		//initCombatTest(target);
	}
	
	/**
	 * Tests that combat was initiated. Fired by {@link io.github.izdwuut.yarl.model.systems.MovementSystemTest#movementTest() a movementTest}.
	 */
	void initCombatTest(Coord target) {		
		if(worldSystem.isCreature(target)) {
			for(Entity combat : engine.getEntitiesFor(Family.all(AttackerComponent.class, DefenderComponent.class).get())) {
				if(combat instanceof Combat) {
					Mappers.attacker.get((Combat) combat).getAttacker().equals((Creature) creature);
					Mappers.defender.get((Combat) combat).getDefender().equals(Mappers.dungeon.get(world)
							.getCreature(target));
				}
			}
		}
	}
}
