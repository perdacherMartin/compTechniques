package at.univie.ct.ga.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import at.univie.ct.ga.GAMain;

public class Generation {
	public ArrayList<Individual> allIndividuals; // all individuals in this generation
	private Individual minPath; // minimal path in this generation
	public int populationSize; // population size
	
	/**
	 * Constructor
	 * 
	 * @param m
	 *            
	 */
	public Generation(int populationSize) {
		if (populationSize <= 0) {
			throw new RuntimeException("Population size is not right!");
		}
		this.populationSize = populationSize;
		this.allIndividuals = new ArrayList<Individual>(populationSize);
	}
	
	/**
	 * Roulette selection
	 * 
	 * @param selectRate
	 *           
	 * @return new generation
	 */
	public Generation createGenerationAfterSelect(double selectRate) {
		//TODO
		Generation newGeneration = new Generation(populationSize);
 
 
		return newGeneration;
	}
	
	/**
	 * OXCrossover
	 * 
	 * @param p
	 *            The initial crossover probability
	 * @param numberOfCity
	 *            number of city
	 * @return new generation
	 */
	public Generation oxCross(double p, int numberOfCity) {
		//TODO
		Generation newGeneration = new Generation(populationSize);
		
 
		return newGeneration;
	}
	
	/**
	 * Varation
	 * 
	 * @param ...?
	 * @return new generation
	 */
	public Generation mutate() {
		//TODO
 
		return null;
	}
 
	public Individual getminPath() {
		
		return this.minPath;
	}
 
	

	
 
	/**
	 * find the minimal path in this generation
	 */
	public void findMin() {
		//TODO
		Individual min = null;
		this.minPath = min;
	}
 
	
}
