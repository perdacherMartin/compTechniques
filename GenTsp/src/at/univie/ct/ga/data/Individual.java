package at.univie.ct.ga.data;

import java.util.ArrayList;
import java.util.Random;

import at.univie.ct.ga.InitData;



public class Individual implements Comparable<Individual>{

	
	public static double[][] distance;
	public ArrayList<City> myPath = new ArrayList<City>();
    public double fitness;       // the roundtrip of the TSP
   
    /**
	 * Constructor
	 * 
	 * @param path
	 *           
	 */
   
    public Individual(ArrayList<City> myPath){
    	this.myPath = myPath;
    	calculateFitness();
    }
    /**
     * function of calculate the fitness
     */
	public void calculateFitness(){
    	
    	long totalDistance = 0L;
    	 
        //example 123456
        //totalDistance = d(1,6)+d(1,2)+d(2,3)+...d(5,6)
    	totalDistance += distance[myPath.get(this.myPath.size()-1).getNumber()][this.myPath.get(0).getNumber()];
        int start;
        int end;
        for(int i = 0; i < this.myPath.size()-1; i++){
            start = this.myPath.get(i).getNumber();
            end = this.myPath.get(i+1).getNumber();
 
            totalDistance += distance[start][end];
        }
 
 
        this.fitness =  totalDistance;	
    	
    }
    
    /**
     * Variation
     * Generates two random cities, and exchange their position.
     *
     * @return Object Individual
     */
	public Individual mutate(){
		Random rand = new Random();
    	int r1 = rand.nextInt(InitData.numberOfCity - 1 + 1) + 1;
    	int r2 = rand.nextInt(InitData.numberOfCity - 1 + 1) + 1;
    	while(r1 == r2){
    		r2 = rand.nextInt(InitData.numberOfCity - 1 + 1) + 1;
    	}
    	City city1 = new City();
    	City city2 = new City();
    	City temp = new City();
    	int index1 = 0;
    	int index2 = 0;
    	for(City c: this.myPath){
    		// ------only for test----------
    		System.out.print(c.getNumber() + " ");
    		//-----------------------------
    		if(c.getNumber() == r1){
    			city1 = c;
    			index1 = this.myPath.indexOf(c);
    		}
    		if(c.getNumber() == r2){
    			city2 = c;
    			index2 = this.myPath.indexOf(c);
    		}
    	}
    	temp = city1;
    	this.myPath.set(index1, city2);
    	this.myPath.set(index2, temp);
    	// ----------only for test----------------
    	System.out.print("\n");
    	for(City x: this.myPath){
    		System.out.print(x.getNumber() + " ");
    	}
    	//--------------------------------------
    	
		return new Individual(this.myPath);
	}
   
 
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	for(City c: this.myPath){
    		sb.append(c.getNumber()+ " ");
    	}
        return sb.toString() + "  cost is "+this.fitness+"/n";
    }
    
	@Override
	public int compareTo(Individual next) {
		// TODO Auto-generated method stub
		if(this.fitness < next.fitness) return 1;
        if(this.fitness > next.fitness) return -1;
		return 0;
	}

}
