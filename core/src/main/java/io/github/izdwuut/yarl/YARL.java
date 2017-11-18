package io.github.izdwuut.yarl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.izdwuut.yarl.controllers.GameController;

public class YARL extends Game {
	private SpriteBatch batch;
	public YARL() {
		
	}
	public void create() {
		this.batch = new SpriteBatch();
		new GameController(this);
	}
	public Batch getBatch() {
		return batch;
	}
}
