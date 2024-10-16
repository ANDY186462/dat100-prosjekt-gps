package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {

		this.gpspoints = new GPSPoint[antall];
		this.antall = 0;

	}

	public GPSPoint[] getGPSPoints() {

		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		if (antall < gpspoints.length) {

			gpspoints[antall] = gpspoint;

			antall++;

			return true;
		}

		return false;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);

		return insertGPS(gpspoint);
	}

	public void print() {
		String resultat = "====== GPS Data - START ======\n";

		for (int i = 0; i < antall; i++) {
			resultat += (i + 1) + " " + gpspoints[i].toString() + "\n";
		}

		resultat += "====== GPS Data - SLUTT ======";

		System.out.println(resultat);
	}
}