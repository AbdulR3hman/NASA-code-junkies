import javax.vecmath.Vector3d;


/**
 * 
 * @author AbdurRehman & Ted & Mat
 *	Gravity Object class which provides the positioning details.
 */

public abstract class GravityObject {

	double xPosition;
	double yPosition;
	double theta;
	double phaseDif;
	double semiMajorAxis;
	double semiMinorAxis;
	double currentTime;
	double periodT;
	Vector3d positions ;
	final double  MU = 3.986E14;
	
	GravityObject()
	{
		this.currentTime = 0 ;
		//this.distanceFromParent = 0;
	}
	
	
	
	
}
