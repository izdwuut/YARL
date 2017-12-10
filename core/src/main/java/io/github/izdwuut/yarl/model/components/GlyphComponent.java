package io.github.izdwuut.yarl.model.components;

import com.badlogic.ashley.core.Component;

public class GlyphComponent implements Component {
	private char glyph;
	
	public GlyphComponent(char glyph) {
		this.glyph = glyph;
	}
	
	public char getGlyph() {
		return glyph;
	}
}
