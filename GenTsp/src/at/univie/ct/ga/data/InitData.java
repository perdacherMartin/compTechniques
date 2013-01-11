package at.univie.ct.ga.data;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.univie.ct.ga.GAException;


/**
 * you can load the number of cities and city coordinates from the file and store in the map.
 * and calculate the distances for each city to other cities.
 * 
 */

public class InitData {

	// distance matrix, which stores the distance from each city to each city
	private double[][] distance;
	// Singelton object
	private static final InitData INSTANCE = new InitData();
	private List<City> cities = new ArrayList<City>();
	
	private InitData(){ 
	}
	
	public void addCity(City c){
		cities.add(c);
		this.setCityDistance();
	}
	
	public static InitData getInstance(){
		return INSTANCE;
	}
	
	public void setCities(List<City> cities){
		this.cities = cities;
		this.setCityDistance();
	}
	
	public Individual getRandomIndividual(){
		ArrayList<City> ind = new ArrayList<City>(cities);
		java.util.Collections.shuffle(ind);
		
		return new Individual(ind);
	}
	
	public void setCities(String filename) {
        // store city coordinates in the map.
		// read the file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			for (String str = reader.readLine(); str != null; str = reader.readLine()) {
				// find the city coordinates from the file
				if (str.matches("([0-9]+)(\\s*)([0-9]+)(.?)([0-9]*)(\\s*)([0-9]+)(.?)([0-9]*)")) {
					String[] data = str.split("(\\s+)");
					City city = new City(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));
//				System.out.println("Index " + city.getNumber() + " X: " + city.getX() + " Y: " + city.getY());
					cities.add(city);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not find file " + filename + "! Class: InitData!" );
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Error in parsing data! Class: InitData!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException! Class: InitData!");
			e.printStackTrace();
		}
		this.setCityDistance();
	}
	
//	store the distance value of one city to other cities.
	private void setCityDistance() {
		distance = new double[cities.size()][cities.size()];
		for (int i = 0; i < cities.size() ; i++) {
			for (int j = 0; j < cities.size() ; j++) {
				distance[i][j] = sqrt(
						pow((cities.get(i).getX() - cities.get(j).getX()), 2) + 
						pow((cities.get(i).getY() - cities.get(j).getY()), 2)
					);
			}
		}
	}
	
	public double getDistance(int city1, int city2) throws GAException{
		city1 -= 1; city2 -= 1;
		if ( city1 >= cities.size() || city1 < 0 ||
				city2 >= cities.size() || city2 < 0 ){
			throw new GAException("Index violation, Class: InitData!");
		}
		return distance[city1][city2];
	}
	
	public int getCityCount(){
		return cities.size();
	}
}
