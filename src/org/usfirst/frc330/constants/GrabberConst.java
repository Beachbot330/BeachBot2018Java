// Robot Grabber Constants

package org.usfirst.frc330.constants;

public final class GrabberConst {
	
	private GrabberConst(){}
	
	//Pickup Count
	public static final int hasCubeMinRepitition						= 5;		//AP 4/5/18
	
	//Lengths
	public static final double maxLengthBetweenRollers					= 17.0;		//(inches) JR 3.3.18
	public static final double minLengthBetweenRollers					= 10.0;		//(inches) EJO 2.4.18
	public static final double rollerLength								= 16.0;		//(inches) JR 3.3.18
	public static final double maxBoxLengthSensor						= 10.0;		//(inches) BT 2.4.18
	public static final double cubeWidth								= 13.0;		//(inches) AP 4/5/18
	public static final double cubeHeight								= 11.0;		//(inches) AP 4/5/18
	
	//Roller Speed
	public static final double rollerMaxSpeed							= 1.0;		//JR 3.3.18
	public static final double rollerDeploySpeed						= -1.0;	//JR 3.3.18
	public static final double MaxOutputPercent							= 1.0;
	
	//Roller Inversion
	public static final boolean leftRollerInversionStatus				= true;				//JDR 3/3/2018
	public static final boolean	rightRollerInversionStatus				= false;			//JDR 3/3/2018
	
	//Sensor
	public static final double distanceToTriggerOnDiamond				= 5.0;				//AP 4/5/18
	public static final double distanceBetweenSensors					= 1.0; 				//(inches) EJO 2.4.18
	public static final double sensorMaximumInnerDistance				= 2.2; 				//How far the sensor is from the gripper back wall     //(inches) JR 3.3.18    
	public static final double sensorMinLength							= 1.5748031496; 	//(inches) EJO 2.4.18
	public static final double sensorMaxLength							= 15.0;				//(inches) JR 3.3.18
	public static final double sensorMaximumOuterDistance				= 12.0 + sensorMaximumInnerDistance;	//(inches) JR 3.3.18
	public static final double centerSensorMaximumInnerDistance			= 2.8 + sensorMaximumInnerDistance;		//(inches) JR 3.3.18
	
	public static final double kalmanProcessNoise						= 4.5;				//(inches) 14ft/sec*12in/ft/50samples per second + 0.5 JDR 2/24/18
	public static final double kalmanSensorNoise						= 1.0;				//(inches) Based on observed data JDR 2/24/18
	public static final double kalmanEstimatedError						= 100;				//(inches) //JDR 2/24/18

	public static final int    medianSamples							= 5;				//JDR 3/4/2018
}	