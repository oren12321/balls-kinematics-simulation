import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
 * The class implements general N dimensional vector.
 */
public class VectorN implements Cloneable {

	/*
	 * The vector components.
	 */
	protected List<Double> components;
	
	/*
	 * Parameterized constructor with collection of components.
	 */
	public VectorN(List<Double> components) {
		this.set(components);
	}
	
	/*
	 * Parameterized constructor with array of components.
	 */
	public VectorN(Double[] components) {
		this.set(components);
	}
	
	/*
	 * Parameterized constructor that build 0 vector according to
	 * given dimension.
	 */
	public VectorN(Integer dimension) {
		this.components = new ArrayList<Double>();
		Double[] components = new Double[dimension];
		Arrays.fill(components, 0.0);
		this.components.addAll(Arrays.asList(components));
	}
	
	/*
	 * Get vector dimension.
	 */
	public Integer dimension() {
		return this.components.size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * Check if two vectors contains the same components and in the same order.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof VectorN)) {
			return false;
		}
		VectorN instance = (VectorN)obj;
		if(this.dimension() != instance.dimension()) {
			return false;
		}
		return this.components.equals(instance.components);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 * Get clone of this vector.
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new VectorN(this.components);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Return string of the vector components.
	 */
	@Override
	public String toString() {
		return this.components.toString();
	}
	
	/*
	 * Get specific component of the vector.
	 */
	public Double component(Integer i) {
		return (i < this.dimension()) ? (this.components.get(i)) : (null);
	}
	
	/*
	 * Change specific component of the vector.
	 */
	public void set(Integer i, Double component) {
		if(i < this.dimension()) {
			this.components.set(i, component);
		}
	}
	
	/*
	 * Change components by list.
	 */
	public void set(List<Double> components) {
		this.components = components;
	}
	
	/*
	 * Change components by array.
	 */
	public void set(Double[] components) {
		this.components = new ArrayList<Double>(Arrays.asList(components));
	}
	
	/*
	 * Move components by scalar.
	 */
	public void move(Double scalar) {
		for(Integer i = 0; i < this.dimension(); i++) {
			this.set(i, this.component(i) + scalar);
		}
	}
	
	/*
	 * Move components by scalars list.
	 */
	public void move(List<Double> components) {
		if(this.dimension() == components.size()) {
			for(Integer i = 0; i < this.dimension(); i++) {
				this.set(i, this.component(i) + components.get(i));
			}
		}
	}
	
	/*
	 * Move components by scalars array.
	 */
	public void move(Double[] components) {
		if(this.dimension() == components.length) {
			for(Integer i = 0; i < this.dimension(); i++) {
				this.set(i, this.component(i) + components[i]);
			}
		}
	}
	
	/*
	 * Get components iterator.
	 */
	public VectorNIterator iterator() {
		return new VectorNIterator(this);
	}
	
	/*
	 * Get (this vector) + (v).
	 */
	public VectorN add(VectorN v) {
		if(this.dimension() != v.dimension()) {
			return null;
		}
		VectorN result = new VectorN(this.dimension());
		for(Integer i = 0; i < result.dimension(); i++) {
			result.set(i, this.component(i) + v.component(i));
		}
		return result;
	}
	
	/*
	 * Get (this vector) - (v).
	 */
	public VectorN subtract(VectorN v) {
		return this.add(this.multiply(-1.0));
	}
	
	/*
	 * Get scalar * (this vector).
	 */
	public VectorN multiply(Double scalar) {
		VectorN result = new VectorN(this.dimension());
		for(Integer i = 0; i < result.dimension(); i++) {
			result.set(i, this.component(i) * scalar);
		}
		return result;
	}
	
	/*
	 * Get dot product of this vector with other vector.
	 */
	public Double dot(VectorN v) {
		if(this.dimension() != v.dimension()) {
			return null;
		}
		Double result = 0.0;
		for(Integer i = 0; i < this.dimension(); i++) {
			result += (this.component(i) * v.component(i));
		}
		return result;
	}
	
	/*
	 * Return magnitude(this)^2
	 */
	public Double magnitudeSquared() {
		return this.dot(this);
	}
	
	/*
	 * Return magnitude(this)
	 */
	public Double magnitude() {
		return Math.sqrt(this.magnitudeSquared());
	}
	
	/*
	 * Get normalization of this vector.
	 */
	public VectorN normalized() {
		return this.multiply(1 / this.magnitude());
	}
	
	/*
	 * Return the null vector of this dimension.
	 */
	public VectorN zero() {
		return new VectorN(this.dimension());
	}
	
	/*
	 * Return the unit vector if this dimension.
	 */
	public VectorN unit() {
		VectorN result = new VectorN(this.dimension());
		for(Integer i = 0; i < result.dimension(); i++) {
			result.set(i, 0.0);
		}
		return result;
	}
	
	/*
	 * Distance between two vectors.
	 */
	public Double distance(VectorN v) {
		if(this.dimension() != v.dimension()) {
			return null;
		}
		Double result = 0.0;
		for(Integer i = 0; i < this.dimension(); i++) {
			result += Math.pow(v.component(i) - this.component(i), 2);
		}
		return Math.sqrt(result);
	}
}
