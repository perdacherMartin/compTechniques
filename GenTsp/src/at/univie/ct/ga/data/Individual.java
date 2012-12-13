package at.univie.ct.ga.data;

import java.util.List;

public class Individual {
	
	private double     fitness; // the roundtrip of the TSP
	private List<City> cities;
	
	public Individual(List<City> cities){
		setCities(cities);
	}
	
	public double getFitness() {
		return fitness;
	}
	
	private void calculateFitness() {
		City first=null,previous=null;
		double roundtrip=0.0;

		for ( City city : cities ){
			if ( previous == null ){
				first = city;
			}else{
				roundtrip += city.getDistance(previous);
			}
			
			previous = city;
		}
		roundtrip += previous.getDistance(first);
		this.fitness=roundtrip;
	}
	
	public List<City> getCities() {
		return cities;
	}
	
	public void setCities(List<City> cities) {
		this.cities = cities;
		this.calculateFitness();
	}
	
	
}
