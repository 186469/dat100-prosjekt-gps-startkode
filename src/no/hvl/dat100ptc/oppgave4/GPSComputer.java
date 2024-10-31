package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0.0;
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double dist = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			distance = dist + distance;
		}
		return distance;

	}

	public double totalElevation() {

		double elevation = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			double elevation1 = gpspoints[i].getElevation();
			double elevation2 = gpspoints[i+1].getElevation();	
			
			if(elevation2 > elevation1) {
				elevation = (elevation2 - elevation1) + elevation;
			}
		}
		return elevation;
		
	}

	public int totalTime() {

		int start = gpspoints[0].getTime();
		int slutt = gpspoints[gpspoints.length - 1].getTime();
		
		int totalTid = slutt - start;
		return totalTid;
		
		
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double distanse = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			double timeDif = gpspoints[i+1].getTime() - gpspoints[i].getTime();
			double MpS = distanse / timeDif;
			
			speeds[i] = MpS;
		}
		return speeds;
		
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		double[]maxTab = speeds();
		
		for (int i = 0; i < maxTab.length; i++) {
			if(maxTab[i] > maxspeed) {
				maxspeed = maxTab[i];
			}
		}
		return maxspeed;
		
	
	}

	public double averageSpeed() {

		double average = 0;
		double distanse = totalDistance();
		double tid = totalTime();
		
		average = distanse / tid;
		
		return average;
		
	}

	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double met = 0;		
		double speedmph = speed * MS;

		if (speedmph < 10) {
			met = 4.0;
		}
		else if (speedmph < 12) {
			met = 6.0;
		}
		else if (speedmph < 14) {
			met = 8.0;
		}
		else if (speedmph < 16) {
			met = 10.0;
		}
		else if (speedmph < 20) {
			met = 12.0;
		}
		else if (speedmph >= 20) {
			met = 16.0;
		}
		
		double t = secs / 3600.0;
		
		double kcal = met * weight * t;
		
		return kcal;
		}
		
	
	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			int secs = gpspoints[i+1].getTime() - gpspoints[i].getTime();
			double speed = GPSUtils.distance(gpspoints[i], gpspoints[i+1]) / secs;
			double segmentKcal = kcal(weight,secs,speed);
			totalkcal += segmentKcal;
		}
		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		

		System.out.println("=================================================");
		
		double distanse = totalDistance() / 1000;
		int tid = totalTime();
		double høydeForskjell = totalElevation();
		double maksFart = maxSpeed() * 3.6;
		double gjenFart = averageSpeed() * 3.6;
		double kalorier = totalKcal(WEIGHT);
		
		String time = String.format("%02d:%02d:%02d", tid / 3600, (tid % 3600) / 60, tid % 60);
		String distance = String.format("%10.2f km", distanse);
		String elevation = String.format("%10.2f m", høydeForskjell);
		String maxSpeed = String.format("%10.2f km/t", maksFart);
		String averageSpeed = String.format("%10.2f km/t", gjenFart);
		String kcal = String.format("%10.2f kcal", kalorier);
		
		System.out.println("Total time     : " + time);
		System.out.println("Total distance : " + distance);
		System.out.println("Total elevation: " + elevation);
		System.out.println("Max speed      : " + maxSpeed);
		System.out.println("Average speed  : " + averageSpeed);
		System.out.println("Energy         : " + kcal);
		
		System.out.println("=================================================");
		
	}

}
