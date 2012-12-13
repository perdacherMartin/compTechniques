package at.univie.ct.ga.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class CityTest {

	@Test
	public void test() {
		City c1 = new City(1,5.0, 5.0);
		City c2 = new City(2,5.0, 10.0);
		assertEquals(c1.getDistance(c2), 5.0, 0.0);
		
		City c3 = new City(3, 10.0, 10.0);
		assertEquals(c3.getDistance(c1), 7.07, 0.1);
	}

}
