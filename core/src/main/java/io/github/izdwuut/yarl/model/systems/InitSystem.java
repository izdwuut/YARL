package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.CreatureFactory;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;

/**
 * {@link com.badlogic.ashley.core.EntitySystem A system} that is responsible for
 * some initialization stuff. It dosen't process entities 
 * and can be retrieved through an Ashley engine. Every {@link com.badlogic.ashley.core.EntitySystem System} is created here.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-08
 */
public class InitSystem extends EntitySystem {
	/** 
	 * {@link #world World} settings 
	 */
	Settings settings;
	
	/** 
	 * World entity 
	 */
	World world;
	
	/** 
	 * An Ashley Engine 
	 */
	Engine engine;
	
	/** 
	 * A player entity. 
	 */
	Creature player;
	
	/**
	 * Gets main entities from factories and adds systems to an {@link #engine engine}.
	 * 
	 * @param engine an Ashley engine
	 */
	public InitSystem(Engine engine) {
		this.engine = engine;
		
		settings = new SettingsFactory().getSettings();
		world = new WorldFactory(settings).getWorld();
		player = new CreatureFactory().player("izdwuut");
		engine.addEntity(player);
		
		addSystems();
	}
	
	
	/**
	 * Creates and adds systems to an {@link #engine engine}.
	 */
	void addSystems() {
		engine.addSystem(this);

		WorldSystem worldSystem = new WorldSystem(world, settings, engine);
		engine.addSystem(worldSystem);
		
		MovementSystem movementSystem = new MovementSystem(engine, world);
		movementSystem.priority = 1;
		engine.addSystem(movementSystem);

		CombatSystem combatSystem = new CombatSystem(engine);
		engine.addSystem(combatSystem);
		
		WinSystem winSys = new WinSystem(engine);
		engine.addSystem(winSys);
		
		engine.removeSystem(this);
		
		pause();
	}
	
	/**
	 * Pauses systems.
	 */
	void pause() {
		for (EntitySystem system : engine.getSystems()) {
			system.setProcessing(false);
		}
	}
	
	/**
	 * Gets {@link #settings settings}.
	 * 
	 * @return world settings
	 */
	public Settings getSettings() {
		return settings;
	}
	
	/**
	 * Gets {@link #world world}.
	 * 
	 * @return world entity
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Gets a {@link #player player}.
	 * 
	 * @return a player entity
	 */
	public Creature getPlayer() {
		return player;
	}
}
