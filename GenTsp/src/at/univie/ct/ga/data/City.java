package at.univie.ct.ga.data;

import at.univie.ct.ga.GAException;
 
public class City {
	private int number;
	private double x;
	private double y;
	private InitData initData = InitData.getInstance();
	
	
	public City(int number, double x, double y) {
		this.setNumber(number);
		this.x = x;
		this.y = y;
		initData.addCity(this);
	}
 
	public double getDistance(City next) {
		double distance = 0.0;
		try {
			distance = initData.getDistance(this.getNumber(), next.getNumber());
		} catch (GAException e) {
			System.err.println("Error in Class City! Could not associate number to city!");
			e.printStackTrace();
		}
		return distance;
	}
 
	/********************** getter and setter **************************/
	public double getX() {
		return x;
	}
 
	public void setX(double x) {
		this.x = x;
	}
 
	public double getY() {
		return y;
	}
 
	public void setY(double y) {
		this.y = y;
	}
 
	public void setNumber(int number) {
		this.number = number;
	}
 
	public int getNumber() {
		return number;
	}
}