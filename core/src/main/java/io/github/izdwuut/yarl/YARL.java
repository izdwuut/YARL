package io.github.izdwuut.yarl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import io.github.izdwuut.yarl.controllers.Controller;
import io.github.izdwuut.yarl.controllers.GameController;
import squidpony.squidgrid.gui.gdx.SquidInput;

//TODO: populate system
public class YARL extends Game {
	SquidInput input;
	Controller controller;
	public void create() {
		
		this.controller = new GameController(this);
	}
	@Override
	public void render() {
		super.render();
		controller.update();
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
