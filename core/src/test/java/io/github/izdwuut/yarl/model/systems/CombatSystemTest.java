package io.github.izdwuut.yarl.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.entities.Combat;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.utils.Mappers;

/**
 * Tests {@link io.github.izdwuut.yarl.model.systems.CombatSystem a CombatSystem}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-27
 */
class CombatSystemTest {
	/**
	 * An Ashley system.
	 */
	static Engine engine;
	
	/**
	 * A system that populates a dungeon with creatures.
	 */
	static WorldSystem worldSystem;
	
	/**
	 * A tested system.
	 */
	static CombatSystem combatSystem;
	
	/**
	 * A world entity.
	 */
	static World world;
	
	static Creature attacker;
	
	@BeforeAll
	static void initAll() {
		engine = new Engine();
		Settings settings = new SettingsFactory().getSettings();
		world = new WorldFactory(settings).getWorld();
		worldSystem = new WorldSystem(world, settings, engine);
		engine.addSystem(worldSystem);
		combatSystem = new CombatSystem(engine);
		engine.addSystem(combatSystem);
		attacker = new Creature("Test creature");
	}
	
	/**
	 * Tests that a cleaning crew did it's job right. 
	 * Covers {@link io.github.izdwuut.yarl.model.systems.CombatSystem#cleanup(Creature creature, Entity combat) cleanup}.
	 */
	@Test
	void cleanupTest() {	
		Iterator<Entity> iterator = engine.getEntities().iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			if(entity instanceof Creature) {
				Combat combat = new Combat();
				combat.setDefender((Creature) entity);
				combat.setAttacker(attacker);
				engine.addEntity(combat);
				engine.removeEntity(entity);
			}
		}
		
		engine.update(0);
		assertEquals(engine.getEntities().size(), 0);
		assertEquals(Mappers.dungeon.get(world).getCreatureMap().size(), 0);
	}
}
