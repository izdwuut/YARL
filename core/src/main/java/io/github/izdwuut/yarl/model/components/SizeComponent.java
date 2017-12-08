package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

/**
 * A component that describes {@link com.badlogic.ashley.core.Entity Entity's} dimensions.
 * It is one of {@link io.github.izdwuut.yarl.model.entities.World World}'s entity components 
 * used to set it's {@link width width} and {@link #height height} (measured in cells).
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class SizeComponent implements Component {
	/** {@link com.badlogic.ashley.core.Entity An Entity} width. */
	private int width;
	
	/** {@link com.badlogic.ashley.core.Entity An Entity} height. */
	private int height;
	
	/**
	 * Constructs a component and sets it's fields to provided values.
	 * 
	 * @param width width to set
	 * @param height height to set
	 */
	public SizeComponent(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Gets width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets width.
	 * 
	 * @param width width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Gets height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets height.
	 * 
	 * @param height height to set
	 */
	public void setHeight(int height) {
		this.width = height;
	}
}
