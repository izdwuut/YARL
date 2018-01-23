package io.github.izdwuut.yarl.model.components.items;

import com.badlogic.ashley.core.Component;

/**
 * A weapon component. Tags an item that can be used as arms.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 */
public class WeaponComponent implements Component {
	/**
	 * Weapon damage as dice rolls (1d4 etc).
	 */
	String dmg;
	
	public WeaponComponent(String dmg) {
		this.dmg = dmg;
	}
	
	/**
	 * Gets weapon {@link #dmg damage}.
	 * 
	 * @return weapon {@link #dmg damage}
	 */
	public String getDmg() {
		return dmg;
	}

	/**
	 * Sets weapon {@link #dmg damage}.
	 * 
	 * @param dmg a damage to set
	 */
	public void setDmg(String dmg) {
		this.dmg = dmg;
	}
}
