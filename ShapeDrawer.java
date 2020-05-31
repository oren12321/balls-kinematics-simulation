import java.awt.Color;
import java.awt.Component;

import javax.swing.Timer;


public abstract class ShapeDrawer {

	/*
	 * The shape color
	 */
	protected Color color;
	
	/*
	 * Timer that controls the shape repaint ratio
	 */
	protected Timer timer;
	
	/*
	 * Shape draw-on component
	 */
	protected Component component;
}
