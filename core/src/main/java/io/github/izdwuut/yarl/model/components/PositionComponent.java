package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

import squidpony.squidmath.Coord;

/**
 * A position on a map. Described by a {@link squidpony.squidmath.Coord Coord}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-20
 */
public class PositionComponent implements Component {
	/** A position on a map. */
	private Coord position;
	
	/**
	 * Initiates a position field to a default (0, 0).
	 */
	public PositionComponent() {
		this.position = Coord.get(1, 1);
	}
	
	/**
	 * Gets a position.
	 * 
	 * @return a position field
	 */
	public Coord getPosition() {
		return position;
	}
	
	/**
	 * Sets a position using provided x and y.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void setPosition(int x, int y) {
		this.position = Coord.get(x, y);
	}
	
	/**
	 * Sets a position using a {@link squidpony.squidmath.Coord Coord} object.
	 * 
	 * @param position a position to set
	 */
	public void setPosition(Coord position) {
		this.position = position;
	}
}
