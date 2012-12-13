package at.univie.ct.ga.data;

public class City {
	private int    node;
	private double x;
	private double y;
	
	public City(int node, double x, double y){
		setNode(node);
		setX(x);
		setY(y);
	}
	
	public int getNode() {
		return node;
	}
	
	public void setNode(int node) {
		this.node = node;
	}
	
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
	
	public double getDistance(City next){
		//TODO a change this code with a pre-computation matrix from all cities to all-cities, which can be indexed with the city-Id
		double xValue = Math.abs(this.getX() - next.getX());
		double yValue = Math.abs(this.getY() - next.getY());
		
		return Math.sqrt( Math.pow(xValue, 2) + Math.pow(yValue, 2) );
	}
}
