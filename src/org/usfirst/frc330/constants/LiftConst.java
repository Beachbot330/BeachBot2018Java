// Robot ARM Constants

package org.usfirst.frc330.constants;

public final class LiftConst {
	
	private LiftConst(){}

	// Tolerance
	public static final double tolerance        		= 5.0;  // WAG

	// PID Constants
	public static final double proportional      		= 0.2;   // AP 2/17/18
	public static final double integral         		= 0.000; // WAG
	public static final double derivative        		= 0.0;   // WAG
	
	// Positions
	public static final double lowerLimit				=  1.0; //AP 1-30-2018 WAG
	public static final double defensePosition			=  1.0; //wag -EJO
	
	public static final double intakePosition			=  1.0; //wag -EJO
	public static final double scaleDropoffMin			=  1.0; //AP 1-30-2018 
	public static final double intakePortalPosition		=  7.0; //AP 1-30-2018
	public static final double scaleDropoffMid          =  4.0; //AP 1-30-2018
	public static final double switchDropoff			= 20.0; //AP 1-30-2018
	public static final double scaleDropoffMax			= 26.2; //AP 2-17-2018
	
	public static final double climbCenterPosition		= 26.2; //AP 2-17-2018 WAG
	public static final double climbPosition 			= 26.2; //AP 2-17-2018 WAG
	
	public static final double upperLimit				= 26.2; //AP 2-17-2018
		
	// Encoder Stuff - from 2016... remove?
	public static final int    ticksPerRev		  		= 4096;  //AP 2/17/18
	public static final double inchesPerRev				= 3.997; //AP 2/17/18 

	public static final double VoltageRampRate			= 1.23;	//WAG  JB
	public static final double MaxOutputPercent			= 1;	// AP 2/17/18
	public static final int    CAN_Timeout				= 10; //AP 2/18/18
	public static final int    CAN_Timeout_No_Wait      = 0;  // JR 2/19/18
	public static final int    CAN_Status_Frame_13_Period = 20; //JR 2/19/19
	
	// Other
	public static final double calibrationSpeed			= -0.10; //WAG AP 2/16/18
}	