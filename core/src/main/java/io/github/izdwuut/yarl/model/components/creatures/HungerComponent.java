package io.github.izdwuut.yarl.model.components.creatures;

import com.badlogic.ashley.core.Component;

public class HungerComponent implements Component {
	private int fulness;
	public HungerComponent(int fulness) {
		this.fulness = fulness;
	}
	public int getFulness() {
		return fulness;
	}
	public void setFulness(int fulness) {
		this.fulness = fulness;
	}
}
