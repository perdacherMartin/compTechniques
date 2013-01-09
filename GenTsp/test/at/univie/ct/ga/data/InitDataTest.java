package at.univie.ct.ga.data;

import java.io.IOException;

import org.junit.Test;

import at.univie.ct.ga.GAMain;
import at.univie.ct.ga.InitData;


public class InitDataTest {

	@Test
	public void test() throws IOException{
		GAMain main = new GAMain();
		InitData.setCityMapAndNumberOfCity(main.filePath);
		
	}
}
