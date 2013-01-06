package at.univie.ct.ga;



import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import at.univie.ct.ga.data.Generation;
import at.univie.ct.ga.data.Individual;



public class GAMain {

	/**
	 * @param args
	 */
	static Configuration cf = new Configuration(" information.properties");
	public static double[][] distance;
	public static int numberOfCity = Integer.parseInt(cf.getValue("numberOfCity"));
	public int generationMax = Integer.parseInt(cf.getValue("generationMax"));
	public double selectRate = Double.parseDouble(cf.getValue("selectRate"));
	public double mutationRate2 = Double.parseDouble(cf.getValue("mutationRate2"));
//	public double mutationRate4 = Double.parseDouble(cf.getValue("mutationRate4"));
	public double crossRate = Double.parseDouble(cf.getValue("crossRate"));
	public int populationSize = Integer.parseInt(cf.getValue("populationSize"));
	public String crossoverMethode = cf.getValue("crossoverMethode");
	public String filePath = cf.getValue("filePath");
	
	/**
	 * Randomly generated generation
	 * @return  Individual
	 */
	
	public static Individual randomGen(){
    	StringBuilder sb = new StringBuilder();
    	int total = 0;
    	boolean flag = false;// repeat or not, repeat:true,no repeat:false  
        int[] intArray = new int[numberOfCity];  
    	Random rand = new Random();
    	while (total < numberOfCity)  
         {  
             int intTemp = rand.nextInt(numberOfCity - 1 + 1) + 1;  
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
         Arrays.sort(intArray);
         for(int i: intArray){
        	 System.out.print(i + " ");
         }
         */
    	for(int i: intArray){
    		sb.append(i+" ");
    	}
    	
//    	System.out.print("Path " + sb);
    	return new Individual(sb.toString());
    }
	
	
	public Individual ga(boolean log){
	       if(this.distance == null){
	           throw new RuntimeException("distance can not be null");
	       }
	 
	       Generation first = init();	//initialization of individual
	       if(log){
	    	   System.out.println(first.toString());
	       }
	 
	       for(int i = 0; i < generationMax; i++){
//	    	   first = first.createGenerationAfterSelect(selectRate);	//selection of individual
//	    	   first.findMin();
	    	   if(log){
	    		   System.out.println("Selection in "+i+"th generation ");
	    		   System.out.println(first.toString());
	    		   System.out.println();
	    	   }
//	    	   first = first.oxCross(crossRate, numberOfCity);
//	    	   first.findMin();
	    	   if(log){
	    		   System.out.println("Corossover in "+i+"th generation ");
	    		   System.out.println(first.toString());
	    		   System.out.println();
	    	   }
//	    	   first = first.mutate(mutationRate2, mutationRate4, numberOfCity);
//	    	   first.findMin();
	    	   if(log){
	    		   System.out.println("Variation in "+i+"th generation ");
	    		   System.out.println(first.toString());
	    		   System.out.println();
	    	   }
	       }
	 
	       return first.getminPath();
	 
	    }
	 
	    private Generation init(){
	    	Generation first = new Generation(populationSize);
	 
	    	Individual gene;
	    	for(int i = 0; i < populationSize; i++){
	    		gene = randomGen();
	    		first.allIndividuals.add(gene);
	    	}
	    	return first;
	    }
	
	public static void main(String[] args) throws IOException {
		GAMain main = new GAMain();
		
		GAMain.distance = InitData.getCityDistance(InitData.getCityData(main.filePath));
		Individual.distance = GAMain.distance;
		System.out.println(main.ga(false));
	}

}
