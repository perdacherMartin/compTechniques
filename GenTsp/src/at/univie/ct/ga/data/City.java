package at.univie.ct.ga.data;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
 
public class City {
	
	private int number;
	private double x;
	private double y;
	
	
	public City(){
		
	}
	
	public City(int number, double x, double y) {
		this.setNumber(number);
		this.x = x;
		this.y = y;
	}
 
	public double getDistance(City next) {
		
		// simple euclidian distance
		// TODO: see optional issue #11
		return sqrt(
					pow(this.getX() - next.getX(),2) +
					pow(this.getY() - next.getY(),2)
				);
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