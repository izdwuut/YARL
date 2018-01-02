package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Family;

import io.github.izdwuut.yarl.model.components.creatures.HPComponent;
import io.github.izdwuut.yarl.model.components.creatures.PlayerComponent;

/**
 * Lazily initialized {@link com.badlogic.ashley.core.Entity Entity} {@link com.badlogic.ashley.core.Family Families}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-01
 */
public class Families {
	private Family creatures;
	
	public Family getCreatures() {
		if(creatures == null) {
			creatures = Family.all(NameComponent.class, HPComponent.class).exclude(PlayerComponent.class).get();
		}
		
		return creatures;
	}
}
