import java.util.ArrayList;

import javax.vecmath.Vector3d;


/**
 * 
 * @author AbdurRehman
 *	Create a Sats Collection to be called 
 */

public class SatsCollection {

	private int satsNumber;
	private ArrayList<Sat> Sats;
	private int counter;
	public SatsCollection(int n) {

		this.satsNumber = n;
		Sats = new ArrayList<Sat>();
		this.counter = 0;
		this.iniateSats();
	}
	
	//Initialises the sats collection
	private void iniateSats(){
		
		for (int i = 0 ; i < satsNumber ; i++)
		{
			double randA = Math.random() * (4.24 - 1.0) + 1.0 ;			
			double randB = Math.random() * (randA - 0.7) + 0.7;
			
			while (Math.pow(randA, 2) - Math.pow(randB, 2) <= 0.65)
			{
				randA = Math.random() * (4.24 - 1.0) + 1.0 ;			
				randB = Math.random() * (randA - 0.7) + 0.7;
			}

			randA = randA * 1.0E07;
			randB = randB * 1.0E07;
			
			
			Sat sat = new Sat(randA, randB, i);

			Sats.add(sat);						
		}		
	}
	
	
	
	/**
	 * This is for the RENDER to get the positions; the previous method is to update everything
	 * @param ID
	 * @return Vector3
	 */
	public Vector3d getSatPositionByID(int ID)
	{
		return this.getSatPositionByID(ID, this.getCounter());		
	}
	
	
	//Our COunter which replaces the timer
	private double getCounter() {
		
		this.counter = this.counter + 60;
		return this.counter;
	}

	/**
	 *  Function that sets a new positions and returns a vector
	 *  Please DO ignore z , it is always 1 (this is only private to provide a timer, please do not call it from outside, call the previouse one)
	 *  
	 *  MANU; YOU CAN ADD THE RENDER.UPDATE HERE
	 * 
	 * @param ID
	 * @return Vector3 Positions
	 */
	private Vector3d getSatPositionByID(int ID, double t)
	{
		return Sats.get(ID).getPositions(t);
	}

	public int getsize() {
		return Sats.size();
	}

	
}
