package io.github.izdwuut.yarl.views;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.izdwuut.yarl.model.Event;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.systems.WorldSystem;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.gui.gdx.SColor;
import squidpony.squidgrid.gui.gdx.SparseLayers;
import squidpony.squidgrid.gui.gdx.TextCellFactory;
import squidpony.squidmath.Coord;

/**
 * A main screen - the game screen. It displays {@link #dungeon a dungeon} and a message log.
 * It listens to {@link io.github.izdwuut.yarl.model.systems.MovementSystem a MovementSystem} in order to update
 * a display based on a player input.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class GameScreen extends Screen implements Listener<Event> {
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
	//TODO: actors index. listen on display.glyphs
	private TextCellFactory.Glyph playerGlyph;
	
	/** A world entity. */
	World world;
	
	/** A component that stores dungeon data. */
	DungeonComponent dungComp;
	
	/** An Ashley system that is used to query {@link #world a world entity}. */
	WorldSystem worldSystem;

	/**
	 * Constructs a game screen using provided parameters. Sets a {@link com.badlogic.gdx.utils.viewport.Viewport Viewport}, 
	 * adds actors to a {@link #stage Stage} and calls for {@link #putMap() putMap} to put 
	 * {@link #dungeon a dungeon} on a {@link squidpony.squidgrid.gui.gdx.SparseLayers SparseLayers}.
	 * 
	 * @param world a {@link io.github.izdwuut.yarl.model.entities.World World} entity
	 * @param settings {@link io.github.izdwuut.yarl.model.entities.Settings Settings} entity
	 * @param player a player {@link io.github.izdwuut.yarl.model.entities.Creature Creature}
	 */
	//TODO: init()
	public GameScreen(World world, Settings settings, Creature player, WorldSystem worldSystem) {
		super(settings);
		
		this.player = player;
		this.world = world;
		this.worldSystem = worldSystem;
		
		SizeComponent size = Mappers.size.get(world);
		height = size.getHeight();
		width = size.getWidth();
		
		dungComp = Mappers.dungeon.get(world);
		
		Viewport vp = new StretchViewport(width * cellWidth, height * cellHeight);
		vp.setScreenBounds(0, 0, width * cellWidth, height * cellWidth);
		display = new SparseLayers(width, height, cellWidth, cellHeight);
		display.setPosition(0f, 0f);
		
		stage = new Stage(vp);
		stage.addActor(display);
		Coord pos = Mappers.position.get(player).getPosition();
		playerGlyph = display.glyph(Mappers.glyph.get(player).getGlyph(), SColor.SAFETY_ORANGE.toFloatBits(), pos.x, pos.y);
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
	 * Puts a map on a {@link squidpony.squidgrid.gui.gdx.SparseLayers SparseLayers}.
	 */
	private void putMap() {
		//TODO: template
		float bg = SColor.DARK_SLATE_GRAY.toFloatBits();
		for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                display.put(x, y, getGlyph(Coord.get(x, y)), SColor.FLOAT_WHITE, bg);
            }
		}	
	}
	
	/**
	 * Moves {@link #player a player}.
	 */
	private void slide() {
		Coord pos = Mappers.position.get(player).getPosition();
		setCamera(pos);	
		display.slide(playerGlyph, (int) playerGlyph.getX(), (int) playerGlyph.getY(), pos.x, pos.y, 0, null);
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
	
	/**
	 * Gets a character at a given position.
	 * 
	 * @param pos a queried position
	 * 
	 * @return a character at a given position
	 */
	private char getGlyph(Coord pos) {
    	if(worldSystem.isCreature(pos)) {
    		return Mappers.glyph.get(dungComp.getCreature(pos)).getGlyph();
    	}
    	
    	return dungComp.getDungeon()[pos.x][pos.y];
	}
}		
