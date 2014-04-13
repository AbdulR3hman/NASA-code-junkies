import javax.vecmath.Vector3d;

/**
 * 
 * @authors AbdurRehman & Ted & Mat
 *	Sat object which describe it's orbit...
 */

public class Sat extends GravityObject {

	private final double  normliseFactorS = 4.24E07;
	private int ID; 
	public Sat(){}
	public Sat(double a, double b, int id)
	{
		super();
		this.ID = id;
		this.semiMajorAxis = a;
		this.semiMinorAxis = b;
		this.theta = Math.random() * ( 6.28 - 0);
		this.setPeriodT(this.semiMajorAxis, this.semiMinorAxis);
		this.phaseDif = Math.random() * (this.periodT - 0 ) ;
	}
	
	/**
	 * Get the Positions!! z is always 1
	 * @param time double
	 * @return Vector3 positions
	 */
	public Vector3d getPositions(double t)
	{ 

		double xDash = this.getX(t + this.phaseDif) * Math.cos(theta) + this.getY(t  + this.phaseDif) * Math.sin(theta);
		double yDash = -this.getX(t  + this.phaseDif) * Math.sin(theta) + this.getY(t  + this.phaseDif) * Math.cos(theta);
		double z;
		
		z = ( t/this.periodT > 1/2 ) ? 0.0 : -0.1;
		
		Vector3d temp = new Vector3d(xDash, yDash, z);
		return temp;
	}
	
	private void setPeriodT(double a, double b)
	{
		this.periodT = 2*Math.PI * Math.sqrt((Math.pow(this.semiMajorAxis, 3)/MU));
		//System.out.println(" \nPeriod: " + this.periodT+ "\n");
	}
	
	/*
	 * returns C positions 
	 * @param double t // for current time you want to find the position
	 */
	public double getY(double t)
	{
		this.currentTime = t;
		this.yPosition = 
				(this.semiMinorAxis * Math.sin((this.currentTime/this.periodT)*2*Math.PI)) / (2 * this.normliseFactorS) ;
		
		return this.yPosition;
	}
	
	/*
	 * returns C positions 
	 * @param double t // for current time you want to find the position
	 */
	public double getX(double t)
	{
		this.currentTime = t;
		//Calculating X axis
				this.xPosition = 
						( Math.sqrt( Math.pow(this.semiMajorAxis, 2) -  Math.pow(this.semiMinorAxis, 2)) 
						+ this.semiMajorAxis * Math.cos((this.currentTime/this.periodT)*2*Math.PI) ) / (2 * this.normliseFactorS);
		return this.xPosition;
	}
		
	
}
