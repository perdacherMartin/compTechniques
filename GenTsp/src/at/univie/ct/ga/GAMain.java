package at.univie.ct.ga;


import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import at.univie.ct.ga.data.ResultOfOneGeneration;



public class GAMain {

	/**
	 * @param args
	 */
	static Configuration cf = new Configuration(" information.properties");
	public static double[][] distance;
	public static int numberOfCity = Integer.parseInt(cf.getValue("numberOfCity"));
	public int generationMax = Integer.parseInt(cf.getValue("generationMax"));
	public double selectRate = Double.parseDouble(cf.getValue("selectRate"));
	public double mutationRate = Double.parseDouble(cf.getValue("mutationRate"));
	public double crossRate = Double.parseDouble(cf.getValue("crossRate"));
	public int populationSize = Integer.parseInt(cf.getValue("populationSize"));
	public String crossoverMethode = cf.getValue("crossoverMethode");
	public String filePath = cf.getValue("filePath");
	
	/**
	 * Randomly generated generation
	 * @return Object ResultOfOneGeneration
	 */
	
	public static ResultOfOneGeneration randomGen(){
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
    		sb.append(""+i);
    	}
    	
    	System.out.print("Path " + sb);
    	return new ResultOfOneGeneration(sb.toString());
    }
	
	public static void main(String[] args) throws IOException {
		GAMain main = new GAMain();
		
		GAMain.distance = InitData.getCityDistance(InitData.getCityData(main.filePath));
		GAMain.randomGen();
		
	}

}
