package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double totalDistance = 0;
		
		for(int i = 0; i < gpspoints.length - 1; i++) {
			GPSPoint p1 = gpspoints[i];
			GPSPoint p2 = gpspoints[i+1];
			
			
			double lat1 = p1.getLatitude();
			double lon1 = p1.getLongitude();
			double lat2 = p2.getLatitude();
			double lon2 = p2.getLongitude();
		

		double avstand = GPSUtils.distance(lat1, lon1, lat2, lon2);
		
		totalDistance += avstand;
		}
		return totalDistance;
		
	}
	

	public double totalElevation() {

		double totalElevation = 0;
		for(int i = 0; i < gpspoints.length - 1; i++) {
			double elevationDifference = gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
		if (elevationDifference > 0){
				totalElevation += elevationDifference;
			}}
			return totalElevation;
		}
	
		
	

	public int totalTime() {
			
		int tidBrukt = 0;
		
		GPSUtils.formatTime(5);
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		// TODO
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO 
		throw new UnsupportedOperationException(TODO.method());
	
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO
		throw new UnsupportedOperationException(TODO.method());
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

}
