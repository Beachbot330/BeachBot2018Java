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
	
	// Poitions
	public static final double limitLower   		=  2.0;  // WAG
	public static final double limitUpper   		= 113.0; //WAG
	
	// Encoder Stuff - from 2016... remove?
	public static final int    maxAngleDegrees   		= 72;    //JM 2-10
	public static final int    maxEncoderCounts  		= -4096; //AP 2-20
	public static final int    minQuadrant       		= 0;     //JM 2-10
	public static final int    maxQuadrant       		= 4;     //JM 2-10

	public static final double VoltageRampRate			= 1.23;	//WAG  JB
	public static final double MaxOutputPercent			= 1;	//WAG  JB
	public static final int	   timeOutMS				= 0;	//WAG  JB
	
}	