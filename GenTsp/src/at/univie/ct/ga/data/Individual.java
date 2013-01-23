package at.univie.ct.ga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	public Individual mutate(){
		// TODO: issue #2
		Random rand = new Random();
    	int r1 = rand.nextInt(this.cities.size() - 1 + 1) + 1;
    	int r2 = rand.nextInt(this.cities.size() - 1 + 1) + 1;
    	while(r1 == r2){
    		r2 = rand.nextInt(this.cities.size() - 1 + 1) + 1;
    	}
    	City city1 = new City();
    	City city2 = new City();
    	City temp = new City();
    	int index1 = 0;
    	int index2 = 0;
    	for(City c: this.cities){
    		// ------only for test----------
//    		System.out.print(c.getNumber() + " ");
    		//-----------------------------
    		if(c.getNumber() == r1){
    			city1 = c;
    			index1 = this.cities.indexOf(c);
    		}
    		if(c.getNumber() == r2){
    			city2 = c;
    			index2 = this.cities.indexOf(c);
    		}
    	}
    	temp = city1;
    	this.cities.set(index1, city2);
    	this.cities.set(index2, temp);
    	// ----------only for test----------------
//    	System.out.print("\n");
//    	for(City x: this.cities){
//    		System.out.print(x.getNumber() + " ");
//    	}
    	//--------------------------------------
    	
		return new Individual(this.cities);
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
