package at.univie.ct.ga;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import at.univie.ct.ga.data.City;
import at.univie.ct.ga.data.Generation;
import at.univie.ct.ga.data.Individual;



public class GAMain {

	/**
	 * @param args
	 */
	static Configuration cf = new Configuration(" information.properties");
	public static double[][] distance;
	public double selectRate = Double.parseDouble(cf.getValue("selectRate"));
	public double mutationRate = Double.parseDouble(cf.getValue("mutationRate"));
	public int populationSize = Integer.parseInt(cf.getValue("populationSize"));
	public String crossoverMethode = cf.getValue("crossoverMethode");
	public String filePath = cf.getValue("filePath");
	
	/**
	 * randomly generate the individuals in a generation
	 * @return  new Individual
	 */
	
	public static Individual randomlyGenerateIndividuals(){
		ArrayList<City> path = new ArrayList<City>();
    	int total = 0;
    	boolean flag = false;// repeat or not, repeat:true,no repeat:false  
        int[] intArray = new int[InitData.numberOfCity];  
    	Random rand = new Random();
    	while (total < InitData.numberOfCity)  
         {  
             int intTemp = rand.nextInt(InitData.numberOfCity - 1 + 1) + 1;  
             // Determine whether the generated data are equal.  
             for (int j = 0; j < total; j++)  
             {  
                 if (intTemp == intArray[j])  
                 {  
                     flag = true;  
                     break;  
                 }  
                 else  
                 {  
                     flag = false;  
                 }  
             }  
             if (false == flag)  
             {  
                 intArray[total] = intTemp;  
                 total++;  
             }  
         } 
   /* 	
//    	 only for test
//         Arrays.sort(intArray);
         for(int i: intArray){
        	 System.out.print(i + " ");
         }
         System.out.print("\n");
       */  
    	for(int i: intArray){
    		path.add(InitData.cityMap.get(i));
    	}
   /* 	
    	for(City c: path){
    		System.out.print(c.getNumber() + " ");
    	}
    	*/
    	return new Individual(path);
    }
	
	/**
	 * Attention: Maybe there will be changed.
	 * Find the best individual in a generation.
	 * @param log
	          Control output
	 * @return new Individual
	 */
	
	public Individual findTheBestIndividual(boolean log){
	       if(this.distance == null){
	           throw new RuntimeException("Distance can not be null!");
	       }
	 
	       Generation first = init();	//initialization of individual
	      
	 
	       for(int i = 0; i < this.populationSize; i++){
//	    	   first = first.rouletteSelcetion(selectRate);	//selection of individual
//	    	   first.findMin();
	    	   if(log){
	    		   System.out.println("Selection in "+i+"th generation ");
//	    		   System.out.println(first.toString());
	    		   System.out.println();
	    	   }
//	    	   first = first.crossover(this.crossoverMethode);
//	    	   first.findMin();
	    	   if(log){
	    		   System.out.println("Corossover in "+i+"th generation ");
//	    		   System.out.println(first.toString());
	    		   System.out.println();
	    	   }
	    	   first = first.mutate(mutationRate);
	    	   first.findMin();
	    	   if(log){
	    		   System.out.println("Variation in "+i+"th generation ");
	    		   System.out.println(first.toString());
	    		   System.out.println();
	    	   }
	       }
	 
	       return first.getminPath();
	 
	    }
	 /**
	  * randomly initialize one generation
	  * @return new Generation
	  */
	    private Generation init(){
	    	Generation first = new Generation(populationSize);
	 
	    	Individual gene;
	    	for(int i = 0; i < populationSize; i++){
	    		gene = randomlyGenerateIndividuals();
	    		first.allIndividuals.add(gene);
	    	}
	    	return first;
	    }
	
	public static void main(String[] args) throws IOException {
		GAMain main = new GAMain();
		InitData.setCityMapAndNumberOfCity(main.filePath);
		GAMain.distance = InitData.getCityDistance(InitData.cityMap);
		Individual.distance = GAMain.distance;
		System.out.println(main.findTheBestIndividual(true));

	}

}
