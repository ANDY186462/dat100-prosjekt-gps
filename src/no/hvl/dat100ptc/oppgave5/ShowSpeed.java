package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {

	private static int MARGIN = 50;
	private static int BARHEIGHT = 500;

	private GPSComputer gpscomputer;

	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		
		int N = gpscomputer.speeds().length;

		makeWindow("Speed profile", 2 * MARGIN + 3 * N, 2 * MARGIN + BARHEIGHT);

		showSpeedProfile(MARGIN + BARHEIGHT);
	}

	public void showSpeedProfile(int ybase) {

		double[] speeds = gpscomputer.speeds();
		double averagespeed = gpscomputer.averageSpeed();

		setColor(0, 0, 255);

		int x = MARGIN, y;

		for (int i = 0; i < speeds.length; i++) {
			int speed = (int) speeds[i];
			drawLine(x, ybase, x, ybase - speed);
			x += 2;
		}
		
		setColor(0, 255, 0);
		
		drawLine(MARGIN, ybase - (int) averagespeed, MARGIN + 2 * speeds.length, ybase - (int) averagespeed);
		

	}
}
