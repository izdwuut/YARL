package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.EntitySystem;

import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.utils.Mappers;

/**
 * A health points system. 
 * It can be used to query {@link io.github.izdwuut.yarl.model.entities.Creature a Creature} HP.
 * It doesn't process entities in an Ashley engine loop.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-22
 */
public class HpSystem extends EntitySystem {
	/**
	 * Gets a percentage of creature's HP left. 
	 * 
	 * @param creature a queried creature
	 * 
	 * @return a percentage of HP left
	 */
	double getHpRatio(Creature creature) {
		int hp = Mappers.hp.get(creature).getHp();
		int maxHp = Mappers.maxHp.get(creature).getHP();
		
		return (double) hp / maxHp;
	}
	
	/**
	 * Checks if a creature's HP is low.
	 * 
	 * @param creature a queried creature
	 * 
	 * @return true if HP is low, false otherwise
	 */
	public boolean isHpLow(Creature creature) {
		return getHpRatio(creature) <= 0.5;
	}
	
	/**
	 * Checks if a creature's HP is critical.
	 * 
	 * @param creature a queried creature
	 * 
	 * @return true if HP is critical, false otherwise
	 */
	public boolean isHpCritical(Creature creature) {
		return getHpRatio(creature) <= 0.2;
	}
	
	/**
	 * Gets a {@code #creature creature} HP.
	 * 
	 * @param creature a queried creature
	 * 
	 * @return creature HP
	 */
	public int getHp(Creature creature) {
		return Mappers.hp.get(creature).getHp();
	}
	
	/**
	 * Adds HP to a creature.
	 * 
	 * @param creature a creature that gains HP
	 * 
	 * @param hp added HP
	 */
	public void addHp(Creature creature, int hp) {
		Mappers.hp.get(creature).addHP(hp);
	}
}
