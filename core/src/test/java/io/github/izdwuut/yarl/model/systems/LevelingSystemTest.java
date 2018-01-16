package io.github.izdwuut.yarl.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.badlogic.ashley.core.Engine;

import io.github.izdwuut.yarl.model.components.creatures.ArmsComponent;
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
	
	@BeforeAll
	static void initAll() {
		engine = new Engine();
		engine.addSystem(new LevelingSystem(engine));
		attacker = new Creature("Test creature").setExp(0);
		attacker.add(new ArmsComponent(new ItemFactory().sword()));
	}
	
	/**
	 * Tests that a creature gained experience points.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#processEntity(com.badlogic.ashley.core.Entity, float) processEntity}.
	 */
	@Test
	void gainExpTest() {
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
}
