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
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		
		int x = MARGIN, y;
		double[] speeds = gpscomputer.speeds();
		double averageSpeed = gpscomputer.averageSpeed();
		
		double maxSpeed = 0;
		
		for (double speed : speeds) {
			if (speed > maxSpeed) {
				maxSpeed = speed;
			}
		}
		
		for (int i = 0; i < speeds.length; i++) {
			int barHeight = (int) ((speeds[i] / maxSpeed) * BARHEIGHT);
			y = ybase - barHeight;
			
			setColor(0, 0, 255);
			fillRectangle(x, y, 2, barHeight);
			
			x += 2;
		}
		
		int averageY = ybase - (int) ((averageSpeed / maxSpeed) * BARHEIGHT);
		setColor(0, 255, 0 );
		drawLine(MARGIN, averageY, MARGIN + speeds.length * 2, averageY);
		
		setColor(0, 0, 0);
		drawString(String.format("Gjennomsnitt: %.1f km/t", averageSpeed), MARGIN, averageY - 5);
	}
}
