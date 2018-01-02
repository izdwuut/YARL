package io.github.izdwuut.yarl.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.systems.InitSystem;
import io.github.izdwuut.yarl.model.systems.WorldSystem;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.gui.gdx.SparseLayers;

/**
 * A win screen. As a name suggests - it gets displayed after a game is won.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2018-01-01
 */
public class WinScreen extends Screen{
	/** 
	 * {@link squidpony.squidgrid.gui.gdx.SparseLayers A Layer} with a win message. 
	 */
	SparseLayers display;
	
	/** 
	 * {@link com.badlogic.gdx.scenes.scene2d.Stage A stage} that handles display. 
	 */
	Stage stage;
	
	public WinScreen(InitSystem initSystem, WorldSystem worldSystem) {
		super(initSystem.getSettings());
		SizeComponent size = Mappers.size.get(worldSystem.getWorld());
		int height = size.getHeight();
		int width = size.getWidth();
		Viewport vp = new StretchViewport(width * cellWidth, height * cellHeight);
		vp.setScreenBounds(0, 0, width * cellWidth, height * cellWidth);
		display = new SparseLayers(width, height, cellWidth, cellHeight);
		display.setPosition(0f, 0f);
		
		stage = new Stage(vp);
		stage.addActor(display);
	}
	
	@Override
	public void render(float deltaTime) {
        clear();
        
        putMessage("You won!");
        
		stage.act();
		stage.draw();
	}
	
	/**
	 * Puts a message on a screen.
	 * 
	 * @param msg a message to put on the screen
	 */
	private void putMessage(String msg) {
		display.put(1, 1, msg, Color.WHITE);
	}
}
