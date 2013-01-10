package at.univie.ct.ga.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import at.univie.ct.ga.GAMain;
import at.univie.ct.ga.InitData;

public class Generation {
	public ArrayList<Individual> allIndividuals; // all individuals in this generation
	private Individual minPath; // minimal path in this generation
	private double minCost = Double.MIN_VALUE; // minimal cost in this generation
	public int populationSize; // population size
	
	/**
	 * Constructor
	 * 
	 * @param populationSize
	 *         size of population
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
	 * roulette selection
	 * Problem..............every survival probability is so smaller as select rate.
	 * @param selectRate
	 *           
	 * @return new generation
	 */
	public Generation rouletteSelcetion(double selectRate) {
		//TODO
		Generation newGeneration = new Generation(populationSize);
		
		double sum = 0.0;
//		survival Probability for every Individual
		double[] survivalProbability = new double[this.populationSize];
		
		for(Individual indi: this.allIndividuals){
			
//			sum = sum + 1/indi.fitness;
			sum = sum + indi.fitness;
		}
		for(int i=0; i<this.populationSize;i++){
//			survivalProbability[i] = 1 - (1/this.allIndividuals.get(i).fitness)/sum;
			survivalProbability[i] = this.allIndividuals.get(i).fitness/sum;
			System.out.print(survivalProbability[i]+ " ");
		}
		for(int j=0; j<this.populationSize;j++){
			if(survivalProbability[j] > selectRate){
				newGeneration.allIndividuals.add(j, this.allIndividuals.get(j));
			} else {
				newGeneration.allIndividuals.add(j, GAMain.randomlyGenerateIndividuals());
			}
		}
		return newGeneration;
	}
		
	
	/**
	 * Variation
	 * 
	 * @param mutationRate
	 *            the rate of variation
	 * @return new Generation
	 */
	public Generation mutate(double mutationRate) {
		int r;	
		Individual individual;
		Random rand = new Random();
		for(int i = 0; i < populationSize; i++){
			r = rand.nextInt(populationSize-1 - 0 + 1) + 0;
			individual = allIndividuals.get(r);
 
			//begin to variate
			double random = Math.random();
			if(random < mutationRate && random >0){
				this.allIndividuals.set(i, individual.mutate());
			}
		}
 
		return this;
	}
 
 
	/**
	 * Choose the OderOX or pmx.
	 * @param crossoverMethode
	 * @return new Generation
	 */
	
	public Generation crossover(String crossoverMethode){
		
		if(crossoverMethode.equals("OX")){
			//TODO
		} else if (crossoverMethode.equals("PMX")){
			//TODO
		} else {
			throw new RuntimeException("There are not this Methods (Only OX or PMX)!");
		}
		return this;
	}
	
	/**
	 * orderOX
	 * 
	 * @param f1
	 * @param f2
	 *            two Individuals
	 * @return new Individual
	 */
	public Individual orderOX(Individual f1, Individual f2) {
		int length = InitData.numberOfCity - 1;
		Random rand = new Random();
		// radom nummber from 0 to length
    	int r1 = rand.nextInt(length - 0 + 1) + 0; 
    	int r2 = rand.nextInt(length - 0 + 1) + 0; 
    	while(r1==r2){
    		r1 = rand.nextInt(length - 0 + 1) + 0; 
    		r2 = rand.nextInt(length - 0 + 1) + 0;
    	}
    	//------only for test-----------------------
    	System.out.print("R1: " + r1 + " R2: " + r2);
    	System.out.print("\n");
    	//------------------------------------------
    	ArrayList<City> sonPath = new ArrayList<City>();
    	
    	for(int xx=0;xx<=length;xx++){
    		City c = new City();
    		c.setNumber(0);
    		sonPath.add(c);
    	}
    	
    	if(r1>r2){
    		
    		oxTemp(r2, r1,length, sonPath,f1, f2);
    		/*
    		for(int i=r2;i<=r1;i++){
    			sonPath.set(i, f1.myPath.get(i));
    		}
    		boolean flag = false;
    		for(int j=0;j<=length;j++){
    			
    			for(int k=r2;k<=r1;k++){
    				if(f2.myPath.get(j).getNumber()== f1.myPath.get(k).getNumber()){
    					flag = true;
    				}
    			}
    			if(flag == false){
//    				add elment to son
    				for(int x=0;x<=length;x++){
    					if(sonPath.get(x).getNumber() == 0){
    						sonPath.set(x, f2.myPath.get(j));
    						break;
    					}
    				}
    			}
    			flag = false;
    			
    		}
    		*/
    	} else {
    		oxTemp(r1, r2,length, sonPath,f1, f2);
    		/*
    		for(int i=r1;i<=r2;i++){
    			sonPath.set(i, f1.myPath.get(i));
    		}
    		boolean flag = false;
    		for(int j=0;j<=length;j++){
    			
    			for(int k=r1;k<=r2;k++){
    				if(f2.myPath.get(j).getNumber()== f1.myPath.get(k).getNumber()){
    					flag = true;
    				}
    			}
    			if(flag == false){
//    				add elment to son
    				for(int x=0;x<=length;x++){
    					if(sonPath.get(x).getNumber() == 0){
    						sonPath.set(x, f2.myPath.get(j));
    						break;
    					}
    				}
    			}
    			flag = false;
    			
    		}
    		*/
    	}
    	
    	
		return new Individual(sonPath);
	}
	
	public void oxTemp(int min, int max,int length, ArrayList<City> sonPath,Individual f1, Individual f2){
		
		for(int i=min;i<=max;i++){
			sonPath.set(i, f1.myPath.get(i));
		}
		boolean flag = false;
		for(int j=0;j<=length;j++){
			
			for(int k=min;k<=max;k++){
				if(f2.myPath.get(j).getNumber()== f1.myPath.get(k).getNumber()){
					flag = true;
				}
			}
			if(flag == false){
//				add element to son
				for(int x=0;x<=length;x++){
					if(sonPath.get(x).getNumber() == 0){
						sonPath.set(x, f2.myPath.get(j));
						break;
					}
				}
			}
			flag = false;
			
		}
	}
	/**
	 * PMX
	 * Wen can not use PMX for TSP Problem.
	 * @param f1
	 * @param f2
	 *            two Individuals
	 * @return new Individual
	 */
 
	public Individual pmx(Individual f1, Individual f2){
		ArrayList<City> sonPath = new ArrayList<City>();
		//TODO
		int length = InitData.numberOfCity - 1;
		Random rand = new Random();
		// radom nummber from 1 to length-1
    	int r1 = rand.nextInt(length-1 - 1 + 1) + 1; 
    	int r2 = rand.nextInt(length-1 - 1 + 1) + 1; 
    	while(r1==r2){
    		r1 = rand.nextInt(length-1 - 1 + 1) + 1; 
    		r2 = rand.nextInt(length-1 - 1 + 1) + 1;
    	}
    	//----------only for test-------------------
    	
    	System.out.println("R1: " + r1 + " R2: " + r2);
    	//------------------------------------------
    	for(int xx=0;xx<=length;xx++){
    		City c = new City();
    		c.setNumber(0);
    		sonPath.add(c);
    	}
    	int index = -1;
    	if(r1>r2){
    		for(int i=r2;i<=r1;i++){
    			sonPath.set(i, f1.myPath.get(i));
    		}
    		for(int j=0;j<=length;j++){
    			
    			for(int k=r2;k<=r1;k++){
    				if(f2.myPath.get(j).getNumber()== f1.myPath.get(k).getNumber()){
    					index = f1.myPath.indexOf(f1.myPath.get(k));
    				}
    			}
    			
    			if(index > -1){
    				if(j>=r2 && j<=r1){
    					
    				} else {
    					sonPath.set(j, f2.myPath.get(index));
    				}
    				
    			} else {
    				if(j>=r2 && j<=r1){
    					
    				} else {
    					sonPath.set(j, f2.myPath.get(j));
    				}
    				
    			}
    			index = -1;
    		}
    		
    	} else {
    		for(int i=r1;i<=r2;i++){
    			sonPath.set(i, f1.myPath.get(i));
    		}
    		for(int j=0;j<=length;j++){
    			
    			for(int k=r1;k<=r2;k++){
    				if(f2.myPath.get(j).getNumber()== f1.myPath.get(k).getNumber()){
    					index = f1.myPath.indexOf(f1.myPath.get(k));
    				}
    			}
    			
    			if(index > -1){
    				if(j>=r1 && j<=r2){
    					
    				} else {
    					sonPath.set(j, f2.myPath.get(index));
    				}
    				
    			} else {
    				if(j>=r1 && j<=r2){
    					
    				} else {
    					sonPath.set(j, f2.myPath.get(j));
    				}
    				
    			}
    			index = -1;
    		}
    		
    	}
    	
		return new Individual(sonPath);
	}
 
	/**
	 * find minimal value
	 */
	public void findMin() {
//		System.out.println("Length: " + this.allIndividuals.size());
		Individual min = this.allIndividuals.get(0);
		if (min == null) {
			throw new RuntimeException("The Data of this Generation is null!");
		}
 
		Individual temp;
		for (int i = 1; i < allIndividuals.size(); i++) {
			temp = allIndividuals.get(i);
			if (min.fitness > temp.fitness) {
				min = temp;
			}
		}
 
		this.minPath = min;
	}

	public Individual getminPath() {
		if (this.minPath != null) {
			return this.minPath;
		}
 
		findMin();
		return this.minPath;
	}
	
	public double getMinCost() {
		if (this.minCost - Double.MIN_VALUE > 0) {
			return this.minCost;
		}
 
		findMin();
		this.minCost = this.minPath.fitness;
 
		return this.minCost;
	}
 
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
    	for(City c: this.minPath.myPath){
    		sb.append(c.getNumber()+ " ");
    	}
		return  " minPath is "
				+ sb.toString() + " and the path costs " + getMinCost()
				+ "\n";
	}
}
