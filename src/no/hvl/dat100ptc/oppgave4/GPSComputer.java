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

		// går gjennom speed-tabellen og finner største speed
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

		if (totalTime > 0) {
			average = totalDistance / totalTime;
		} else {
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

		double[][] metTable = { 
				{ 10.0, 4.0 }, // Hastigheten er 10 (mph), Met er 4 osv...
				{ 12.0, 6.0 }, 
				{ 14.0, 8.0 }, 
				{ 16.0, 10.0 }, 
				{ 20.0, 12.0 }, 
				{ Double.MAX_VALUE, 16.0 } // Når hastigheten er max, er Met 16
				};

		for (int i = 0; i < metTable.length; i++) {
			if (speedmph < metTable[i][0]) {
				met = metTable[i][1];
				break;
				
			}
		}

			double secsToHours = secs / 3600;

			kcal = met * weight * secsToHours;

			return kcal;
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		double totalDistance = totalDistance();
		double totalTime = totalTime();
		
		double speed = totalDistance/totalTime; // meter i sekundet
		
		double met = 0;
		double speedmph = speed * MS; // meter i sekundet * 2.23 = 1 mph

		double[][] metTable = { 
				{ 10.0, 4.0 }, // Hastigheten er 10(mph), Met er 4 osv...
				{ 12.0, 6.0 }, 
				{ 14.0, 8.0 }, 
				{ 16.0, 10.0 }, 
				{ 20.0, 12.0 }, 
				{ Double.MAX_VALUE, 16.0 } // Når hastigheten er max, er Met 16
				};

		for (int i = 0; i < metTable.length; i++) {
			if (speedmph < metTable[i][0]) {
				met = metTable[i][1];
				break;
				
			}
		}

			double secsToHours = totalTime / 3600;

			totalkcal = met * weight * secsToHours;

			return totalkcal;
	}
		
		

	private static double WEIGHT = 80.0;

	public void displayStatistics() {
		double totalTime = totalTime();
		double totalDistance = totalDistance();
		double totalElevation = totalElevation();
		double maxSpeed = maxSpeed();
		double totalkcal = totalKcal(WEIGHT);	
		
		
		int hours = (int) (totalTime / 3600);
		int minutes = (int) ((totalTime % 3600) / 60);
		int seconds = (int) (totalTime % 60);
		
		double averageSpeed = (totalDistance/1000) / (totalTime/3600); 
		
		
		System.out.println("==============================================");
		System.out.println("Total time: " + hours+":"+minutes+":"+seconds);
		System.out.println("Total distance: " + totalDistance);
		System.out.println("Total elevation: " + totalElevation);
		System.out.println("Max speed: " + maxSpeed);
		System.out.println("Average speed: " + averageSpeed);
		System.out.println("Energy: " + totalkcal);
		System.out.println("==============================================");
	}

}
