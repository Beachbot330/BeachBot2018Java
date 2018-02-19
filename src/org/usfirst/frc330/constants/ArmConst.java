// Robot ARM Constants

//COPIED FROM 2016 TO 2018 -jb

package org.usfirst.frc330.constants;

import org.usfirst.frc330.wpilibj.PIDGains;

public final class ArmConst {
	
	private ArmConst(){}
	
	// Length
	public static final double length					= 38.5;  // (inches) EJO 1/28 

	// Tolerance
	public static final double tolerance        		= 5.0;
	public static final double deadZone			 		= 0.05;  //AP WAG 1-30-18
	public static final double gamepadDeadZone   		= 0.10;  //AP WAG 1-30-18
	public static final double pickupTolerance	 		= 5.0; 	 //AP WAG 1-30-18
	public static final double defaultTimeout	 		= 5.0;	 //AP WAG 1-30-18
	public static final int    inertiaCounter	 		= 10;	 //AP WAG 1-30-18
	
	// PID Constants
	public static final double proportional      		= 0.25;   // AP WAG 2-18-18
	public static final double integral         		= 0.00; // AP WAG 1-30-18
	public static final double derivative        		= 0.00;   // AP WAG 1-30-18
	public static final double feedForward	     		= 0.00;   // AP WAG 1-30-18
	public static final double VoltageRampRate   		= 48.0;  // AP WAG 1-30-18
	public static final double MaxOutputVoltage  		= 12.0;  // AP WAG 1-30-18
	public static final double MaxOutputPercent			= 1;	// AP 2/17/18 WAG
	public static final PIDGains fullPID = new PIDGains(proportional, integral, derivative, 
														feedForward, MaxOutputVoltage, MaxOutputVoltage, "default");
	
	// Angles
	public static final double lowerLimit               = -60.0; //MF 1/27 WAG
	public static final double dropoffLow				= -60.0; //AP 2/18/18 WAG
	public static final double intakePosition  			= -60.0; //WAG JB 1/27
	public static final double Defense 					= -60.0; //WAG JB 1/27
	public static final double portalPosition			= -60.0; //EJO 1/28
	public static final double leveledArm               =   0.0; //MF 1/27 WAG
	public static final double medArm                   =   0.0; //MF 1/27 WAG
	public static final double switchArm                =   0.0; // MF 1/27 WAG needs value
	public static final double climbPosition			=  90.0; // wag -EJO 1/27
	public static final double dropoffHigh				=  90.0; // AP WAG 2/18/18
	public static final double limitSwitchAngle			= 160.0; // AP 2/18/18 WAG
	public static final double upperLimit               = 160.0; //EJO 1/28 WAG
	
	// Encoder Stuff
	public static final int    ticksPerEncoderRev  		= 4096;  //AP 2/17/18
	public static final double gearRatio		  		= 15 ; //AP 2/17/18 
	
	// Other
	public static final double calibrationSpeed			= 0.10; //WAG AP 2/16/18
	public static final int    CAN_Timeout				= 10; // AP 2/18/18
}	