// Robot ARM Constants



//COPIED FROM 2016 TO 2018 -jb

package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.wpilibj.PIDGains;

public final class WristConst {
	
	private WristConst(){}
	
	// Length
	public static final double length					= 19; 	 // (inches) EJO 1/28

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
	public static final double pickUp					=  0.0;  //JB 1-27
	public static final double Defense					=  90.0; //JB 1-27
	public static final double leveledWrist             =  0.0;  //MF 1/27 WAG
	public static final double maxWrist                 =  90.0; //MF 1/27 WAG
	public static final double minWrist                 = -90.0; //MF 1/27 WAG
	
	// Encoder Stuff
	public static final int    maxAngleDegrees   		= 72;    //JM 2-10
	public static final int    maxEncoderCounts  		= -4096; //AP 2-20
	public static final int    minQuadrant       		= 0;     //JM 2-10
	public static final int    maxQuadrant       		= 4;     //JM 2-10

	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	