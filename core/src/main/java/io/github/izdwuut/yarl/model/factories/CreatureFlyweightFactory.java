package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.components.GlyphComponent;
import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * A flyweight creature factory. Separates common {@link io.github.izdwuut.yarl.model.entities.Creature Creature}'s components. 
 * Created to reduce boilerplate code. Relies on lazy initialization. 
 * It is separated from a {@link io.github.izdwuut.yarl.model.factories.CreatureFactory} to unclog it.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-27
 */
public abstract class CreatureFlyweightFactory extends FlyweightFactory<Character, Creature> {
	/**
	 * Gets a weapon with flyweight components.
	 * 
	 * @param name an item's name
	 * @param dmg an item's damage
	 * 
	 * @return a weapon with flyweight components
	 */
	protected Creature getCreature(String name, char glyph, Creature creature) {	
		if(hasFlyweight(glyph)) {
			return mergeFlyweight(glyph, creature);		
		}
		
		return addCommon(name, glyph, creature);
	}
	
	/**
	 * Adds common components to a {@code creature} and flyweight object.
	 * 
	 * @param name a creature's name
	 * @param creature an creature with no components
	 * 
	 * @return a {@code creature} expanded by flyweight components
	 */
	//TODO: further reduce boilerplate code
	private Creature addCommon(String name, char glyph, Creature creature) {
		Creature flyweight = new Creature();
		NameComponent nameComp = new NameComponent(name);
		GlyphComponent glyphComp = new GlyphComponent(glyph);
		creature.add(nameComp)
		.add(glyphComp);
		flyweight.add(nameComp)
		.add(glyphComp);
		addFlyweight(glyph, flyweight);
		
		return creature;
	}
}
