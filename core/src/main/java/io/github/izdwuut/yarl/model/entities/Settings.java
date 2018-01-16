package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.settings.RNGComponent;
import squidpony.squidmath.RNG;

/**
 * World's settings.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class Settings extends Entity {
	/**
	 * Sets {@link io.github.izdwuut.yarl.model.components.settings.SizeComponent cell size} 
	 * using provided width and height.
	 * 
	 * @param width cell width (in pixels)
	 * @param height cell height (in pixels)
	 * 
	 * @return current instance of an object (chaining)
	 */
	public Settings setCellSize(int width, int height) {
		add(new SizeComponent(width, height));
		
		return this;
	}
	
	/**
	 * Sets {@link io.github.izdwuut.yarl.model.components.settings.RNGComponent a random number generator} 
	 * using {@link squidpony.squidmath.RNG a provided one}.
	 * 
	 * @param rng a random number generator
	 * 
	 * @return current instance of an object (chaining)
	 */
	public Settings setRNG(RNG rng) {
		add(new RNGComponent(rng));
		
		return this;
	}
}
