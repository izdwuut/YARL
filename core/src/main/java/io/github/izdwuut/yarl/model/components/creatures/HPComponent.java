package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

public class HPComponent implements Component {
	private int HP;
	public HPComponent(int HP) {
		this.HP = HP;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
}
