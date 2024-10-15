package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {
		GPSPoint gpsPoint = new GPSPoint(1,2.0,3.0,5);
		
		System.out.println("Tidspunkt: " + gpsPoint.getTime()); 
		
		gpsPoint.setTime(2);
		
		System.out.println("Oppdatert tidspunkt: " + gpsPoint.toString());
		
	}
}
