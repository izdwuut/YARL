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
	 * Weapon damage.
	 */
	int dmg;

	public WeaponComponent(int dmg) {
		this.dmg = dmg;
	}
	
	/**
	 * Gets weapon {@link #damage damage}.
	 * 
	 * @return weapon {@link #damage damage}
	 */
	public int getDmg() {
		return dmg;
	}

	/**
	 * Sets weapon {@link #damage damage}.
	 * 
	 * @param dmg a damage to set
	 */
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
}
