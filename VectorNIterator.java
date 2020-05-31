import java.util.Iterator;


public class VectorNIterator implements Iterator<Double> {

	private Integer current = 0;
	private VectorN vector;
	
	public VectorNIterator(VectorN vector) {
		this.vector = vector;
	}
	
	@Override
	public boolean hasNext() {
		return (this.current < this.vector.dimension());
	}

	@Override
	public Double next() {
		return this.vector.component(this.current++);
	}

	@Override
	public void remove() {
		// Do nothing (vector need to be with fixed dimension).
	}

}
