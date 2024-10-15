package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

    private static int TIME_STARTINDEX = 11;  

    public static int toSeconds(String timestr) {

    	String hours = timestr.substring(TIME_STARTINDEX, TIME_STARTINDEX + 2);
        String minutes = timestr.substring(TIME_STARTINDEX + 2, TIME_STARTINDEX + 5);
        String seconds = timestr.substring(TIME_STARTINDEX + 6, TIME_STARTINDEX + 8);

        int hr = Integer.parseInt(hours);
        int min = Integer.parseInt(minutes);
        int sec = Integer.parseInt(seconds);

        return hr * 3600 + min * 60 + sec;
    }

    public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

        int timeInSeconds = toSeconds(timeStr);
        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);
        double elevation = Double.parseDouble(elevationStr);

        return new GPSPoint(timeInSeconds, latitude, longitude, elevation);
    }
}
