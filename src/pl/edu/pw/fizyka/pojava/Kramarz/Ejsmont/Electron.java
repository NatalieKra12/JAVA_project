package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Natalia Kramarz

import java.util.Random;

public class Electron {
	
	int xLocation;
	int yLocation;
	int zLocation;
	double energyInit;
	double velocity;
	
	static double electronMass = 9.11*Math.pow(10, -31); // kg
	static double planckConstant = 6.63*Math.pow(10, -34); // m^2*kg/s
	static double lightVelocity = 3*Math.pow(10, 8); // m/s
	
	public Electron (double energyInit, int xLocation) {
		super();
		this.xLocation = countXLocation();
		this.yLocation = countYLocation();
		this.zLocation = countZLocation();
		this.energyInit = energyInit; //keV
		this.velocity = countInitVelocity(this.energyInit);
	}//end of Electron constructor
	
	public int countXLocation() {
		 Random randXLocation = new Random();
		 return randXLocation.nextInt(200)+20;
	 }//end of countYLocation method
	
	public int countYLocation() {
		 Random randYLocation = new Random();
		 return randYLocation.nextInt(100)+50;
	 }//end of countYLocation method
	
	public int countZLocation() {
		 Random randZLocation = new Random();
		 return randZLocation.nextInt(100);
	 }//end of countYLocation method
	
	public double countInitVelocity(double energyOfElectron) {
		Random randVelocityDeviation = new Random();
		energyOfElectron = randVelocityDeviation.nextDouble()*4+energyOfElectron;
		double energyOfElectronJoul = energyOfElectron*1000*1.602*Math.pow(10, -19); //Joul
		return Math.sqrt(2*energyOfElectronJoul/electronMass); //m/s
	 }//end of countInitVelocity method
	
	//setters and getters
	public int getxLocation() {
		return xLocation;
	}
	
	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}
	
	public int getyLocation() {
		return yLocation;
	}
	
	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}
	
	public int getzLocation() {
		return zLocation;
	}

	public void setzLocation(int zLocation) {
		this.zLocation = zLocation;
	}

	public double getEnergyInit() {
		return energyInit;
	}
	
	public void setEnergyInit(double energyInit) {
		this.energyInit = energyInit;	
	}
	
	public double getVelocity() {
		return velocity;	
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

}//end Electron class
