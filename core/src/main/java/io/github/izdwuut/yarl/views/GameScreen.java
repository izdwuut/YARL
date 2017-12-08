package io.github.izdwuut.yarl.views;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.izdwuut.yarl.model.Event;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.gui.gdx.SColor;
import squidpony.squidgrid.gui.gdx.SparseLayers;
import squidpony.squidgrid.gui.gdx.TextCellFactory;
import squidpony.squidmath.Coord;

/**
 * A main screen - the game screen. It displays {@link #dungeon a dungeon} and message log.
 * It listens to {@link io.github.izdwuut.yarl.model.systems.MovementSystem a MovementSystem} in order to update
 * display based on player input.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class GameScreen extends ScreenAdapter implements Listener<Event> {
	/** An ASCII dungeon provided by {@link io.github.izdwuut.yarl.model.entities.World a World}. */
	private char[][] dungeon;
	
	/** Cell width (in pixels). */
	private int cellWidth;
	
	/** Cell height (in pixels). */
	private int cellHeight;
	
	/** {@link #dungeon Dungeon} width. */
	private int width;
	
	/** {@link #dungeon Dungeon} height. */
	private int height;
	
	/** {@link com.badlogic.gdx.scenes.scene2d.Stage A stage} that handles display. */
	public Stage stage;
	
	/** {@link squidpony.squidgrid.gui.gdx.SparseLayers A Layer} with a dungeon. */
	private SparseLayers display;
	
	/** A player {@link io.github.izdwuut.yarl.model.entities.Creature creature}. */
	private Creature player;
	
	/** A player {@link squidpony.squidgrid.gui.gdx.TextCellFactory.Glyph Glyph} (@). */
	//TODO: move elsewhere
	private TextCellFactory.Glyph glyph;

	/**
	 * Constructs a game screen using provided parameters. Sets a {@link com.badlogic.gdx.utils.viewport.Viewport Viewport}, 
	 * adds actors to a {@link #stage Stage} and calls for {@link #putMap() putMap} to put 
	 * {@link #dungeon a dungeon} on a {@link squidpony.squidgrid.gui.gdx.SparseLayers SparseLayers}.
	 * 
	 * @param world a {@link io.github.izdwuut.yarl.model.entities.World World} entity
	 * @param settings {@link io.github.izdwuut.yarl.model.entities.Settings Settings} entity
	 * @param player a player {@link io.github.izdwuut.yarl.model.entities.Creature Creature}
	 */
	//TODO: generic screen (stuff like cell dimensions)
	//TODO: init()
	//TODO: check if a creature is a player
	public GameScreen(World world, Settings settings, Creature player) {
		this.player = player;
		
		CellSizeComponent cell = Mappers.cellSize.get(settings);
		SizeComponent size = Mappers.size.get(world);
	    
	    cellWidth = cell.getWidth();
	    cellHeight = cell.getHeight();
		height = size.getHeight();
		width = size.getWidth();
		dungeon = Mappers.dungeon.get(world).getDungeon();
		
		Viewport vp = new StretchViewport(width * cellWidth, height * cellHeight);
		vp.setScreenBounds(0, 0, width * cellWidth, height * cellWidth);
		display = new SparseLayers(width, height, cellWidth, cellHeight);
		display.setPosition(0f, 0f);
		
		stage = new Stage(vp);
		stage.addActor(display);
		
		Coord pos = Mappers.position.get(player).getPosition();
		glyph = display.glyph('@', SColor.SAFETY_ORANGE.toFloatBits(), pos.x, pos.y);
		setCamera(pos);
		
		putMap();
	}
	
	@Override
	public void render(float deltaTime) {
        clear();
		stage.act();
		stage.draw();
	}
	
	/**
	 * Clears a screen.
	 */
	private void clear() {
		Color bg = SColor.DARK_SLATE_GRAY;
		Gdx.gl.glClearColor(bg.r / 255.0f, bg.g / 255.0f, bg.b / 255.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Puts {@link #dungeon a dungeon} on a {@link squidpony.squidgrid.gui.gdx.SparseLayers SparseLayers}.
	 */
	private void putMap() {
		//TODO: template
		float bg = SColor.DARK_SLATE_GRAY.toFloatBits();
		for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                display.put(i, j, dungeon[i][j], SColor.FLOAT_WHITE, bg);
            }
		}	
	}
	
	/**
	 * Moves {@link #player a player}.
	 */
	private void slide() {
		Coord pos = Mappers.position.get(player).getPosition();
		setCamera(pos);	
		display.slide(glyph, (int) glyph.getX(), (int) glyph.getY(), pos.x, pos.y, 0, null);
	}
	
	/**
	 * Sets camera position.
	 * 
	 * @param coord a position to set
	 */
	private void setCamera(Coord coord) {
		stage.getCamera().position.x = coord.x * cellWidth;
		stage.getCamera().position.y = (height - coord.y) * cellHeight;
	}
	
	/**
	 * Listens for an {@link io.github.izdwuut.yarl.model.Event Event} dispatched by
	 * {@link io.github.izdwuut.yarl.model.systems.MovementSystem a MovementSystem}.
	 */
	@Override
	public void receive(Signal<Event> signal, Event e) {
		switch(e) {
		case MOVEMENT_END:
			putMap();
			slide();
		break;
		}
	}
}		
