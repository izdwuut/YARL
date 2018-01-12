package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

/**
 * An experience component. It can denote either how
 * experienced is an entity or how much experience it yields.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-13
 */
public class ExpComponent implements Component {
	/**
	 * Experience points.
	 */
	int exp;
	
	public ExpComponent(int exp) {
		this.exp = exp;
	}
	
	/**
	 * Gets {@link #exp} experience points.
	 * 
	 * @return number of experience points
	 */
	public int getExp() {
		return exp;
	}
	
	/**
	 * Sets experience points to provided number.
	 * 
	 * @param exp number of experience points to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
}
