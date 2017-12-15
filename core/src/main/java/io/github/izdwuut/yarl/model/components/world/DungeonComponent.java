package io.github.izdwuut.yarl.model.components.world;

import com.badlogic.ashley.core.Component;

import squidpony.squidmath.GreasedRegion;

/**
 * An ASCII representation of {@link #dungeon a dungeon}. It also stores {@link #floors floors}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-05
 */
public class DungeonComponent implements Component {
	/** An ASCII representation of a dungeon. */
	char[][] dungeon;
	
	/** Floors generated from a {@link #dungeon}. */
	private GreasedRegion floors;
	
	/**
	 * Assigns passed two-dimensional array to a corresponding field.
	 * The array is an ASCII representation of {@link #dungeon a dungeon}.
	 * It is used to create a {@link squidpony.squidmath.GreasedRegion GreasedRegion} 
	 * of cells that are {@link #floors floors}.
	 *  
	 * @param dungeon a two-dimensional array of ASCII characters representing a dungeon
	 */
	public DungeonComponent(char[][] dungeon) {
		this.dungeon = dungeon;
		//it should probably be provided by a system
		this.floors = new GreasedRegion(dungeon, '.');
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
	 * @return a {@link squidpony.squidmath.GreasedRegion GreasedRegion} of cells that are floors
	 */
	public GreasedRegion getFloors() {
		return floors;
	}
}
