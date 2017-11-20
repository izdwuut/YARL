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
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		MovementComponent mov = mm.get(entity);
		Direction dir = mov.getDirection();
		
		System.out.print("move");
		if(dir != null) {
			PositionComponent pos = pm.get(entity);
			pos.setPosition(pos.getPosition().translate(dir.deltaX, dir.deltaY));
			mov.removeDirection();
		}
	}
	public void move(Entity entity, Direction direction) {
		MovementComponent mov = mm.get(entity);
		System.out.println(direction.deltaX + " " + direction.deltaY);
		mov.setDirection(direction);
	}
}
