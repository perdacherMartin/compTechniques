package at.univie.ct.ga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

import at.univie.ct.ga.data.City;
import at.univie.ct.ga.data.Individual;

public class GeneticAlgorithmTest {

	@Ignore
	public void test() {
		try {
			Properties prop = new Properties();	
			
			prop.load(new FileInputStream("information.properties"));
			GeneticAlgorithm ga = new GeneticAlgorithm(prop);
			
			assertEquals(ga.getMutationRate(), Double.parseDouble(prop.getProperty("mutationRate")),0.0);
			assertEquals(ga.getPopulationSize(), Integer.parseInt(prop.getProperty("populationSize")));

			if ( "OX".equals(prop.getProperty("crossoverMethode")) )
				assertEquals(ga.getCrossover(), CrossoverType.OX);
			else
				assertEquals(ga.getCrossover(), CrossoverType.PMX);

			assertEquals(ga.getElites(), Integer.parseInt(prop.getProperty("eliten")));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCrossover() {
		Properties prop = new Properties();	
		
		try {
			prop.load(new FileInputStream("information.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneticAlgorithm ga = new GeneticAlgorithm(prop);

		Individual i1 = ga.createRandomIndividual();
		Individual i2 = ga.createRandomIndividual();
		
		Individual childOX = ga.CrossoverOX(i1, i2);
		Individual childPMX = ga.CrossoverPMX(i1, i2);
		
		assertEquals( childOX.getCities().size(), ga.getCities().size());
		assertTrue(ga.getCities().containsAll(childOX.getCities()));
		
		assertEquals( childPMX.getCities().size(), ga.getCities().size());
		assertTrue(ga.getCities().containsAll(childPMX.getCities()));		
	}

	@Test
	public void testGetEltern(){
		Properties prop = new Properties();	
		
		try {
			prop.load(new FileInputStream("information.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GeneticAlgorithm ga = new GeneticAlgorithm(prop);
		Individual indi = ga.getElternteil();

		assertTrue(ga.getPopulation().contains(indi));
		
	}
	
	@Test
	public void testSelectElites(){
		Properties prop = new Properties();	
		
		try {
			prop.load(new FileInputStream("information.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneticAlgorithm ga = new GeneticAlgorithm(prop);
		
		List<Individual> elites =  new ArrayList<Individual>(ga.selectElites());
		
		assertEquals(elites.size(), Integer.parseInt(prop.getProperty("eliten")));
		
	}
	
	@Test
	public void testOptimal(){
		Properties prop = new Properties();	
		
		try {
			prop.load(new FileInputStream("information.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneticAlgorithm ga = new GeneticAlgorithm(prop);
		Individual temp = ga.getOptimal();
		for(City c:temp.cities){
			System.out.print(c.getNumber() + " ");
		}
		System.out.print("\n");
		System.out.println("Roundtrip: " + temp.getRoundtrip());
		
	}

}
