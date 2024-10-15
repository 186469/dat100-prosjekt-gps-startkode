package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

<<<<<<< Updated upstream
		GPSPoint point1 = new GPSPoint(3600, 1.0, 2.0, 3.0);
		GPSPoint point2 = new GPSPoint(7200, 4.0, 5.0, 6.0);
		
		GPSData gpsdata = new GPSData(2);
		
		gpsdata.insertGPS(point1);
		gpsdata.insertGPS(point2);
		
		gpsdata.print();
=======
		//Oppgave 1
//		GPSPoint point = GPSDataConverter.convert("2017-08-13T08:52:26.000Z","60.385390","5.217217","61.9");
//		System.out.println("Tiden er : " + point.getTime());
		
		GPSPoint point1 = new GPSPoint(1, 60.385390, 5.332200, 50.0); 
        GPSPoint point2 = new GPSPoint(2, 60.390000, 5.340000, 55.0);
        
        GPSData gpsData = new GPSData(2);
       		
        gpsData.insertGPS(point1);
        gpsData.insertGPS(point2);       
        gpsData.print();
       
>>>>>>> Stashed changes
	}
}
