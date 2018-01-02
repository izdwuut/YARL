package io.github.izdwuut.yarl.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.systems.CombatSystem;
import io.github.izdwuut.yarl.model.systems.Event;
import io.github.izdwuut.yarl.model.systems.InitSystem;
import io.github.izdwuut.yarl.model.systems.MovementSystem;
import io.github.izdwuut.yarl.model.systems.WinSystem;
import io.github.izdwuut.yarl.model.systems.WorldSystem;
import io.github.izdwuut.yarl.views.GameScreen;
import io.github.izdwuut.yarl.views.WinScreen;
import squidpony.squidgrid.Direction;
import squidpony.squidgrid.gui.gdx.SquidInput;

/**
 * Main controller. Handles input applicable to {@link io.github.izdwuut.yarl.views.GameScreen GameScreen},
 * which is used to display map and message log.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class GameController extends Controller implements Listener<Event> {
	/** 
	 * A player entity. 
	 */
	Creature player;
	
	/** 
	 * A screen that is used by {@link io.github.izdwuut.yarl.YARL YARL} to 
	 * {@link io.github.izdwuut.yarl.YARL#render() render} stuff. 
	 * GameController is only responsible for 
	 * {@link io.github.izdwuut.yarl.views.GameScreen#GameScreen(InitSystem initSystem, WorldSystem worldSystem) constructing} it. 
	 * */
	GameScreen screen;
	
	/**
	 * An initialization system.
	 */
	InitSystem initSys;
	
	/**
	 * A world system.
	 */
	WorldSystem worldSys;
	
	/**
	 * Takes as parameters {@link io.github.izdwuut.yarl.YARL YARL} object (used in {@link #init() init} method to
	 * set current screen) and an Ashley {@link com.badlogic.ashley.core.Engine Engine}. When parameters are assigned to appropriate fields, 
	 * an {@link #init() init} method is invoked to perform further actions 
	 * needed to build the GameController.
	 * 
	 * @param game a main game object
	 * @param engine an Ashley engine
	 */
	public GameController(Engine engine, Game game) {
		super(engine, game);
		
		this.game = game;
		
		init();
	}

	/**
	 * Further actions needed to be done to build a GameController.
	 * An InitSystem has to not be used outside constructors.
	 */
	void init() {
		initSys = new InitSystem(engine);
		worldSys = engine.getSystem(WorldSystem.class);
		player = initSys.getPlayer();
		screen = new GameScreen(initSys, worldSys);
		
		//TODO: listeners are set in screens
		engine.getSystem(MovementSystem.class)
			.addListener(screen);	
		game.setScreen(screen);
		engine.getSystem(CombatSystem.class)
		.addListener(screen);
		engine.getSystem(WinSystem.class)
		.addListener(this);
		
		handleInput();
		
		resume();
	}
	
	/**
	 * Specifies controls and assigns them to actions.
	 */
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
	
	@Override
	protected void pause() {
		engine.getSystem(MovementSystem.class).setProcessing(false);
		engine.getSystem(CombatSystem.class).setProcessing(false);
		engine.getSystem(WinSystem.class).setProcessing(true);
	}
	
	@Override
	protected void resume() {
		engine.getSystem(MovementSystem.class).setProcessing(true);
		engine.getSystem(CombatSystem.class).setProcessing(true);
		engine.getSystem(WinSystem.class).setProcessing(true);
	}
	
	/**
	 * Listens for a {@link io.github.izdwuut.yarl.model.systems.Event#FLOOR_CLEAR FLOOR_CLEAR} event dispatched by 
	 * {@link io.github.izdwuut.yarl.model.systems.CombatSystem a CombatSystem}.
	 */
	@Override
	public void receive(Signal<Event> signal, Event e) {
		switch(e) {
		case FLOOR_CLEAR:
			game.setScreen(new WinScreen(initSys, worldSys));
		}
	}
}
