package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Natalia Kramarz, Julia Ejsmont

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GraphicalUserInterface extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel buttonsPanel;
	private SimulationPanel simulationPanel;
	private JPanel slidersPanel;
	
	private JPanel energyPanel;
	private JPanel densityPanel;
	private JPanel firstAttitudeSection;
	private JPanel secondAttitudeSection;
	private JPanel thirdAttitudeSection;
	//to delete after simulation is ready
	//private JLabel simulationInfo;

	private JButton startSimulationButton;
	private JButton stopSimulationButton;
	private JButton restartButton;
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
	
	private GridBagConstraints firstAttitudeConstraints;
	private GridBagConstraints secondAttitudeConstraints;
	private GridBagConstraints thirdAttitudeConstraints;
	
	public GraphicalUserInterface() throws HeadlessException{
		// TODO Auto-generated constructor stub
		//userInterface settings
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 800));
		this.setSize(600,700);
		this.setTitle("Symulacja zorzy polarnej");
		this.setLayout(new BorderLayout());	
		
		//buttonsPanel settings
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(98, 101, 103));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Ustawienia ogólne", TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));
		//buttonsPanel.setPreferredSize(new Dimension(600, 40));
		this.add(buttonsPanel, BorderLayout.PAGE_START);
		
		//simulationPanel settings
		simulationPanel = new SimulationPanel();
		/*simulationPanel.setBackground(new Color(28, 40, 51));
		simulationPanel.setPreferredSize(new Dimension(280, 700));
		simulationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Wynik symulacji" ,TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));
		simulationPanel.setLayout(new BorderLayout()); //to delete after simulation is ready*/ 
		//removed_phase 2
		this.add(simulationPanel, BorderLayout.CENTER);
		
		
		//slidersPanel settings
		slidersPanel = new JPanel();
		slidersPanel.setBackground(new Color(98, 101, 103));
		slidersPanel.setPreferredSize(new Dimension(320,700));
		slidersPanel.setLayout(new GridLayout(5,1));
		this.add(slidersPanel, BorderLayout.LINE_END);
		
		//to delete after simulation is ready
		//simulationInfo = new JLabel("Tu pojawi sie wynik symulacji");
		//simulationInfo.setForeground(new Color(212, 230, 241));
		//simulationPanel.add(simulationInfo);
		
		//button in buttonsPanel settings
		startSimulationButton = new JButton("START");
		startSimulationButton.setPreferredSize(new Dimension(70, 20));
		startSimulationButton.setBorder(BorderFactory.createRaisedBevelBorder());
		startSimulationButton.setBackground(Color.white);
		startSimulationButton.setActionCommand("startSimulation");
		startSimulationButton.addActionListener(this);
		buttonsPanel.add(startSimulationButton);
		
		stopSimulationButton = new JButton("STOP");
		stopSimulationButton.setPreferredSize(new Dimension(70, 20));
		stopSimulationButton.setBorder(BorderFactory.createRaisedBevelBorder());
		stopSimulationButton.setBackground(Color.white);
		stopSimulationButton.setActionCommand("stopSimulation");
		stopSimulationButton.addActionListener(this);
		buttonsPanel.add(stopSimulationButton);
		
		restartButton = new JButton("RESTART");
		restartButton.setPreferredSize(new Dimension(70, 20));
		restartButton.setBorder(BorderFactory.createRaisedBevelBorder());
		restartButton.setBackground(Color.white);
		restartButton.setActionCommand("restart");
		restartButton.addActionListener(this);
		buttonsPanel.add(restartButton);
		
		
		exitButton = new JButton("EXIT");
		exitButton.setPreferredSize(new Dimension(70, 20));
		exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
		exitButton.setBackground(Color.white);
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(this);
		buttonsPanel.add(exitButton);
		
		//panels in slidersPanel settings
		energyPanel = new JPanel();
		energyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 35));
		energyPanel.setBackground(new Color(98, 101, 103));
		energyPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Energia Elektronów" ,TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));
		slidersPanel.add(energyPanel);
		
		densityPanel = new JPanel();
		densityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 35));
		densityPanel.setBackground(new Color(98, 101, 103));
		densityPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Gęstość powietrza na 100km" ,TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));
		slidersPanel.add(densityPanel);
		
		firstAttitudeSection = new JPanel();
		firstAttitudeSection.setBackground(new Color(98, 101, 103));
		firstAttitudeSection.setLayout(new GridBagLayout());
		firstAttitudeSection.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Wysokość 200-300km" ,TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));
		slidersPanel.add(firstAttitudeSection);
		
		secondAttitudeSection = new JPanel();
		secondAttitudeSection.setBackground(new Color(98, 101, 103));
		secondAttitudeSection.setLayout(new GridBagLayout());
		secondAttitudeSection.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Wysokość 100-200km" ,TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));
		slidersPanel.add(secondAttitudeSection);
		
		thirdAttitudeSection = new JPanel();
		thirdAttitudeSection.setBackground(new Color(98, 101, 103));
		thirdAttitudeSection.setLayout(new GridBagLayout());
		thirdAttitudeSection.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(212, 230, 241)),
		"Wysokość 100km",TitledBorder.LEFT, TitledBorder.TOP, getFont(), new Color(212, 230, 241)));
		slidersPanel.add(thirdAttitudeSection);
		
		//elements in energyPanel settings
		electoronEnergySlider = new JSlider(4, 12, 4);
		electoronEnergySlider.setBackground(new Color(98, 101, 103));
		electoronEnergySlider.addChangeListener(new EnergyAndDensityListener());
		energyPanel.add(electoronEnergySlider);
		
		electoronEnergyLabel = new JLabel(String.format("%d", electoronEnergySlider.getValue())+" keV");;
		energyPanel.add(electoronEnergyLabel);
		
		atmDensitySlider = new JSlider(47, 57, 47); //10^-8kg/m^3
		atmDensitySlider.setBackground(new Color(98, 101, 103));
		atmDensitySlider.addChangeListener(new EnergyAndDensityListener());
		densityPanel.add(atmDensitySlider);
		atmDensityLabel = new JLabel(String.format("%d", atmDensitySlider.getValue())+" E-8 kg/m3");
		densityPanel.add(atmDensityLabel);
	
		
		//elements in firstAttitudeSection settings
		firstAttitudeConstraints = new GridBagConstraints();
		firstAttitudeConstraints.fill = GridBagConstraints.BOTH;
		
		oxygenFirstAtLabel = new JLabel("Tlen");
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.5;
		firstAttitudeConstraints.gridx = 0;
		firstAttitudeConstraints.gridy = 0;
		firstAttitudeSection.add(oxygenFirstAtLabel, firstAttitudeConstraints);
		
		oxygenFirstAtSlider = new JSlider(0, 100, 40);
		oxygenFirstAtSlider.setBackground(new Color(98, 101, 103));
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.9;
		firstAttitudeConstraints.gridx = 1;
		firstAttitudeConstraints.gridy = 0;
		firstAttitudeSection.add(oxygenFirstAtSlider, firstAttitudeConstraints);
		
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.5;
		firstAttitudeConstraints.gridx = 2;
		firstAttitudeConstraints.gridy = 0;
		firstAttitudeSection.add(createListeningLabel(oxygenFirstAtSlider), firstAttitudeConstraints);
		
		nitrogenFirstAtLabel = new JLabel("Azot");
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.5;
		firstAttitudeConstraints.gridx = 0;
		firstAttitudeConstraints.gridy = 1;
		firstAttitudeSection.add(nitrogenFirstAtLabel, firstAttitudeConstraints);
		
		nitrogenFirstAtSlider = new JSlider(0, 100, 30);
		nitrogenFirstAtSlider.setBackground(new Color(98, 101, 103));
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.9;
		firstAttitudeConstraints.gridx = 1;
		firstAttitudeConstraints.gridy = 1;
		
		firstAttitudeSection.add(nitrogenFirstAtSlider, firstAttitudeConstraints);
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.5;
		firstAttitudeConstraints.gridx = 2;
		firstAttitudeConstraints.gridy = 1;
		firstAttitudeSection.add(createListeningLabel(nitrogenFirstAtSlider), firstAttitudeConstraints);
		
		hydrogenFirstAtLabel = new JLabel("Wodór");
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.5;
		firstAttitudeConstraints.gridx = 0;
		firstAttitudeConstraints.gridy = 2;
		firstAttitudeSection.add(hydrogenFirstAtLabel, firstAttitudeConstraints);
		
		hydrogenFirstAtSlider = new JSlider(0, 100, 30);
		hydrogenFirstAtSlider.setBackground(new Color(98, 101, 103));
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.9;
		firstAttitudeConstraints.gridx = 1;
		firstAttitudeConstraints.gridy = 2;
		
		firstAttitudeSection.add(hydrogenFirstAtSlider, firstAttitudeConstraints);
		firstAttitudeConstraints.ipady = 10;
		firstAttitudeConstraints.weightx = 0.5;
		firstAttitudeConstraints.gridx = 2;
		firstAttitudeConstraints.gridy = 2;
		firstAttitudeSection.add(createListeningLabel(hydrogenFirstAtSlider), firstAttitudeConstraints);
		
		firstAtSliderGroup = new SliderGroup();
		firstAtSliderGroup.add(oxygenFirstAtSlider);
		firstAtSliderGroup.add(nitrogenFirstAtSlider);
		firstAtSliderGroup.add(hydrogenFirstAtSlider);
		
		//elements in secondAttitudeSection settings
		secondAttitudeConstraints = new GridBagConstraints();
		secondAttitudeConstraints.fill = GridBagConstraints.BOTH;
		
		oxygenSecondAtLabel = new JLabel("Tlen");
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 0;
		secondAttitudeConstraints.gridy = 0;
		secondAttitudeSection.add(oxygenSecondAtLabel, secondAttitudeConstraints);
		
		oxygenSecondAtSlider = new JSlider(0, 100, 40);
		oxygenSecondAtSlider.setBackground(new Color(98, 101, 103));
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.9;
		secondAttitudeConstraints.gridx = 1;
		secondAttitudeConstraints.gridy = 0;
		secondAttitudeSection.add(oxygenSecondAtSlider, secondAttitudeConstraints);
		
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 2;
		secondAttitudeConstraints.gridy = 0;
		secondAttitudeSection.add(createListeningLabel(oxygenSecondAtSlider), secondAttitudeConstraints);
		
		nitrogenSecondAtLabel = new JLabel("Azot");
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 0;
		secondAttitudeConstraints.gridy = 1;
		secondAttitudeSection.add(nitrogenSecondAtLabel, secondAttitudeConstraints);
		
		nitrogenSecondAtSlider = new JSlider(0, 100, 30);
		nitrogenSecondAtSlider.setBackground(new Color(98, 101, 103));
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 1;
		secondAttitudeConstraints.gridy = 1;
		secondAttitudeSection.add(nitrogenSecondAtSlider, secondAttitudeConstraints);
		
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 2;
		secondAttitudeConstraints.gridy = 1;
		secondAttitudeSection.add(createListeningLabel(nitrogenSecondAtSlider), secondAttitudeConstraints);
		
		hydrogenSecondAtLabel = new JLabel("Wodór");
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 0;
		secondAttitudeConstraints.gridy = 2;
		secondAttitudeSection.add(hydrogenSecondAtLabel, secondAttitudeConstraints);
		
		hydrogenSecondAtSlider = new JSlider(0, 100, 30);
		hydrogenSecondAtSlider.setBackground(new Color(98, 101, 103));
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 1;
		secondAttitudeConstraints.gridy = 2;
		secondAttitudeSection.add(hydrogenSecondAtSlider, secondAttitudeConstraints);
		
		secondAttitudeConstraints.ipady = 10;
		secondAttitudeConstraints.weightx = 0.5;
		secondAttitudeConstraints.gridx = 2;
		secondAttitudeConstraints.gridy = 2;
		secondAttitudeSection.add(createListeningLabel(hydrogenSecondAtSlider), secondAttitudeConstraints);
		
		secondAtSliderGroup = new SliderGroup();
		secondAtSliderGroup.add(oxygenSecondAtSlider);
		secondAtSliderGroup.add(nitrogenSecondAtSlider);
		secondAtSliderGroup.add(hydrogenSecondAtSlider);
		
		//elements thirdAttitudeSection settings
		thirdAttitudeConstraints = new GridBagConstraints();
		thirdAttitudeConstraints.fill = GridBagConstraints.BOTH;
		
		oxygenThirdAtLabel = new JLabel("Tlen");
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.5;
		thirdAttitudeConstraints.gridx = 0;
		thirdAttitudeConstraints.gridy = 0;
		thirdAttitudeSection.add(oxygenThirdAtLabel, thirdAttitudeConstraints);
		
		oxygenThirdAtSlider = new JSlider(0, 100, 30);
		oxygenThirdAtSlider.setBackground(new Color(98, 101, 103));
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.9;
		thirdAttitudeConstraints.gridx = 1;
		thirdAttitudeConstraints.gridy = 0;
		thirdAttitudeSection.add(oxygenThirdAtSlider, thirdAttitudeConstraints);
		
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.5;
		thirdAttitudeConstraints.gridx = 2;
		thirdAttitudeConstraints.gridy = 0;
		thirdAttitudeSection.add(createListeningLabel(oxygenThirdAtSlider), thirdAttitudeConstraints);
		
		
		nitrogenThirdAtLabel = new JLabel("Azot");
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.5;
		thirdAttitudeConstraints.gridx = 0;
		thirdAttitudeConstraints.gridy = 1;
		thirdAttitudeSection.add(nitrogenThirdAtLabel, thirdAttitudeConstraints);
		
		nitrogenThirdAtSlider = new JSlider(0, 100, 40);
		nitrogenThirdAtSlider.setBackground(new Color(98, 101, 103));
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.9;
		thirdAttitudeConstraints.gridx = 1;
		thirdAttitudeConstraints.gridy = 1;
		thirdAttitudeSection.add(nitrogenThirdAtSlider, thirdAttitudeConstraints);
		
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.5;
		thirdAttitudeConstraints.gridx = 2;
		thirdAttitudeConstraints.gridy = 1;
		thirdAttitudeSection.add(createListeningLabel(nitrogenThirdAtSlider), thirdAttitudeConstraints);

		hydrogenThirdAtLabel = new JLabel("Wodór");
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.5;
		thirdAttitudeConstraints.gridx = 0;
		thirdAttitudeConstraints.gridy = 2;
		thirdAttitudeSection.add(hydrogenThirdAtLabel, thirdAttitudeConstraints);
		
		hydrogenThirdAtSlider = new JSlider(0, 100, 30);
		hydrogenThirdAtSlider.setBackground(new Color(98, 101, 103));
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.9;
		thirdAttitudeConstraints.gridx = 1;
		thirdAttitudeConstraints.gridy = 2;
		thirdAttitudeSection.add(hydrogenThirdAtSlider, thirdAttitudeConstraints);
		
		thirdAttitudeConstraints.ipady = 10;
		thirdAttitudeConstraints.weightx = 0.5;
		thirdAttitudeConstraints.gridx = 2;
		thirdAttitudeConstraints.gridy = 2;
		thirdAttitudeSection.add(createListeningLabel(hydrogenThirdAtSlider), thirdAttitudeConstraints);
		
		thirdAtSliderGroup = new SliderGroup();
		thirdAtSliderGroup.add(hydrogenThirdAtSlider);
		thirdAtSliderGroup.add(oxygenThirdAtSlider);
		thirdAtSliderGroup.add(nitrogenThirdAtSlider);
		
		
	}//end class constructor
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		choosenButtonId = arg0.getActionCommand();
		if(choosenButtonId.equals("startSimulation")){
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
			simulationPanel.createInitElectrons(electoronEnergySlider.getValue());
			
		}//end if_starting simulation
		else if(choosenButtonId.equals("stopSimulation")){
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
			
			simulationPanel.clearAll();
			
		}//end else if
		else if (choosenButtonId.equals("restart")) {
			
			//going back to initial settings
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
			
			electoronEnergySlider.setValue(4);
			atmDensitySlider.setValue(170);
			oxygenFirstAtSlider.setValue(50);;
			nitrogenFirstAtSlider.setValue(50);
			oxygenSecondAtSlider.setValue(50);
			nitrogenSecondAtSlider.setValue(50);
			oxygenThirdAtSlider.setValue(30);
			nitrogenThirdAtSlider.setValue(40);
			hydrogenThirdAtSlider.setValue(30);			
			
			simulationPanel.clearAll();
			
		}//end else if
		else {
			simulationPanel.clearAll();
			System.exit(0);
			
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
	
    private static JLabel createListeningLabel(final JSlider slider){
    	final JLabel label = new JLabel("");
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e)
            {
                label.setText(String.valueOf(slider.getValue())+" %");
            }
        }//end ChangeListener
        );//end addChangeListener
        return label;
    }//end createListeningLabel
    
    
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GraphicalUserInterface userInterface = new GraphicalUserInterface();
				userInterface.setVisible(true);
			}
		});
	}//end main

}//end GraphicalUserInterface