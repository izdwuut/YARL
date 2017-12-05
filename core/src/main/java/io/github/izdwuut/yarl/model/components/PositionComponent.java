package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

import squidpony.squidmath.Coord;

public class PositionComponent implements Component {
	private Coord position;
	public PositionComponent() {
		this.position = Coord.get(1, 1);
	}
	public Coord getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position = Coord.get(x, y);
	}
	public void setPosition(Coord position) {
		this.position = position;
	}
}
