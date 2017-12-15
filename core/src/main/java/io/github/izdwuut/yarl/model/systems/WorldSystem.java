package io.github.izdwuut.yarl.model.systems;

import com.badlogic.ashley.core.EntitySystem;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidmath.Coord;

/**
 * Logic related to a {@link io.github.izdwuut.yarl.model.entities.World World}.
 * This system doesn't process entities, but is retrievable from an Ashley engine.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-12
 */
public class WorldSystem extends EntitySystem {
	private World world;
	
	public WorldSystem(World world) {
		this.world = world;
	}
	
	/**
	 * Checks if a provided {@link squidpony.squidmath.Coord Coord} is in {@link #world world's} bounds.
	 * 
	 * @param target a coord that is supposed to be in bounds
	 * 
	 * @return true if in bounds, false otherwise
	 */
	public boolean isBounds(Coord target) {
		SizeComponent size = Mappers.size.get(world);
		if(target.x >= 0 
				&& target.x < size.getWidth() 
				&& target.y >= 0 
				&& target.y < size.getHeight()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a provided {@link squidpony.squidmath.Coord Coord} corresponds to a floor.
	 * 
	 * @param coord {@link squidpony.squidmath.Coord a Coord} that is supposed to be a floor
	 * 
	 * @return true if {@link squidpony.squidmath.Coord a Coord} is a floor, false otherwise
	 */
	public boolean isFloor(Coord coord) {
		return Mappers.dungeon.get(world)
				.getFloors()
				.contains(coord);
	}
}
