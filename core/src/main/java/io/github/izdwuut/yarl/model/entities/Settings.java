package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.components.settings.RNGComponent;
import squidpony.squidmath.RNG;

public class Settings extends Entity {
	public Settings setCellSize(int width, int height) {
		add(new CellSizeComponent(width, height));
		
		return this;
	}
	public Settings setRNG(RNG rng) {
		add(new RNGComponent(rng));
		
		return this;
	}
}
