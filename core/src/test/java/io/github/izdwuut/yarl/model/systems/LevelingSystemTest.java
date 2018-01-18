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
		assertEquals(100, levelingSystem.getRemainingExp(attacker));
		
		engine.addEntity(new Exp(attacker, 99));
		engine.update(0);
		assertEquals(1, levelingSystem.getRemainingExp(attacker));
		
		engine.addEntity(new Exp(attacker, 1));
		engine.update(0);
		assertEquals(50, levelingSystem.getRemainingExp(attacker));
		
		assertEquals(0, engine.getEntities().size());
	}
	
	/**
	 * Test next experience breakpoint.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#getBreakpoint(int) getBreakpoint}.
	 */
	@Test
	void getBreakpointTest() {
		int[] breakpoints = {100, 150, 225, 338, 507, 761, 1142, 1713, 2570, 3855,
			5783, 8675, 13013, 19520, 29280, 43920, 65880, 98820, 148230, 222345
		};

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
		engine.addEntity(new Exp(attacker, 99));
		engine.update(0);
		assertEquals(1, lvl.getLvl());
		
		engine.addEntity(new Exp(attacker, 1));
		engine.update(0);
		assertEquals(2, lvl.getLvl());
		
		engine.addEntity(new Exp(attacker, 125));
		engine.update(0);
		assertEquals(4, lvl.getLvl());
		
	}
}
