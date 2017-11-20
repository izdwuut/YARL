package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.utils.Mappers;
import squidpony.squidgrid.Direction;

public class MovementSystem extends IteratingSystem {
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<MovementComponent> mm;
	
	public MovementSystem() {
		super(Family.all(PositionComponent.class, MovementComponent.class).get());
		
		pm = Mappers.position;
		mm = Mappers.movement;
	}
	
	public void processEntity(Entity entity, float deltaTime) {
		PositionComponent pos = pm.get(entity);
		MovementComponent mov = mm.get(entity);
		
		if(mov.getDirection() != null) {
			Direction dir = mov.getDirection();
			pos.getPosition().translate(dir.deltaX, dir.deltaY);
		}
	}
	public void move(Entity entity, Direction direction) {
		MovementComponent mov = mm.get(entity);
		mov.setDirection(direction);
	}
}
