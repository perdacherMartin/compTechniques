package at.univie.ct.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import at.univie.ct.ga.data.City;
/**
 * you can load the number of cities and city coordinates from the file and store in the map.
 * and calculate the distances for each city to other cities.
 * 
 */
public class InitData {

	
	public static HashMap<Integer, City> cityMap = new HashMap<Integer, City>();
	public static int numberOfCity = 0;
	

	public static void setCityMapAndNumberOfCity(String filename) throws IOException {
		
       // store city coordinates in the map.
		
		// read the file
		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
		for (String str = reader.readLine(); str != null; str = reader.readLine()) {
			// find the number of city
			if(str.matches("([A-Z]+)[:]?(\\s*)([0-9]+)")){
				String[] data = str.split("(\\s+)");
				numberOfCity = Integer.parseInt(data[1]);
//				System.out.println("TTTTTTTTTTTTT "+data[1]);
			}
			// find the city coordinates from the file
			if (str.matches("([0-9]+)(\\s*)([0-9]+)(.?)([0-9]*)(\\s*)([0-9]+)(.?)([0-9]*)")) {
				String[] data = str.split("(\\s+)");
				City city = new City(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));
//				System.out.println("Index " + city.getNumber() + " X: " + city.getX() + " Y: " + city.getY());
				cityMap.put(city.getNumber(), city);
			}
		}
	}
	
//	store the distance value of one city to other cities.
	public static double[][] getCityDistance(HashMap<Integer, City> map) {
		
		double[][] distance = new double[map.size() + 1][map.size() + 1];
		for (int i = 1; i < map.size() + 1; i++) {
			for (int j = 1; j < map.size() + 1; j++) {
				distance[i][j] = map.get(i).getDistance(map.get(j));
//				System.out.println("Distance[" +i+"]"+"["+j+"]"+" "+ distance[i][j]);
			}
		}
 
		return distance;
	}
}
