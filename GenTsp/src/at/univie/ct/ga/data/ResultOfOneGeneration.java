package at.univie.ct.ga.data;


public class ResultOfOneGeneration implements Comparable<ResultOfOneGeneration>{

	
	public static double[][] distance;
    public String path;       //path of the city
    public double totalDistance;       //total distance
    public long reciprocal;           //reciprocal of total distance
   
    
    public ResultOfOneGeneration(String path){
        this.path = path;
        calculateFitness();
    }
    
    /**
     * function of calculate the fitness
     */
    public void calculateFitness(){
    	
    	 
         
    	
    }
    
	@Override
	public int compareTo(ResultOfOneGeneration arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
