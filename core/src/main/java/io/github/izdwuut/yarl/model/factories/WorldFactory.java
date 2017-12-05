package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.utils.Mappers;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.IDungeonGenerator;
import squidpony.squidmath.RNG;

public class WorldFactory {
	private RNG rng;
	IDungeonGenerator gen;
	Settings settings;
	//TODO: put all settings in a separate class
	public WorldFactory(Settings settings) {
		this.settings = settings;
		this.rng = Mappers.rng.get(settings).getRng();
		this.gen = new DungeonGenerator(80, 24, rng);
	}
	public World getWorld() {
		return new World(gen.generate());
	}
}
