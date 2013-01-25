package at.univie.ct.ga.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

import at.univie.ct.ga.GeneticAlgorithm;

import static org.junit.Assert.assertTrue;

public class IndividualTest {
	
	@Test
	public void testForCalculateFitness() throws IOException{
//		GAMain main = new GAMain();
//		InitData.setCityMapAndNumberOfCity(main.filePath);
//		Individual.distance = InitData.getCityDistance(InitData.cityMap);
//		GAMain.randomlyGenerateIndividuals();
//		System.out.print("Fitness: " + GAMain.randomlyGenerateIndividuals().fitness);
	}
	
	@Test
	public void testForMutate() throws IOException{
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

		i1.mutate();
		
		assertTrue(i1.getCities().containsAll(ga.getCities()));
	}
	

}
