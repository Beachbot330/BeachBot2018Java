// Robot WRIST Constants

//COPIED FROM 2016 TO 2018 -jb

package org.usfirst.frc330.constants;

import org.usfirst.frc330.wpilibj.PIDGains;

public final class HandConst {
	
	private HandConst(){}
	
	// Length
	public static final double length					= 19.0; 	 // (inches) EJO 1/28

	// Tolerance
	public static final double tolerance        		= 5.0;
	public static final double deadZone			 		= 0.05;  //AP 2-20
	public static final double gamepadDeadZone   		= 0.10;  //AP 2-27
	public static final double pickupTolerance	 		= 5.0; 	 //AP 2-6 (WAG)
	public static final double defaultTimeout	 		= 5.0;	 //JR 2-28
	public static final int    inertiaCounter	 		= 10;	 //JR 2-28 (loops after manual control before starting position control)
	
	// PID Constants
	public static final double proportional      		= 0.5;   // AP 2/25/18
	public static final double integral         		= 0.00; // JR 2/23
	public static final double derivative        		= 0.00;   // AP 2/18/18
	public static final double feedForward	     		= 0.0;   // JR 2/23
	public static final double VoltageRampRate   		= 0.0;  // AP 3-15
	public static final double MaxOutputVoltage  		= 12.0;   // AP 3-15
	public static final PIDGains fullPID = new PIDGains(proportional, integral, derivative, 
														feedForward, MaxOutputVoltage, MaxOutputVoltage, "default");
	
	// Angles relative to ground
	public static final double pickUp					=  0.0;  //JB 1-27
	public static final double Defense					=  90.0; //JB 1-27
	public static final double leveledWrist             =  0.0;  //MF 1/27 WAG
	public static final double maxWrist                 =  90.0; //MF 1/27 WAG
	public static final double minWrist                 = -90.0; //MF 1/27 WAG
	public static final double portalPosition			=  90.0; //EJO 1/28
	
	// Angles relative to arm (encoder)
	public static final double encLowerLimit			= -144.0; // AP 2/18/18
	public static final double encLimitSwitch			=  144.0; // AP 2/18/18
	public static final double encUpperLimit			=  144.0; // AP 2/18/18
	
	// Encoder Stuff
	public static final int    ticksPerEncoderRev  		= 4096;  //AP 2/18/18
	public static final double gearRatio				= 4.125;  //AP 2/18/18
	
	// Other
	public static final double calibrationSpeed			= 0.10; //WAG AP 2/16/18
	public static final int  CAN_Timeout				= 10; //AP 2/18/18
	public static final int  CAN_Timeout_No_Wait        = 0;  // JR 2/19/18
	public static final int  CAN_Status_Frame_13_Period = 20; //JR 2/19/19
}	