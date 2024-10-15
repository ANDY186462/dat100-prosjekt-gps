package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

//"2017-08-13T08:52:26.000Z" er eksempel data input	
//substring 11,12 og 13 tilnærmer den 11te, 12te og 13ne sifferet i variabelen, ogs...
	
	public static int toSeconds(String timestr) {

		int hours = Integer.parseInt(timestr.substring(11, 13));
		int minutes = Integer.parseInt(timestr.substring(14, 16));
		int seconds = Integer.parseInt(timestr.substring(17, 19));

		return hours * 3600 + minutes * 60 + seconds;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		int time = toSeconds(timeStr);

		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);

		return new GPSPoint(time, latitude, longitude, elevation);
	}
}
