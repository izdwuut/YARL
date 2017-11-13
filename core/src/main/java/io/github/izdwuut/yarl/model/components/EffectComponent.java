package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

public class EffectComponent implements Component {
	private int duration;
	public EffectComponent(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
