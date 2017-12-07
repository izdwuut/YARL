package io.github.izdwuut.yarl.model.utils;

import com.badlogic.ashley.core.ComponentMapper;

import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.components.settings.RNGComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.components.world.FloorComponent;

public class Mappers {
	public static final ComponentMapper<SizeComponent> size = ComponentMapper.getFor(SizeComponent.class);
	public static final ComponentMapper<DungeonComponent> dungeon = ComponentMapper.getFor(DungeonComponent.class);
	public static final ComponentMapper<CellSizeComponent> cellSize = ComponentMapper.getFor(CellSizeComponent.class);
	public static final ComponentMapper<RNGComponent> rng = ComponentMapper.getFor(RNGComponent.class);
	public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
	public static final ComponentMapper<MovementComponent> movement = ComponentMapper.getFor(MovementComponent.class);
	public static final ComponentMapper<FloorComponent> floor = ComponentMapper.getFor(FloorComponent.class);
}
