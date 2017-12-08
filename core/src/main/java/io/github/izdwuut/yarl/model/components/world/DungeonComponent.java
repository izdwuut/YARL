package io.github.izdwuut.yarl.model.components.world;

import com.badlogic.ashley.core.Component;

/**
 * An ASCII representation of {@link #dungeon a dungeon}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-05
 */
public class DungeonComponent implements Component {
	/** An ASCII representation of a dungeon. */
	char[][] dungeon;
	
	/**
	 * Assigns a passed two-dimensional array to a corresponding field.
	 * The array is an ASCII representation of {@link #dungeon a dungeon}.
	 * 
	 * @param dungeon a two-dimensional array of ASCII characters representing a dungeon
	 */
	public DungeonComponent(char[][] dungeon) {
		this.dungeon = dungeon;
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
}
