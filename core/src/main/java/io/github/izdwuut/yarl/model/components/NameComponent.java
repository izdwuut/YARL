package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

/**
 * A name of {@link com.badlogic.ashley.core.Entity an entity}. 
 * It can be a part of {@link io.github.izdwuut.yarl.model.entities.Item an Item} or {@link io.github.izdwuut.yarl.model.entities.Creature a Creature}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
public class NameComponent implements Component {
	/** {@link com.badlogic.ashley.core.Entity An entity} name. */
	private String name;
	
	/**
	 * Creates a component and sets it's {@link #name name} field to a provided string.
	 * 
	 * @param name a name string
	 */
	public NameComponent(String name) {
		this.name = name;
	}
	
	/**
	 * Gets a name.
	 * 
	 * @return name string
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets a name.
	 * 
	 * @param name a name string
	 */
	public void setName(String name) {
		this.name = name;
	}
}
