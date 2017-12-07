package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

import squidpony.squidgrid.Direction;

public class MovementComponent implements Component {
	private Direction direction = null;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void removeDirection() {
		direction = null;
	}
}
