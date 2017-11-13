package io.github.izdwuut.yarl.model.components.items;

import com.badlogic.ashley.core.Component;

public class MeleeComponent implements Component {
	private int dmg;
	public MeleeComponent(int dmg) {
		this.dmg = dmg;
	}
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
}
