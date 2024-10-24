package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));

		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);

		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);

		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		setColor(0, 0, 255); // Sett fargen til blå for ruten

		for (int i = 0; i < gpspoints.length - 1; i++) {

			// Hent posisjonene til to påfølgende GPS-punkter
			int x1 = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
			int y1 = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);
			int x2 = MARGIN + (int) ((gpspoints[i + 1].getLongitude() - minlon) * xstep);
			int y2 = ybase - (int) ((gpspoints[i + 1].getLatitude() - minlat) * ystep);

			// Tegn linjen mellom de to punktene
			drawLine(x1, y1, x2, y2);
		}

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int x = MARGIN;
		int y = MARGIN;

		// Sett fargen til svart for teksten
		setColor(0, 0, 0);
		setFont("Courier", 12);

		// Hent statistikk fra gpscomputer
		double totalDistance = gpscomputer.totalDistance() / 1000; // i km
		double totalElevation = gpscomputer.totalElevation(); // i meter
		double totalTime = gpscomputer.totalTime(); // i sekunder
		double averageSpeed = gpscomputer.averageSpeed(); // i km/t
		double maxSpeed = gpscomputer.maxSpeed(); // i km/t

		// Tegn statistikken i vinduet
		drawString(String.format("Total distance: %.2f km", totalDistance), x, y);
		drawString(String.format("Total elevation: %.2f m", totalElevation), x, y + TEXTDISTANCE);
		drawString(String.format("Total time: %.2f s", totalTime), x, y + 2 * TEXTDISTANCE);
		drawString(String.format("Average speed: %.2f km/h", averageSpeed), x, y + 3 * TEXTDISTANCE);
		drawString(String.format("Max speed: %.2f km/h", maxSpeed), x, y + 4 * TEXTDISTANCE);
	}

	public void replayRoute(int ybase) {

		setColor(0, 0, 255); // Blå farge for sirkelen
		int radius = 5; // Radius for sirkelen

		// Tegn en sirkel ved første punkt
		int x = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
		int y = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep);
		int circle = fillCircle(x, y, radius);

		// Flytt sirkelen langs ruten
		for (int i = 1; i < gpspoints.length; i++) {
			x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
			y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);
			moveCircle(circle, x, y);
			pause(200); // Pause for å se bevegelsen
		}
	}
}
