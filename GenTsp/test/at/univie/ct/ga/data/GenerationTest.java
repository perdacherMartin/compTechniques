package at.univie.ct.ga.data;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import at.univie.ct.ga.GAMain;
import at.univie.ct.ga.InitData;


public class GenerationTest {
	
	@Ignore
	public void testForOrderOX() throws IOException{
		GAMain main = new GAMain();
		InitData.setCityMapAndNumberOfCity(main.filePath);
		Generation gen = new Generation(main.populationSize);
		Individual.distance = InitData.getCityDistance(InitData.cityMap);
		Individual f1 = GAMain.randomlyGenerateIndividuals();
		
		Individual f2 = GAMain.randomlyGenerateIndividuals();
		System.out.print("Path1: ");
		for(City c: f1.myPath){
			System.out.print(c.getNumber() + " ");
		}
		System.out.print("\n");
		System.out.print("Path2: ");
		for(City c: f2.myPath){
			System.out.print(c.getNumber() + " ");
		}
		System.out.print("\n");
		System.out.print("OX: ");
		Individual.distance = InitData.getCityDistance(InitData.cityMap);
		Individual temp = gen.orderOX(f1, f2);
		for(City c: temp.myPath){
			System.out.print(c.getNumber() + " ");
		}
		
	}
	@Test
	public void testForPMX() throws IOException{
		
		GAMain main = new GAMain();
		InitData.setCityMapAndNumberOfCity(main.filePath);
		Generation gen = new Generation(main.populationSize);
		Individual.distance = InitData.getCityDistance(InitData.cityMap);
		Individual f1 = GAMain.randomlyGenerateIndividuals();
		
		Individual f2 = GAMain.randomlyGenerateIndividuals();
		System.out.print("Path1: ");
		for(City c: f1.myPath){
			System.out.print(c.getNumber() + " ");
		}
		System.out.print("\n");
		System.out.print("Path2: ");
		for(City c: f2.myPath){
			System.out.print(c.getNumber() + " ");
		}
		System.out.print("\n");
		System.out.print("PMX: ");
		Individual.distance = InitData.getCityDistance(InitData.cityMap);
		Individual temp = gen.pmx(f1, f2);
		for(City c: temp.myPath){
			System.out.print(c.getNumber() + " ");
		}
		
	}

}
