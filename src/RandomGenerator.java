
//  RandomGenerator.java

import java.util.Random;

/**
 * The type Random generator.
 */
class RandomGenerator {

	/**
	 * creates a random number ranging between lo and hi,
	 *
	 * @param lo the lo
	 * @param hi the hi
	 * @return int
	 */
	int discrete(int lo, int hi)
	{
		if(lo >= hi){
			System.out.println("Error discrete, lo >= hi");
			System.exit(0);
		}
		
		Random r = new Random();
		int d = r.nextInt(hi - lo + 1) + lo;
		return d;
	}
	
}
