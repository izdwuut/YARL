package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

public class ExpComponent implements Component {
	private int exp;
	public ExpComponent(int exp) {
		this.exp = exp;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
}
