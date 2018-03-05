// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.constants.GrabberConst;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;
import org.usfirst.frc330.wpilibj.KalmanFilter;
import org.usfirst.frc330.wpilibj.MedianFilter;
import org.usfirst.frc330.wpilibj.SharpIR;
import org.usfirst.frc330.wpilibj.SharpIR.SharpType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */

public class Grabber extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX intakeLeft;
    private BBDoubleSolenoid pincher;
    private WPI_TalonSRX intakeRight;
    private SharpIR grabberIRLeft;
    private SharpIR grabberIRCenter;
    private SharpIR grabberIRRight;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private KalmanFilter leftKalman, centerKalman, rightKalman;
    private MedianFilter leftMedian, centerMedian, rightMedian;

    public Grabber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        intakeLeft = new WPI_TalonSRX(3);
        
        
        pincher = new BBDoubleSolenoid(0, 3, 4);
        addChild(pincher);
        
        intakeRight = new WPI_TalonSRX(10);
        
        
        grabberIRLeft = new SharpIR(SharpType.GP2Y0A41SK0F, 1);
        addChild(grabberIRLeft);
        
        grabberIRCenter = new SharpIR(SharpType.GP2Y0A41SK0F, 2);
        addChild(grabberIRCenter);
        
        grabberIRRight = new SharpIR(SharpType.GP2Y0A41SK0F, 3);
        addChild(grabberIRRight);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        intakeLeft.setInverted(GrabberConst.leftRollerInversionStatus);
        intakeRight.setInverted(GrabberConst.rightRollerInversionStatus);	
		
		intakeLeft.configForwardSoftLimitEnable(false, HandConst.CAN_Timeout); 
		intakeLeft.configReverseSoftLimitEnable(false, HandConst.CAN_Timeout);
		intakeLeft.setNeutralMode(NeutralMode.Brake);
		
		intakeRight.configForwardSoftLimitEnable(false, HandConst.CAN_Timeout); 
		intakeRight.configReverseSoftLimitEnable(false, HandConst.CAN_Timeout);
		intakeRight.setNeutralMode(NeutralMode.Brake);
		
		intakeLeft.configOpenloopRamp(0, HandConst.CAN_Timeout);
		intakeLeft.configPeakOutputForward(GrabberConst.MaxOutputPercent, HandConst.CAN_Timeout);
        intakeLeft.configPeakOutputReverse(-GrabberConst.MaxOutputPercent, HandConst.CAN_Timeout);
        
        intakeRight.configOpenloopRamp(0, HandConst.CAN_Timeout);
		intakeRight.configPeakOutputForward(HandConst.MaxOutputPercent, HandConst.CAN_Timeout);
        intakeRight.configPeakOutputReverse(-HandConst.MaxOutputPercent, HandConst.CAN_Timeout);
        
        intakeLeft.configNominalOutputForward(0, HandConst.CAN_Timeout);	
		intakeLeft.configNominalOutputReverse(0, HandConst.CAN_Timeout);
		
		intakeRight.configNominalOutputForward(0, HandConst.CAN_Timeout);	
		intakeRight.configNominalOutputReverse(0, HandConst.CAN_Timeout);
		
		intakeLeft.configForwardLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, HandConst.CAN_Timeout);
		intakeLeft.configReverseLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, HandConst.CAN_Timeout);
        
		intakeRight.configForwardLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, HandConst.CAN_Timeout);
		intakeRight.configReverseLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, HandConst.CAN_Timeout);
        
        //double process_noise, double sensor_noise, double estimated_error, double intial_value
        leftKalman = new KalmanFilter(GrabberConst.kalmanProcessNoise, GrabberConst.kalmanSensorNoise, GrabberConst.kalmanEstimatedError, grabberIRLeft.getDistance());
        centerKalman = new KalmanFilter(GrabberConst.kalmanProcessNoise, GrabberConst.kalmanSensorNoise, GrabberConst.kalmanEstimatedError, grabberIRCenter.getDistance());
        rightKalman = new KalmanFilter(GrabberConst.kalmanProcessNoise, GrabberConst.kalmanSensorNoise, GrabberConst.kalmanEstimatedError, grabberIRRight.getDistance());
        
        leftMedian = new MedianFilter(GrabberConst.medianSamples);
        centerMedian = new MedianFilter(GrabberConst.medianSamples);
        rightMedian = new MedianFilter(GrabberConst.medianSamples);
        
        //------------------------------------------------------------------------------
        // Logging
        //------------------------------------------------------------------------------
        CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return getSensorLDistance(); }
		};
		CSVLogger.getInstance().add("GetSensorLOutput", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getSensorRDistance(); }
		};
		CSVLogger.getInstance().add("GetSensorROutput", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getSensorCDistance(); }
		};
		CSVLogger.getInstance().add("GetSensorCOutput", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return grabberIRLeft.getDistance(); }
		};
		CSVLogger.getInstance().add("GrabberIRLeft", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return grabberIRCenter.getDistance(); }
		};
		CSVLogger.getInstance().add("GrabberIRCenter", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return grabberIRRight.getDistance(); }
		};
		CSVLogger.getInstance().add("GrabberIRRight", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { 
				return getNumberOfSensorsReceivingInput();
			}			
		};
		CSVLogger.getInstance().add("Sensors Receiving Input", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return ExtrapolDistanceFromAngle; }			
		};
		CSVLogger.getInstance().add("ExtrapolatedDistanceFromAngle", temp);
		
		temp = new CSVLoggable(true) {
			public double get() {
				if(hasCubeClose())
					return 1.0;			
				else 
					return 0.0;
			}			
		};
		CSVLogger.getInstance().add("HasCubeClose", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return centerKalman.getEstimatedError(); }			
		};
		CSVLogger.getInstance().add("CenterSensorEstimatedError", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return leftKalman.getEstimatedError(); }			
		};
		CSVLogger.getInstance().add("LeftSensorEstimatedError", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return rightKalman.getEstimatedError(); }			
		};
		CSVLogger.getInstance().add("RightSensorEstimatedError", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return centerMedian.getFilteredValue(); }			
		};
		CSVLogger.getInstance().add("CenterMedianDistance", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return leftMedian.getFilteredValue(); }			
		};
		CSVLogger.getInstance().add("LeftMedianDistance", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return rightMedian.getFilteredValue(); }			
		};
		CSVLogger.getInstance().add("RightMedianDistance", temp);
    }

    @Override
    public void initDefaultCommand() {
    	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    	updateSensorLDistance();
    	updateSensorRDistance();
    	updateSensorCDistance();
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private double updateSensorLDistance() {
    	leftKalman.setParameters(Robot.chassis.getSpeed() + GrabberConst.kalmanProcessNoise, grabberIRLeft.getNoise());
    	leftMedian.updateFilteredValue(grabberIRLeft.getDistance());
    	return leftKalman.updateFilteredValue(grabberIRLeft.getDistance());
    }
    
    private double updateSensorCDistance() {
    	centerKalman.setParameters(Robot.chassis.getSpeed() + GrabberConst.kalmanProcessNoise, grabberIRCenter.getNoise());
    	centerMedian.updateFilteredValue(grabberIRCenter.getDistance());
    	return centerKalman.updateFilteredValue(grabberIRCenter.getDistance());
    }
    
    private double updateSensorRDistance() {
    	rightKalman.setParameters(Robot.chassis.getSpeed() + GrabberConst.kalmanProcessNoise, grabberIRRight.getNoise());
    	rightMedian.updateFilteredValue(grabberIRRight.getDistance());
    	return rightKalman.updateFilteredValue(grabberIRRight.getDistance());
    }
    
    public double getSensorLDistance() {
    	return leftKalman.getFilteredValue();
    }
    
    public double getSensorCDistance() {
    	return centerKalman.getFilteredValue();
    }
    
    public double getSensorRDistance() {
    	return rightKalman.getFilteredValue();
    }
    
    public void openClaw() {
    	pincher.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void closeClaw() {
    	pincher.set(DoubleSolenoid.Value.kForward);
    }
    
    public void rollerOn() {
    	intakeLeft.set(GrabberConst.rollerMaxSpeed);
    	intakeRight.set(GrabberConst.rollerMaxSpeed);
    }
    
    public void rollerOff() {
    	intakeLeft.stopMotor();
    	intakeRight.stopMotor();
    }
    
    public void rollerReverse() {
    	intakeLeft.set((GrabberConst.rollerDeploySpeed)); //the negative one is to make it rotate the other way
    	intakeRight.set((GrabberConst.rollerDeploySpeed));
    }
    
	public void pickupOff() {
		intakeLeft.disable();
	}
	
	public void stopGrabber() {
		if (intakeLeft.isAlive()) {		
			intakeLeft.setIntegralAccumulator(0.0, 0, 0);
		}
		intakeLeft.set(0);
		intakeLeft.disable();
		Logger.getInstance().println("Grabber disabled", Logger.Severity.INFO);
	}
	
	// ----------------
	// hasCube methods
	// ----------------
	
	//Note, hasCubeClose uses isAngleWithinMaximumOuterDistance, but doesn't return the result.
	//Can remove once certain which method to use
	public boolean hasCubeClose() {
		double leftAngle, rightAngle, fullAngle;
		double sL = getSensorLDistance();
		double sC = getSensorCDistance();
		double sR = getSensorRDistance();
		
		int sensorsReceivingInput = getNumberOfSensorsReceivingInput();
		
		if (sensorsReceivingInput < 3)
			return false;
		
		//If cube is within max inner distance, robot has cube
		if (sL < GrabberConst.centerSensorMaximumInnerDistance &&
		   sC < GrabberConst.centerSensorMaximumInnerDistance &&
		   sR < GrabberConst.centerSensorMaximumInnerDistance)
			return true;
		
		leftAngle = getAngleBetweenSensors(getSensorLDistance(), getSensorCDistance(), GrabberConst.distanceBetweenSensors);
	    rightAngle = getAngleBetweenSensors(getSensorCDistance(), getSensorRDistance(), GrabberConst.distanceBetweenSensors);
	    
		if (sC < sL && sC < sR) {
			double shallowAngle = Math.min(leftAngle, rightAngle);
		    isAngleWithinMaximumOuterDistance(shallowAngle, GrabberConst.distanceBetweenSensors);
		    
			return isCubeWithinMaxOuterDistance(true);
		}
		
	    fullAngle = getAngleBetweenSensors(sL, sR, (GrabberConst.distanceBetweenSensors * 2));
	    
		if ((sL < sC && sC < sR) || (sL > sC && sC > sR)) {
			//offset is smallest distance of the three sensors
			double closetDistance = Math.min(sL, sR);	
			isAngleWithinMaximumOuterDistance(fullAngle, closetDistance);
			
		    closetDistance = Math.abs(sL - sR);	

			return isCubeWithinMaxOuterDistance(false);
		}
		else
			return false;
	}
    
	// ---------------------------
	// Private methods for hasCube
	// --------------------------- 
    
    private double ExtrapolDistanceFromAngle;
    
    private boolean isAngleWithinMaximumOuterDistance(double shallowAngle, double offsetDistance) {
    	
    	double sinInRad = Math.sin(Math.toRadians(shallowAngle));
    	//the distance is the hypotenuse (which is equal to box length)
        ExtrapolDistanceFromAngle = (sinInRad * 13) + offsetDistance; 		
    	
    	return ExtrapolDistanceFromAngle < GrabberConst.sensorMaximumOuterDistance;
    }
    
    private double extrapolDistance;
    
    private boolean isCubeWithinMaxOuterDistance(boolean isDiamondCase) {
    	double distanceBetweenSensors;		
    	double distance;					//Distance = difference of two sensors to compare
    	double smallestSensorDistance;		//shortest distance of the three sensors
		double sL = getSensorLDistance();
		double sC = getSensorCDistance();
		double sR = getSensorRDistance();
    	
    	if(isDiamondCase) {
        	return sC < GrabberConst.centerSensorMaximumInnerDistance;
    	}
    	else {
    		distanceBetweenSensors = GrabberConst.distanceBetweenSensors * 2;
        	distance = Math.abs(sL - sR);
        	smallestSensorDistance = Math.min(sL, sR);
    	}
    	
    	extrapolDistance = ((distance * (GrabberConst.maxBoxLengthSensor / distanceBetweenSensors))) + smallestSensorDistance;
    	return (extrapolDistance < GrabberConst.sensorMaximumOuterDistance);
    }

    private int getNumberOfSensorsReceivingInput() { 
    	int numberOfSensorsReceivingInput = 0;
    	if (isSensorReceivingInput(getSensorLDistance())) 
    		numberOfSensorsReceivingInput++;
    	if (isSensorReceivingInput(getSensorRDistance()))  
    		numberOfSensorsReceivingInput++;
    	if (isSensorReceivingInput(getSensorCDistance()))
    		numberOfSensorsReceivingInput++;
    	
    	return numberOfSensorsReceivingInput; 
    }
    
    private boolean isSensorReceivingInput(double distance) {
    	return distance < GrabberConst.sensorMaxLength;
    }
    
    private double getAngleBetweenSensors(double leftDistance, double rightDistance, double distanceBetweenSensors) {
    	double y = Math.abs(leftDistance - rightDistance);
		double x = distanceBetweenSensors;
		double AngleBetweenSensors = Math.toDegrees(Math.atan2(y, x));
		
		return AngleBetweenSensors;
	}
    

//	//hasCube w/ angled sensors on rear of robot & center vertical sensor
//	public boolean hasCubeCanted() { 	
//		if(sLstatus && sRstatus) return true;
//		else if(sC < GrabberConst.centerSensorMaximumInnerDistance) return true;
//		else return false;
//	}

	public double getIntakeLeftFirmwareVersion() {
		int firmwareVersion = intakeLeft.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
	}
	
	public double getIntakeRightFirmwareVersion() {
		int firmwareVersion = intakeRight.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
	}
	
	
}
