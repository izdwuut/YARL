package io.github.izdwuut.yarl.model.components.world;

import com.badlogic.ashley.core.Component;

import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;

/**
 * A {@link io.github.izdwuut.yarl.model.entities.World World's} component that has knowledge of 
 * cells that are floors ("."). It is used to check if a player can step on a cell.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-05
 */
public class FloorComponent implements Component {
	private GreasedRegion floors;
	
	/**
	 * Uses provided dungeon to create a {@link squidpony.squidmath.GreasedRegion GreasedRegion} 
	 * of cells that are {@link #floors floors}. 
	 * 
	 * @param dungeon dungeon as a two-dimensional ASCII array
	 */
	public FloorComponent(char[][] dungeon) {
		//it should probably be provided by a system
		this.floors = new GreasedRegion(dungeon, '.');
	}
	
	/**
	 * Gets cells that are {@link #floors floors}.
	 * 
	 * @return a {@link squidpony.squidmath.GreasedRegion GreasedRegion} object of cells that are floors
	 */
	public GreasedRegion getFloors() {
		return floors;
	}
	
	/**
	 * Checks if a provided {@link squidpony.squidmath.Coord Coord} corresponds to a floor.
	 * 
	 * @param coord
	 * @return
	 */
	public boolean isFloor(Coord coord) {
		return floors.contains(coord);
	}
}
