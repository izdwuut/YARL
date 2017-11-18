package io.github.izdwuut.yarl.model.components.settings;

import com.badlogic.ashley.core.Component;

import squidpony.squidmath.RNG;

public class RNGComponent implements Component {
	private RNG rng;
	public RNGComponent(RNG rng) {
		this.rng = rng;
	}
	public RNG getRng() {
		return rng;
	}
	public void setRng(RNG rng) {
		this.rng = rng;
	}
}
