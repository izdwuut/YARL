package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

/**
 * An experience level component. Denotes a Creature experience level.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-17
 */
public class LvlComponent implements Component {
	/**
	 * A level.
	 */
	int lvl;

	/**
	 * Gets {@link #lvl an experience level}.
	 * 
	 * @return a level
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * Sets {@link #lvl an experience level}.
	 * 
	 * @param lvl a desired value
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
