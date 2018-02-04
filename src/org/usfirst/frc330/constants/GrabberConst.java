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
	//Sensor
	public static final double centerSensorPosition						= 9.5; 		//(inches) EJO 2.4.18
	public static final double otherSensorDistanceFromCenter			= 4.0; 		//(inches) EJO 2.4.18
	public static final double sensorMinLength							= 4.0; 		//(cm) EJO 2.4.18
	public static final double sensorMaxLength							= 30.0;		//(cm) EJO 2.4.18
	
}	