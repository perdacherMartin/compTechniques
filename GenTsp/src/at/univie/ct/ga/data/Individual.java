package at.univie.ct.ga.data;

import java.util.Random;



public class Individual implements Comparable<Individual>{

	
	public static double[][] distance;
    public String path;       //path of cities
    public double fitness;       // the roundtrip of the TSP
    public long reciprocal;           //reciprocal of total distance
   
    /**
	 * Constructor
	 * 
	 * @param path
	 *           
	 */
    public Individual(String path){
        this.path = path;
        calculateFitness();
    }
    
    /**
     * function of calculate the fitness
     */
	@SuppressWarnings("null")
	public void calculateFitness(){
    	
    	long totalDistance = 0L;
    	 
        //example 123456
        //totalDistance = d(1,6)+d(1,2)+d(2,3)+...d(5,6)
    	String[] tempPath = this.path.split(" ");
    	int[] numberPath = new int[tempPath.length];
    	
    	for(int i=0;i<tempPath.length;i++){
    		
    		numberPath[i] = Integer.parseInt(tempPath[i]);
    	}
    	
    	totalDistance += distance[numberPath[numberPath.length - 1]][numberPath[0]];
        int start;
        int end;
        for(int i = 0; i < tempPath.length-1; i++){
            start = numberPath[i];
            end = numberPath[i+1];
 
            totalDistance += distance[start][end];
        }
 
//        this.reciprocal = totalDistance;
 
        this.fitness =  totalDistance;	
//        System.out.println(totalDistance);
    	
    }
    
    /**
     * Variation
     * Generates two random cities, and exchange their position.
     * @param numberOfCity
     * @return
     */
    public Individual mutate(int numberOfCity){
    	StringBuilder sb = new StringBuilder();
    	Random rand = new Random();
    	int r1 = rand.nextInt(numberOfCity - 1 + 1) + 1;
    	int r2 = rand.nextInt(numberOfCity - 1 + 1) + 1;
    	while(r1 == r2){
    		r2 = rand.nextInt(numberOfCity - 1 + 1) + 1;
    	}
    	String[] tempPath = this.path.split(" ");
    	int[] numberPath = new int[tempPath.length];
    	
    	for(int i=0;i<tempPath.length;i++){
    		
    		numberPath[i] = Integer.parseInt(tempPath[i]);
    	}
    	
    	for(int k=0;k<tempPath.length;k++){
    		if(numberPath[k] == r1){
    			numberPath[k] = r2;
    		}
    		if(numberPath[k] == r2){
    			numberPath[k] = r1;
    		}
    	}
    	for(int m: numberPath){
    		sb.append(m+" ");
    	}
    	
    	return new Individual(sb.toString());
    }
    public String toString(){
        return "The path is "+this.path+"  cost is "+this.fitness+"/n";
    }
    
	@Override
	public int compareTo(Individual next) {
		// TODO Auto-generated method stub
		if(this.fitness < next.fitness) return 1;
        if(this.fitness > next.fitness) return -1;
		return 0;
	}

}
