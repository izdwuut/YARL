package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.IDungeonGenerator;
import squidpony.squidmath.RNG;

/**
 * A factory that produces {@link io.github.izdwuut.yarl.model.entities.World a World}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 *
 */
public class WorldFactory {
	/** A random number generator obtained from {@link io.github.izdwuut.yarl.model.entities.Settings Settings}. */
	RNG rng;
	
	/** A dungeon generator. */
	IDungeonGenerator gen;
	
	/** World settings. */
	Settings settings;
	
	/**
	 * Constructs a factory and produces {@link io.github.izdwuut.yarl.model.entities.World a World} 
	 * using provided {@link io.github.izdwuut.yarl.model.entities.Settings settings}.
	 * 
	 * @param settings world settings
	 */
	public WorldFactory(Settings settings) {
		this.settings = settings;
		this.rng = Mappers.rng.get(settings).getRng();
		this.gen = new DungeonGenerator(80, 24, rng);
	}
	
	/**
	 * Gets {@link io.github.izdwuut.yarl.model.entities.World a World}.
	 * 
	 * @return a produced {@link io.github.izdwuut.yarl.model.entities.World World.}
	 */
	public World getWorld() {
		return new World(gen.generate());
	}
}
