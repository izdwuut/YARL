package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.components.creatures.ArmsComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import squidpony.squidmath.Coord;

/**
 * A factory that produces {@link io.github.izdwuut.yarl.model.entities.Creature Creatures}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-20
 */
public class CreatureFactory extends CreatureFlyweightFactory {
	/**
	 * Gets a player with a provided name.
	 * 
	 * @param name player's name
	 * 
	 * @return a creature entity tagged as a player
	 */
	public Creature player(String name) {
		Creature player = new Creature();
		player.setPlayer()
				.setInv(10)
				.setMov()
				.setPos(Coord.get(1, 1))
				.add(new ArmsComponent(new ItemFactory().sword()));
				
		return getCreature(name, '@', player);
	}
	
	/**
	 * Gets a Sloth. He is really friendly (or just lazy) and not in a mood
	 * to do anything but sit and think of things he could possibly not do.
	 * Everybody needs to be like the Sloth from time to time.
	 * 
	 * @return a Sloth creature
	 */
	public Creature sloth() {
		Creature sloth = new Creature().setHP(20);
		return getCreature("Sloth", 'S', sloth);
	}
	
	/**
	 * Gets a random monster. There is only a {@link #sloth() Sloth} for now, 
	 * so returning it is as random as it gets.
	 * 
	 * @return a random monster (a Sloth for now)
	 */
	public Creature random() {
		return sloth();
	}
}
