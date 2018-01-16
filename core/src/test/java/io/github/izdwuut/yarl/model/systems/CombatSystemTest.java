package io.github.izdwuut.yarl.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.creatures.ArmsComponent;
import io.github.izdwuut.yarl.model.components.creatures.ExpComponent;
import io.github.izdwuut.yarl.model.entities.Combat;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Exp;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.factories.CreatureFactory;
import io.github.izdwuut.yarl.model.factories.ItemFactory;
import io.github.izdwuut.yarl.model.factories.SettingsFactory;
import io.github.izdwuut.yarl.model.factories.WorldFactory;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidmath.Coord;

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
	
	/**
	 * An active side of combat.
	 */
	static Creature attacker;
	
	/**
	 * A factory that produces creatures.
	 */
	static CreatureFactory creatureFactory;
	
	/**
	 * Creature positions.
	 */
	static List<Coord> positions;
	
	@BeforeAll
	static void initAll() {
		engine = new Engine();
		Settings settings = new SettingsFactory().getSettings();
		world = new WorldFactory(settings).getWorld();
		worldSystem = new WorldSystem(world, settings, engine);
		engine.addSystem(worldSystem);
		combatSystem = new CombatSystem(engine);
		engine.addSystem(combatSystem);
		creatureFactory = new CreatureFactory();
		attacker = new Creature("Test creature");
		attacker.add(new ArmsComponent(new ItemFactory().sword()));
		positions = Arrays.asList(Coord.get(71, 10),  
				Coord.get(77, 12),  
				Coord.get(58, 21),  
				Coord.get(72, 13), 
				Coord.get(46, 22),  
				Coord.get(61, 21),  
				Coord.get(15, 21),  
				Coord.get(11, 3),  
				Coord.get(41, 16),  
				Coord.get(5, 20));
	}
	
	@BeforeEach
	void init() {
		engine.removeAllEntities();
		for(Coord pos : positions) {
			engine.addEntity(creatureFactory.random().setPos(pos));
		}
	}
	
	/**
	 * Tests that a cleaning crew did it's job right. 
	 * Covers {@link io.github.izdwuut.yarl.model.systems.CombatSystem#cleanUp(Creature creature, Entity combat) cleanUp}.
	 */
	@Test
	void cleanUpTest() {
		Iterator<Entity> iterator = engine.getEntities().iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			if(entity instanceof Creature) {
				Combat combat = new Combat();
				combat.setDefender((Creature) entity);
				combat.setAttacker(attacker);
				engine.addEntity(combat);
			}
		}
		engine.update(0);
		for(Entity entity : engine.getEntities()) {
			assertNotEquals(entity.getClass(), Creature.class);
			assertNotEquals(entity.getClass(), Combat.class);
		}
		assertEquals(Mappers.dungeon.get(world).getCreatureMap().size(), 0);
	}
	
	/**
	 * Tests that killing monsters yields experience points.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.CombatSystem#addExp(Creature creature, int exp) addExp}.
	 */
	@Test
	void addExpTest() {
		List<Exp> exps = new ArrayList<Exp>();
		for(Entity entity : engine.getEntities()) {
			exps.add(new Exp(attacker, entity.getComponent(ExpComponent.class).getExp()));	
		}
		engine.update(0);
		Iterator<Exp> expsIter = exps.iterator();
		Iterator<Entity> entityIter = engine.getEntities().iterator();
		while(expsIter.hasNext() && entityIter.hasNext()) {
			Exp exp = expsIter.next();
			Entity entity = entityIter.next();
			assertEquals(Mappers.creature.get(exp).getCreature(), attacker);
			assertEquals(Mappers.exp.get(entity).getExp(), Mappers.exp.get(exp).getExp());
		}
	}
}
