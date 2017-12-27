package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

import squidpony.squidgrid.Direction;

/**
 * A component that describes movement. Every {@link com.badlogic.ashley.core.Entity Entity} 
 * that can move has it.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since 2017-11-20
 */
public class MovementComponent implements Component {
	/** Movement direction. */
	Direction direction = null;

	/**
	 * Gets {@link #direction a direction}.
	 * 
	 * @return direction field
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * {@link #direction a direction} to a provided value.
	 * See {@link squidpony.squidgrid.Direction Direction} for details.
	 * 
	 * @param direction a direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Removes {@link #direction a direction}.
	 * Called after a move is done.
	 */
	public void removeDirection() {
		direction = null;
	}
}
