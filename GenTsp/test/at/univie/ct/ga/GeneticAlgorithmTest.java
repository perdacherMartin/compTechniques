package at.univie.ct.ga;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GeneticAlgorithmTest {

	@Test
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

}
