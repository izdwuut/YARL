package io.github.izdwuut.yarl;

import com.badlogic.gdx.Game;

import io.github.izdwuut.yarl.controllers.GameController;
import squidpony.squidgrid.gui.gdx.SquidInput;

//TODO: populate system
public class YARL extends Game {
	SquidInput input;
	GameController controller;
	public void create() {
		this.controller = new GameController(this);
	}
	@Override
	public void render() {
		controller.update();
		super.render();
	}
}
