package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

import squidpony.squidmath.Coord;

public class PositionComponent implements Component {
	private Coord position;

	public Coord getPosition() {
		return position;
	}

	public void setPosition(Coord position) {
		this.position = position;
	}
}
