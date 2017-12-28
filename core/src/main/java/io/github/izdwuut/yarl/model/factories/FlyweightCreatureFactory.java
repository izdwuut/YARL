package io.github.izdwuut.yarl.model.factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.github.izdwuut.yarl.model.components.GlyphComponent;
import io.github.izdwuut.yarl.model.components.NameComponent;
import io.github.izdwuut.yarl.model.entities.Creature;

/**
 * A flyweight creature factory. Separates common creature components. 
 * Implements lo and behold, the flyweight design pattern. Relies on lazy initialization.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-27
 */
public abstract class FlyweightCreatureFactory {
	/**
	 * Common creature names.
	 */
	List<NameComponent> names;
	
	/**
	 * Common creature display characters.
	 */
	List<GlyphComponent> glyphs;
	
	public FlyweightCreatureFactory() {
		names = new ArrayList<NameComponent>();
		glyphs = new ArrayList<GlyphComponent>();
	}
	
	/**
	 * Gets a flyweight Sloth.
	 * 
	 * @return a flyweight Sloth.
	 */
	protected Creature sloth() {		
		return getFlyweight("Sloth", 'S');
	}
	
	/**
	 * Gets a flyweight creature. Created to reduce boilerplate code.
	 * Make sure to operate on {@link #names names} and {@link glyphs} from this method only,
	 * because both name and glyph have to be treated as one!
	 * 
	 * @param name a creature's name
	 * @param glyph a creature's display character
	 * 
	 * @return a flyweight creature
	 */
	//TODO: wouldn't be efficient for multiple creatures. use a hashmap instead, add a merge method (takes two creatures as parameters).
	Creature getFlyweight(String name, char glyph) {
		Iterator<NameComponent> nameIter = names.iterator();
		Iterator<GlyphComponent> glyphIter = glyphs.iterator();
		Creature creature = new Creature();
		
		while(nameIter.hasNext() && glyphIter.hasNext()) {
			NameComponent currName = nameIter.next();
			GlyphComponent currGlyph = glyphIter.next(); 
			if(currName.getName() == name && currGlyph.getGlyph() == glyph) {
				creature.add(currName)
				.add(currGlyph);
				
				return creature;
			}
		}
		
		NameComponent nameComp = new NameComponent(name);
		GlyphComponent glyphComp = new GlyphComponent(glyph);
		creature.add(nameComp)
		.add(glyphComp);
		names.add(nameComp);
		glyphs.add(glyphComp);
		
		return creature;
	}
}
