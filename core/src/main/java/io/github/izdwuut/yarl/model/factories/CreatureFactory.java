package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.components.GlyphComponent;
import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.components.creatures.ExpComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import squidpony.squidmath.Coord;

/**
 * A factory that produces {@link io.github.izdwuut.yarl.model.entities.Creature Creatures}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-20
 */
public class CreatureFactory extends FlyweightFactory<String, Creature> {
	/**
	 * An item factory.
	 */
	ItemFactory itemFactory;
	
	public CreatureFactory() {
		super(Creature.class);
		
		itemFactory = new ItemFactory();
	}
	
	/**
	 * Gets a player with a provided name.
	 * 
	 * @param name player's name
	 * 
	 * @return a creature entity tagged as a player
	 */
	public Creature player(String name) {
		return new Creature().setPlayer()
				.setInv(10)
				.setMov()
				.setPos(Coord.get(1, 1))
				.setArms(itemFactory.sword())
				.setExp(0)
				.setName(name)
				.setGlyph('@')
				.setLvl();
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
		
		return getCreature("Sloth", 'S', 100, sloth);
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
	
	
	/**
	 * Gets a creature using provided common parameters 
	 * and {@code #creature a creature} with custom components.
	 * 
	 * @param name {@code #creature a creature}'s name
	 * @param glyph {@code #creature a creature}'s display character
	 * @param exp number of experience points yielded from {@code #creature a creature}
	 * @param creature {@code #creature a creature} with custom components
	 * 
	 * @return {@code #creature a creature} with flyweight components
	 */
	Creature getCreature(String name, char glyph, int exp, Creature creature) {
		if(hasFlyweight(name)) {
			return getEntity(name, creature);
		}
		
		return getEntity(name, creature, new NameComponent(name), new GlyphComponent(glyph), new ExpComponent(exp));
	}
}
