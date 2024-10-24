package no.hvl.dat100ptc.oppgave5;

import no.hvl.dat100ptc.TODO;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;

	private static final int MAXBARHEIGHT = 500;

	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn (uten .csv): ");
		GPSComputer gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length;

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		showHeightProfile(MARGIN + MAXBARHEIGHT);
	}

	public void showHeightProfile(int ybase) {

		int x = MARGIN;

		for (int i = 0; i < gpspoints.length; i++) {

			int height = (int) gpspoints[i].getElevation();

			if (height < 0) {
				height = 0;
			}

			drawLine(x, ybase, x, ybase - height);

			x += 3;
		}
	}
}
