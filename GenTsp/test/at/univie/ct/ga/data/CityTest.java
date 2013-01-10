package at.univie.ct.ga.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CityTest {

	@Test
	public void test() {
		InitData initData = InitData.getInstance();
		ArrayList<City> cities = new ArrayList<City>();
		
		City c1 = new City(1,5.0, 5.0);
		City c2 = new City(2,5.0, 10.0);
		City c3 = new City(3, 10.0, 10.0);
		
		cities.add(c1);
		cities.add(c2);
		cities.add(c3);
		
		initData.setCities(cities);
		
		assertEquals(c1.getDistance(c2), 5.0, 0.0);
		
		
		
		assertEquals(c3.getDistance(c1), 7.07, 0.1);
	}

}
