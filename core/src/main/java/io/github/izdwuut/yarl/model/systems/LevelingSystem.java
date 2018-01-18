package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.izdwuut.yarl.model.components.CreatureComponent;
import io.github.izdwuut.yarl.model.components.creatures.ExpComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.utils.Mappers;

/**
 * A leveling system. It handles gaining experience points and 
 * leveling to higher experience levels. It process 
 * {@link io.github.izdwuut.yarl.model.entities.Exp Exp} entities that are
 * added to an entity engine by a 
 * {@link io.github.izdwuut.yarl.model.systems.Combat} system. In future 
 * it might be possible to gain experience for other activities than killing monsters.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-15
 */
//TODO: dispatch only one event on level up, either gain_exp or level_up
public class LevelingSystem extends IteratingSystem implements Listenable<Event> {
	/**
	 * An Ashley engine.
	 */
	Engine engine;
	
	/** 
	 * An {@link io.github.izdwuut.yarl.model.systems.Event Event} dispatcher. 
	 */
	Signal<Event> dispatcher;
	
	public LevelingSystem(Engine engine) {
		super(Family.all(CreatureComponent.class, ExpComponent.class).get());
		
		this.engine = engine;
		this.dispatcher = new Signal<Event>();
	}
	
	/**
	 * Breaks down an {@link io.github.izdwuut.yarl.model.entities.Exp Exp} 
	 * entity. Adds experience points to a ature Creature}.
	 */
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		Creature creature = Mappers.creature.get(entity).getCreature();
		addExp(creature, Mappers.exp.get(entity).getExp());
		levelUp(creature);
		engine.removeEntity(entity);
	}
	
	/**
	 * Adds [@code #exp experience points} to {@code #creature a creature}.
	 * 
	 * @param creature a creature that gains experience
	 * @param exp experience points to add
	 */
	void addExp(Creature creature, int exp) {
		Mappers.exp.get(creature).addExp(exp);
		dispatcher.dispatch(Event.GAIN_EXP);
	}
	
	/**
	 * Gets experience points remaining to a next level.
	 * 
	 * @param creature an examined creature
	 * 
	 * @return experience points remaining to a next level
	 */
	public int getRemainingExp(Creature creature) {
		return getBreakpoint(Mappers.lvl.get(creature).getLvl()) - Mappers.exp.get(creature).getExp();
	}
	
	/**
	 * Gets experience points needed for a certain level.
	 * 
	 * @param lvl an examined experience level
	 * 
	 * @return an experience breakpoint for a given level
	 */
	public int getBreakpoint(int lvl) {
		int breakpoint = 100;

		for(int i = 1; i < lvl; i++) {
			breakpoint += Math.round((double) breakpoint / 2);
		}
		
		return breakpoint;
	}
	
	/**
	 * Grants a level to a {@code #creature}.
	 * 
	 * @param creature {@link io.github.izdwuut.yarl.model.entities.Creature a Creature} that gains a level
	 */
	void levelUp(Creature creature) {
		int lvl = Mappers.lvl.get(creature).getLvl();
		if(getRemainingExp(creature) <= 0) {
			Mappers.lvl.get(creature).setLvl(lvl + 1);
			dispatcher.dispatch(Event.LEVEL_UP);
		}
	}
	
	@Override
	public void addListener(Listener<Event> listener) {
		dispatcher.add(listener);
	}
}
