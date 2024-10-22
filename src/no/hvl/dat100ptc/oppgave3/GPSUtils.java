package no.hvl.dat100ptc.oppgave3;

import java.util.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
//import no.hvl.dat100ptc.TODO;

public class GPSUtils {
//max tall i tabellen
	public static double findMax(double[] da) {

		double max;

		max = da[0];
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

//Minste tall i tabellen 
	public static double findMin(double[] da) {

		double min;

		min = da[0];
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;
	}

//		Oppgave b 1 breddegradene
	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();

		}
		return latitudes;

	}

//Oppgave c lengdegrader
	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}

		return longitudes;
	}

//	oppgave d
	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double latitude1 = gpspoint1.getLatitude();
		double longitude1 = gpspoint1.getLongitude();
		double latitude2 = gpspoint2.getLatitude();
		double longitude2 = gpspoint2.getLongitude();

		double latitude1Rad = Math.toRadians(latitude1);
		double latitude2Rad = Math.toRadians(latitude2);
		double deltaPhi = Math.toRadians(latitude2 - latitude1);
		double deltaLambda = Math.toRadians(longitude2 - longitude1);

		double a = compute_a(latitude1Rad, latitude2Rad, deltaPhi, deltaLambda);

		double c = compute_c(a);

		double d = R * c; // R = 6 371 000 meter
		return d;

	}

//	for å regne ut verdien av a ved hjelp av formelen som er gitt i oppgava
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
//haversine formel som er gitt i oppgava
		double a = Math.pow(Math.sin(deltaphi / 2), 2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltadelta / 2), 2);

		return a;

	}

	// dinna metoden skal bruke verdien a for å regne ut c
	private static double compute_c(double a) {
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return c;

	}

//	Oppgave e
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		double distance = distance(gpspoint1, gpspoint2);
		double speed = distance / secs;

		return speed;

	}

//oppgave f ska retunere time,min, sek, i stregn metode
	public static String formatTime(int secs) {

		int timer = secs / 3600;
		int min = (secs % 3600) / 60;
		int sek = secs % 60;

		String tidIstring = String.format("%02d:%02d:%02d", timer, min, sek);

		return String.format("%10s", tidIstring);

	}

//	oppgave g
	public static String formatDouble(double d) {

		String str = String.format(Locale.US, "%10.2f", d);
		return str;

	}
}
