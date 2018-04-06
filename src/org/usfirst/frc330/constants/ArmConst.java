// Robot ARM Constants

//COPIED FROM 2016 TO 2018 -jb

package org.usfirst.frc330.constants;

import org.usfirst.frc330.wpilibj.PIDGains;

public final class ArmConst {
	
	private ArmConst(){}
	
	// Length
	public static final double length					= 35;  // (inches) AP 3/3/18 

	// Tolerance
	public static final double tolerance        		= 5.0;
	public static final double deadZone			 		= 0.05;  //AP WAG 1-30-18
	public static final double gamepadDeadZone   		= 0.40;  //AP WAG 1-30-18
	public static final double triggerDeadZone   		= 0.10;  //AP WAG 1-30-18
	public static final double pickupTolerance	 		= 5.0; 	 //AP WAG 1-30-18
	public static final double defaultTimeout	 		= 5.0;	 //AP WAG 1-30-18
	public static final int    inertiaCounter	 		= 0;	 //AP 3/3/18
	
	// PID Constants
	//public static final double proportional      		= 0.25;   // AP WAG 2-18-18
	public static final double proportional      		= 0.25;   // AP WAG 2-18-18
	public static final double integral         		= 0.00; // AP WAG 1-30-18
	public static final double derivative        		= 4.00;   // AP WAG 1-30-18
	public static final double feedForward	     		= 0.45;   // AP WAG 1-30-18
	public static final double VoltageRampRate   		= 48.0;  // AP WAG 1-30-18
	public static final double MaxOutputPercent			= 1.0;	// AP 3/3/18
	public static final double MaxOutputPercentUP		= 1.0;
	
	// Motion Magic Constants
	public static final int velocityLimit              = 2300;   //AP 3/24/18
	public static final int accelLimit                 = 4600;   //AP 3/24/18
	
	// Angles
	public static final double lowerLimit               = -54.0; //MF 1/27 WAG
	public static final double limitSwitchAngle			= -54.0; // AP 2/18/18 WAG
	public static final double dropoffLow				=  47.0; //AP 3/3/18
	public static final double intakePosition  			= -54.0; //WAG JB 1/27
	public static final double Defense 					= -50.0; //WAG JB 1/27
	public static final double portalPosition			= -54.0; //EJO 1/28
	public static final double leveledArm               =   0.0; //MF 1/27 WAG
	public static final double safeAngle				=  50.0; //AP Any hand position above this is safe
	public static final double medArm                   =  72.0; //MF 1/27 WAG
	public static final double switchArm                = -54.0; // AP 3/3/18
	public static final double climbPosition			=  90.0; // wag -EJO 1/27
	public static final double dropoffHigh				=  90.0; // AP WAG 2/18/18
	public static final double vertical					=  90.0; // AP 3/4/18
	public static final double upperLimit               = 160.0; //EJO 1/28 WAG
	
	// Encoder Stuff
	public static final int    ticksPerEncoderRev  		= 4096;  //AP 2/17/18
	public static final double gearRatio		  		= 15 ; //AP 2/17/18 
	
	// Other
	public static final double calibrationSpeed			= -0.1; //WAG JR 2/19/18
	public static final int    CAN_Timeout				= 10; // AP 2/18/18
	public static final int    CAN_Timeout_No_Wait      = 0;  // JR 2/19/18
	public static final int    CAN_Status_Frame_13_Period = 20; //JR 2/19/19
}	