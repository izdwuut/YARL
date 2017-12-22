package io.github.izdwuut.yarl.model.components.combat;

import com.badlogic.ashley.core.Component;

import io.github.izdwuut.yarl.model.entities.Creature;

public class DefenderComponent implements Component{
	Creature defender;
	
	public DefenderComponent(Creature defender) {
		this.defender = defender;
	}
	
	public Creature getDefender() {
		return defender;
	}

	public void setDefender(Creature defender) {
		this.defender = defender;
	}
}
