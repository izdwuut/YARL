package io.github.izdwuut.yarl.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.ashley.core.Engine;

import io.github.izdwuut.yarl.model.components.creatures.ArmsComponent;
import io.github.izdwuut.yarl.model.components.creatures.LvlComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Exp;
import io.github.izdwuut.yarl.model.factories.ItemFactory;
import io.github.izdwuut.yarl.model.utils.Mappers;

/**
 * Tests {@link io.github.izdwuut.yarl.model.systems.LevelingSystem a LevelingSystem}
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-16
 */
class LevelingSystemTest {
	/**
	 * An Ashley system.
	 */
	static Engine engine;
	
	/**
	 * A creature that gains experience points.
	 */
	static Creature attacker;
	
	static LevelingSystem levelingSystem;
	
	@BeforeAll
	static void initAll() {
		engine = new Engine();
		levelingSystem = new LevelingSystem(engine);
		engine.addSystem(levelingSystem);
		attacker = new Creature("Test creature");
		attacker.add(new ArmsComponent(new ItemFactory().sword()));
	}
	
	@BeforeEach
	void init() {
		attacker.setExp(0)
		.setLvl();
	}
	
	/**
	 * Tests that a creature gained experience points.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#addExp(Creature, int) addExp}.
	 */
	@Test
	void addExpTest() {
		int[] exp = {1, 20, 30, -5, 10, 0, 11, 15, 14, 11};
		int sum = 0;
		
		for(int i = 0; i < exp.length; i++) {
			engine.addEntity(new Exp(attacker, exp[i]));
			sum += exp[i];
		}
		engine.update(0);
		assertEquals(Mappers.exp.get(attacker).getExp(), sum);
		assertEquals(engine.getEntities().size(), 0);
	}
	
	/**
	 * Tests how much experience points remain to a next level.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#getRemainingExp(Creature) getRemainingExp}.
	 */
	@Test 
	void getRemainingExpTest() {
		assertEquals(200, levelingSystem.getRemainingExp(attacker));
		
		engine.addEntity(new Exp(attacker, 199));
		engine.update(0);
		assertEquals(1, levelingSystem.getRemainingExp(attacker));
		
		engine.addEntity(new Exp(attacker, 1));
		engine.update(0);
		assertEquals(350, levelingSystem.getRemainingExp(attacker));
		
		assertEquals(0, engine.getEntities().size());
	}
	
	/**
	 * Test next experience breakpoint.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#getBreakpoint(int) getBreakpoint}.
	 */
	@Test
	void getBreakpointTest() {
		int[] breakpoints = {200, 550, 1050, 1700, 2500, 3450, 4550, 5800, 7200, 8750,
				10450, 12300, 14300, 16450, 18750, 21200, 23800, 26550, 29450};

		for(int i = 0; i < breakpoints.length; i++) {
			assertEquals(breakpoints[i], levelingSystem.getBreakpoint(i + 1));
		}
	}
	
	/**
	 * Tests that a creature gained a level.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#levelUp(Creature) levelUp}.
	 */
	@Test
	void levelUpTest() {
		LvlComponent lvl = Mappers.lvl.get(attacker);
		
		assertEquals(1, lvl.getLvl());
		engine.addEntity(new Exp(attacker, 199));
		engine.update(0);
		assertEquals(1, lvl.getLvl());
		
		engine.addEntity(new Exp(attacker, 1));
		engine.update(0);
		assertEquals(2, lvl.getLvl());
		
		engine.addEntity(new Exp(attacker, 850));
		engine.update(0);
		assertEquals(4, lvl.getLvl());
		
	}
}
