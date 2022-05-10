package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//Author Kramarz, Ejsmont

import java.awt.Color;
import java.util.Random;

public class WaveEmission {
	
	double airDensityInit;
	double oxygennumber;
	double nitrogennumber;
	double hydrogennumber;
	
	static double k = 0.0512;
	static double planckStatic = 6.6260693*Math.pow(10, -34);  //J*s
	static double lightVelocity = 2.98*Math.pow(10, 8); //m/s
	static double electronMass = 9.11*Math.pow(10, -31); // kg
	
	Color waveGreen;
	Color waveRed;
	Color waveBlue;
	
	
	public WaveEmission(double airDensityInit, double oxygennumber, double nitrogennumber, double hydrogennumber) {
		super();
		this.airDensityInit = airDensityInit;
		this.oxygennumber = oxygennumber/100;
		this.nitrogennumber = nitrogennumber/100;
		this.hydrogennumber = hydrogennumber/100;
		
		waveGreen = new Color(185,225,0);
		waveRed = new Color(255,79,0);
		waveBlue = new Color(75, 0, 255);
	}//end of WaveEmission constructor
	
	public boolean emissionProbability (int electronYLocation) {
		Random randifEmission = new Random();
		boolean ifEmission = false;
		double yLocationDensity;
		if(electronYLocation>450) {//px
			ifEmission = true;
		}
		else {
			yLocationDensity =  airDensityInit*Math.exp(-k*((600-(electronYLocation-50))/2-100)); //*10-8 kg/m^3
			if((randifEmission.nextDouble(airDensityInit))<=yLocationDensity*100)
			{
				ifEmission = true;
			}
		}

		return ifEmission;
	}//end of EmisionProbability method
	
	public Color colorEmission () {
		Random randWaveColor = new Random();
		double p = randWaveColor.nextDouble(1);
			if(p<=oxygennumber) {
				return waveGreen;
			}
			else if(p<=oxygennumber+nitrogennumber) {
				return waveRed;
			}
		
			else {
				return waveBlue;
			}
	}//end of colorEmission method
	
	public double LostVelocity (Color c) {
		double lostEnergy = 0;
		double lostVelocity = 0;
		if(c.getRGB() == waveGreen.getRGB()) {
			lostEnergy = (planckStatic*lightVelocity)/(630*Math.pow(10, -9));
			//System.out.println(lostEnergy);

		}
		else if (c.getRGB() == waveRed.getRGB()) {
			lostEnergy = (planckStatic*lightVelocity)/(557*Math.pow(10, -9));
			//System.out.println(lostEnergy);
		}
		else {
			lostEnergy = (planckStatic*lightVelocity)/(427*Math.pow(10, -9));
			//System.out.println(lostEnergy);
		}
		lostVelocity = Math.sqrt(2*lostEnergy/electronMass); //m/s
		//System.out.println(lostVelocity); //

		return lostVelocity;
		
	}//end of Color
	
}//end of WaveEmission class