package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

public class SizeComponent implements Component {
	protected int width, height;
	public SizeComponent(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.width = height;
	}
}
