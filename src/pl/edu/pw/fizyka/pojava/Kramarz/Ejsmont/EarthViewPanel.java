package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Natalia Kramarz
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class EarthViewPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame earthViewFrame;
	Popup popUpWindow;
	PopupFactory popUpFactory;
	EarthViewSimulationPanel earthViewPanel;
	
	int xInit;
	int yInit;
	
	JButton exitButton;
	boolean activePopUp = false;
	
	public EarthViewPanel(int xInit, int yInit) {
		// TODO Auto-generated constructor stub
		this.xInit = xInit;
		this.yInit = yInit;
		
		activePopUp = true;
		earthViewFrame = new JFrame();
		popUpFactory = new PopupFactory();
		earthViewPanel = new EarthViewSimulationPanel();
		popUpWindow = popUpFactory.getPopup(earthViewFrame, earthViewPanel, xInit, yInit);
		
		exitButton = new JButton("EXIT");
		exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
		exitButton.setBackground(Color.white);
		earthViewPanel.add(exitButton);		
	}//end EarthViewPanel constructor
	
	public void showPopUp() {
		popUpWindow.show();
	}//end showPopUp
	
	public void hidePopUp() {
		activePopUp = false;
		popUpWindow.hide();
	}//end hidePopUp
}//end EarthViewPanel class
