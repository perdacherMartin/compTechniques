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

	public GeneticAlgortihm(Properties prop){
		initData.setCities(prop.getProperty("problem"));
		optimal = new Individual(prop.getProperty("optimal"));
		this.setMutationRate(Double.parseDouble(prop.getProperty("mutationRate")));
		int populationSize = Integer.parseInt(prop.getProperty("populationSize"));
		this.setCrossover(prop.getProperty("crossoverMethode"));
		
		for ( int i=0 ; i < populationSize ; i++ ){
			population.add(initData.createRandomIndividual());
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
	

	
}
