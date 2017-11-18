package io.github.izdwuut.yarl.controllers;

import com.badlogic.ashley.core.Engine;

import io.github.izdwuut.yarl.YARL;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.views.GameScreen;

public class GameController {
	private YARL game;
	private Engine engine;
	public GameController(YARL game) {
		this.game = game;
		this.engine = new Engine();
		init();
	}
	private void init() {
		Settings settings = new SettingsFactory().getSettings();
		World world = new WorldFactory(settings).getWorld();
		engine.addEntity(world);
		engine.addEntity(settings);

		game.setScreen(new GameScreen(game, world, settings));
	}
}
