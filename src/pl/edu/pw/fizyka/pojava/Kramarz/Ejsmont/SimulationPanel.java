package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//Author Natalia Kramarz, Julia Ejsmont

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Line> drawnLines;
	public List<Line> firstDrawnLines;
	public List<Line> secondDrawnLines;
	public List<Line> thirdDrawnLines;

	public List<Electron> initElectrons;
	public List<Electron> firstAltitudeElectrons;
	public List<Electron> secondAltitudeElectrons;
	public List<Electron> thirdAltitudeElectrons;
	
	ScheduledExecutorService createInitElectronsExecutor;
	ScheduledExecutorService electronsCalcExecutor;
	
	boolean ifFirstElectronsFirstAltitude = true;
	boolean ifSecondElectronsFirstAltitude = true;

	WaveEmission waveEmissionFirstAlt;
	WaveEmission waveEmissionSecondAlt;
	WaveEmission waveEmissionThirdAlt;

	boolean ifElectronsExecutorRunning = false;
	boolean ifCalcExecutorRunning = false;
	
	public SimulationPanel() {
		
		setBackground(new Color(28, 40, 51));
		setPreferredSize(new Dimension(280, 700));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
				"Wynik symulacji", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));

		drawnLines = new ArrayList<Line>();
		initElectrons = new ArrayList<Electron>();
		firstAltitudeElectrons = new ArrayList<Electron>();
		secondAltitudeElectrons = new ArrayList<Electron>();
		thirdAltitudeElectrons = new ArrayList<Electron>();

		firstDrawnLines = new ArrayList<Line>();
		secondDrawnLines = new ArrayList<Line>();
		thirdDrawnLines = new ArrayList<Line>();
		
	}//end of SimulationPanel constructor

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*for (Line line : drawnLines) {
			line.draw((Graphics2D) g);
		}*/
		for (Line line1 : firstDrawnLines) {
			line1.draw((Graphics2D) g);
		}
		for (Line line2 : secondDrawnLines) {
			line2.draw((Graphics2D) g);
		}
		for (Line line3 : thirdDrawnLines) {
			line3.draw((Graphics2D) g);
		}
		g.setColor(Color.white);
		g.drawLine(20, 50, 220, 50); //300km
		g.drawLine(20, 250, 220, 250); //200km
		g.drawLine(20, 450, 220, 450); //100km
		g.drawLine(20, 650, 220, 650); //0km

	}//end of paintComponent method

	//creating class to define emission
	public void createWaveEmission(int densityofAir, int oxygenFirst, int nitrogenFirst, int hydrogenFirst, int oxygenSecond, int nitrogenSecond,
			int hydrogenSecond, int oxygenThird, int nitrogenThird, int hydrogenThird) {
		waveEmissionFirstAlt = new WaveEmission(densityofAir, oxygenFirst, nitrogenFirst, hydrogenFirst);
		waveEmissionSecondAlt = new WaveEmission(densityofAir, oxygenSecond, nitrogenSecond, hydrogenSecond);
		waveEmissionThirdAlt = new WaveEmission(densityofAir, oxygenThird, nitrogenThird, hydrogenThird);

	}//end of createWaveEmission method

	public void createInitElectrons(int electoronEnergySliderValue) {
		ifElectronsExecutorRunning = true;
		createInitElectronsExecutor = Executors.newScheduledThreadPool(1);
		createInitElectronsExecutor.scheduleAtFixedRate(new Runnable() {
			public void run() {
				createElectrons(electoronEnergySliderValue);
			}
		}, 0, 200, TimeUnit.MILLISECONDS);
	}//end of createInitElectrons method
	
	public void createElectrons(int electoronEnergySliderValue) {
		firstAltitudeElectrons.addAll(initElectrons);
		drawnLines.clear();
		initElectrons.clear();
		
		if(ifFirstElectronsFirstAltitude){
			startElectronsMove();
			ifFirstElectronsFirstAltitude = false;
		}//end of if
		for(int numberOfInitElectrons = 0; numberOfInitElectrons < 200; numberOfInitElectrons++){
			
			Electron newElectron = new Electron(electoronEnergySliderValue, 2 + numberOfInitElectrons);
			initElectrons.add(newElectron);
		}//end of for loop
	}//end of createElectrons method
	
	
	public void startElectronsMove() {
		ifCalcExecutorRunning = true;
		electronsCalcExecutor = Executors.newScheduledThreadPool(1);
		electronsCalcExecutor.scheduleAtFixedRate(new Runnable() {
			public void run() {
				updatingFirstAltElectrons();
				updatingSecondAltElectrons();
				updatingThirdAltElectron();
				repaint();

			}
		}, 0, 200, TimeUnit.MILLISECONDS);
	}//end of startElectronsMove method
	
	public void updatingFirstAltElectrons() {
		
		firstDrawnLines.clear();
		for (int i = 0; i < firstAltitudeElectrons.size(); i++) {
			firstAltitudeElectrons.get(i).setyLocation((firstAltitudeElectrons.get(i).getyLocation()
						+ (int) (0.2*2.5*Math.pow(10, -4) * (firstAltitudeElectrons.get(i).getVelocity() / 500))));
				
			if (waveEmissionFirstAlt.emissionProbability(firstAltitudeElectrons.get(i).getyLocation()) == true) {
				Color waveColor = waveEmissionFirstAlt.colorEmission();
				double velocityLoss = waveEmissionFirstAlt.LostVelocity(waveColor);
				firstAltitudeElectrons.get(i).setVelocity(firstAltitudeElectrons.get(i).getVelocity() - velocityLoss);
				firstDrawnLines.add(new Line(firstAltitudeElectrons.get(i).getxLocation(),
						firstAltitudeElectrons.get(i).getyLocation(), firstAltitudeElectrons.get(i).getxLocation(),
						firstAltitudeElectrons.get(i).getyLocation() + 5, waveColor, waveColor));
			
			//System.out.println(firstAltitudeElectrons.get(i).getVelocity()+"    "+velocityLoss);
			}
			if (firstAltitudeElectrons.get(i).getyLocation() >= 250) {
				updateSecondLevel(i);
			}


		}
		firstAltitudeElectrons.removeIf(firstAltitudeElectrons -> firstAltitudeElectrons.getyLocation() >= 250);	//absolute board is 250
		firstAltitudeElectrons.removeIf(firstAltitudeElectrons -> firstAltitudeElectrons.getVelocity() <= 0);
	}	
	
	public void updateSecondLevel(int i) {
		secondAltitudeElectrons.add(firstAltitudeElectrons.get(i));
	}

	public void updatingSecondAltElectrons() {
		secondDrawnLines.clear();

		for (int i = 0; i < secondAltitudeElectrons.size(); i++) {
			secondAltitudeElectrons.get(i).setyLocation(secondAltitudeElectrons.get(i).getyLocation()
						+ (int) (0.2*2.5*Math.pow(10, -4) * (secondAltitudeElectrons.get(i).getVelocity() / 500)));
			
			if (waveEmissionSecondAlt.emissionProbability(secondAltitudeElectrons.get(i).getyLocation()) == true) {
				Color waveColor = waveEmissionSecondAlt.colorEmission();
				double velocityLoss = waveEmissionSecondAlt.LostVelocity(waveColor);
				secondAltitudeElectrons.get(i).setVelocity(secondAltitudeElectrons.get(i).getVelocity() - velocityLoss);
				secondDrawnLines.add(new Line(secondAltitudeElectrons.get(i).getxLocation(),
						secondAltitudeElectrons.get(i).getyLocation(), secondAltitudeElectrons.get(i).getxLocation(),
						secondAltitudeElectrons.get(i).getyLocation() + 5, waveColor, waveColor));
			}
			
			if (secondAltitudeElectrons.get(i).getyLocation() >= 450) {

				updateThridLevel(i);
			}

		}
		secondAltitudeElectrons.removeIf(secondAltitudeElectrons -> secondAltitudeElectrons.getyLocation() >= 450); //absolute boards is 450
		secondAltitudeElectrons.removeIf(secondAltitudeElectrons -> secondAltitudeElectrons.getVelocity() <= 0);
	}
	
	public void updateThridLevel(int i) {
		thirdAltitudeElectrons.add(secondAltitudeElectrons.get(i));
	}
	
	public void updatingThirdAltElectron() {
		thirdDrawnLines.clear();
		
		for (int i = 0; i < thirdAltitudeElectrons.size(); i++) {
			thirdAltitudeElectrons.get(i).setyLocation(thirdAltitudeElectrons.get(i).getyLocation()
						+ (int) (0.2*2.5*Math.pow(10, -4) * (thirdAltitudeElectrons.get(i).getVelocity() / 500)));
			
			if (waveEmissionThirdAlt.emissionProbability(thirdAltitudeElectrons.get(i).getyLocation()) == true) {
				Color waveColor = waveEmissionThirdAlt.colorEmission();
				double velocityLoss = waveEmissionThirdAlt.LostVelocity(waveColor);
				thirdAltitudeElectrons.get(i).setVelocity(thirdAltitudeElectrons.get(i).getVelocity() - velocityLoss);
				//System.out.println(thirdAltitudeElectrons.get(i).getVelocity());
				secondDrawnLines.add(new Line(thirdAltitudeElectrons.get(i).getxLocation(),
						thirdAltitudeElectrons.get(i).getyLocation(), thirdAltitudeElectrons.get(i).getxLocation(),
						thirdAltitudeElectrons.get(i).getyLocation() + 5, waveColor, waveColor));
			}
		}
		thirdAltitudeElectrons.removeIf(secondAltitudeElectrons -> secondAltitudeElectrons.getyLocation() > 600);
		thirdAltitudeElectrons.removeIf(secondAltitudeElectrons -> secondAltitudeElectrons.getVelocity() <= 0);
	}
	
	public void clearAll() {
		if(ifElectronsExecutorRunning) {
					createInitElectronsExecutor.shutdown();
		}
		if(ifCalcExecutorRunning) {
				electronsCalcExecutor.shutdown();
		}
		drawnLines.clear();
		firstDrawnLines.clear();;
		secondDrawnLines.clear();;
		thirdDrawnLines.clear();;

		initElectrons.clear();
		firstAltitudeElectrons.clear();
		secondAltitudeElectrons.clear();
		thirdAltitudeElectrons.clear();
		
		ifFirstElectronsFirstAltitude = true;
		ifSecondElectronsFirstAltitude = true;

		repaint();
	}
	
}//end of SimulationPanel class
