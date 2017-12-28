package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.entities.Settings;
import squidpony.squidmath.RNG;

/**
 * A factory that produces game {@link io.github.izdwuut.yarl.model.entities.Settings Settings}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class SettingsFactory {
	/** 
	 * Settings that a factory produces. 
	 */
	Settings settings;
	
	/** 
	 * A {@link #rng random number generator} seed. 
	 */
	//TODO: random seed
	String seed = "YARL";
	
	/** 
	 * A {@link #seed seeded} random number generator. 
	 */
	RNG rng = new RNG(seed);
	
	/**
	 * Constructs a factory and produces {@link io.github.izdwuut.yarl.model.entities.Settings Settings}
	 * using hard-coded values specified in {@link #build() a build} method. 
	 */
	public SettingsFactory() {
		this.settings = new Settings();
	
		build();
	}
	
	/**
	 * Gets {@link #settings settings}.
	 * 
	 * @return produced {@link io.github.izdwuut.yarl.model.entities.Settings Settings}
	 */
	public Settings getSettings() {
		return settings;
	}
	
	/**
	 * Produces {@link #settings settings}.
	 */
	void build() {
		settings.setCellSize(10, 20)
			.setRNG(rng);
	}
}