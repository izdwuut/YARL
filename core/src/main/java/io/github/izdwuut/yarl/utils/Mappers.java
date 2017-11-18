package io.github.izdwuut.yarl.utils;

import com.badlogic.ashley.core.ComponentMapper;

import io.github.izdwuut.yarl.model.components.DungeonComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.components.settings.RNGComponent;

public class Mappers {
	public static final ComponentMapper<SizeComponent> size = ComponentMapper.getFor(SizeComponent.class);
	public static final ComponentMapper<DungeonComponent> dungeon = ComponentMapper.getFor(DungeonComponent.class);
	public static final ComponentMapper<CellSizeComponent> cellSize = ComponentMapper.getFor(CellSizeComponent.class);
	public static final ComponentMapper<RNGComponent> rng = ComponentMapper.getFor(RNGComponent.class);
}
