package at.univie.ct.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import at.univie.ct.ga.data.City;
import at.univie.ct.ga.data.Individual;

public class GeneticAlgorithm {
	private double                mutationRate;
	private CrossoverType 		  crossover;
	private List<Individual>      population = new ArrayList<Individual>();
	private Individual            optimal;
	private List<City>            cities = new ArrayList<City>(); // all available cities to generate random individuals
	private int                   elites; 

	public GeneticAlgorithm(Properties prop){
		this.setCities(prop.getProperty("problem"));
		optimal = new Individual(prop.getProperty("optimal"));
		this.setMutationRate(Double.parseDouble(prop.getProperty("mutationRate")));
		int populationSize = Integer.parseInt(prop.getProperty("populationSize"));
		this.setCrossover(prop.getProperty("crossoverMethode"));
		this.setElites(Integer.parseInt(prop.getProperty("eliten")));
		for ( int i=0 ; i < populationSize ; i++ ){
			population.add(this.createRandomIndividual());
		}
		
	}	
	
	public ArrayList<Individual> selectElites(int count){
		// TODO: see issue #9
		return null;
	}
	
	public void doGenerate(){
		System.out.println("hello world!");
		// TODO: see issue #10 
	}
	
	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public CrossoverType getCrossover() {
		return crossover;
	}

	public void setCrossover(CrossoverType crossover) {
		this.crossover = crossover;
	}
	
	public int getElites() {
		return elites;
	}

	public void setElites(int elites) {
		this.elites = elites;
	}

	
	public void setCrossover(String crossover) {
		if ( crossover.equals("OX") ){
			this.crossover = CrossoverType.OX;
		}else if ( crossover.equals("PMX") ) {
			this.crossover = CrossoverType.PMX;
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
	
	public List<Individual> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Individual> population) {
		this.population = population;
	}
	
	public int getPopulationSize(){
		return this.population.size();
	}
	
	public void setCities(String filename) {
        // store city coordinates in the map.
		// read the file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			for (String str = reader.readLine(); str != null; str = reader.readLine()) {
				// find the city coordinates from the file
				if (str.matches("([0-9]+)(\\s*)([0-9]+)(\\.?)([0-9]*)(\\s*)([0-9]+)(\\.?)([0-9]*)")) {
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
