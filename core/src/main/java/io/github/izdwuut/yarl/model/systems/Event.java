package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * An enum that holds every event that an Ashley 
 * {@link com.badlogic.ashley.core.EntitySystem system} can dispatch.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-29
 */
public enum Event {
	/**
	 * Dispatches after {@link io.github.izdwuut.yarl.model.systems.MovementSystem} 
	 * {@link io.github.izdwuut.yarl.model.systems.MovementSystem#update(float deltaTime) update}. 
	 */
	MOVEMENT_END,
	
	/**
	 * Dispatched by {@link io.github.izdwuut.yarl.model.systems.CombatSystem CombatSystem}'s 
	 * {@link io.github.izdwuut.yarl.model.systems.CombatSystem#cleanUp(Creature creature, Entity combat) cleanUp} method.
	 */
	CREATURE_KILL,
	
	/**
	 * Dispatched by {@link io.github.izdwuut.yarl.model.systems.CombatSystem CombatSystem}'s 
	 * {@link io.github.izdwuut.yarl.model.systems.CombatSystem CombatSystem#isClear() isClear} method 
	 * after every creature on a floor was killed.
	 */
	FLOOR_CLEAR,
	
	/**
	 * Dispatched by {@link io.github.izdwuut.yarl.model.systems.LevelingSystem a LevelingSystem}'s 
	 * {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#levelUp(Creature) levelUp} method
	 * when a player levels up.
	 */
	LEVEL_UP,
	
	/**
	 * Dispatched by {@link io.github.izdwuut.yarl.model.systems.LevelingSystem a LevelingSystem}'s 
	 * {@link io.github.izdwuut.yarl.model.systems.LevelingSystem#addExp(Creature, int) addExp} method
	 * when a player gains experience points.
	 */
	GAIN_EXP,
	
	/**
	 * Dispatched by {@link io.github.izdwuut.yarl.model.systems.CombatSystem CombatSystem}'s 
	 * {@link io.github.izdwuut.yarl.model.systems.CombatSystem CombatSystem#resolveCombat(Creature, Creature) resolveCombat} method 
	 * when an attacker deals damage and a defender is not killed.
	 */
	DEAL_DMG;
}
