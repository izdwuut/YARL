package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * A factory that produces {@link io.github.izdwuut.yarl.model.entities.Creature Creatures}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-20
 */
public class CreatureFactory {
	/**
	 * Gets a player with a provided name.
	 * 
	 * @param name player's name
	 * 
	 * @return a creature entity tagged as a player
	 */
	public Creature player(String name) {
		Creature player = new Creature(name);
		player.setPlayer()
			.setInv(10)
			.setMov()
			.setGlyph('@');
		
		return player;
	}
}
