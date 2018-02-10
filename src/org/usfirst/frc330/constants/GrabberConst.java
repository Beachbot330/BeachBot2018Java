// Robot Grabber Constants



package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.wpilibj.PIDGains;

public final class GrabberConst {
	
	private GrabberConst(){}
	
	//Lengths
	public static final double maxLengthBetweenRollers					= 19.0;		//(inches) EJO 2.4.18
	public static final double minLengthBetweenRollers					= 10.0;		//(inches) EJO 2.4.18
	public static final double rollerLength								= 13.0;		//(inches) EJO 2.4.18
	
	//Roller Speed
	public static final double leftRollerMaxSpeed						= 10.0;		//wag (units unknown) EJO 2.4.18
	public static final double rightRollerMaxSpeed						= 10.0;		//wag (units unknown) EJO 2.4.18
	
	//Roller Inversion
	public static final boolean leftRollerInversionStatus				= false;		//needs to be checked EJO 2.5.18
	public static final boolean	rightRollerInversionStatus				= true;			//needs to be checked EJO 2.5.18
	
	//Sensor
	public static final double centerSensorPosition						= 9.5; 				//(inches) EJO 2.4.18
	public static final double distanceBetweenSensors					= 4.0; 				//(inches) EJO 2.4.18
	public static final double sensorMinLength							= 1.5748031496; 	//(inches) EJO 2.4.18
	public static final double sensorMaxLength							= 11.811023622;		//(inches) EJO 2.4.18
	public static final double sensorOutputTolerance					= 0.25;				//wag (inches) EJO 2.4.18
	public static final double distanceWhenWeHaveCube					= 12.0;				//(inches) EJO 2.10.18
	
}	