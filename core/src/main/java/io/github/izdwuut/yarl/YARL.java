package io.github.izdwuut.yarl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.izdwuut.yarl.controllers.GameController;
import squidpony.squidgrid.gui.gdx.SquidInput;

public class YARL extends Game {
	SquidInput input;
	GameController controller;
	public void create() {
		this.controller = new GameController(this);
	}
	@Override
	public void render() {
		super.render();
		controller.update();
	}
}
