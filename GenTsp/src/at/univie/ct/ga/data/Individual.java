package at.univie.ct.ga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Individual implements Comparable<Individual>{

	public ArrayList<City> cities = new ArrayList<City>();
    public double roundtrip;       // the roundtrip of the TSP
   
    
    /**
     * The optimal individual is set with the filename of the optimal solution
     * 	
     * @param filename
     */
    public Individual(String filename, List<City> cities){
    	//TODO: see issue #8
//    	ArrayList<City> cities = new ArrayList<City>();
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			for (String str = reader.readLine(); str != null; str = reader.readLine()) {
				// find the city coordinates from the file
				if (str.matches("([0-9]+)")) {
					for(City c: cities){
						if(Integer.parseInt(str) == c.getNumber()){
							this.cities.add(c);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not find file " + filename + "! Class: GeneticAlgorithm!" );
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Error in parsing data! Class: GeneticAlgorithm!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException! Class: GeneticAlgorithm!");
			e.printStackTrace();
		}
		
		calculateRoundtrip();
	    
    }
    
    public Individual(ArrayList<City> cities){
    	this.cities = cities;
    	calculateRoundtrip();
    }
    
    
    /**
     * function of calculate the fitness
     */
    public double getFitness(){
    	// TODO: see issue #1, calculate fitness like in the presentation defined!
    	double fitness = 1/this.roundtrip;
    	return fitness;
    }
    
	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	public void calculateRoundtrip(){
    	roundtrip = 0.0;
    	City previous=null, first=null;
		for ( City c : cities ){
			if ( previous == null ){
				previous = c;
				first = c;
			}else{
				roundtrip += c.getDistance(previous);
				previous = c;
			}
		}
		roundtrip += first.getDistance(previous);
		
    }
    
    /**
     * Variation
     * Generates two random cities, and exchange their position.
     *
     * @return new Individual
     */
	public void mutate(){
		Random rand = new Random();
    	int index1 = rand.nextInt(this.cities.size() );
    	int index2 = rand.nextInt(this.cities.size() );
    	
    	while(index1 == index2){
    		index2 = rand.nextInt(this.cities.size());
    	}
    	
    	Collections.swap(this.cities, index1, index2);
    	
	}
   
 
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	for(City c: cities){
    		sb.append(c.getNumber()+ " ");
    	}
//        return "Path: " + sb.toString() + "\ncost is "+this.getFitness() +"/n";
        return "Path: " + sb.toString() + "\ncost is "+this.roundtrip +"/n";
    }
    
	@Override
	public int compareTo(Individual next) {
		if(this.getFitness() < next.getFitness()) return 1;
        if(this.getFitness() > next.getFitness()) return -1;
		return 0;
	}

	public double getRoundtrip() {
		return roundtrip;
	}


}
