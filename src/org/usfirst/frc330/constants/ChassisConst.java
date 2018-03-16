// Robot Chassis Constants

//copied from 2016 to 2018 chassis const - JB

package org.usfirst.frc330.constants;

import org.usfirst.frc330.wpilibj.PIDGains;

public final class ChassisConst {
	private ChassisConst(){}
	
	// Length
	public static final double liftToFrame				  = 25.75; 	// (inches) EJO 1/28
	public static final double liftToFramRear			  = 6.75; 	// (inches) EJO 1/28
	public static final double maxExtension				  = 16;  	// (inches) EJO 1/28
	
	// PID MaxOutputs
	public static final double backupThrottle       	  = 0.5;
	public static final double defaultMaxOutput     	  = 0.9;
	public static final double defaultMaxOutputStep 	  = 0.05;
		
	// Pick Up Speed
	public static final double pickupSpeed 				  = 1.0;
	public static final double pickupReverseSpeed         = 2.0;
	
	//Encoder Distance Constants
    public static final double wheelDiameter 			  = 6;
    public static final double pulsePerRevolution 		  = 1024;
    public static final double encoderGearRatio 		  = 3;
    public static final double gearRatio 				  = 64.0/20.0;
    public static final double Fudgefactor 				  = 1.03;       //JR 3/11/18
    
    //Turn Gyro 
    public static final double rotateProportional 		  = 0.11;
    public static final int    gyroTolerancebuffer        = 5;  //JR 3/20
    public static final double gyroTurnMin				  = 0.20; //JB 1/27
    
    public static final double defaultTolerance 		= 3;
    public static final double defaultTurnTolerance		= 2;
    
    public static final PIDGains DriveLow	   = new PIDGains(0.100,0,0.000,0,defaultMaxOutput,defaultMaxOutputStep, "DriveLow");
    public static final PIDGains DriveHigh     = new PIDGains(0.050,0,0.70,0,defaultMaxOutput,defaultMaxOutputStep, "DriveHigh"); //AP 3-9-18
    public static final PIDGains GyroTurnLow   = new PIDGains(0.020,0,0.05,0,0.5,1,"GyroTurnLow");
    public static final PIDGains GyroTurnHigh  = new PIDGains(0.030,0,0.000,0,1,1, "GyroTurnHigh"); //AP 3-9-18
    public static final PIDGains GyroDriveLow  = new PIDGains(0.010,0,0.000,0,1,1, "GyroDriveLow");
    public static final PIDGains GyroDriveHigh = new PIDGains(0.01,0,0.000,0,1,1, "GyroDriveHigh"); //AP 3-9-18
    
    //Drive distances
    public static final double driveStraightAuto     = 100;
       
}