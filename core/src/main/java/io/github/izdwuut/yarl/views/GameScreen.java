package io.github.izdwuut.yarl.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.izdwuut.yarl.YARL;
import io.github.izdwuut.yarl.model.components.SizeComponent;
import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.entities.World;
import io.github.izdwuut.yarl.utils.Mappers;
import squidpony.squidgrid.gui.gdx.SColor;
import squidpony.squidgrid.gui.gdx.SparseLayers;

public class GameScreen extends ScreenAdapter {
	private YARL game;
	private World world;
	private Settings settings;
	private char[][] dungeon;
	private int cellWidth, cellHeight, width, height;
	private Viewport vp;
	private Stage stage;
	private SparseLayers display;

	public GameScreen(YARL game, World world, Settings settings) {
		this.game = game;
		this.world = world;
		this.settings = settings;
		init();
	}
	
	@Override
	public void render(float delta) {
		stage.getCamera().position.x = 5 * cellWidth;
        stage.getCamera().position.y = 5 * cellHeight;
		
        //Should reside in a template
		Color bgColor = SColor.DARK_SLATE_GRAY;
        Gdx.gl.glClearColor(bgColor.r / 255.0f, bgColor.g / 255.0f, bgColor.b / 255.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                float bg = SColor.DARK_SLATE_GRAY.toFloatBits();
                display.put(i, j, dungeon[i][j], SColor.FLOAT_WHITE, bg);
            }
		}

		stage.act();
		stage.draw();
	}
	
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
		stage = new Stage(vp, game.getBatch());
		display = new SparseLayers(width, height, cellWidth, cellHeight);
		display.setPosition(0f, 0f);
		stage.addActor(display);
	}
}		
