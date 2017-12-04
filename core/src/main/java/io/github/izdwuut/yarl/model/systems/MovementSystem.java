package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.ComponentMapper;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.izdwuut.yarl.model.Event;
import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.utils.Mappers;
import squidpony.squidgrid.Direction;
import squidpony.squidmath.Coord;

public class MovementSystem extends IteratingSystem implements Listenable<Event> {
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<MovementComponent> mm;
	private Signal<Event> dispatcher;
	private World world;
	
	public MovementSystem(World world) {
		super(Family.all(PositionComponent.class, MovementComponent.class).get());
		
		this.pm = Mappers.position;
		this.mm = Mappers.movement;
		this.dispatcher = new Signal<Event>();
		this.world = world;
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent mov = mm.get(entity);
		Direction dir = mov.getDirection();
		
		if(dir != null) {
			PositionComponent pos = pm.get(entity);
			Coord target = pos.getPosition()
					.translate(dir.deltaX, dir.deltaY);
			//TODO: move to world/game system
			SizeComponent size = Mappers.size.get(world);
			if(target.x >= 0 && target.x < size.getWidth() && target.y >= 0 && target.y < size.getHeight()) {
				pos.setPosition(target);
			}
			mov.removeDirection();
		}
	}
	
	public void move(Entity entity, Direction direction) {
		MovementComponent mov = mm.get(entity);
		mov.setDirection(direction);
	}
	
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
