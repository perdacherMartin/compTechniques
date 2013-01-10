package at.univie.ct.ga;

import java.util.ArrayList;
import java.util.Properties;

import at.univie.ct.ga.data.Individual;
import at.univie.ct.ga.data.InitData;

public class GeneticAlgortihm {

	public enum crossoverType{ OX, PMX }
	private double                mutationRate;
	private crossoverType 		  crossover;
	private ArrayList<Individual> population;
	private Individual            optimal;
	private InitData              initData = InitData.getInstance();
	
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

	public ArrayList<Individual> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Individual> population) {
		this.population = population;
	}
	
	public GeneticAlgortihm(Properties prop){
		initData.setCities(prop.getProperty("problem"));
		optimal = new Individual(prop.getProperty("optimal"));
		this.setMutationRate(Double.parseDouble(prop.getProperty("mutationRate")));
		int populationSize = Integer.parseInt(prop.getProperty("populationSize"));
		this.setCrossover(prop.getProperty("crossoverMethode"));
		
		for ( int i=0 ; i < populationSize ; i++ ){
			population.add(initData.getRandomIndividual());
		}
		
	}
	
}
