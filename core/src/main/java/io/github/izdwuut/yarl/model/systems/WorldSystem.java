package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.CreatureFactory;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;

/**
 * Logic related to a {@link io.github.izdwuut.yarl.model.entities.World World}.
 * This system doesn't process entities, but is retrievable from an Ashley engine.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-12
 */
public class WorldSystem extends EntitySystem {
	/** A world entity. */
	World world;
	
	/** Game settings. */
	Settings settings;
	
	/** Floors obtained from a {@link #dungeonComp dungeon component}. */
	GreasedRegion floors;
	
	/** A dungeon component. */
	DungeonComponent dungeonComp;
	
	Engine engine;
	
	public WorldSystem(World world, Settings settings, Engine engine) {
		this.world = world;
		this.settings = settings;
		this.dungeonComp = Mappers.dungeon.get(world);
		this.floors = dungeonComp.getFloors();
		this.engine = engine;
		
		populate();
	}
	
	/**
	 * Checks if a provided {@link squidpony.squidmath.Coord Coord} is in {@link #world world's} bounds.
	 * 
	 * @param target a coord that is supposed to be in bounds
	 * 
	 * @return true if in bounds, false otherwise
	 */
	public boolean isBounds(Coord target) {
		SizeComponent size = Mappers.size.get(world);
		if(target.x >= 0 
				&& target.x < size.getWidth() 
				&& target.y >= 0 
				&& target.y < size.getHeight()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a provided {@link squidpony.squidmath.Coord Coord} corresponds to a floor.
	 * 
	 * @param coord {@link squidpony.squidmath.Coord a Coord} that is supposed to be a floor
	 * 
	 * @return true if {@link squidpony.squidmath.Coord a Coord} is a floor, false otherwise
	 */
	public boolean isFloor(Coord coord) {
		return floors.contains(coord);
	}
	
	/**
	 * Populate a dungeon with procedurally generated creatures.
	 */
	//TODO: handle no empty floor
	void populate() {
		CreatureFactory factory = new CreatureFactory();
		for(int i = 0; i < 10; i++) {
			Creature creature = factory.random();
			Mappers.position.get(creature)
			.setPosition(getRandomFloor());
			addCreature(creature);
		}
	}
	
	/**
	 * Adds {@link io.github.izdwuut.yarl.model.entities.Creature a creature} to a dungeon.
	 * 
	 * @param creature {@link io.github.izdwuut.yarl.model.entities.Creature a creature} to add to a dungeon
	 */
	private void addCreature(Creature creature) {
		Coord pos = Mappers.position.get(creature).getPosition();
		if(isFloor(pos)) {
			dungeonComp.setCreature(creature);
			dungeonComp.getFloors()
			.remove(pos);
			engine.addEntity(creature);
		}
	}
	
	/**
	 * Checks if a provided {@link squidpony.squidmath.Coord coord} is inhabited by any creature.
	 * 
	 * @param coord {@link squidpony.squidmath.Coord a coord} to check
	 * 
	 * @return true if a provided {@link squidpony.squidmath.Coord coord} is inhabited, false otherwise
	 */
	public boolean isCreature(Coord coord) {
		return Mappers.dungeon.get(world)
				.getCreatureMap()
				.contains(coord);
	}
	
	/**
	 * Gets a random empty floor. Returns (-1,-1) if none is found.
	 * 
	 * @return {@link squidpony.squidmath.Coord a coord} that represents an empty dungeon cell
	 */
	private Coord getRandomFloor() {
		return floors.singleRandom(Mappers.rng.get(settings).getRng());
	}

	/**
	 * Gets {@link #world a world} entity
	 * 
	 * @return {@link #world a world} entity
	 */
	public World getWorld() {
		return world;
	}
	
	public void removeCreature(Creature creature) {
		dungeonComp.removeCreature(Mappers.position.get(creature)
				.getPosition());
		engine.removeEntity(creature);
	}
}
