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

		for (int i = 0; i < gpspoints.length - 1; i++) {

			double avstand = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);

			totalDistance += avstand;
		}
		return totalDistance;

	}

	public double totalElevation() {

		double totalElevation = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double elevationDifference = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			if (elevationDifference > 0) {
				totalElevation += elevationDifference;
			}
		}
		return totalElevation;
	}

	public int totalTime() {

		int tidBrukt = 0;
		if (gpspoints == null || gpspoints.length < 2) {
			return 0;
		}
		for (int i = 0; i < gpspoints.length - 1; i++) {
			int sekunder = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			tidBrukt += sekunder;
		}
		return tidBrukt;
	}

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length - 1];

		for (int i = 0; i < gpspoints.length - 1; i++) {
			// Regn ut avstand mellom punkit i og i+1
			double avstand = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			
			// Regn ut tidsforskjellen mellom i+1 og i (antall sekunder)
			double sekunder = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			// kan ikke dele på 0
			if (sekunder > 0) {
				speeds[i] = avstand / sekunder;
				// Hvis det deles på null (0 sekunder) så blir gjennomsnittsfarten 0
			} else {
				speeds[i] = 0;
			}
		}
		return speeds;

	}

	public double maxSpeed() {

		double maxSpeed = 0;
		double speeds[] = speeds();

		// går gjennom speed-tabellen of finner største speed
		for (double speed : speeds) {
			if (speed > maxSpeed) {
				maxSpeed = speed;
			}
		}
		return maxSpeed;
	}

	public double averageSpeed() {

		double average = 0;
		double totalDistance = totalDistance();
		double totalTime = totalTime();
		
		if(totalTime > 0) {
			average = totalDistance/totalTime;
		}else {
			average = 0;
			
		}
		
		return average;
			
	
	
	
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
