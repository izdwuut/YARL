package io.github.izdwuut.yarl.model.utils;

import com.badlogic.ashley.core.ComponentMapper;

import io.github.izdwuut.yarl.model.components.GlyphComponent;
import io.github.izdwuut.yarl.model.components.PositionComponent;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.combat.AttackerComponent;
import io.github.izdwuut.yarl.model.components.combat.DefenderComponent;
import io.github.izdwuut.yarl.model.components.creatures.ArmsComponent;
import io.github.izdwuut.yarl.model.components.creatures.HPComponent;
import io.github.izdwuut.yarl.model.components.creatures.MovementComponent;
import io.github.izdwuut.yarl.model.components.items.WeaponComponent;
import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.components.settings.RNGComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;

/**
 * Component mappers. A static utility class.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class Mappers {
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.SizeComponent A generic Size} mapper. 
	 */
	public static final ComponentMapper<SizeComponent> size = ComponentMapper.getFor(SizeComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.world.DungeonComponent A dungeon} mapper. 
	 */
	public static final ComponentMapper<DungeonComponent> dungeon = ComponentMapper.getFor(DungeonComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.settings.CellSizeComponent A cell size} mapper. 
	 */
	public static final ComponentMapper<CellSizeComponent> cellSize = ComponentMapper.getFor(CellSizeComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.settings.RNGComponent A random number generator} mapper. 
	 */
	public static final ComponentMapper<RNGComponent> rng = ComponentMapper.getFor(RNGComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.PositionComponent A position} mapper. 
	 */
	public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.creatures.MovementComponent A movement} mapper. 
	 */
	public static final ComponentMapper<MovementComponent> movement = ComponentMapper.getFor(MovementComponent.class);

	/** 
	 * {@link io.github.izdwuut.yarl.model.components.GlyphComponent A Glyph} mapper. 
	 */
	public static final ComponentMapper<GlyphComponent> glyph = ComponentMapper.getFor(GlyphComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.combat.AttackerComponent An Attacker} mapper. 
	 */
	public static final ComponentMapper<AttackerComponent> attacker = ComponentMapper.getFor(AttackerComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.combat.DefenderComponent A Defender} mapper. 
	 */
	public static final ComponentMapper<DefenderComponent> defender = ComponentMapper.getFor(DefenderComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.creatures.HPComponent An HP} mapper. 
	 */
	public static final ComponentMapper<HPComponent> hp = ComponentMapper.getFor(HPComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.creatures.ArmsComponent An Arms} mapper. 
	 */
	public static final ComponentMapper<ArmsComponent> arms = ComponentMapper.getFor(ArmsComponent.class);
	
	/** 
	 * {@link io.github.izdwuut.yarl.model.components.items.WeaponComponent A Weapon} mapper. 
	 */
	public static final ComponentMapper<WeaponComponent> weapon = ComponentMapper.getFor(WeaponComponent.class);
}
