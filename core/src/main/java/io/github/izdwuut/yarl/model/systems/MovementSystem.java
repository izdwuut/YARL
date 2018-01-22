package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.entities.Combat;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.Direction;
import squidpony.squidmath.Coord;

/**
 * {@link com.badlogic.ashley.core.EntitySystem A system} that is responsible for 
 * {@link com.badlogic.ashley.core.Entity Entity} movement.
 * Uses {@link com.badlogic.ashley.signals.Signal Signal} to dispatch 
 * {@link io.github.izdwuut.yarl.model.systems.Event Events}. Relies on a 
 * {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-20
 */
public class MovementSystem extends IteratingSystem implements Listenable<Event> {
	/** 
	 * A movement {@link com.badlogic.ashley.core.ComponentMapper Mapper}. 
	 */
	ComponentMapper<MovementComponent> mm;
	
	/** 
	 * An {@link io.github.izdwuut.yarl.model.systems.Event Event} dispatcher. 
	 */
	Signals dispatcher;
	
	/** 
	 * An Ashley engine needed to retrieve {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}. 
	 */
	Engine engine;
	
	/** 
	 * A world entity. 
	 */
	World world;
	
	/** 
	 * A world system. 
	 */
	WorldSystem worldSystem;
	
	/**
	 * Specifies a class of {@link com.badlogic.ashley.core.Entity Entities} that are processed by the system, 
	 * which is every {@link com.badlogic.ashley.core.Entity Entity} that can be moved 
	 * (i.e. has {@link io.github.izdwuut.yarl.model.components.PositionComponent PositionComponent}
	 * that can be manipulated and {@link io.github.izdwuut.yarl.model.components.creatures.MovementComponent MovementComponent} 
	 * that describes movement).
	 * 
	 * @param engine an Ashley engine needed to retrieve {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}
	 * @param world a {@link io.github.izdwuut.yarl.model.entities.World World} [@link com.badlogic.ashley.core.Entity Entity}
	 */
	public MovementSystem(Engine engine, World world) {
		super(Family.all(PositionComponent.class, MovementComponent.class).get());
		
		this.engine = engine;
		this.mm = Mappers.movement;
		this.dispatcher = new Signals();
		this.world = world;
		this.worldSystem = engine.getSystem(WorldSystem.class);
	}
	
	/**
	 * Translates {@link com.badlogic.ashley.core.Entity Entity} 
	 * {@link io.github.izdwuut.yarl.model.components.PositionComponent#position position} based on 
	 * {@link io.github.izdwuut.yarl.model.components.creatures.MovementComponent#direction direction} 
	 * property obtained from {@link io.github.izdwuut.yarl.model.components.creatures.MovementComponent MovementComponent}. 
	 * Fired iteratively in {@link #update(float deltaTime) an update} method.
	 * 
	 * @param entity currently processed entity
	 * @param deltaTime time that passed since last engine update
	 */
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		processMovement(entity);
		Mappers.movement.get(entity)
		.removeDirection();
	}
	
	/**
	 * Used outside this class to set movement 
	 * {@link io.github.izdwuut.yarl.model.components.creatures.MovementComponent#direction direction}.
	 * 
	 * @param entity entity that moves
	 * @param direction movement direction
	 */
	public void move(Entity entity, Direction direction) {
		mm.get(entity)
		.setDirection(direction);
	}
	
	/**
	 * Applies movement to every entity specified in {@link #MovementSystem(Engine engine, World world) a Constructor}
	 * and dispatches a {@link io.github.izdwuut.yarl.model.systems.Event#MOVEMENT_END MOVEMENT_END} 
	 * {@link io.github.izdwuut.yarl.model.systems.Event Event}.
	 * 
	 * @param deltaTime time that passed since last engine update
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		dispatcher.dispatch(Event.MOVEMENT_END);
	}

	@Override
	public void addListener(Listener<Event> listener, Event event) {
		dispatcher.addListener(listener, event);
	}
	
	/**
	 * Initiates combat.
	 * 
	 * @param target a position of a defender {@link io.github.izdwuut.yarl.model.entities.Creature Creature}
	 * @param attacker an attacker {@link io.github.izdwuut.yarl.model.entities.Creature Creature} fetched from
	 * {@link #engine an engine}.
	 */
	void initCombat(Coord target, Entity attacker) {
		if(worldSystem.isCreature(target) && attacker instanceof Creature) {
			Combat combat = new Combat();
			combat.setAttacker((Creature) attacker);
			combat.setDefender(Mappers.dungeon.get(world).getCreature(target));
			engine.addEntity(combat);
		}
	}
	
	void processMovement(Entity entity) {
		MovementComponent mov = mm.get(entity);
		Direction dir = mov.getDirection();
		if(dir == null) {
			return;
		}
		PositionComponent pos = Mappers.position.get(entity);
		Coord target = pos.getPosition()
				.translate(dir.deltaX, dir.deltaY);
		if(!worldSystem.isBounds(target)) {
			return;
		}
		if(worldSystem.isFloor(target)) {
			pos.setPosition(target);
		} else {
			initCombat(target, entity);
		}
	}
}
