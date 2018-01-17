package io.github.izdwuut.yarl.views;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.world.DungeonComponent;
import io.github.izdwuut.yarl.model.entities.Creature;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.model.systems.Event;
import io.github.izdwuut.yarl.model.systems.InitSystem;
import io.github.izdwuut.yarl.model.systems.LevelingSystem;
import io.github.izdwuut.yarl.model.systems.WorldSystem;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.gui.gdx.SColor;
import squidpony.squidgrid.gui.gdx.SparseLayers;
import squidpony.squidgrid.gui.gdx.TextCellFactory;
import squidpony.squidmath.Coord;

/**
 * A main screen - the game screen. It displays a dungeon fetched from {@link #dungComp a dungeon component}.
 * It listens to {@link io.github.izdwuut.yarl.model.systems.MovementSystem a MovementSystem} in order to update
 * a display based on a player input.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
//TODO: refactor with fire
//rename fields (display -> map)
//templates
//init()
//break it into chunks
public class GameScreen extends Screen implements Listener<Event> {
	/** 
	 * {@link #dungComp Dungeon} width. 
	 */
	int displayWidth;
	
	/** 
	 * {@link #dungComp Dungeon} height. 
	 */
	int displayHeight;
	
	/** 
	 * {@link com.badlogic.gdx.scenes.scene2d.Stage A stage} that handles map display. 
	 */
	Stage stage;
	
	/** 
	 * {@link squidpony.squidgrid.gui.gdx.SparseLayers A Layer} with a dungeon. 
	 */
	SparseLayers display;
	
	/** 
	 * A player {@link io.github.izdwuut.yarl.model.entities.Creature creature}. 
	 */
	Creature player;
	
	/** 
	 * A player {@link squidpony.squidgrid.gui.gdx.TextCellFactory.Glyph Glyph} (@). 
	 */
	//TODO: actors index. listen on display.glyphs
	TextCellFactory.Glyph playerGlyph;
	
	/** 
	 * A world entity. 
	 */
	World world;
	
	/** 
	 * A component that stores dungeon data. 
	 */
	DungeonComponent dungComp;
	
	/** 
	 * An Ashley system that is used to query {@link #world a world entity}. 
	 */
	WorldSystem worldSystem;
	
	/** 
	 * {@link squidpony.squidgrid.gui.gdx.SparseLayers A Layer} with player stats. 
	 */
	SparseLayers stats;
	
	/**
	 * A stage that handles player stats display.
	 */
	Stage statsStage;
	
	/**
	 * Stats panel width.
	 */
	int statsWidth;
	
	/**
	 * Stats panel height.
	 */
	int statsHeight;
	
	/**
	 * A system that provides player stats.
	 */
	LevelingSystem levelingSystem;

	/**
	 * Constructs a game screen using provided parameters. Sets a {@link com.badlogic.gdx.utils.viewport.Viewport Viewport}, 
	 * adds actors to a {@link #stage Stage} and calls for {@link #putMap() putMap} to put 
	 * {@link #dungComp a dungeon} on a {@link squidpony.squidgrid.gui.gdx.SparseLayers SparseLayers}.
	 * 
	 * @param initSystem an initialization system
	 * @param worldSystem a world system
	 */
	//TODO: init()
	public GameScreen(InitSystem initSystem, WorldSystem worldSystem, LevelingSystem levelingSystem) {
		super(initSystem.getSettings());
		
		this.player = initSystem.getPlayer();
		this.world = initSystem.getWorld();
		this.worldSystem = worldSystem;
		this.levelingSystem = levelingSystem;
		
		SizeComponent size = Mappers.size.get(world);
		displayHeight = size.getHeight();
		displayWidth = size.getWidth();
		statsWidth = displayWidth;
		statsHeight = 1;
		
		dungComp = Mappers.dungeon.get(world);
		
		Viewport mainVp = new StretchViewport(displayWidth * cellWidth, displayHeight * cellHeight);
		mainVp.setScreenBounds(0, 0, displayWidth * cellWidth, displayHeight * cellWidth);
		display = new SparseLayers(displayWidth, displayHeight, cellWidth, cellHeight);
		display.setPosition(0f, 0f);
		stage = new Stage(mainVp);
		stage.addActor(display);
		
		Coord pos = Mappers.position.get(player).getPosition();
		playerGlyph = display.glyph(Mappers.glyph.get(player).getGlyph(), SColor.SAFETY_ORANGE.toFloatBits(), pos.x, pos.y);
		setCamera(pos);
		
		Viewport statsVp = new StretchViewport(statsWidth * cellWidth, 1 * statsHeight * cellHeight);
		statsVp.setScreenBounds(0, 0, statsWidth * cellWidth, statsHeight * cellHeight);
		stats = new SparseLayers(statsWidth, statsHeight, cellWidth, cellHeight);
		statsStage = new Stage(statsVp);
		statsStage.addActor(stats);
		
		
		putMap();
		putStats();
	}
	
	@Override
	public void render(float deltaTime) {
        clear();
        
        statsStage.getViewport().apply(false);
        statsStage.draw();

        stage.getViewport().apply(false);
        stage.draw();
	}
	
	/**
	 * Puts a map on a screen.
	 */
	void putMap() {
		//TODO: template
		float bg = SColor.BLACK.toFloatBits();
		for (int x = 0; x < displayWidth; x++) {
            for (int y = 0; y < displayHeight; y++) {
                display.put(x, y, getGlyph(Coord.get(x, y)), SColor.FLOAT_WHITE, bg);
            }
		}	
	}
	
	/**
	 * Moves {@link #player a player}.
	 */
	void slide() {
		Coord pos = Mappers.position.get(player).getPosition();
		setCamera(pos);	
		display.slide(playerGlyph, (int) playerGlyph.getX(), (int) playerGlyph.getY(), pos.x, pos.y, 0, null);
	}
	
	/**
	 * Sets camera position.
	 * 
	 * @param coord a position to set
	 */
	void setCamera(Coord coord) {
		stage.getCamera().position.x = coord.x * cellWidth;
		stage.getCamera().position.y = (displayHeight - coord.y) * cellHeight;
	}
	
	/**
	 * Listens for {@link io.github.izdwuut.yarl.model.systems.Event Events}:
	 * <ul>
	 * <li>MOVEMENT_END - dispatched by {@link io.github.izdwuut.yarl.model.systems.MovementSystem a MovementSystem} when entities stop to move</li>
	 * <li>CREATURE_KILL - dispatched by {@link io.github.izdwuut.yarl.model.systems.CombatSystem a CombatSystem} when a creature is killed</li>
	 * <li>LEVEL_UP - dispatched by {@link io.github.izdwuut.yarl.model.systems.LevelingSystem a LevelingSystem} when a player levels up</li>
	 * <li>GAIN_EXP - dispatched by {@link io.github.izdwuut.yarl.model.systems.LevelingSystem a LevelingSystem} when a player gains experience points</li>
	 * </ul>
	 */
	@Override
	public void receive(Signal<Event> signal, Event e) {
		switch(e) {
		case MOVEMENT_END:
			slide();
		case CREATURE_KILL:
			putMap();
		break;
		case LEVEL_UP:
		case GAIN_EXP:
			putStats();
		}
	}
	
	/**
	 * Gets a character at a given position.
	 * 
	 * @param pos a queried position
	 * 
	 * @return a character at a given position
	 */
	char getGlyph(Coord pos) {
    	if(worldSystem.isCreature(pos)) {
    		return Mappers.glyph.get(dungComp.getCreature(pos)).getGlyph();
    	}
    	
    	return dungComp.getDungeon()[pos.x][pos.y];
	}
	
	/**
	 * Puts a player stats panel on a screen.
	 */
	void putStats() {
		stats.clear();
		putExp(putLevel());
	}
	
	/**
	 * Puts a player experience level on a stats panel.
	 * 
	 * @return a current panel position
	 */
	int putLevel() {
		String s = "Level: " + String.format("%2d", Mappers.lvl.get(player).getLvl());
		int pos = 1;
		stats.put(pos, 0, s, Color.WHITE);
		
		return s.length() + pos;
	}
	
	/**
	 * Puts a player experience points remaining to a next level.
	 * 
	 * @param pos x offset
	 * 
	 * @return a current panel position
	 */
	int putExp(int pos) {
		String s = "/" + levelingSystem.getRemainingExp(player);
		stats.put(pos, 0, s, Color.WHITE);

		return s.length() + pos;
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

        float currentHeight = (float) height / (displayHeight + statsHeight);
        stats.setBounds(0, 0, width, currentHeight * statsHeight);
        statsStage.getViewport().update(width, height, false);
        statsStage.getViewport().setScreenBounds(0, 0, width, (int)currentHeight);
        stage.getViewport().update(width, height, false);
        stage.getViewport().setScreenBounds(0, (int)currentHeight,
                width, height - (int)currentHeight);
	}
}		
