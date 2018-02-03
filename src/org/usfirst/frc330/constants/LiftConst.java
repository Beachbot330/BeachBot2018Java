// Robot ARM Constants

package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.wpilibj.PIDGains;

public final class LiftConst {
	
	private LiftConst(){}

	// Tolerance
	public static final double tolerance        		= 5.0;  // WAG

	// PID Constants
	public static final double proportional      		= 1.0;   // WAG
	public static final double integral         		= 0.000; // WAG
	public static final double derivative        		= 1.0;   // WAG
	
	// Positions
	public static final double lowerLimit				=  1.0; //AP 1-30-2018 WAG
	public static final double defensePosition			=  1.0; //wag -EJO
	
	public static final double intakePosition			=  1.0; //wag -EJO
	public static final double scaleDropoffMin			=  1.0; //AP 1-30-2018 
	public static final double intakePortalPosition		=  7.0; //AP 1-30-2018
	public static final double scaleDropoffMid          =  4.0; //AP 1-30-2018
	public static final double switchDropoff			= 20.0; //AP 1-30-2018
	public static final double scaleDropoffMax			= 30.0; //wag -EJO
	
	public static final double climbCenterPosition		= 35.0; //wag EJO 2.3.18 (this is supposed to use the V-hook to center us w/ the bar (called in climbready))
	public static final double climbPosition 			= 30.0; //needs value -EJO
	
	public static final double upperLimit				= 30.0; //AP 1-30-2018 WAG
	
	
	// Encoder Stuff - from 2016... remove?
	public static final int    maxAngleDegrees   		= 72;    //JM 2-10
	public static final int    maxEncoderCounts  		= -4096; //AP 2-20
	public static final int    minQuadrant       		= 0;     //JM 2-10
	public static final int    maxQuadrant       		= 4;     //JM 2-10
	public static final double positionScaler  			= 0	; 	//Needs Value -MF

	public static final double VoltageRampRate			= 1.23;	//WAG  JB
	public static final double MaxOutputPercent			= 1;	//WAG  JB
	public static final int	   timeOutMS				= 10;	//WAG  JB
	
}	