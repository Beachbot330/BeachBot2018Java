// Robot WRIST Constants

//COPIED FROM 2016 TO 2018 -jb

package org.usfirst.frc330.constants;

import org.usfirst.frc330.wpilibj.PIDGains;

public final class HandConst {
	
	private HandConst(){}
	
	// Length
	public static final double length					= 19.0; 	 // (inches) EJO 1/28

	// Tolerance
	public static final double defaultTolerance  		= 5.0;
	public static final double deadZone			 		= 0.05;  //AP 2-20
	public static final double gamepadDeadZone   		= 0.20;  //AP 2-27
	public static final double pickupTolerance	 		= 5.0; 	 //AP 2-6 (WAG)
	public static final double defaultTimeout	 		= 5.0;	 //JR 2-28
	
	// PID Constants
	//public static final double proportional      		= 4.0;   // AP 3/3/18
	public static final double proportional      		= 2.0;   // AP 3/24/18
	public static final double integral         		= 0.00; // JR 2/23
	public static final double derivative        		= 0.00;   // AP 3/24/18
	public static final double feedForward	     		= 0.85;   // AP 3/24/18 (1024/Vmax)
	public static final double VoltageRampRate   		= 0.0;  // AP 3-15
	public static final double MaxOutputPercent			= 1.0;	// AP 3/24/18
	public static final double slopAdjust				= 6.0;
	public static final double rearSlopAdjust			= 18.0;
	
	// Motion Magic Constants
	public static final int velocityLimit              = 1200;   //AP 3/24/18
	public static final int accelLimit                 = 3600;   //AP 3/24/18
	
	// Angles relative to ground
	public static final double pickUp					=  -5.0;  //JDR 3/3/18
	public static final double e_pickup                 = -10; //MF 3/17/18
	public static final double Defense					=  75.0; // AP 3/5/18
	public static final double switchDropoff			= 30.0; //AP 3/3/18
	public static final double leveledWrist             =  0.0;  //MF 1/27 WAG
	public static final double scaleDropoff				=  0.0; //AP 3/3/18
	public static final double minWrist                 = -90.0; //MF 1/27 WAG
	public static final double portalPosition			=  90.0; //EJO 1/28
	public static final double rearLevel				= 180.0; //AP 3/4/18
	
	// Angles relative to arm (encoder)
	public static final double encLowerLimit			= -144.0; // AP 2/18/18
	public static final double encFrameSafe				=  90.0; // AP 3/3/18
	public static final double encLimitSwitch			=  144.0; // AP 2/18/18
	public static final double encUpperLimit			=  144.0; // AP 2/18/18
	
	// Encoder Stuff
	public static final int    ticksPerEncoderRev  		= 4096;  //AP 2/18/18
	public static final double gearRatio				= 4.125;  //AP 2/18/18
	
	// Other
	public static final double calibrationSpeed			= 0.45; //WAG AP 2/16/18
	public static final int  CAN_Timeout				= 10; //AP 2/18/18
	public static final int  CAN_Timeout_No_Wait        = 0;  // JR 2/19/18
	public static final int  CAN_Status_Frame_13_Period = 20; //JR 2/19/19
	public static final double framePerimeterSafety		= 2.0; //How much margin do we want?
	public static final double stepSize					= 10.0; //AP 3/25/18
}	