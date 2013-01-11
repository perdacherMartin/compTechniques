package at.univie.ct.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import at.univie.ct.ga.data.City;
import at.univie.ct.ga.data.Individual;

public class GeneticAlgortihm {

	public enum crossoverType{ OX, PMX }
	private double                mutationRate;
	private crossoverType 		  crossover;
	private ArrayList<Individual> population;
	private Individual            optimal;
	private ArrayList<City>       cities; // all available cities to generate random individuals

	public GeneticAlgortihm(Properties prop){
		this.setCities(prop.getProperty("problem"));
		optimal = new Individual(prop.getProperty("optimal"));
		this.setMutationRate(Double.parseDouble(prop.getProperty("mutationRate")));
		int populationSize = Integer.parseInt(prop.getProperty("populationSize"));
		this.setCrossover(prop.getProperty("crossoverMethode"));
		
		for ( int i=0 ; i < populationSize ; i++ ){
			population.add(this.createRandomIndividual());
		}
		
	}	
	
	public ArrayList<Individual> selectElites(int count){
		// TODO: see issue #9
		return null;
	}
	
	public void doGenerate(){
		// TODO: see issue #10 
	}
	
	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public crossoverType getCrossover() {
		return crossover;
	}

	public void setCrossover(crossoverType crossover) {
		this.crossover = crossover;
	}
	
	public void setCrossover(String crossover) {
		if ( crossover.equals("OX") ){
			this.crossover = crossoverType.OX;
		}else if ( crossover.equals("PMX") ) {
			this.crossover = crossoverType.PMX;
		}else{
			System.err.println("Error occured! Could not set Crossovertype! Class:GeneticAlgorithm");
			System.exit(1);
		}
		
	}
	
	private Individual getElternteil(){
		// TODO: issue #3
		return null;
	}
	
	public Individual createRandomIndividual(){
		ArrayList<City> ind = new ArrayList<City>(cities);
		java.util.Collections.shuffle(ind);
		
		return new Individual(ind);
	}
	
	private Individual SelectAndCrossover(){
		Individual i1 = this.getElternteil();
		Individual i2 = this.getElternteil();
		Individual child = null;
		
		switch ( crossover ){
			case OX :
				child = CrossoverOX(i1,i2);
				break;
			case PMX :
				child = CrossoverPMX(i1,i2);
				break;
		}
		
		return child;
	}
	
	private Individual CrossoverPMX(Individual i1, Individual i2){
		// TODO: issue #5
		return null;
	}
	
	private Individual CrossoverOX(Individual i1, Individual i2){
		// TODO: issue #6
		return null;
	}
	
	public ArrayList<Individual> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Individual> population) {
		this.population = population;
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
					cities.add(new City(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2])));
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
		
	}
	
}
