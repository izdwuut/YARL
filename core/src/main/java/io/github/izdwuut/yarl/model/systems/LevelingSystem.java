package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
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
public class LevelingSystem extends IteratingSystem {
	/**
	 * An Ashley engine.
	 */
	Engine engine;
	
	public LevelingSystem(Engine engine) {
		super(Family.all(CreatureComponent.class, ExpComponent.class).get());
		
		this.engine = engine;
	}
	
	/**
	 * Breaks down an {@link io.github.izdwuut.yarl.model.entities.Exp Exp} 
	 * entity. Adds experience points to a 
	 * {@link io.github.izdwuut.yarl.model.entities.Creature Creature}.
	 */
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		Creature creature = Mappers.creature.get(entity).getCreature();
		int exp = Mappers.exp.get(entity).getExp();
		Mappers.exp.get(creature).addExp(exp);
		engine.removeEntity(entity);
	}
}
