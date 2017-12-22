package io.github.izdwuut.yarl.model.entities;

import com.badlogic.ashley.core.Entity;

import io.github.izdwuut.yarl.model.components.combat.AttackerComponent;
import io.github.izdwuut.yarl.model.components.combat.DefenderComponent;

public class CombatEntity extends Entity {
	public void setAttacker(Creature attacker) {
		add(new AttackerComponent(attacker));
	}
	
	public void setDefender(Creature defender) {
		add(new DefenderComponent(defender));
	}
}
