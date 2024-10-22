package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import javax.swing.JOptionPane;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

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

	public static double findMin(double[] da) {

		double min = da[0];

		for (int i = 0; i < da.length; i++) {
			if(da[i] < min) {
				min = da[i];
			}
		}
		return min;
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			latitude[i] = gpspoints[i].getLatitude();
			}
		return latitude;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[]longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		double latitude1 = gpspoint1.getLatitude();
		double longitude1 = gpspoint1.getLongitude();
		double latitude2 = gpspoint2.getLatitude();
		double longitude2 = gpspoint2.getLongitude();

		double phi1 = Math.toRadians(latitude1);
		double phi2 = Math.toRadians(latitude2);
		double deltaphi = Math.toRadians(latitude2 - latitude1);
		double deltadelta = Math.toRadians(longitude2 - longitude1);
		
		double a = compute_a(phi1,phi2,deltaphi,deltadelta);
		double c = compute_c(a);
		double d = R * c;
		
		return d;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		return Math.sin(deltaphi / 2) * Math.sin(deltaphi / 2) +
		           Math.cos(phi1) * Math.cos(phi2) *
		           Math.sin(deltadelta / 2) * Math.sin(deltadelta / 2);
	}
	private static double compute_c(double a) {

		 return 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {


		double distance = distance(gpspoint1, gpspoint2);
		double timeDif = gpspoint2.getTime() - gpspoint1.getTime();
		
		double speed = distance / timeDif;
		
		return speed;

	}

	public static String formatTime(int secs) {
		
		int timer = secs / 3600;
		int minutter = (secs % 3600) / 60;
		int sekunder = secs % 60;
		
		String res = String.format("%02d:%02d:%02d", timer, minutter, sekunder);
		String resultat = String.format("%10s", res);
		
		return resultat;
		
		
	}
	
	//får den bare til å bli blå.
	
	private static final int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String res = String.format("%.2f", d); 
	    String resultat = String.format("%" + TEXTWIDTH + "s", res); 
	    return resultat;
		
	}
}




