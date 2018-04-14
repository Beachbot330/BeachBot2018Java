// Robot Chassis Constants

//copied from 2016 to 2018 chassis const - JB

package org.usfirst.frc330.constants;

import org.usfirst.frc330.commands.drivecommands.DrivePIDGains;
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
	public static final double defaultMinStartOutput      = 0.20;
	
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
    
    //Tolerances
    public static final double defaultTolerance 		= 3;
    public static final double defaultTurnTolerance		= 2;
    
    //PID Gains
    public static final DrivePIDGains DriveLow	   = new DrivePIDGains(0.100,0,0.000,0,defaultMaxOutput,defaultMaxOutputStep,defaultMinStartOutput, "DriveLow");
    public static final DrivePIDGains DriveHigh     = new DrivePIDGains(0.100,0,0.80,0,defaultMaxOutput,defaultMaxOutputStep, defaultMinStartOutput,"DriveHigh"); //AP 3-30-18
    public static final DrivePIDGains GyroTurnLow   = new DrivePIDGains(0.020,0,0.05,0,0.5,1,0,"GyroTurnLow");
    public static final DrivePIDGains GyroTurnHigh  = new DrivePIDGains(0.030,0,0.000,0,1,1,0, "GyroTurnHigh"); //AP 3-9-18
    public static final DrivePIDGains GyroDriveLow  = new DrivePIDGains(0.010,0,0.000,0,1,1,0, "GyroDriveLow");
    public static final DrivePIDGains GyroDriveHigh = new DrivePIDGains(0.01,0,0.000,0,1,1,0, "GyroDriveHigh"); //AP 3-9-18
    
    //Drive distances
    public static final double driveStraightAuto     = 100;
       
}