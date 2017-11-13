package io.github.izdwuut.yarl.model.components.items;

import com.badlogic.ashley.core.Component;

public class CaloriesComponent implements Component {
	private int calories;
	public CaloriesComponent(int calories) {
		this.calories = calories;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
}
