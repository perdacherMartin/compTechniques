package at.univie.ct.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;


import at.univie.ct.ga.data.City;
import at.univie.ct.ga.data.Individual;

public class GeneticAlgorithm {
	private double                mutationRate;
//	private double                selectRate;
	private CrossoverType 		  crossover;
	private List<Individual>      population = new ArrayList<Individual>();
	private Individual            optimal;
	private List<City>            cities = new ArrayList<City>(); // all available cities to generate random individuals
	private int                   elites; 
	
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public GeneticAlgorithm(Properties prop){
		this.setCities(prop.getProperty("problem"));
		optimal = new Individual(prop.getProperty("optimal"), this.cities);
		this.setMutationRate(Double.parseDouble(prop.getProperty("mutationRate")));
//		this.setSelectRate(Double.parseDouble(prop.getProperty("selectRate")));
		int populationSize = Integer.parseInt(prop.getProperty("populationSize"));
		this.setCrossover(prop.getProperty("crossoverMethode"));
		this.setElites(Integer.parseInt(prop.getProperty("eliten")));
		for ( int i=0 ; i < populationSize ; i++ ){
			population.add(this.createRandomIndividual());
		}
		
	}	
	
	public ArrayList<Individual> selectElites(int count){
		// TODO: see issue #9
		ArrayList<Individual> temp = (ArrayList<Individual>) this.population;
		ArrayList<Individual> best = new ArrayList<Individual>(count);
		Collections.sort(temp);
		for(int i=0;i<count;i++){
			best.add(temp.get(i));
		}
		
		return best;
	}
	
	private Individual mutate(Individual ind) {
		Random rand = new Random();
		double probability = rand.nextDouble(); 
	
		if ( this.getMutationRate() >=  probability ){
			ind.mutate();
		}
		
		return ind;
	}
	
//	Ich bin nicht sicher, ob dieser Teil richtig ist oder nicht.
	public void doGenerate(){
		// TODO: see issue #10
		List<Individual> nextGeneration = new ArrayList<Individual>();
		
		for ( int i = 0 ; i < population.size() - this.getElites() ; ++i ){
			nextGeneration.add(this.SelectAndCrossover());
		}
		
		
//        ArrayList<Individual> temp = new ArrayList<Individual>(this.getPopulationSize());
//        ArrayList<Individual> best = this.selectElites(this.elites);
//        for(int i=0;i<this.elites;i++){
//        	temp.add(best.get(i));
//        }
//        for(int k= this.elites; k<this.getPopulationSize();k++){
//        	temp.add(k, SelectAndCrossover());
//        }
//        this.setPopulation(temp);
////      mutation aufrufen mit wahrscheinlichkeit von mutationsrate aufrufen
//		mutate();
//		System.out.println(best.get(0).toString());
		
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
	
	
	public Individual getElternteil(){
		double[] indArray = new double[population.size()]; // array containing the cumulative survival probability to be selected for each individual
		
		Random rand = new Random();
		double sum = 0.0;
		double cumSum=0.0;
		double survivalProbability = 0.0;
		
		
		for(Individual indi : this.population){
			sum = sum + indi.getFitness();	
		}
		
		for ( int i = 0 ; i < this.population.size() ; ++i  ){
			cumSum += this.population.get(i).getFitness()/sum;
			indArray[i] = cumSum;
		}
		
		survivalProbability = rand.nextDouble(); // generates random number between zero and 1
		
		return this.population.get(-1 * Arrays.binarySearch(indArray, survivalProbability) -1 );
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
		
		
		return mutate(child);
	}
 
	private void oxTemp(int min, int max,int length, ArrayList<City> sonPath,Individual i1, Individual i2){
		
		for(int i=min;i<=max;i++){
			sonPath.set(i, i1.cities.get(i));
		}
		boolean flag = false;
		for(int j=0;j<=length;j++){
			
			for(int k=min;k<=max;k++){
				if(i2.cities.get(j).getNumber()== i1.cities.get(k).getNumber()){
					flag = true;
				}
			}
			if(flag == false){
//				add element to son
				for(int x=0;x<=length;x++){
					if(sonPath.get(x).getNumber() == 0){
						sonPath.set(x, i2.cities.get(j));
						break;
					}
				}
			}
			flag = false;
			
		}
	}
	
	
	public Individual CrossoverOX(Individual i1, Individual i2){
		
		int length = this.cities.size() - 1;
		Random rand = new Random();
		// radom nummber from 0 to length
    	int r1 = rand.nextInt(length - 0 + 1) + 0; 
    	int r2 = rand.nextInt(length - 0 + 1) + 0; 
    	while(r1==r2){
    		r1 = rand.nextInt(length - 0 + 1) + 0; 
    		r2 = rand.nextInt(length - 0 + 1) + 0;
    	}
    	/*
    	//------only for test-----------------------
    	System.out.print("R1: " + r1 + " R2: " + r2);
    	System.out.print("\n");
    	//------------------------------------------
    	*/
    	ArrayList<City> sonPath = new ArrayList<City>();
    	
    	for(int xx=0;xx<=length;xx++){
    		City c = new City();
    		c.setNumber(0);
    		sonPath.add(c);
    	}
    	
    	if(r1>r2){
    		
    		oxTemp(r2, r1,length, sonPath,i1, i2);
    		
    	} else {
    		oxTemp(r1, r2,length, sonPath,i1, i2);
    		
    	}
    	
    	
		return new Individual(sonPath);
	}
	
	private void pmxTemp(int min, int max,int length, ArrayList<City> sonPath,Individual i1, Individual i2){
		
		int index = -1;
		for(int i=min;i<=max;i++){
			sonPath.set(i, i1.cities.get(i));
		}
	    boolean flag = false;
	    
		for(int j=0;j<=length;j++){
			
			if(j>=min && j<=max){
				
			} else {
				
				for(int k=min;k<=max;k++){
					if(i2.cities.get(j).getNumber()== i1.cities.get(k).getNumber()){
						index = i1.cities.indexOf(i1.cities.get(k));
					}
				}
				
				if(index > -1){
					do{
						flag = false;
						
						for(int k=min;k<=max;k++){
							
							if(i2.cities.get(index).getNumber()== i1.cities.get(k).getNumber()){
								index = i1.cities.indexOf(i1.cities.get(k));
								flag = true;
								break;
							} 
						}
					}while(flag);
					
					sonPath.set(j, i2.cities.get(index));
					
					
				} else {
					
					sonPath.set(j, i2.cities.get(j));
				}
				index = -1;
				
			}
		}
	
	}
	
	public Individual CrossoverPMX(Individual i1, Individual i2){
		ArrayList<City> sonPath = new ArrayList<City>();
		int length = this.cities.size() - 1;
		Random rand = new Random();
		// radom nummber from 0 to length
    	int r1 = rand.nextInt(length - 0 + 1) + 0; 
    	int r2 = rand.nextInt(length - 0 + 1) + 0; 
    	while(r1==r2){
    		r1 = rand.nextInt(length - 0 + 1) + 0; 
    		r2 = rand.nextInt(length - 0 + 1) + 0;
    	}
    	//----------only for test-------------------
    	
//    	System.out.println("R1: " + r1 + " R2: " + r2);
    	//------------------------------------------
    	for(int xx=0;xx<=length;xx++){
    		City c = new City();
    		c.setNumber(0);
    		sonPath.add(c);
    	}
    	
    	if(r1>r2){
    		pmxTemp(r2,r1,length, sonPath, i1,i2);
    		
    	} else {
    		
    		pmxTemp(r1,r2,length, sonPath, i1,i2);
    	}
    	
		return new Individual(sonPath);
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
	public int getNumberOfCity(){
		return this.cities.size();
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
/*
	public double getSelectRate() {
		return selectRate;
	}

	public void setSelectRate(double selectRate) {
		this.selectRate = selectRate;
	}
	*/

	public Individual getOptimal() {
		return optimal;
	}


	
}
