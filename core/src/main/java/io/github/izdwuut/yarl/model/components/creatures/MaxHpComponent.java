package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

/**
 * Hit points component.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class MaxHpComponent implements Component {
	/** 
	 * Max hit points. 
	 */
	int hp;
	
	public MaxHpComponent(int hp) {
		this.hp = hp;
	}
	
	/**
	 * Gets hit points.
	 * 
	 * @return hit points
	 */
	public int getHP() {
		return hp;
	}
	
	/**
	 * Sets {@link #hp max hp} to a provided value.
	 * 
	 * @param hp hit points to set
	 */
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	/**
	 * Modifies {@link #hp max hit points}.
	 * 
	 * @param hp an amount of hit points to add
	 */
	public void addHP(int hp) {
		this.hp += hp;
	}
}
