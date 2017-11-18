package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.entities.Settings;
import squidpony.squidmath.RNG;

public class SettingsFactory {
	private Settings settings;
	String seed = "YARL";
	RNG rng = new RNG(seed);
	public SettingsFactory() {
		this.settings = new Settings();
	}
	public Settings getSettings() {
		return build();
	}
	private Settings build() {
		return settings.setCellSize(10, 20).setRNG(rng);
	}
}