import java.util.List;


public class Vector3 extends VectorN {
	
	/*
	 * Parameterized constructor with collection of components.
	 */
	private Vector3(List<Double> components) {
		super(components);
	}
	
	/*
	 * Parameterized constructor with array of components.
	 */
	private Vector3(Double[] components) {
		super(components);
	}
	
	/*
	 * Parameterized constructor that build 0 vector according to
	 * given dimension.
	 */
	private Vector3(Integer dimension) {
		super(dimension);
	}
	
	/*
	 * Parameterized constructor with x & y & z coordinates.
	 */
	public Vector3(Double x, Double y, Double z) {
		super(new Double[] {x, y, z});
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
	 * Get z coordinate
	 */
	public Double z() {
		return this.component(2);
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
	 * Set z coordinate
	 */
	public void z(Double z) {
		this.set(2, z);
	}
	
	/*
	 * Set components by x & y & z
	 */
	public void set(Double x, Double y, Double z) {
		this.set(new Double[] {x, y, z});
	}
	
	/*
	 * Move components by x & y & z
	 */
	public void move(Double x, Double y, Double z) {
		this.move(new Double[] {x, y, z});
	}
	
	/*
	 * Cross product
	 */
	public Vector3 cross(Vector3 v) {
		return new Vector3(
				this.y() * v.z() - this.z() * v.y(),
				this.z() * v.x() - this.x() * v.z(),
				this.x() * v.y() - this.y() * v.x());
	}
	
	/*
	 * i vector
	 */
	public Vector3 i() {
		return new Vector3(1.0, 0.0, 0.0);
	}
	
	/*
	 * j vector
	 */
	public Vector3 j() {
		return new Vector3(0.0, 1.0, 0.0);
	}
	
	/*
	 * k vector
	 */
	public Vector3 k() {
		return new Vector3(0.0, 0.0, 1.0);
	}
}
