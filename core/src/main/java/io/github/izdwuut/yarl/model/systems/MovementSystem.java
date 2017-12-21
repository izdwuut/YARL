package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.izdwuut.yarl.model.Event;
import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.Direction;
import squidpony.squidmath.Coord;

/**
 * {@link com.badlogic.ashley.core.EntitySystem A system} that is responsible for 
 * {@link com.badlogic.ashley.core.Entity Entity} movement.
 * Uses {@link com.badlogic.ashley.signals.Signal Signal} to dispatch 
 * {@link io.github.izdwuut.yarl.model.Event Events}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-20
 */
public class MovementSystem extends IteratingSystem implements Listenable<Event> {
	/** A movement {@link com.badlogic.ashley.core.ComponentMapper Mapper}. */
	private ComponentMapper<MovementComponent> mm;
	
	/** An {@link io.github.izdwuut.yarl.model.Event Event} dispatcher. */
	private Signal<Event> dispatcher;
	
	/** An Ashley engine needed to retrieve {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}. */
	private Engine engine;
	
	/**
	 * Specifies a class of {@link com.badlogic.ashley.core.Entity Entities} that are processed by the system, 
	 * which is every {@link com.badlogic.ashley.core.Entity Entity} that can be moved 
	 * (i.e. has {@link io.github.izdwuut.yarl.model.components.PositionComponent PositionComponent}
	 * that can be manipulated and {@link io.github.izdwuut.yarl.model.components.creatures.MovementComponent MovementComponent} 
	 * that describes movement).
	 * 
	 * @param engine an Ashley engine needed to retrieve {@link io.github.izdwuut.yarl.model.systems.WorldSystem WorldSystem}
	 */
	public MovementSystem(Engine engine) {
		super(Family.all(PositionComponent.class, MovementComponent.class).get());
		
		this.engine = engine;
		this.mm = Mappers.movement;
		this.dispatcher = new Signal<Event>();
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
		MovementComponent mov = mm.get(entity);
		Direction direction = mov.getDirection();
		if(direction != null) {
			PositionComponent pos = Mappers.position.get(entity);
			Coord target = pos.getPosition()
					.translate(direction.deltaX, direction.deltaY);
			WorldSystem world = engine.getSystem(WorldSystem.class);
			if(world.isBounds(target)
					&& world.isFloor(target)) {
				pos.setPosition(target);
			}
			mov.removeDirection();
		}
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
	 * Applies movement to every entity specified in {@link #MovementSystem(Engine) a Constructor}
	 * and dispatches a {@link io.github.izdwuut.yarl.model.Event#MOVEMENT_END MOVEMENT_END} 
	 * {@link io.github.izdwuut.yarl.model.Event Event}.
	 * 
	 * @param deltaTime time that passed since last engine update
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		dispatcher.dispatch(Event.MOVEMENT_END);
	}

	@Override
	public void addListener(Listener<Event> listener) {
		dispatcher.add(listener);
	}
}
