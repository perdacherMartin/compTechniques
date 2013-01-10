package at.univie.ct.ga.data;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import at.univie.ct.ga.GAMain;
import at.univie.ct.ga.InitData;


public class IndividualTest {
	
	@Test
	public void testForCalculateFitness() throws IOException{
		GAMain main = new GAMain();
		InitData.setCityMapAndNumberOfCity(main.filePath);
		Individual.distance = InitData.getCityDistance(InitData.cityMap);
		GAMain.randomlyGenerateIndividuals();
		System.out.print("Fitness: " + GAMain.randomlyGenerateIndividuals().fitness);
	}
	
	@Test
	public void testForMutate() throws IOException{
		GAMain main = new GAMain();
		InitData.setCityMapAndNumberOfCity(main.filePath);
		Individual.distance = InitData.getCityDistance(InitData.cityMap);
		
		Individual indi = new Individual(GAMain.randomlyGenerateIndividuals().myPath);
		indi.mutate();
	}
	

}
