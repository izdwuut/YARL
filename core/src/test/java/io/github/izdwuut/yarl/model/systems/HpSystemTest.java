package io.github.izdwuut.yarl.model.systems;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.izdwuut.yarl.model.components.creatures.HpComponent;
import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * Tests {@link io.github.izdwuut.yarl.model.systems.HpSystem a health points system}.
 *  
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-24
 */
class HpSystemTest {
	/**
	 * A tested system.
	 */
	static HpSystem hpSystem;
	
	/**
	 * A queried entity.
	 */
	static Creature creature;
	
	/**
	 * Current HP of {@link #creature a tested creature}.
	 */
	HpComponent hpComp;
	
	/**
	 * A current and max HP of {@link #creature a creature}.
	 */
	static int hp;
	
	@BeforeAll
	static void initAll() {
		hpSystem = new HpSystem();
		hp = 10;
		creature = new Creature().setMaxHp(hp);
	}
	
	@BeforeEach
	void init() {
		hpComp = new HpComponent(hp);
		creature.add(hpComp);
	}
	
	/**
	 * Tests if a creature is low on health points.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.HpSystem#isHpLow(Creature) isHpLow}.
	 */
	@Test
	void isHpLowTest() {
		System.out.println(hpComp.getHp());
		assertFalse(hpSystem.isHpLow(creature));
		hpComp.addHP(-4);
		assertFalse(hpSystem.isHpLow(creature));
		hpComp.addHP(-1);
		assertTrue(hpSystem.isHpLow(creature));
	}
	
	/**
	 * Tests if a creature is critical on health points.
	 * Covers {@link io.github.izdwuut.yarl.model.systems.HpSystem#isHpCritical(Creature) isHpCritical}.
	 */
	@Test
	void isHpCriticalTest() {
		assertFalse(hpSystem.isHpCritical(creature));
		hpComp.addHP(-7);
		assertFalse(hpSystem.isHpCritical(creature));
		hpComp.addHP(-1);
		assertTrue(hpSystem.isHpCritical(creature));
	}
}
