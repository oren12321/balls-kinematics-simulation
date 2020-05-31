import java.util.List;


public class Vector2 extends VectorN {
	
	/*
	 * Parameterized constructor with collection of components.
	 */
	private Vector2(List<Double> components) {
		super(components);
	}
	
	/*
	 * Parameterized constructor with array of components.
	 */
	private Vector2(Double[] components) {
		super(components);
	}
	
	/*
	 * Parameterized constructor that build 0 vector according to
	 * given dimension.
	 */
	private Vector2(Integer dimension) {
		super(dimension);
	}
	
	/*
	 * Parameterized constructor with x & y coordinates.
	 */
	public Vector2(Double x, Double y) {
		super(new Double[] {x, y});
	}
	
	/*
	 * Get x coordinate
	 */
	public Double x() {
		return this.component(0);
	}
	
	/*
	 * Get y coordinate
	 */
	public Double y() {
		return this.component(1);
	}
	
	/*
	 * Set x coordinate
	 */
	public void x(Double x) {
		this.set(0, x);
	}
	
	/*
	 * Set y coordinate
	 */
	public void y(Double y) {
		this.set(1, y);
	}
	
	/*
	 * Set components by x & y
	 */
	public void set(Double x, Double y) {
		this.set(new Double[] {x, y});
	}
	
	/*
	 * Move components by x & y
	 */
	public void move(Double x, Double y) {
		this.move(new Double[] {x, y});
	}
	
	/*
	 * Cross product
	 */
	public Double cross(Vector2 v) {
		return (this.x() * v.y() - v.x() * this.y());
	}
	
	/*
	 * i vector
	 */
	public Vector2 i() {
		return new Vector2(1.0, 0.0);
	}
	
	/*
	 * j vector
	 */
	public Vector2 j() {
		return new Vector2(0.0, 1.0);
	}
}
