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
import io.github.izdwuut.yarl.utils.Mappers;
import squidpony.squidgrid.gui.gdx.SColor;
import squidpony.squidgrid.gui.gdx.SparseLayers;
import squidpony.squidgrid.gui.gdx.TextCellFactory;
import squidpony.squidmath.Coord;

public class GameScreen extends ScreenAdapter implements Listener<Event> {
	private World world;
	private Settings settings;
	private char[][] dungeon;
	private int cellWidth, cellHeight, width, height;
	private Viewport vp;
	public Stage stage;
	private SparseLayers display;
	private Creature player;
	private TextCellFactory.Glyph glyph;

	public GameScreen(World world, Settings settings, Creature player) {
		this.world = world;
		this.settings = settings;
		this.player = player;
		init();
	}
	
	@Override
	public void render(float deltaTime) {
        clear();
		stage.act();
		stage.draw();
	}
	
	private void clear() {
		Color bg = SColor.DARK_SLATE_GRAY;
		Gdx.gl.glClearColor(bg.r / 255.0f, bg.g / 255.0f, bg.b / 255.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void putMap() {
		//TODO: templates
		float bg = SColor.DARK_SLATE_GRAY.toFloatBits();
		for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                display.put(i, j, dungeon[i][j], SColor.FLOAT_WHITE, bg);
            }
		}
		Coord pos = Mappers.position.get(player).getPosition();
		setCamera(pos);		
		display.slide(glyph, (int) glyph.getX(), (int) glyph.getY(), pos.x, pos.y, 0, null);
	}
	
	//should be show() instead
	private void init() {
		CellSizeComponent cell = Mappers.cellSize.get(settings);
		SizeComponent size = Mappers.size.get(world);
	    
	    cellWidth = cell.getWidth();
	    cellHeight = cell.getHeight();
		height = size.getHeight();
		width = size.getWidth();
		dungeon = Mappers.dungeon.get(world).getDungeon();
		
		vp = new StretchViewport(width * cellWidth, height * cellHeight);
		vp.setScreenBounds(0, 0, width * cellWidth, height * cellWidth);
		stage = new Stage(vp);
		display = new SparseLayers(width, height, cellWidth, cellHeight);
		display.setPosition(0f, 0f);
		stage.addActor(display);
		Coord pos = Mappers.position.get(player).getPosition();
		glyph = display.glyph('@', SColor.SAFETY_ORANGE.toFloatBits(), pos.x, pos.y);
		putMap();
	}	
	
	private void setCamera(Coord coord) {
		stage.getCamera().position.x = coord.x * cellWidth;
		stage.getCamera().position.y = coord.y * cellHeight;
	}
	public void receive(Signal<Event> signal, Event e) {
		switch(e) {
		case MOVEMENT_END:
			putMap();
		break;
		}
		System.out.println(e);
	}
}		
