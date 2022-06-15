package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//Author Natalia Kramarz

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class EarthViewSimulationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Line> allDrawnLines;
	public CopyOnWriteArrayList<Line> LinesCopy;
	public List<EarthViewElectron> initElectrons;
	public List<EarthViewElectron> allAltitudeElectrons;

	EarthViewWaveEmission waveEmissionFirstAlt;
	EarthViewWaveEmission waveEmissionSecondAlt;
	EarthViewWaveEmission waveEmissionThirdAlt;
	EarthViewWaveEmission allEmissionThirdAlt;

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
	
	int lineHeight = 0;
	int lineWidth =  0;
	
	//Image background;
	
	public EarthViewSimulationPanel() {
		
		setBackground(new Color(0, 0, 27));
		setPreferredSize(new Dimension(400, 500));
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		initElectrons = new ArrayList<EarthViewElectron>();
		allAltitudeElectrons = new ArrayList<EarthViewElectron>();
		allDrawnLines = new ArrayList<Line>();
		LinesCopy = new CopyOnWriteArrayList<Line>();
		
		dateTime = new Date();
		//background = Toolkit.getDefaultToolkit().createImage("IMG\\Background.jpg");
		
	}//end of EarthViewSimulationPanel constructor

	//@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(background, 0, 0, null);
		if(!LinesCopy.isEmpty()) {
			for(Line line: LinesCopy) {
				if(line.getxLineStart()>=0 || line.getxLineEnd()<=this.getWidth()) {
					line.draw((Graphics2D) g);
				}
			}
		}	
	}//end of paintComponent method

	public void createWaveEmission(int densityofAir, int oxygenFirst, int nitrogenFirst, int hydrogenFirst, int oxygenSecond, int nitrogenSecond,
			int hydrogenSecond, int oxygenThird, int nitrogenThird, int hydrogenThird) {
		waveEmissionFirstAlt = new EarthViewWaveEmission(densityofAir, oxygenFirst, nitrogenFirst, hydrogenFirst);
		waveEmissionSecondAlt = new EarthViewWaveEmission(densityofAir, oxygenSecond, nitrogenSecond, hydrogenSecond);
		waveEmissionThirdAlt = new EarthViewWaveEmission(densityofAir, oxygenThird, nitrogenThird, hydrogenThird);
	}//end of createWaveEmission method
	
	public void createElectrons(int electoronEnergySliderValue) {
		initElectrons.removeAll(initElectrons);
		for(int numberOfInitElectrons = 0; numberOfInitElectrons < 40; numberOfInitElectrons++) {
			EarthViewElectron newElectron = new EarthViewElectron(electoronEnergySliderValue, 2 + numberOfInitElectrons);
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
			calculateLineSize(allAltitudeElectrons.get(i).getyLocation());
			allAltitudeElectrons.get(i).setyLocation((allAltitudeElectrons.get(i).getyLocation()
						+ (int) (0.1*2.5*Math.pow(10, -4) * (allAltitudeElectrons.get(i).getVelocity() / 500))));
			
			allAltitudeElectrons.get(i).setxLocation((allAltitudeElectrons.get(i).getxLocation()
					+ (int) (0.25*0.2*2.5*Math.pow(10, -4) * (allAltitudeElectrons.get(i).getVelocity() / 500))));
			
			if(allAltitudeElectrons.get(i).getyLocation() > 400) {
				if (waveEmissionThirdAlt.emissionProbability(allAltitudeElectrons.get(i).getyLocation()) == true) {
					waveColor = waveEmissionThirdAlt.colorEmission(allAltitudeElectrons.get(i).getyLocation());
					velocityLoss = waveEmissionThirdAlt.LostVelocity(waveColor);
					ifToAdd = true;
				}
				
			}
			else if(allAltitudeElectrons.get(i).getyLocation() > 250) {
				if (waveEmissionSecondAlt.emissionProbability(allAltitudeElectrons.get(i).getyLocation()) == true) {
					waveColor = waveEmissionSecondAlt.colorEmission(allAltitudeElectrons.get(i).getyLocation());
					velocityLoss = waveEmissionSecondAlt.LostVelocity(waveColor);
					ifToAdd = true;
				}
			}
			
			else if (allAltitudeElectrons.get(i).getyLocation() <= 250){
				if (waveEmissionFirstAlt.emissionProbability(allAltitudeElectrons.get(i).getyLocation()) == true) {
					waveColor = waveEmissionFirstAlt.colorEmission(allAltitudeElectrons.get(i).getyLocation());
					velocityLoss = waveEmissionFirstAlt.LostVelocity(waveColor);
					ifToAdd = true;
				}
			}
			
			if(ifToAdd) {
				currentTime = dateTime.getTime();
				allAltitudeElectrons.get(i).setVelocity(allAltitudeElectrons.get(i).getVelocity() - velocityLoss);
				allDrawnLines.add(new Line(allAltitudeElectrons.get(i).getxLocation(),
						allAltitudeElectrons.get(i).getyLocation(), allAltitudeElectrons.get(i).getxLocation()+lineWidth,
						allAltitudeElectrons.get(i).getyLocation() + lineHeight, waveColor, currentTime));
				ifToAdd = false;
			}
		}//end of for
		
		allAltitudeElectrons.removeIf(allAltitudeElectrons -> allAltitudeElectrons.getyLocation() >= 500);	//absolute board is 250
		allAltitudeElectrons.removeIf(allAltitudeElectrons -> allAltitudeElectrons.getVelocity() <= 0);
		}
	}//end updatingAllElectrons
	
	public void calculateLineSize(int Height) {
		if(Height<100) {
			lineHeight = 1;
			lineWidth =1;
		}
		else if(Height<200) {
			lineHeight = 2;
			lineWidth = 1;
		}
		else if(Height<250) {
			lineHeight = 4;
			lineWidth = 2;
		}
		else if(Height<300) {
			lineHeight = 6;
			lineWidth = 3;
		}
		else if(Height<350) {
			lineHeight = 8;
			lineWidth = 4;
		}
	}//end calculateLineSize 
	
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