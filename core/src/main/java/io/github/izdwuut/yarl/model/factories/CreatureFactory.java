package io.github.izdwuut.yarl.model.factories;

import io.github.izdwuut.yarl.model.entities.Creature;

public class CreatureFactory {
	public Creature getPlayer(String name) {
		Creature player = new Creature(name);
		player.setPlayer()
			.setInv(10)
			.setMov();
		
		return player;
	}
}
