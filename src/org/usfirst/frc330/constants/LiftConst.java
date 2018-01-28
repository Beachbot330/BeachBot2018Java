// Robot ARM Constants

package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.wpilibj.PIDGains;

public final class LiftConst {
	
	private LiftConst(){}

	// Tolerance
	public static final double tolerance        		= 5.0;

	// PID Constants
	public static final double proportional      		= 1.0;   // WAG
	public static final double integral         		= 0.000; // WAG
	public static final double derivative        		= 1.0;   // WAG
	
	// Positions
	public static final double limitLower   			= 2.0;  	// WAG
	public static final double limitUpper   			= 113.0; 	//WAG
	public static final double positionScaler  			= 0	; 		//Needs Value -MF
	public static final double climbPosition 			= 0; //needs value -EJO
	public static final double maxPosition				= 100.0; //wag -EJO
	public static final double minPosition				= 0.0; //wag -EJO
	public static final double enemyScalePosition		= 90.0; //wag -EJO
	public static final double ownedScalePosition		= 30.0; //wag -EJO
	public static final double switchDropoffPosition	= 10.0; //wag -EJO
	public static final double intakePosition			= 0.0; //wag -EJO
	public static final double portalPosition			= 20.0; //wag -EJO
	public static final double defensePosition			= 0.0; //wag -EJO
	
	
	// Encoder Stuff - from 2016... remove?
	public static final int    maxAngleDegrees   		= 72;    //JM 2-10
	public static final int    maxEncoderCounts  		= -4096; //AP 2-20
	public static final int    minQuadrant       		= 0;     //JM 2-10
	public static final int    maxQuadrant       		= 4;     //JM 2-10

	public static final double VoltageRampRate			= 1.23;	//WAG  JB
	public static final double MaxOutputPercent			= 1;	//WAG  JB
	public static final int	   timeOutMS				= 0;	//WAG  JB
	
}	