package io.github.izdwuut.yarl.model.components.world;

import com.badlogic.ashley.core.Component;

/**
 * A current dungeon floor component.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-17
 */
public class FloorComponent implements Component {
	/**
	 * A current floor.
	 */
	int floor;

	/**
	 * Gets {@link #floor a current floor}.
	 * 
	 * @return a level
	 */
	public int getLvl() {
		return floor;
	}

	/**
	 * Sets {@link #floor a current floor}.
	 * 
	 * @param lvl a desired value
	 */
	public void setFloor(int lvl) {
		this.floor = lvl;
	}
	
	/**
	 * Sets floor to a default value of 1.
	 */
	public void setFloor() {
		setFloor(1);
	}
}
