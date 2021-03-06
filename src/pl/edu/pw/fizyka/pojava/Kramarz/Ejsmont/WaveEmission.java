package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//Author Natalia Kramarz

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
	
	Color waveOxygenRed;
	Color waveOxygenGreen;
	Color waveNitrogenRed;
	Color waveNitrogenBlue;
	Color waveHydrogenBlue;
	
	Color emissionColor;
	
	public WaveEmission(double airDensityInit, double oxygennumber, double nitrogennumber, double hydrogennumber) {
		super();
		this.airDensityInit = airDensityInit;
		this.oxygennumber = oxygennumber/100;
		this.nitrogennumber = nitrogennumber/100;
		this.hydrogennumber = hydrogennumber/100;
		
		waveOxygenRed = new Color(255, 79, 0, 254);
		waveOxygenGreen = new Color(185,225,0, 254);
		waveNitrogenRed = new Color(255, 0, 0, 254);
		waveNitrogenBlue = new Color(0, 169, 255, 254);
		waveHydrogenBlue = new Color(75, 0, 255, 254);
		
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
			if(((randifEmission.nextDouble())*airDensityInit)<=yLocationDensity*100) {
				ifEmission = true;
			}
		}
		return ifEmission;
	}//end of EmisionProbability method
	
	public Color colorEmission (int electronZLocation, int electronYLocation) {
		Random randWaveColor = new Random();
		Random randWaveColor2 = new Random();
		double p = randWaveColor.nextDouble();
		int r = randWaveColor2.nextInt(2);
			if(p<=oxygennumber) {
				if(electronYLocation<250) {
					emissionColor = new Color(255, 79, 0, electronZLocation*2+54);
				}
				else if(electronYLocation>=250) {
					emissionColor = new Color(185, 225, 0, electronZLocation*2+54);
				}	
			}
			else if(p<=oxygennumber+nitrogennumber) {
				if(electronYLocation<450) {
					emissionColor = new Color(0, 169, 255, electronZLocation*2+54);
				}
				else if(electronYLocation>=450) {
					if(r==1) {
						emissionColor = new Color(255, 0, 0, electronZLocation*2+54);
					}
					else {
						emissionColor = new Color (0, 169, 255, electronZLocation*2+54);
					}
				}
				
			}
			else {
				emissionColor= new Color(75, 0, 255, electronZLocation*2+54);
			}
			return emissionColor;
	}//end of colorEmission method
	
	public double LostVelocity (Color c) {
		double lostEnergy;
		double lostVelocity = 0;
		if(c.getRed() == 185) { //Oxygen Green 557
			  lostEnergy = (planckStatic*lightVelocity)/(557*Math.pow(10, -9));
		}
		else if(c.getRed() == 255) { //Oxygen Red 630
			  lostEnergy = (planckStatic*lightVelocity)/(630*Math.pow(10, -9));
		}
		else if(c.getRed() == 0) { //NitrogenBlue 427
			  lostEnergy = (planckStatic*lightVelocity)/(427*Math.pow(10, -9));
		}
		else if(emissionColor.getRed() == 255 && emissionColor.getGreen() == 0) { //NitrgoenRed 700
			  lostEnergy = (planckStatic*lightVelocity)/(700*Math.pow(10, -9));
		}
		else {//Hydrogen Blue
			  lostEnergy = (planckStatic*lightVelocity)/(427*Math.pow(10, -9));
		}
		lostVelocity = Math.sqrt(2*lostEnergy/electronMass);
		return lostVelocity;
		
	}//end LostVelocity
	
}//end of WaveEmission class