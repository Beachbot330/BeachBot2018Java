// Robot Chassis Constants
//copied from 2016 to 2018 chassis const - JB

package org.usfirst.frc330.constants;

import org.usfirst.frc330.wpilibj.PIDGains;

public final class ChassisConst {
	private ChassisConst(){}
	
	// Length
	public static final double liftToFrame				  = 26; 	// (inches) EJO 1/28
	public static final double liftToFramRear			  = 6.5; 	// (inches) EJO 1/28
	public static final double maxExtension				  = 16;  	// (inches) EJO 1/28
	
	// PID MaxOutputs
	public static final double backupThrottle       	  = 0.5;
	public static final double defaultMaxOutput     	  = 0.8;
	public static final double defaultMaxOutputStep 	  = 0.05;
		
	// Pick Up Speed
	public static final double pickupSpeed 				  = 1.0;
	public static final double pickupReverseSpeed         = 2.0;
	
	//Encoder Distance Constants
    public static final double wheelDiameter 			  = 7.65;
    public static final double pulsePerRevolution 		  = 360;
    public static final double practicePulsePerRevolution = 250;
    public static final double encoderGearRatio 		  = 3;
    public static final double gearRatio 				  = 64.0/20.0;
    public static final double Fudgefactor 				  = 0.97;
    
    //Turn Gyro 
    public static final double rotateProportional 		  = 0.11;
    public static final int    gyroTolerancebuffer        = 5;  //JR 3/20
    public static final double gyroTurnMin				  = 0.20; //JB 1/27
    
    public static final PIDGains DriveLow	   = new PIDGains(0.100,0,0.000,0,defaultMaxOutput,defaultMaxOutputStep, "DriveLow");
    public static final PIDGains DriveHigh     = new PIDGains(0.050,0,0.050,0,defaultMaxOutput,defaultMaxOutputStep, "DriveHigh"); //AP 3-18
    public static final PIDGains GyroTurnLow   = new PIDGains(0.020,0,0.000,0,0.5,1,"GyroTurnLow");
    public static final PIDGains GyroTurnHigh  = new PIDGains(0.030,0,0.050,0,1,1, "GyroTurnHigh"); //AP 3-18
    public static final PIDGains GyroDriveLow  = new PIDGains(0.010,0,0.000,0,1,1, "GyroDriveLow");
    public static final PIDGains GyroDriveHigh = new PIDGains(0.005,0,0.001,0,1,1, "GyroDriveHigh"); //AP 3-18
    
    // Used for Frills for PDP
    public enum Devices {
        DRIVETRAIN_LEFT1 (0),
        DRIVETRAIN_LEFT2 (1),
        DRIVETRAIN_LEFT3 (2),
        DRIVETRAIN_RIGHT1 (3),
        DRIVETRAIN_RIGHT2 (4),
        DRIVETRAIN_RIGHT3 (5),
        ARM_LEFT (6),
        ARM_RIGHT (7),
        PICKUP_LEFT (8),
        PICKUP_RIGHT (9),
        TURRET (10); 
        
        private int value;
        
        private Devices(int value) {
        	this.value = value;
        }
        
        public int getValue() {
        	return value;
        }
    };
    
}