package io.github.izdwuut.yarl;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;

import io.github.izdwuut.yarl.controllers.Controller;
import io.github.izdwuut.yarl.controllers.GameController;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.systems.MovementSystem;

//TODO: populate system
public class YARL extends Game {
	private Controller controller;
	private Engine engine;
	
	public YARL() {
		super();
		this.engine = new Engine();
	}
	
	//TODO: put factories in systems
	public void create() {
		Settings settings = new SettingsFactory().getSettings();
		World world = new WorldFactory(settings).getWorld();
		
		MovementSystem system = new MovementSystem(world);
		engine.addSystem(system);		

		this.controller = new GameController(this, engine, world, settings);
	}
	
	//TODO: non-continuous rendering
	@Override
	public void render() {
		super.render();
		controller.update();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
