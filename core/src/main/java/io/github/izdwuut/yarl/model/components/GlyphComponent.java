package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

/**
 * A display character.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-10
 */
public class GlyphComponent implements Component {
	/** A character that is displayed on a screen */
	private char glyph;
	
	public GlyphComponent(char glyph) {
		this.glyph = glyph;
	}
	
	/**
	 * Gets {@link #glyph a glyph}.
	 * 
	 * @return {@link #glyph a glyph}
	 */
	public char getGlyph() {
		return glyph;
	}
}
