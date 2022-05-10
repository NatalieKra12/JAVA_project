package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;

import java.util.ArrayList;
import java.util.List;

public class ToMoveElectrons extends Thread {
	List<Electron> RegardedElectrons;
	
	public ToMoveElectrons(List<Electron> RegardedElectrons) {
		super();
		this.RegardedElectrons = new ArrayList<>();
		this.RegardedElectrons = new ArrayList<>(RegardedElectrons);
		System.out.println("OK");
		System.out.println(RegardedElectrons.size());
	}
	
	
	public void updatingElectron() {
			//int time = (int)((System.nanoTime() - regardedEl.getStartTime())*Math.pow(10, -9)); //s 
			//System.out.println(time);
			//regardedEl.setxLocation(regardedEl.getxLocation()+(int)regardedEl.getVelocity()*time);
		}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("Dziala");
		updatingElectron();
		System.out.println("OKI");
	}
	
	
	

}
