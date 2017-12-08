package io.github.izdwuut.yarl.model.components.settings;

import io.github.izdwuut.yarl.model.components.SizeComponent;

/**
 * A component that describes a cell dimensions. Measured in pixels. It derives from a 
 * {@link io.github.izdwuut.yarl.model.components.SizeComponent SizeComponent}.
 * A component of {@link io.github.izdwuut.yarl.model.entities.Settings Settings}.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-11-18
 */
public class CellSizeComponent extends SizeComponent {
	/**
	 * Sets width and height to provided values.
	 * 
	 * @param width width to set
	 * @param height height to set
	 */
	public CellSizeComponent(int width, int height) {
		super(width, height);
	}
}
