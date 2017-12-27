package io.github.izdwuut.yarl.model.factories;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Component;

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
	List<Component> commonSloth;
	
	protected Creature sloth() {
		if(commonSloth == null) {
			commonSloth = new ArrayList<Component>();
			commonSloth.add(new NameComponent("Sloth"));
			commonSloth.add(new GlyphComponent('S'));
		}
		
		Creature sloth = new Creature();
		
		for(Component comp : commonSloth) {
			sloth.add(comp);
		}
		
		return sloth;
	}
}
