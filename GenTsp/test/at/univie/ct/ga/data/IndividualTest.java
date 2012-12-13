package at.univie.ct.ga.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IndividualTest {

	@Test
	public void test() {
		City c1 = new City(1, 1.0, 1.0);
		City c2 = new City(2, 1.0, 2.0);
		City c3 = new City(3, 1.0, 3.0);
		
		List<City> cities = new ArrayList<City>();
		cities.add(c1);
		cities.add(c2);
		cities.add(c3);
		
		Individual ind = new Individual(cities);
		assertEquals(ind.getFitness(), 4.0, 0.0);
	}

}
