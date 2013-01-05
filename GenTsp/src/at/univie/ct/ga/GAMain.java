package at.univie.ct.ga;

import java.io.IOException;



public class GAMain {

	/**
	 * @param args
	 */
	public static double[][] distance;
	
	public static void main(String[] args) throws IOException {
		GAMain main = new GAMain();
		Configuration cf = new Configuration(" information.properties");
		String path = cf.getValue("filePath");
		GAMain.distance = InitData.getCityDistance(InitData.getCityData(path));
	}

}
