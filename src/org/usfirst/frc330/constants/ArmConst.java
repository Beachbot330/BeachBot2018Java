// Robot ARM Constants



//COPIED FROM 2016 TO 2018 -jb



package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.wpilibj.PIDGains;

public final class ArmConst {
	
	private ArmConst(){}
	
	// Length
	public static final double length					= 38.5;  // (inches) EJO 1/28 

	// Tolerance
	public static final double tolerance        		= 5.0;
	public static final double deadZone			 		= 0.05;  //AP 2-20
	public static final double gamepadDeadZone   		= 0.10;  //AP 2-27
	public static final double pickupTolerance	 		= 5.0; 	 //AP 2-6 (WAG)
	public static final double defaultTimeout	 		= 5.0;	 //JR 2-28
	public static final int    inertiaCounter	 		= 10;	 //JR 2-28 (loops after manual control before starting position control)
	
	// PID Constants
	public static final double proportional      		= 1.0;   // JR 2/23
	public static final double integral         		= 0.000; // JR 2/23
	public static final double derivative        		= 1.0;   // JR 2/23
	public static final double feedForward	     		= 0.0;   // JR 2/23
	public static final double VoltageRampRate   		= 48.0;  // AP 3-15
	public static final double MaxOutputVoltage  		= 12.0;   // AP 3-15
	public static final PIDGains fullPID = new PIDGains(proportional, integral, derivative, 
														feedForward, MaxOutputVoltage, MaxOutputVoltage, "default");
	
	// Angles
	public static final double intakePosition  			= -60;   //WAG JB 1/27
	public static final double Defense 					= -60;   //WAG JB 1/27
	public static final double leveledArm               =  0.0;  //MF 1/27 WAG
	public static final double maxArm                   =  160.0; //EJO 1/28 WAG
	public static final double minArm                   = -60.0; //MF 1/27 WAG
	public static final double medArm                   =  0.0; //MF 1/27 WAG
	public static final double switchArm                = 0.0; // MF 1/27 WAG needs value
	public static final double climbPosition			= 90.0; // wag -EJO 1/27
	public static final double portalPosition			= -60.0; //EJO 1/28
	
	// Encoder Stuff
	public static final int    maxAngleDegrees   		= 72;    //JM 2-10
	public static final int    maxEncoderCounts  		= -4096; //AP 2-20
	public static final int    minQuadrant       		= 0;     //JM 2-10
	public static final int    maxQuadrant       		= 4;     //JM 2-10

	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	