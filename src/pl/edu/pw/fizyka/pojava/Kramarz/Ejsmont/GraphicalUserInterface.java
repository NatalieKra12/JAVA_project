package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Natalia Kramarz, Julia Ejsmont

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GraphicalUserInterface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

//Panels
	private JPanel buttonsPanel;
	private SimulationPanel simulationPanel;
	private JPanel slidersPanel;
	
	private JPanel energyPanel;
	private JPanel densityPanel;
	private JPanel firstAttitudeSection;
	private JPanel secondAttitudeSection;
	private JPanel thirdAttitudeSection;	
	
	private JPanel firstOxygenSection;
	private JPanel firstNitrogenSection;
	private JPanel firstHydrogenSection;
	private JPanel secondOxygenSection;
	private JPanel secondNitrogenSection;
	private JPanel secondHydrogenSection;
	private JPanel thirdOxygenSection;
	private JPanel thirdNitrogenSection;
	private JPanel thirdHydrogenSection;

	private JButton startSimulationButton;
	private JButton stopSimulationButton;
	private JButton restartButton;
	private JButton earthViewButton;
	private JButton exitButton;
	
	private JSlider electoronEnergySlider;
	private JSlider atmDensitySlider;
	
	private JSlider oxygenFirstAtSlider;
	private JSlider nitrogenFirstAtSlider;
	private JSlider hydrogenFirstAtSlider;
	private JSlider oxygenSecondAtSlider;
	private JSlider nitrogenSecondAtSlider;
	private JSlider hydrogenSecondAtSlider;
	private JSlider oxygenThirdAtSlider;
	private JSlider nitrogenThirdAtSlider;
	private JSlider hydrogenThirdAtSlider;
	
	private JLabel electoronEnergyLabel;
	private JLabel atmDensityLabel;
	private JLabel oxygenFirstAtLabel;
	private JLabel nitrogenFirstAtLabel;
	private JLabel hydrogenFirstAtLabel;
	private JLabel oxygenSecondAtLabel;
	private JLabel nitrogenSecondAtLabel;
	private JLabel hydrogenSecondAtLabel;
	private JLabel oxygenThirdAtLabel;
	private JLabel nitrogenThirdAtLabel;
	private JLabel hydrogenThirdAtLabel;
	
	private String choosenButtonId=null;
	
	private SliderGroup firstAtSliderGroup;
	private SliderGroup secondAtSliderGroup;
	private SliderGroup thirdAtSliderGroup;
	
	private JButton languageButton;
	private JPopupMenu languageMenu;
	private JMenuItem englishItem;
	private JMenuItem polishItem;
	private JMenuItem germanItem;
	
	private ChangeLanguage language;
	private TitledBorder titledBorderForButtonsPanel;
	private TitledBorder titledBorderForEnergyPanel;
	private TitledBorder titledBorderForDensityPanel;
	private TitledBorder titledBorderForFirstAlt;
	private TitledBorder titledBorderForSecondAlt;
	private TitledBorder titledBorderForThirdAlt;
	
	boolean simulationStopped = false;
	
	EarthViewPanel earthViewWindow;
	int frameXCorner = 0;
	int frameYCorner = 0;
	boolean popUpActive = false;
	boolean simulationPanelActive = false;
	
	
	public GraphicalUserInterface() throws HeadlessException{
		// TODO Auto-generated constructor stub
//language class
		language = new ChangeLanguage();
//userInterface settings	
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(600, 500);
		this.setTitle(language.namesOfElements.get(0));
		this.setLayout(new BorderLayout());	
//titledBorders settings
		titledBorderForButtonsPanel = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)), " ",TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241));
		titledBorderForEnergyPanel = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)), " ", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241));
		titledBorderForDensityPanel = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)), " ", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241));
		titledBorderForFirstAlt = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)), " ", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241));
		titledBorderForSecondAlt = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)), " ", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241));
		titledBorderForThirdAlt = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)), " ", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241));
		
//buttonsPanel settings
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(98, 101, 103));
		buttonsPanel.setBorder(titledBorderForButtonsPanel);
		titledBorderForButtonsPanel.setTitle(language.namesOfElements.get(1));
		this.add(buttonsPanel, BorderLayout.PAGE_START);
		
//simulationPanel settings
		simulationPanel = new SimulationPanel();
		this.add(simulationPanel, BorderLayout.CENTER);
				
//slidersPanel settings
		slidersPanel = new JPanel();
		slidersPanel.setBackground(new Color(98, 101, 103));
		slidersPanel.setLayout(new GridLayout(5,1));
		this.add(slidersPanel, BorderLayout.LINE_END);
		
//button in buttonsPanel settings
		startSimulationButton = new JButton(language.namesOfElements.get(11));
		startSimulationButton.setBorder(BorderFactory.createRaisedBevelBorder());
		startSimulationButton.setBackground(Color.white);
		startSimulationButton.setActionCommand("startSimulation");
		startSimulationButton.addActionListener(this);
		buttonsPanel.add(startSimulationButton);
		
		stopSimulationButton = new JButton(language.namesOfElements.get(12));
		stopSimulationButton.setBorder(BorderFactory.createRaisedBevelBorder());
		stopSimulationButton.setBackground(Color.white);
		stopSimulationButton.setActionCommand("stopSimulation");
		stopSimulationButton.addActionListener(this);
		buttonsPanel.add(stopSimulationButton);
		
		restartButton = new JButton(language.namesOfElements.get(13));
		restartButton.setBorder(BorderFactory.createRaisedBevelBorder());
		restartButton.setBackground(Color.white);
		restartButton.setActionCommand("restart");
		restartButton.addActionListener(this);
		buttonsPanel.add(restartButton);
		
		earthViewButton = new JButton(language.namesOfElements.get(16));
		earthViewButton.setBorder(BorderFactory.createRaisedBevelBorder());
		earthViewButton.setBackground(Color.white);
		earthViewButton.setActionCommand("earth view");
		earthViewButton.addActionListener(this);
		buttonsPanel.add(earthViewButton);
		
//panels in slidersPanel settings
		energyPanel = new JPanel();
		energyPanel.setLayout(new BorderLayout());
		energyPanel.setBackground(new Color(98, 101, 103));
		energyPanel.setBorder(titledBorderForEnergyPanel);
		titledBorderForEnergyPanel.setTitle(language.namesOfElements.get(3));
		slidersPanel.add(energyPanel);
		
		densityPanel = new JPanel();
		densityPanel.setLayout(new BorderLayout());
		densityPanel.setBackground(new Color(98, 101, 103));
		densityPanel.setBorder(titledBorderForDensityPanel);
		titledBorderForDensityPanel.setTitle(language.namesOfElements.get(4));
		slidersPanel.add(densityPanel);
		
		firstAttitudeSection = new JPanel();
		firstAttitudeSection.setBackground(new Color(98, 101, 103));
		firstAttitudeSection.setLayout(new GridLayout(3, 1));
		firstAttitudeSection.setBorder(titledBorderForFirstAlt);
		titledBorderForFirstAlt.setTitle(language.namesOfElements.get(5));
		slidersPanel.add(firstAttitudeSection);
		
		secondAttitudeSection = new JPanel();
		secondAttitudeSection.setBackground(new Color(98, 101, 103));
		secondAttitudeSection.setLayout(new GridLayout(3, 1));
		secondAttitudeSection.setBorder(titledBorderForSecondAlt);
		titledBorderForSecondAlt.setTitle(language.namesOfElements.get(6));
		slidersPanel.add(secondAttitudeSection);
		
		thirdAttitudeSection = new JPanel();
		thirdAttitudeSection.setBackground(new Color(98, 101, 103));
		thirdAttitudeSection.setLayout(new GridLayout(3, 1));
		thirdAttitudeSection.setBorder(titledBorderForThirdAlt);
		titledBorderForThirdAlt.setTitle(language.namesOfElements.get(7));
		slidersPanel.add(thirdAttitudeSection);
		
//panels in firstAttitudeSection settings
		firstOxygenSection = new JPanel();
		firstOxygenSection.setLayout(new BorderLayout());
		firstOxygenSection.setBackground(new Color(98, 101, 103));
		firstAttitudeSection.add(firstOxygenSection);
		
		firstNitrogenSection = new JPanel();
		firstNitrogenSection.setLayout(new BorderLayout());
		firstNitrogenSection.setBackground(new Color(98, 101, 103));
		firstAttitudeSection.add(firstNitrogenSection);
		
		firstHydrogenSection = new JPanel();
		firstHydrogenSection.setLayout(new BorderLayout());
		firstHydrogenSection.setBackground(new Color(98, 101, 103));
		firstAttitudeSection.add(firstHydrogenSection);
		
//panels in secondAttitudeSection settings
		secondOxygenSection = new JPanel();
		secondOxygenSection.setLayout(new BorderLayout());
		secondOxygenSection.setBackground(new Color(98, 101, 103));
		secondAttitudeSection.add(secondOxygenSection);
		
		secondNitrogenSection = new JPanel();
		secondNitrogenSection.setLayout(new BorderLayout());
		secondNitrogenSection.setBackground(new Color(98, 101, 103));
		secondAttitudeSection.add(secondNitrogenSection);
		
		secondHydrogenSection = new JPanel();
		secondHydrogenSection.setLayout(new BorderLayout());
		secondHydrogenSection.setBackground(new Color(98, 101, 103));
		secondAttitudeSection.add(secondHydrogenSection);

//panels in secondAttitudeSection settings		
		thirdOxygenSection = new JPanel();
		thirdOxygenSection.setLayout(new BorderLayout());
		thirdOxygenSection.setBackground(new Color(98, 101, 103));
		thirdAttitudeSection.add(thirdOxygenSection);
		
		thirdNitrogenSection = new JPanel();
		thirdNitrogenSection.setLayout(new BorderLayout());
		thirdNitrogenSection.setBackground(new Color(98, 101, 103));
		thirdAttitudeSection.add(thirdNitrogenSection);
		
		thirdHydrogenSection = new JPanel();
		thirdHydrogenSection.setLayout(new BorderLayout());
		thirdHydrogenSection.setBackground(new Color(98, 101, 103));
		thirdAttitudeSection.add(thirdHydrogenSection);	
		
//elements in energyPanel settings
		electoronEnergySlider = new JSlider(4, 12, 4);
		electoronEnergySlider.setBackground(new Color(98, 101, 103));
		electoronEnergySlider.addChangeListener(new EnergyAndDensityListener());
		energyPanel.add(electoronEnergySlider, BorderLayout.CENTER);
		
		electoronEnergyLabel = new JLabel(String.format("%d", electoronEnergySlider.getValue())+" keV");;
		energyPanel.add(electoronEnergyLabel, BorderLayout.LINE_END);
		
		atmDensitySlider = new JSlider(47, 57, 47); //10^-8kg/m^3
		atmDensitySlider.setBackground(new Color(98, 101, 103));
		atmDensitySlider.addChangeListener(new EnergyAndDensityListener());
		densityPanel.add(atmDensitySlider, BorderLayout.CENTER);
		
		atmDensityLabel = new JLabel(String.format("%d", atmDensitySlider.getValue())+" E-10 kg/m3");
		densityPanel.add(atmDensityLabel, BorderLayout.LINE_END);
		
//elements in firstAttitudeSection settings
		oxygenFirstAtLabel = new JLabel(language.namesOfElements.get(8));
		firstOxygenSection.add(oxygenFirstAtLabel, BorderLayout.LINE_START);
		
		oxygenFirstAtSlider = new JSlider(0, 100, 40);
		oxygenFirstAtSlider.setBackground(new Color(98, 101, 103));
		firstOxygenSection.add(oxygenFirstAtSlider, BorderLayout.CENTER);
		
		firstOxygenSection.add(createListeningLabel(oxygenFirstAtSlider), BorderLayout.LINE_END);
		
		nitrogenFirstAtLabel = new JLabel(language.namesOfElements.get(9));
		firstNitrogenSection.add(nitrogenFirstAtLabel, BorderLayout.LINE_START);
		
		nitrogenFirstAtSlider = new JSlider(0, 100, 30);
		nitrogenFirstAtSlider.setBackground(new Color(98, 101, 103));
		firstNitrogenSection.add(nitrogenFirstAtSlider, BorderLayout.CENTER);
		
		firstNitrogenSection.add(createListeningLabel(nitrogenFirstAtSlider), BorderLayout.LINE_END);
		
		hydrogenFirstAtLabel = new JLabel(language.namesOfElements.get(10));
		firstHydrogenSection.add(hydrogenFirstAtLabel, BorderLayout.LINE_START);
		
		hydrogenFirstAtSlider = new JSlider(0, 100, 30);
		hydrogenFirstAtSlider.setBackground(new Color(98, 101, 103));
		firstHydrogenSection.add(hydrogenFirstAtSlider, BorderLayout.CENTER);
		
		firstHydrogenSection.add(createListeningLabel(hydrogenFirstAtSlider), BorderLayout.LINE_END);
		
		firstAtSliderGroup = new SliderGroup();
		firstAtSliderGroup.add(oxygenFirstAtSlider);
		firstAtSliderGroup.add(nitrogenFirstAtSlider);
		firstAtSliderGroup.add(hydrogenFirstAtSlider);
		
//elements in secondAttitudeSection settings
		oxygenSecondAtLabel = new JLabel(language.namesOfElements.get(8));
		secondOxygenSection.add(oxygenSecondAtLabel, BorderLayout.LINE_START);
		
		oxygenSecondAtSlider = new JSlider(0, 100, 40);
		oxygenSecondAtSlider.setBackground(new Color(98, 101, 103));
		secondOxygenSection.add(oxygenSecondAtSlider, BorderLayout.CENTER);
		
		secondOxygenSection.add(createListeningLabel(oxygenSecondAtSlider), BorderLayout.LINE_END);
		
		nitrogenSecondAtLabel = new JLabel(language.namesOfElements.get(9));
		secondNitrogenSection.add(nitrogenSecondAtLabel, BorderLayout.LINE_START);
		
		nitrogenSecondAtSlider = new JSlider(0, 100, 30);
		nitrogenSecondAtSlider.setBackground(new Color(98, 101, 103));
		secondNitrogenSection.add(nitrogenSecondAtSlider, BorderLayout.CENTER);
		
		secondNitrogenSection.add(createListeningLabel(nitrogenSecondAtSlider), BorderLayout.LINE_END);
		
		hydrogenSecondAtLabel = new JLabel(language.namesOfElements.get(10));
		secondHydrogenSection.add(hydrogenSecondAtLabel, BorderLayout.LINE_START);
		
		hydrogenSecondAtSlider = new JSlider(0, 100, 30);
		hydrogenSecondAtSlider.setBackground(new Color(98, 101, 103));
		secondHydrogenSection.add(hydrogenSecondAtSlider, BorderLayout.CENTER);
		
		secondHydrogenSection.add(createListeningLabel(hydrogenSecondAtSlider), BorderLayout.LINE_END);
		
		secondAtSliderGroup = new SliderGroup();
		secondAtSliderGroup.add(oxygenSecondAtSlider);
		secondAtSliderGroup.add(nitrogenSecondAtSlider);
		secondAtSliderGroup.add(hydrogenSecondAtSlider);
		
//elements in thirdAttitudeSection settings
		oxygenThirdAtLabel = new JLabel(language.namesOfElements.get(8));
		thirdOxygenSection.add(oxygenThirdAtLabel, BorderLayout.LINE_START);
		
		oxygenThirdAtSlider = new JSlider(0, 100, 40);
		oxygenThirdAtSlider.setBackground(new Color(98, 101, 103));
		thirdOxygenSection.add(oxygenThirdAtSlider, BorderLayout.CENTER);
		
		thirdOxygenSection.add(createListeningLabel(oxygenThirdAtSlider), BorderLayout.LINE_END);
		
		nitrogenThirdAtLabel = new JLabel(language.namesOfElements.get(9));
		thirdNitrogenSection.add(nitrogenThirdAtLabel, BorderLayout.LINE_START);
		
		nitrogenThirdAtSlider = new JSlider(0, 100, 30);
		nitrogenThirdAtSlider.setBackground(new Color(98, 101, 103));
		thirdNitrogenSection.add(nitrogenThirdAtSlider, BorderLayout.CENTER);
		
		thirdNitrogenSection.add(createListeningLabel(nitrogenThirdAtSlider), BorderLayout.LINE_END);

		hydrogenThirdAtLabel = new JLabel(language.namesOfElements.get(10));
		thirdHydrogenSection.add(hydrogenThirdAtLabel, BorderLayout.LINE_START);
		
		hydrogenThirdAtSlider = new JSlider(0, 100, 30);
		hydrogenThirdAtSlider.setBackground(new Color(98, 101, 103));
		thirdHydrogenSection.add(hydrogenThirdAtSlider, BorderLayout.CENTER);
		
		thirdHydrogenSection.add(createListeningLabel(hydrogenThirdAtSlider), BorderLayout.LINE_END);
		
		thirdAtSliderGroup = new SliderGroup();
		thirdAtSliderGroup.add(hydrogenThirdAtSlider);
		thirdAtSliderGroup.add(oxygenThirdAtSlider);
		thirdAtSliderGroup.add(nitrogenThirdAtSlider);

//Pop up menu with language options
		languageButton = new JButton(language.namesOfElements.get(15));
		languageButton.setBorder(BorderFactory.createRaisedBevelBorder());
		languageButton.setBackground(Color.white);
		languageButton.addActionListener(this);
		languageButton.setActionCommand("Jezyk");
		buttonsPanel.add(languageButton);
		
		languageMenu = new JPopupMenu("Wybierz Język");
		
		englishItem = new JMenuItem("English");
		englishItem.addActionListener(this);
		englishItem.setActionCommand("ENGLISH");
		
		polishItem = new JMenuItem("Polski");
		polishItem.addActionListener(this);
		polishItem.setActionCommand("POLSKI");
		
		germanItem = new JMenuItem("Deutsh");
		germanItem.addActionListener(this);
		germanItem.setActionCommand("GERMAN");
		
		languageMenu.add(englishItem);
		languageMenu.add(new JSeparator());
		languageMenu.add(polishItem);
		languageMenu.add(new JSeparator());
		languageMenu.add(germanItem);
		
//exit Button settings
		exitButton = new JButton(language.namesOfElements.get(14));
		exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
		exitButton.setBackground(Color.white);
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(this);
		buttonsPanel.add(exitButton);
		
		
	}//end class constructor
	
	private void setPreferredSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}//end of setPrefferedSize class

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		choosenButtonId = arg0.getActionCommand();
		if(choosenButtonId.equals("startSimulation")) {
//sliders inactive after START
			electoronEnergySlider.setEnabled(false);
			atmDensitySlider.setEnabled(false);
			oxygenFirstAtSlider.setEnabled(false);
			nitrogenFirstAtSlider.setEnabled(false);
			hydrogenFirstAtSlider.setEnabled(false);
			oxygenSecondAtSlider.setEnabled(false);
			nitrogenSecondAtSlider.setEnabled(false);
			hydrogenSecondAtSlider.setEnabled(false);
			oxygenThirdAtSlider.setEnabled(false);
			nitrogenThirdAtSlider.setEnabled(false);
			hydrogenThirdAtSlider.setEnabled(false);
			
//transmitting set values to start simulation 
			simulationPanel.createWaveEmission(atmDensitySlider.getValue(), oxygenFirstAtSlider.getValue(), nitrogenFirstAtSlider.getValue(), hydrogenFirstAtSlider.getValue() ,
					oxygenSecondAtSlider.getValue(), nitrogenSecondAtSlider.getValue(), hydrogenSecondAtSlider.getValue(), oxygenThirdAtSlider.getValue(),
					nitrogenThirdAtSlider.getValue(), hydrogenThirdAtSlider.getValue());
			
			simulationPanel.startElectronsMove(electoronEnergySlider.getValue());
			simulationPanelActive = true;
		}//end if start simulation
		
		else if(choosenButtonId.equals("stopSimulation")) {
//sliders active after STOP
			electoronEnergySlider.setEnabled(true);
			atmDensitySlider.setEnabled(true);
			oxygenFirstAtSlider.setEnabled(true);
			nitrogenFirstAtSlider.setEnabled(true);
			hydrogenFirstAtSlider.setEnabled(true);
			oxygenSecondAtSlider.setEnabled(true);
			nitrogenSecondAtSlider.setEnabled(true);
			hydrogenSecondAtSlider.setEnabled(true);
			oxygenThirdAtSlider.setEnabled(true);
			nitrogenThirdAtSlider.setEnabled(true);
			hydrogenThirdAtSlider.setEnabled(true);
			
			simulationPanelActive = false;
			
//making simulation stop	
			try {
				simulationPanel.timerStop();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end if stop simulation
		
		else if (choosenButtonId.equals("restart")) {
			
//sliders active after RESTART
			electoronEnergySlider.setEnabled(true);
			atmDensitySlider.setEnabled(true);
			oxygenFirstAtSlider.setEnabled(true);
			nitrogenFirstAtSlider.setEnabled(true);
			hydrogenFirstAtSlider.setEnabled(true);
			oxygenSecondAtSlider.setEnabled(true);
			nitrogenSecondAtSlider.setEnabled(true);
			hydrogenSecondAtSlider.setEnabled(true);
			oxygenThirdAtSlider.setEnabled(true);
			nitrogenThirdAtSlider.setEnabled(true);
			hydrogenThirdAtSlider.setEnabled(true);
			
//going back to inital values			
			electoronEnergySlider.setValue(4);
			atmDensitySlider.setValue(47);
			oxygenFirstAtSlider.setValue(40);
			oxygenFirstAtSlider.setEnabled(false);
			nitrogenFirstAtSlider.setValue(30);
			nitrogenFirstAtSlider.setEnabled(false);
			hydrogenFirstAtSlider.setValue(30);
			
			oxygenFirstAtSlider.setEnabled(true);
			nitrogenFirstAtSlider.setEnabled(true);
			
			oxygenSecondAtSlider.setValue(40);
			oxygenSecondAtSlider.setEnabled(false);
			nitrogenSecondAtSlider.setValue(30);
			nitrogenSecondAtSlider.setEnabled(false);
			hydrogenSecondAtSlider.setValue(30);
			
			oxygenSecondAtSlider.setEnabled(true);
			nitrogenSecondAtSlider.setEnabled(true);			
			
			oxygenThirdAtSlider.setValue(40);
			oxygenThirdAtSlider.setEnabled(false);
			nitrogenThirdAtSlider.setValue(30);
			nitrogenThirdAtSlider.setEnabled(false);
			hydrogenThirdAtSlider.setValue(30);
			
			oxygenThirdAtSlider.setEnabled(true);
			nitrogenThirdAtSlider.setEnabled(true);

//removing lists and cancenling timers			
			try {
				simulationPanel.clearAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			simulationPanelActive = false;
			
		}//end if stop simulation
		
		else if (choosenButtonId.equals("exit")) {

//removing lists and cancenling timers
			try {
				simulationPanel.clearAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.exit(0);
			
		}//end if exit simulation
		
		else if(choosenButtonId.equals("Jezyk")) {
			showPopup(arg0);
		}
		
		else if(choosenButtonId.equals("ENGLISH")) {
			//System.out.println("TU");
			language.changeToEnglish();
			changeNames();
		}
		
		else if(choosenButtonId.equals("POLSKI")) {
			//System.out.println("TU");
			language.changeToPolish();
			changeNames();
		}
		
		else if(choosenButtonId.equals("GERMAN")) {
			//System.out.println("TU");
			language.changeToDeutsch();
			changeNames();
		}//end language choice ifs
		else if(choosenButtonId.equals("earth view")) {
			earthViewWindow = new EarthViewPanel(this.getX(), this.getY());
			popUpActive = true;
			try {
				if(simulationPanelActive) {
					simulationPanel.timerStop();
				}
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			earthViewWindow.exitButton.setText(language.namesOfElements.get(14));
			earthViewWindow.exitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						earthViewWindow.earthViewPanel.clearAll();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					earthViewWindow.hidePopUp();
					popUpActive = false;
				}
			});
			earthViewWindow.earthViewPanel.createWaveEmission(atmDensitySlider.getValue(), oxygenFirstAtSlider.getValue(), nitrogenFirstAtSlider.getValue(), hydrogenFirstAtSlider.getValue() ,
					oxygenSecondAtSlider.getValue(), nitrogenSecondAtSlider.getValue(), hydrogenSecondAtSlider.getValue(), oxygenThirdAtSlider.getValue(),
					nitrogenThirdAtSlider.getValue(), hydrogenThirdAtSlider.getValue());
			
			earthViewWindow.showPopUp();
			earthViewWindow.earthViewPanel.startElectronsMove(electoronEnergySlider.getValue());
		}
	}//end actionPerformed
	
	public class EnergyAndDensityListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e){
			// TODO Auto-generated method stub
			if(e.getSource().equals(electoronEnergySlider)){
			String value = String.format("%d", 	electoronEnergySlider.getValue());
			electoronEnergyLabel.setText(value+" keV");
			}//end if
			else{
				String value = String.format("%d", 	atmDensitySlider.getValue());
				atmDensityLabel.setText(value+" E-10 kg/m3");
			}// end else - e.getSource().equals(atmDensitySlider)
		}//end stateChanged
	}//end EnergyAndDensityListener
	
  private static JLabel createListeningLabel(final JSlider slider) {
  	final JLabel label = new JLabel(slider.getValue()+"%  ");
      slider.addChangeListener(new ChangeListener(){
          @Override
          public void stateChanged(ChangeEvent e)
          {
          	if(slider.getValue()>=10 && slider.getValue()!=100)
              label.setText(String.valueOf(" "+slider.getValue())+"%");
          	
          	else if(slider.getValue()==100) {
          		label.setText(String.valueOf(slider.getValue())+"%");
          	}
          	
          	else {
          		label.setText(String.valueOf("   "+slider.getValue())+"%");
          	}
          }
      }//end ChangeListener
      );//end addChangeListener
      return label;
  }//end createListeningLabel
  
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GraphicalUserInterface userInterface = new GraphicalUserInterface();
				userInterface.pack();
				userInterface.setVisible(true);
			}
		});
	}//end main
	
  private void showPopup(ActionEvent ae) {
      Component componentToShowMenu = (Component)ae.getSource();
      Point componentPoint = componentToShowMenu.getLocationOnScreen();
      languageMenu.show(this,0,0);
      languageMenu.setLocation(componentPoint.x, componentPoint.y+componentToShowMenu.getHeight());
  }//end showPopup
  
  public void changeNames() {
  	this.setTitle(language.namesOfElements.get(0));
  	titledBorderForButtonsPanel.setTitle(language.namesOfElements.get(1));
  	simulationPanel.BorderforSimulationPanel.setTitle(language.namesOfElements.get(2));
  	titledBorderForEnergyPanel.setTitle(language.namesOfElements.get(3));
  	titledBorderForDensityPanel.setTitle(language.namesOfElements.get(4));
  	titledBorderForFirstAlt.setTitle(language.namesOfElements.get(5));
  	titledBorderForSecondAlt.setTitle(language.namesOfElements.get(6));
  	titledBorderForThirdAlt.setTitle(language.namesOfElements.get(7));
  	oxygenFirstAtLabel.setText(language.namesOfElements.get(8));
  	nitrogenFirstAtLabel.setText(language.namesOfElements.get(9));
  	hydrogenFirstAtLabel.setText(language.namesOfElements.get(10));
  	oxygenSecondAtLabel.setText(language.namesOfElements.get(8));
  	nitrogenSecondAtLabel.setText(language.namesOfElements.get(9));
  	hydrogenSecondAtLabel.setText(language.namesOfElements.get(10));
  	oxygenThirdAtLabel.setText(language.namesOfElements.get(8));
  	nitrogenThirdAtLabel.setText(language.namesOfElements.get(9));
  	hydrogenThirdAtLabel.setText(language.namesOfElements.get(10));    	
  	startSimulationButton.setText(language.namesOfElements.get(11));
  	stopSimulationButton.setText(language.namesOfElements.get(12));
  	restartButton.setText(language.namesOfElements.get(13));
  	exitButton.setText(language.namesOfElements.get(14));
  	languageButton.setText(language.namesOfElements.get(15));
  	earthViewButton.setText(language.namesOfElements.get(16));
  }//end changeNames
}//end GraphicalUserInterface