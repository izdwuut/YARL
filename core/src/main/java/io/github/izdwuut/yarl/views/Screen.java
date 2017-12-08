package io.github.izdwuut.yarl.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import io.github.izdwuut.yarl.model.components.settings.CellSizeComponent;
import io.github.izdwuut.yarl.model.entities.Settings;
import io.github.izdwuut.yarl.model.utils.Mappers;
import squidpony.squidgrid.gui.gdx.SColor;

/**
 * A generic screen.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-08
 */
public abstract class Screen extends ScreenAdapter {
	/** Cell width (in pixels). */
	protected int cellWidth;
	
	/** Cell height (in pixels). */
	protected int cellHeight;
	
	/**
	 * Constructs a screen and sets cell size.
	 * 
	 * @param settings cell width and height
	 */
	public Screen(Settings settings) {
		CellSizeComponent cell = Mappers.cellSize.get(settings);
	    cellWidth = cell.getWidth();
	    cellHeight = cell.getHeight();
	}
	
	/**
	 * Clears a screen.
	 */
	protected void clear() {
		Color bg = SColor.DARK_SLATE_GRAY;
		Gdx.gl.glClearColor(bg.r / 255.0f, bg.g / 255.0f, bg.b / 255.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
