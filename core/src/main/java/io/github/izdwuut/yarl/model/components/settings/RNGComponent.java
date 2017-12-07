package io.github.izdwuut.yarl.model.components.settings;

import com.badlogic.ashley.core.Component;

import squidpony.squidmath.RNG;

/**
 * A RNG (Random Number Generator) component.
 * One of {@link io.github.izdwuut.yarl.model.entities.World World}'s components.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class RNGComponent implements Component {
	/** A random number generator. */
	private RNG rng;
	
	/**
	 * Builds a RNGComponent using provided random number generator.
	 * 
	 * @param rng a random number generator to set
	 */
	public RNGComponent(RNG rng) {
		this.rng = rng;
	}
	
	/**
	 * Gets a RNG.
	 * 
	 * @return random number generator
	 */
	public RNG getRng() {
		return rng;
	}
	
	/**
	 * Sets a RNG.
	 * 
	 * @param rng random number generator to set
	 */
	public void setRng(RNG rng) {
		this.rng = rng;
	}
}
