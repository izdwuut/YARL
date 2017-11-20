package io.github.izdwuut.yarl.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;

import io.github.izdwuut.yarl.YARL;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.CreatureFactory;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.systems.MovementSystem;
import io.github.izdwuut.yarl.views.GameScreen;
import squidpony.squidgrid.Direction;
import squidpony.squidgrid.gui.gdx.SquidInput;

public class GameController {
	private YARL game;
	private Engine engine;
	private Creature player;
	private SquidInput input;
	public GameController(YARL game) {
		this.game = game;
		this.engine = new Engine();
		init();
	}
	private void init() {
		Settings settings = new SettingsFactory().getSettings();
		World world = new WorldFactory(settings).getWorld();
		player = new CreatureFactory().getPlayer("izdwuut");
		engine.addEntity(player);
		
		engine.addSystem(new MovementSystem());
		//engine.addSystem(init)?
		game.setScreen(new GameScreen(world, settings));
		handleInput();
	}
	//TODO: command pattern
	public void handleInput() {
		input = new SquidInput(new SquidInput.KeyHandler() {
			@Override
			public void handle(char key, boolean alt, boolean ctrl, boolean shift) {
			}
		});
		Gdx.input.setInputProcessor(input);
	}
	
	public void update() {
		if(input.hasNext()) {
            input.next();
        }
	}
}
