package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

public class FOVComponent implements Component {
	private int radius;
	public FOVComponent(int radius) {
		this.radius = radius;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
