// Robot ARM Constants



//COPIED FROM 2016 TO 2018 -jb



package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.wpilibj.PIDGains;

public final class ArmConst {
	
	private ArmConst(){}

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
	public static final double limitLowerAngle   		=  2.0;  //AP 1-26
	public static final double pickupAngle		 		=  3.5;  //JR 2-28 
	public static final double pickupAngleDef			=  5.0;  //AP 4/1
	public static final double lowBar            		=  3.0;  //AP 3-11
	public static final double defenseStance			= 25.0;  //AP 3-19
	public static final double lowLimitNeutral			= 30.0;  //JR 3-15
	public static final double armSafeLimit	     		= 50.0;  //AP 1-26
	public static final double defaultNeutral	        = 55.0;  //AP 3-11    moved from 60 to 55 to protect stinger
	public static final double highLimitNeutral         = 60.0;  // JR 3-15 
	public static final double safeToDeployPortcullis   = 60.0;  // JR 2-27 
	public static final double shootAngleRamp    		= 89.5;  	 //AP 4-28 adjusted for new arm bends and welds, was 87
	public static final double shootAngleFloor   		= 89.5;    //AP 4-28
	public static final double safeToDeployLowerClimber = 95.0;  //AP 3/10
	public static final double safeToDeployUpperClimber = 108.0; //AP 3/10 (could be reduced to 105
	public static final double limitUpperAngle   		= 113.0; //AP 1-26
	
	// Encoder Stuff
	public static final int    maxAngleDegrees   		= 72;    //JM 2-10
	public static final int    maxEncoderCounts  		= -4096; //AP 2-20
	public static final int    minQuadrant       		= 0;     //JM 2-10
	public static final int    maxQuadrant       		= 4;     //JM 2-10

	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	