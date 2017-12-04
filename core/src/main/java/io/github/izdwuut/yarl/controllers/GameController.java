package io.github.izdwuut.yarl.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;

import io.github.izdwuut.yarl.YARL;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.CreatureFactory;
import io.github.izdwuut.yarl.model.systems.MovementSystem;
import io.github.izdwuut.yarl.views.GameScreen;
import squidpony.squidgrid.Direction;
import squidpony.squidgrid.gui.gdx.SquidInput;

public class GameController extends Controller {
	private YARL game;
	private Creature player;
	private GameScreen screen;
	
	//TODO: GameController shouldn't be responsible for creating a player
	public GameController(YARL game, Engine engine, World world, Settings settings) {
		super(engine);
		
		this.game = game;
		this.engine = engine;
		this.player = new CreatureFactory().getPlayer("izdwuut");
		this.screen = new GameScreen(world, settings, player);
		
		init();
	}

	private void init() {
		engine.addEntity(player);
		
		engine.getSystem(MovementSystem.class)
			.addListener(screen);	
		game.setScreen(screen);
		
		handleInput();
	}
	
	//TODO: command pattern
	public void handleInput() {
		input = new SquidInput(new SquidInput.KeyHandler() {
			@Override
			public void handle(char key, boolean alt, boolean ctrl, boolean shift) {
				MovementSystem mov = engine.getSystem(MovementSystem.class);
				switch(key) {
				case SquidInput.UP_ARROW:
					mov.move(player, Direction.UP);
				break;
				case SquidInput.DOWN_ARROW:
					mov.move(player, Direction.DOWN);
				break;
				case SquidInput.LEFT_ARROW:
					mov.move(player, Direction.LEFT);
				break;
				case SquidInput.RIGHT_ARROW:
					mov.move(player, Direction.RIGHT);
				break;
				}
			}
		});
		Gdx.input.setInputProcessor(input);
	}
	
	public void update() {
		if(input.hasNext()) {
            input.next();
            engine.update(Gdx.graphics.getDeltaTime());
        }
	}
	
	private void pause() {
		engine.getSystem(MovementSystem.class).setProcessing(false);
	}
	
	private void resume() {
		engine.getSystem(MovementSystem.class).setProcessing(true);
	}
}
