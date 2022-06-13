package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//Author Natalia Kramarz, Julia Ejsmont

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class SimulationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//public List<Line> drawnLines;
	public List<Line> allDrawnLines;
	public CopyOnWriteArrayList<Line> LinesCopy;
	public List<Electron> initElectrons;
	public List<Electron> allAltitudeElectrons;

	WaveEmission waveEmissionFirstAlt;
	WaveEmission waveEmissionSecondAlt;
	WaveEmission waveEmissionThirdAlt;
	WaveEmission allEmissionThirdAlt;

	boolean ifToAdd = false;
	
	Timer paintingTimer;
	Timer calulationTimer;
	TimerTask paintingTimerTask;
	TimerTask calculationTimerTask;
	boolean paintingTimerWorks = false;
	boolean calculationTimerWorks = false;
	
	int numberOfElectrons = 0;
	Color waveColor;
	double velocityLoss;
	Date dateTime;
	long currentTime = 0;


	TitledBorder BorderforSimulationPanel;
	
	public SimulationPanel() {
		BorderforSimulationPanel = new TitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Symulacja", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241));
		
		setBackground(new Color(28, 40, 51));
		setPreferredSize(new Dimension(280, 700));
		setBorder(BorderforSimulationPanel);

		initElectrons = new ArrayList<Electron>();
		allAltitudeElectrons = new ArrayList<Electron>();
		allDrawnLines = new ArrayList<Line>();
		LinesCopy = new CopyOnWriteArrayList<Line>();
		
		dateTime = new Date();
		
	}//end of SimulationPanel constructor

	//@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!LinesCopy.isEmpty()) {
			for(Line line: LinesCopy) {
				line.draw((Graphics2D) g);
			}
		}
		g.setColor(new Color(255, 255, 255, 50));
		g.drawLine(10, 50, this.getWidth()-10, 50); //300km
		g.drawLine(20, 250, this.getWidth()-10, 250); //200km
		g.drawLine(20, 450, this.getWidth()-10, 450); //100km
		g.drawLine(20, 650, this.getWidth()-10, 650); //0km			
	}//end of paintComponent method

	public void createWaveEmission(int densityofAir, int oxygenFirst, int nitrogenFirst, int hydrogenFirst, int oxygenSecond, int nitrogenSecond,
			int hydrogenSecond, int oxygenThird, int nitrogenThird, int hydrogenThird) {
		waveEmissionFirstAlt = new WaveEmission(densityofAir, oxygenFirst, nitrogenFirst, hydrogenFirst);
		waveEmissionSecondAlt = new WaveEmission(densityofAir, oxygenSecond, nitrogenSecond, hydrogenSecond);
		waveEmissionThirdAlt = new WaveEmission(densityofAir, oxygenThird, nitrogenThird, hydrogenThird);
	}//end of createWaveEmission method
	
	public void createElectrons(int electoronEnergySliderValue) {
		initElectrons.removeAll(initElectrons);
		for(int numberOfInitElectrons = 0; numberOfInitElectrons < 50; numberOfInitElectrons++) {
			Electron newElectron = new Electron(electoronEnergySliderValue, 2 + numberOfInitElectrons);
			initElectrons.add(newElectron);
		}
	}//end of createElectrons method
	
	public void startElectronsMove(int electoronEnergySliderValue) {
		calulationTimer = new Timer();
		calculationTimerWorks = true;
		int begin2 = 0;
		int timeInterval2 = 100;
		paintLines();
		
		calculationTimerTask = new TimerTask() {
			@Override
			public void run() {
				//long start3 = System.currentTimeMillis();
				createElectrons(electoronEnergySliderValue);
				allAltitudeElectrons.addAll(initElectrons);
				updatingAllElectrons();
				deletingFadedElectrons();
				//long end3 = System.currentTimeMillis();
				}
		};
		calulationTimer.schedule(calculationTimerTask, begin2, timeInterval2);		
	}//end startElectronsMove method
	
	public void paintLines() {
		paintingTimer = new Timer();
		paintingTimerWorks = true;
		int begin = 0;
		int timeInterval = 100;
		
		paintingTimerTask = new TimerTask() {	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				transferingData();
				repaint();
			}
		};
		paintingTimer.schedule(paintingTimerTask, begin, timeInterval);
	}//end paintLines method
	
	public void deletingFadedElectrons() {
		currentTime = dateTime.getTime();
		
		allDrawnLines.removeIf(allDrawnLines-> allDrawnLines.getEmissionTime()<=
				currentTime-allDrawnLines.getStartEmissionTime());
		allDrawnLines.removeIf(allDrawnLines-> allDrawnLines.getemissionColor().getAlpha()<=50);
		
		for(int numberOfDrawnLines=0; numberOfDrawnLines<allDrawnLines.size(); numberOfDrawnLines++) {
			allDrawnLines.get(numberOfDrawnLines).setemissionColor(new Color(allDrawnLines.get(numberOfDrawnLines).getemissionColor().getRed(),
					allDrawnLines.get(numberOfDrawnLines).getemissionColor().getGreen(),allDrawnLines.get(numberOfDrawnLines).getemissionColor().getBlue(),
					allDrawnLines.get(numberOfDrawnLines).getemissionColor().getAlpha()-30));
		}//end for
	}//edn deletingFadedElectrons
	
	public void transferingData() {
		LinesCopy.removeAll(LinesCopy);
		LinesCopy.addAll(allDrawnLines);
	}//end tranferingData
	
	public void updatingAllElectrons() {
		if(!allAltitudeElectrons.isEmpty()) {
			numberOfElectrons = allAltitudeElectrons.size();
		for (int i = 0; i < numberOfElectrons; i++) {
			allAltitudeElectrons.get(i).setyLocation((allAltitudeElectrons.get(i).getyLocation()
						+ (int) (0.2*2.5*Math.pow(10, -4) * (allAltitudeElectrons.get(i).getVelocity() / 500))));
			
			if(allAltitudeElectrons.get(i).getyLocation() > 450) {
				if (waveEmissionThirdAlt.emissionProbability(allAltitudeElectrons.get(i).getyLocation()) == true) {
					waveColor = waveEmissionThirdAlt.colorEmission(allAltitudeElectrons.get(i).getzLocation());
					velocityLoss = waveEmissionThirdAlt.LostVelocity(waveColor);
					ifToAdd = true;
				}
				
			}
			else if(allAltitudeElectrons.get(i).getyLocation() > 250) {
				if (waveEmissionSecondAlt.emissionProbability(allAltitudeElectrons.get(i).getyLocation()) == true) {
					waveColor = waveEmissionSecondAlt.colorEmission(allAltitudeElectrons.get(i).getzLocation());
					velocityLoss = waveEmissionSecondAlt.LostVelocity(waveColor);
					ifToAdd = true;
				}
			}
			
			else if (allAltitudeElectrons.get(i).getyLocation() <= 250){
				if (waveEmissionFirstAlt.emissionProbability(allAltitudeElectrons.get(i).getyLocation()) == true) {
					waveColor = waveEmissionFirstAlt.colorEmission(allAltitudeElectrons.get(i).getzLocation());
					velocityLoss = waveEmissionFirstAlt.LostVelocity(waveColor);
					ifToAdd = true;
				}
			}
			
			if(ifToAdd) {
				currentTime = dateTime.getTime();
				allAltitudeElectrons.get(i).setVelocity(allAltitudeElectrons.get(i).getVelocity() - velocityLoss);
				allDrawnLines.add(new Line(allAltitudeElectrons.get(i).getxLocation(),
						allAltitudeElectrons.get(i).getyLocation(), allAltitudeElectrons.get(i).getxLocation(),
						allAltitudeElectrons.get(i).getyLocation() + 4, waveColor, currentTime));
				ifToAdd = false;
			}
		}//end of for
		
		allAltitudeElectrons.removeIf(allAltitudeElectrons -> allAltitudeElectrons.getyLocation() >= 650);	//absolute board is 250
		allAltitudeElectrons.removeIf(allAltitudeElectrons -> allAltitudeElectrons.getVelocity() <= 0);
		}
	}//end updatingAllElectrons
	
	public void clearAll() throws InterruptedException {
		if(paintingTimerWorks) {
			paintingTimer.cancel();
		}
		if(calculationTimerWorks) {
			calulationTimer.cancel();
		}
		allAltitudeElectrons.removeAll(allAltitudeElectrons);
		allDrawnLines.removeAll(allDrawnLines);
		LinesCopy.removeAll(LinesCopy);
		initElectrons.removeAll(initElectrons);
		repaint();
	}//end ClearAll
	
	public void timerStop() throws InterruptedException {
		if(paintingTimerWorks) {
			paintingTimer.cancel();
		}
		if(calculationTimerWorks) {
			calulationTimer.cancel();
		}
	}//end timerStop
	
}//end of SimulationPanel class