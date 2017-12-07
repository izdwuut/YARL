package io.github.izdwuut.yarl.model.utils;

import com.badlogic.ashley.core.ComponentMapper;

import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.components.settings.RNGComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.components.world.FloorComponent;

/**
 * Component mappers. A static utility class.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class Mappers {
	/** A generic size mapper. */
	public static final ComponentMapper<SizeComponent> size = ComponentMapper.getFor(SizeComponent.class);
	
	/** A dungeon mapper. */
	public static final ComponentMapper<DungeonComponent> dungeon = ComponentMapper.getFor(DungeonComponent.class);
	
	/** A cell size mapper. */
	public static final ComponentMapper<CellSizeComponent> cellSize = ComponentMapper.getFor(CellSizeComponent.class);
	
	/** A random number generator mapper. */
	public static final ComponentMapper<RNGComponent> rng = ComponentMapper.getFor(RNGComponent.class);
	
	/** A position mapper. */
	public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
	
	/** A movement mapper. */
	public static final ComponentMapper<MovementComponent> movement = ComponentMapper.getFor(MovementComponent.class);
	
	/** A floor mapper. */
	public static final ComponentMapper<FloorComponent> floor = ComponentMapper.getFor(FloorComponent.class);
}
