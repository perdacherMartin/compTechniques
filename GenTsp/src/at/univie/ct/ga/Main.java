package at.univie.ct.ga;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import at.univie.ct.ga.plot.DeviationPlot;

public class Main {
	
	/*
	 * This is the starting point of the program,
	 * At first, the property file is given as command line argument
	 * 
	 */
	public static void main(String[] args) throws IOException {
		if ( args.length != 1 ){
			System.err.println("No property-file is specified! \n Run the programm with the command line argument of the property-file!");
			System.err.println("Example: jar ... at.univie.ct.ga.Main information.properties");
			System.exit(1);
		}
		
		try{
			DeviationPlot plot;
			XYSeries best = new XYSeries("best");
			XYSeries average = new XYSeries("average");
			XYSeries worst = new XYSeries("worst");
			XYSeriesCollection dataset = new XYSeriesCollection();
			
			Properties prop = new Properties();	
			prop.load(new FileInputStream("information.properties"));

//			System.out.println("Using " + prop.getProperty("problem") + " for problem input.");
//			System.out.println("Comparing with optimal solution " + prop.getProperty("optimal"));
//			System.out.println("Populationsize: " + prop.getProperty("populationSize"));
//			System.out.println("Using a mutation rate of " + prop.getProperty("mutationRate") );
//			System.out.println("Using the " + prop.getProperty("crossoverMethode") + " as crossover method");
//			System.out.println("Using " + prop.getProperty("eliten") + " elites");
			
			GeneticAlgorithm ga = new GeneticAlgorithm(prop);

			/*
			 * durchlaufen der Generationen: 
			 */ 
			int generations = Integer.parseInt(prop.getProperty("generationen"));
			for ( int i=0 ; i < generations ; i++ ){
				ga.doGenerate();
				best.add((double)i,ga.getDistanceToOpt());
				average.add((double)i, ga.getAverageDistance());
				worst.add((double)i, ga.getWorstDistance());
				System.out.println("" + i);
				//System.out.println("" + i + ";" + Double.toString(ga.getDistanceToOpt()).replace('.', ',') + ";"  + Double.toString(ga.getAverageDistance()).replace('.', ',') + ";" + Double.toString(ga.getWorstDistance()).replace('.', ','));
			}
			
			dataset.addSeries(best);
			dataset.addSeries(average);
			dataset.addSeries(worst);
			
			plot = new DeviationPlot(dataset);
			
		}catch(IOException e){
			System.err.println("Could not open Propertyfile!");
			e.printStackTrace();
		}
		
	}
}
