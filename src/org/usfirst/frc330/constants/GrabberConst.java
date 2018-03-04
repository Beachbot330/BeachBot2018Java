// Robot Grabber Constants

package org.usfirst.frc330.constants;

public final class GrabberConst {
	
	private GrabberConst(){}
	
	//Lengths
	public static final double maxLengthBetweenRollers					= 17.0;		//(inches) JR 3.3.18
	public static final double minLengthBetweenRollers					= 10.0;		//(inches) EJO 2.4.18
	public static final double rollerLength								= 16.0;		//(inches) JR 3.3.18
	public static final double maxBoxLengthSensor						= 10.0;		//(inches) BT 2.4.18
	
	//Roller Speed
	public static final double rollerMaxSpeed							= 1.0;		//JR 3.3.18
	public static final double rollerDeploySpeed						= -0.75;	//JR 3.3.18
	
	//Roller Inversion
	public static final boolean leftRollerInversionStatus				= true;				//JDR 3/3/2018
	public static final boolean	rightRollerInversionStatus				= false;			//JDR 3/3/2018
	
	//Sensor
	public static final double sensorAngleTolerance						= 5.0;				//wag (degrees) EJO 2.10.18
	public static final double distanceBetweenSensors					= 1.0; 				//(inches) EJO 2.4.18
	public static final double sensorMinLength							= 1.5748031496; 	//(inches) EJO 2.4.18
	public static final double sensorMaxLength							= 15.0;				//(inches) JR 3.3.18
	public static final double sensorOutputTolerance					= 0.25;				//wag (inches) EJO 2.4.18
	public static final double sensorMaximumOuterDistance				= 14.0;				//(inches) JR 3.3.18
	public static final double sensorMaximumInnerDistance				= 2.0; 				//wag (inches) EJO 2.10.18
	public static final double centerSensorMaximumInnerDistance			= 6.0;				//JR 3.3.18
	
	public static final double kalmanProcessNoise						= 0.5;				//(inches) 14ft/sec*12in/ft/50samples per second + 0.5 JDR 2/24/18
	public static final double kalmanSensorNoise						= 1.0;				//(inches) Based on observed data JDR 2/24/18
	public static final double kalmanEstimatedError						= 100;				//(inches) //JDR 2/24/18

	public static final int    medianSamples							= 5;				//JDR 3/4/2018
}	