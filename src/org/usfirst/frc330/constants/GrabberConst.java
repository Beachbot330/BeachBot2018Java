// Robot Grabber Constants

package org.usfirst.frc330.constants;

public final class GrabberConst {
	
	private GrabberConst(){}
	
	//Lengths
	public static final double maxLengthBetweenRollers					= 19.0;		//(inches) EJO 2.4.18
	public static final double minLengthBetweenRollers					= 10.0;		//(inches) EJO 2.4.18
	public static final double rollerLength								= 13.0;		//(inches) EJO 2.4.18
	public static final double maxBoxLengthSensor						= 10.0;		//(inches) BT 2.4.18
	
	//Roller Speed
	public static final double leftRollerMaxSpeed						= 10.0;		//wag (units unknown) EJO 2.4.18
	public static final double rightRollerMaxSpeed						= 10.0;		//wag (units unknown) EJO 2.4.18
	
	//Roller Inversion
	public static final boolean leftRollerInversionStatus				= false;		//needs to be checked EJO 2.5.18
	public static final boolean	rightRollerInversionStatus				= true;			//needs to be checked EJO 2.5.18
	
	//Sensor
	public static final double sensorAngleTolerance						= 5.0;				//wag (degrees) EJO 2.10.18
	public static final double centerSensorPosition						= 18.5; 			//(inches) EJO 2.4.18
	public static final double distanceBetweenSensors					= 1.25; 			//(inches) EJO 2.4.18
	public static final double sensorMinLength							= 1.5748031496; 	//(inches) EJO 2.4.18
	public static final double sensorMaxLength							= 11.811023622;		//(inches) EJO 2.4.18
	public static final double sensorOutputTolerance					= 0.25;				//wag (inches) EJO 2.4.18\
	public static final double horizontalSensorDifference				= 7.0;				//(inches) EJO 2.10.18
	public static final double sensorMinimumOuterDistance				= 11.0;				//wag (inches) EJO 2.10.18
	public static final double sensorMaximumOuterDistance				= 11.5;				//(inches) EJO 2.20.18
	public static final double sensorMaximumInnerDistance				= 2.0; 				//wag (inches) EJO 2.10.18
	public static final double centerSensorMaximumInnerDistance			= 4.0;				//wag (inches) EJO 2.10.18
	
	public static final double kalmanProcessNoise						= 0.5;				//(inches) 14ft/sec*12in/ft/50samples per second + 0.5 JDR 2/24/18
	public static final double kalmanSensorNoise						= 1.0;				//(inches) Based on observed data JDR 2/24/18
	public static final double kalmanEstimatedError						= 100;				//(inches) //JDR 2/24/18
}	