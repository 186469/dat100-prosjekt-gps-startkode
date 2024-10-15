package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint point1 = new GPSPoint(3600, 1.0, 2.0, 3.0);
		GPSPoint point2 = new GPSPoint(7200, 4.0, 5.0, 6.0);
		
		GPSData gpsdata = new GPSData(2);
		
		gpsdata.insertGPS(point1);
		gpsdata.insertGPS(point2);
		
		gpsdata.print();
	}
}
