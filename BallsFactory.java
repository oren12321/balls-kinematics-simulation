import java.awt.Color;
import java.awt.Component;


/*
 * The class (according to factory pattern) creates balls at
 * random location according to component information.
 */
public class BallsFactory {

	/*
	 * Randomize utility
	 */
	static RandomGenerator rand = new RandomGenerator();
	
	/*
	 * Get new ball according to component bounds
	 */
	public static Ball createBall(Integer tagging, Component component) {
		
		// Get component dimensions
		Double width = new Double(component.getWidth());
		Double height = new Double(component.getHeight());
		
		Double bounds = ((width > height) ? height : width);
		
		// Random radius
		final Double MIN_RADI = (bounds * 1) / 100; // 1%
		final Double MAX_RADI = (bounds * 5) / 100; // 5%
		Double radius = rand.getNext(MIN_RADI, MAX_RADI);
		
		// Random position
		Double pX = rand.getNext(radius, width - radius);
		Double pY = rand.getNext(radius, height - radius);
		Vector2 pos = new Vector2(pX, pY);
		
		// Random velocity
		final Double MIN_VELOCITY = 1.0;
		final Double MAX_VELOCITY = 100.0;
		Double vX = rand.getNext(MIN_VELOCITY, MAX_VELOCITY) / 1000;
		Double vY = rand.getNext(MIN_VELOCITY, MAX_VELOCITY) / 1000;
		Vector2 vel = new Vector2(vX, vY);
		
		// Random acceleration
		final Double MIN_ACC = 1.0;
		final Double MAX_ACC = 100.0;
		Double aX = rand.getNext(MIN_ACC, MAX_ACC) / 1000;
		Double aY = rand.getNext(MIN_ACC, MAX_ACC) / 1000;
		Vector2 acc = new Vector2(aX, aY);
		
		// Random color
		Double r = rand.getNext(255.0);
		Double g = rand.getNext(255.0);
		Double b = rand.getNext(255.0);
		Color c = new Color(r.intValue(), g.intValue(), b.intValue());
		
		// Random mass
		final Double MIN_MASS = 1.0;
		final Double MAX_MASS = 1000.0;
		Double mass = rand.getNext(MIN_MASS, MAX_MASS);
		
		// Create a ball with the above random data
		return new Ball(tagging, pos, radius, vel, acc, c, mass, component);
	}
	
}
