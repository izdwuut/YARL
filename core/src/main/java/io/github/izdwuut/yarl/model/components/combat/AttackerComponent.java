package io.github.izdwuut.yarl.model.components.combat;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Creature;

public class AttackerComponent implements Component {
	Creature attacker;
	
	public AttackerComponent(Creature attacker) {
		this.attacker = attacker;
	}

	public Creature getAttacker() {
		return attacker;
	}

	public void setAttacker(Creature attacker) {
		this.attacker = attacker;
	}
}
