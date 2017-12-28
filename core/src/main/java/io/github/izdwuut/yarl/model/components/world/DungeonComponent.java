package io.github.izdwuut.yarl.model.components.world;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;

/**
 * Everything related to a dungeon, i.e. an ASCII representation of {@link #dungeon a dungeon}, {@link #floors dungeon floors} 
 * and {@link #creatures creatures} that populate the dungeon.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-05
 */
public class DungeonComponent implements Component {
	/** 
	 * An ASCII representation of a dungeon. 
	 */
	char[][] dungeon;
	
	/** 
	 * Floors generated from a {@link #dungeon}. 
	 */
	GreasedRegion floors;
	
	/** 
	 * Creatures that inhabit a dungeon. 
	 */
	Map<Coord, Creature> creatures;
	
	/** 
	 * Cells that are inhabited by creatures. 
	 */
	GreasedRegion creatureMap;
	
	/**
	 * Assigns passed two-dimensional array to a corresponding field.
	 * The array is an ASCII representation of {@link #dungeon a dungeon}.
	 * It is used to create a {@link squidpony.squidmath.GreasedRegion GreasedRegion} 
	 * of cells that are {@link #floors floors} and {@link #creatureMap creatures}.
	 *  
	 * @param dungeon a two-dimensional array of ASCII characters representing a dungeon
	 */
	public DungeonComponent(char[][] dungeon, int width, int height) {
		this.dungeon = dungeon;
		//TODO: it should probably be provided by a system. '.' shouldn't be here
		this.floors = new GreasedRegion(dungeon, '.');
		this.creatureMap = new GreasedRegion(width, height);
		this.creatures = new HashMap<Coord, Creature>();	
	}
	
	/**
	 * Gets {@link #dungeon a Dungeon}.
	 * 
	 * @return a dungeon as a two-dimensional array of ASCII characters
	 */
	public char[][] getDungeon() {
		return dungeon;
	}
	
	/**
	 * Sets {@link #dungeon a Dungeon}.
	 * 
	 * @param dungeon a dungeon as a two-dimensional array of ASCII characters
	 */
	public void setDungeon(char[][] dungeon) {
		this.dungeon = dungeon;
	}
	
	/**
	 * Gets cells that are {@link #floors floors}.
	 * 
	 * @return {@link squidpony.squidmath.GreasedRegion a GreasedRegion} of cells that are floors
	 */
	public GreasedRegion getFloors() {
		return floors;
	}
	
	/**
	 * Gets a {@link #creatures creature} at a given position.
	 * 
	 * @param x an x coordinate of a creature
	 * @param y a y coordinate of a creature
	 * 
	 * @return a creature at (x,y)
	 */
	public Creature getCreature(Coord pos) {
		return creatures.get(pos);
	}

	/**
	 * Adds a creature at it's position.
	 * 
	 * @param creature a creature to add
	 */
	public void setCreature(Creature creature) {
		Coord pos = Mappers.position.get(creature)
				.getPosition();
		creatures.put(pos, creature);
		creatureMap.insert(pos);
	}

	/**
	 * Gets cells that are creatures.
	 * 
	 * @return {@link squidpony.squidmath.GreasedRegion a GreasedRegion} of cells that are creatures
	 */
	public GreasedRegion getCreatureMap() {
		return creatureMap;
	}
	
	/**
	 * Removes a creature at a given position.
	 * 
	 * @param pos creature's position
	 */
	public void removeCreature(Coord pos) {
		creatures.remove(pos);
		creatureMap.remove(pos);
		floors.add(pos);
	}
}
