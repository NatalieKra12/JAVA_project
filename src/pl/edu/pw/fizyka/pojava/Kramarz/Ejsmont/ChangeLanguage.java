package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Natalia Kramarz
import java.util.ArrayList;

public class ChangeLanguage{
	ArrayList<String> namesOfElements;
	public ChangeLanguage() {
		namesOfElements = new ArrayList<String>();
		addInitNames();
	}//enf ChangeLanguage lister
	
	public void addInitNames() {
		namesOfElements.add("Symulacja zorzy polarnej");
		namesOfElements.add("Ustawienia ogólne");
		namesOfElements.add("Symulacja");
		namesOfElements.add("Energia elektronów");
		namesOfElements.add("Gęstość atmosfery na 100 km");
		namesOfElements.add("Wysokość 200-300km");
		namesOfElements.add("Wysokość 100-200km");
		namesOfElements.add("Wysokość 100km");
		namesOfElements.add("Tlen    ");
		namesOfElements.add("Azot    ");
		namesOfElements.add("Wodór");
		namesOfElements.add("START");
		namesOfElements.add("STOP");
		namesOfElements.add("RESTARTUJ");
		namesOfElements.add("ZAKOŃCZ");
		namesOfElements.add("WYBIERZ JĘZYK");
		namesOfElements.add("WIDOK-ZIEMIA");
	}//end addInitNames
	
	public void changeToEnglish() {
		namesOfElements.removeAll(namesOfElements);
		namesOfElements.add("Simulation of Aurora Borelalis");
		namesOfElements.add("General settings");
		namesOfElements.add("Simulation");
		namesOfElements.add("Energy of electrons");
		namesOfElements.add("Density of the atmosphere at 100km");
		namesOfElements.add("Altitude 200-300km");
		namesOfElements.add("Altitude 100-200km");
		namesOfElements.add("Altitude 100km");
		namesOfElements.add("Oxygen    ");
		namesOfElements.add("Nitrogen ");
		namesOfElements.add("Hydrogen");
		namesOfElements.add("START");
		namesOfElements.add("STOP");
		namesOfElements.add("RESTART");
		namesOfElements.add("EXIT");
		namesOfElements.add("CHOOSE LANGUAGE");
		namesOfElements.add("VIEW-EARTH");
	}//end changeToEnglish
	
	public void changeToPolish() {
		namesOfElements.removeAll(namesOfElements);
		namesOfElements.add("Symulacja zorzy polarnej");
		namesOfElements.add("Ustawienia ogólne");
		namesOfElements.add("Symulacja");
		namesOfElements.add("Energia elektronów");
		namesOfElements.add("Gęstość atmosfery na 100 km");
		namesOfElements.add("Wysokość 200-300km");
		namesOfElements.add("Wysokość 100-200km");
		namesOfElements.add("Wysokość 100km");
		namesOfElements.add("Tlen    ");
		namesOfElements.add("Azot    ");
		namesOfElements.add("Wodór");
		namesOfElements.add("START");
		namesOfElements.add("STOP");
		namesOfElements.add("RESTARTUJ");
		namesOfElements.add("ZAKOŃCZ");
		namesOfElements.add("WYBIERZ JĘZYK");
		namesOfElements.add("WIDOK-ZIEMIA");
	}//end changeToPolish
	
	public void changeToDeutsch() {
		namesOfElements.removeAll(namesOfElements);
		namesOfElements.add("Simulation von Polarlichtern");
		namesOfElements.add("Allgemeine Einstellungen");
		namesOfElements.add("Simulation");
		namesOfElements.add("Die Energie der Elektoronen");
		namesOfElements.add("Die atmosphärische Dichtel in einer Höhe von 100 km");
		namesOfElements.add("Die Höhe 200-300km");
		namesOfElements.add("Die Höhe 100-200km");
		namesOfElements.add("Die Höhe 100km");
		namesOfElements.add("Das Oxygen");
		namesOfElements.add("Das Nitrogen");
		namesOfElements.add("Das Hydrogen ");
		namesOfElements.add("DER START");
		namesOfElements.add("STOP");
		namesOfElements.add("DER NEUSTART");
		namesOfElements.add("BEENDEN");
		namesOfElements.add("DIE SPRACHE");
		namesOfElements.add("DER AUSBLICK-DIE ERDE");
	}//end changeToDeutsh
	
}//end ChangeLanguage class