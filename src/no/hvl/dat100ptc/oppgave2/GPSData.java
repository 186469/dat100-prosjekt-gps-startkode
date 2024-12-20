package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		
		this.gpspoints = new GPSPoint[n];
		this.antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
		}
		return inserted;
	
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);

		return insertGPS(gpspoint);
		
	}

	// Fikse så output er lik oppgave?
	public void print() {
		
		System.out.println("====== GPS Data - Start ======");
		for (int n = 0; n < antall; n++) {
			GPSPoint point = gpspoints[n];
			System.out.print(point.toString());
		}
		System.out.println("====== GPS Data - Start ======");
	}
}
