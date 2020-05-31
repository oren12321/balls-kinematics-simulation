import java.util.Random;

/*
 * Class that supplies services for random numbers
 */
public class RandomGenerator {

	/*
	 * Random utility
	 */
	private Random rand;
	
	/*
	 * Random seed constructor
	 */
	public RandomGenerator() {
		this(System.currentTimeMillis());
	}
	
	/*
	 * Specific seed constructor
	 */
	public RandomGenerator(long seed) {
		this.rand = new Random(seed);
	}
	
	/*
	 * Get double between 0.0 to 1.0
	 */
	public Double getNext() {
		return rand.nextDouble();
	}
	
	/*
	 * Get double between 0.0 to upperLimit
	 */
	public Double getNext(Double upperLimit) {
		return this.getNext() * (upperLimit + 1.0);
	}
	
	/*
	 * Get double between lowerLimit to upperLimit
	 */
	public Double getNext(Double lowerLimit, Double upperLimit) {
		return lowerLimit + this.getNext() * (upperLimit - lowerLimit + 1.0);
	}
}
