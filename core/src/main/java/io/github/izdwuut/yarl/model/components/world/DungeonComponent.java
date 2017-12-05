package io.github.izdwuut.yarl.model.components.world;

import com.badlogic.ashley.core.Component;

public class DungeonComponent implements Component {
	char[][] dungeon;
	public DungeonComponent(char[][] dungeon) {
		this.dungeon = dungeon;
	}
	public char[][] getDungeon() {
		return dungeon;
	}
	public void setDungeon(char[][] dungeon) {
		this.dungeon = dungeon;
	}
}
