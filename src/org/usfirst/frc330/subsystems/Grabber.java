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
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.GrabberConst;
import org.usfirst.frc330.constants.WristConst;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;




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
    private AnalogInput sensorL;
    private AnalogInput sensorC;
    private AnalogInput sensorR;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Grabber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        intakeLeft = new WPI_TalonSRX(3);
        
        
        pincher = new BBDoubleSolenoid(0, 3, 4);
        addChild(pincher);
        
        intakeRight = new WPI_TalonSRX(10);
        
        
        sensorL = new AnalogInput(1);
        addChild(sensorL);
        
        sensorC = new AnalogInput(2);
        addChild(sensorC);
        
        sensorR = new AnalogInput(3);
        addChild(sensorR);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        intakeLeft.setInverted(GrabberConst.leftRollerInversionStatus); 	//TODO check to see which intake needs to be inverted
        intakeRight.setInverted(GrabberConst.rightRollerInversionStatus);	//update GrabberConst with correct booleans
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

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public int getSensorLOutput() {
    	return sensorL.getValue(); //can be changed if needed
    }
    
    public int getSensorCOutput() {
    	return sensorC.getValue();
    }
    
    public int getSensorROutput() {
    	return sensorR.getValue();
    }
    
    public void openClaw() {
    	pincher.set(DoubleSolenoid.Value.kForward);
    }
    
    public void closeClaw() {
    	pincher.set(DoubleSolenoid.Value.kReverse);
    }
    
  //TODO verify direction for roller on/off and rollerreverse on/off -EJO
    public void RollerOn() {
    	intakeLeft.set(GrabberConst.leftRollerMaxSpeed);
    	intakeRight.set(GrabberConst.rightRollerMaxSpeed);
    }
    public void RollerOff() {
    	intakeLeft.stopMotor();
    	intakeRight.stopMotor();
    }
    public void RollerReverse() {
    	intakeLeft.set((GrabberConst.leftRollerMaxSpeed * -1)); //the negative one is to make it rotate the other way
    	intakeRight.set((GrabberConst.rightRollerMaxSpeed * -1));
    }
    public void RollerReverseOff() {
    	intakeLeft.stopMotor();
    	intakeRight.stopMotor();
    }
    //----------------------------------------
    // Other methods
    //----------------------------------------
	public void pickupOff() {
		intakeLeft.disable();
	}
    
    private static boolean sLstatus;
    private static boolean sCstatus;
    private static boolean sRstatus;
    
    public boolean hasCube() { //TODO Allen: check everything that is here
    	
    	int sL = getSensorLOutput(); 
    	int sC = getSensorCOutput();
    	int sR = getSensorROutput();
    	int sensorsReceivingInput = getSensorsReceivingInput(sL, sR, sC);
    	
    	switch(sensorsReceivingInput) {
	    	case 2:
	    		return isDistanceWithinTwelveInches(sL, sR, sC);
	    	case 3:
	    		return compareAngles(getAngleBetweenSensors(sL, sC), getAngleBetweenSensors(sR, sC));
	    	default: return false;
    	}
    	// in development 
    	// TODO: eli: Algorithm needs to be developed
    	//       to figure out which sensor
    	//       input signals that it is "present"
    }
    
    private boolean isDistanceWithinTwelveInches(int leftSensorDistance, int rightSensorDistance, int centerSensorDistance) {
//    	if(sLstatus = false) leftSensorDistance = 99;
//    	if(sCstatus = false) centerSensorDistance = 99;
//    	if(sRstatus = false) rightSensorDistance = 99;
    	//not sure if above code is needed
    	if(!sLstatus) {
    		if(centerSensorDistance < GrabberConst.distanceWhenWeHaveCube 
    		&& rightSensorDistance < GrabberConst.distanceWhenWeHaveCube) return true; 		//cube is within 12in
    		else return false;
     	} else if(!sCstatus) {
     		if(leftSensorDistance < GrabberConst.distanceWhenWeHaveCube 
     		&& rightSensorDistance < GrabberConst.distanceWhenWeHaveCube) return true;		//cube is within 12in
     		else return false;
      	} else if(!sRstatus) {
      		if(centerSensorDistance < GrabberConst.distanceWhenWeHaveCube 
      		&& leftSensorDistance < GrabberConst.distanceWhenWeHaveCube) return true;		//cube is within 12 in
      		else return false;
      	} else return false;
    }
    
    private boolean isAngleWithinTwelveInches(double shallowAngle) {
    	//TODO: formula needs to be developed & implemented -ejo
    	return false;
    }
    
    private boolean compareAngles(double angle1, double angle2) {
    	if(angle1 == angle2) return true; 										//if angles are =, we have cube 
    	if(angle1 > angle2) return isAngleWithinTwelveInches(angle2);			//if shallow angle is within 12in, we have cube
    	if(angle2 > angle1) return isAngleWithinTwelveInches(angle1);			//if shallow angle is within 12in, we have cube
    	if((angle1 * -1) == angle2) return isAngleWithinTwelveInches(angle1);	//if shallow angle is within 12in, we have cube
    	return false;
    }
    	
    private int getSensorsReceivingInput(int leftSensorOutput, int rightSensorOutput, int centerSensorOutput) {
    	if(leftSensorOutput > GrabberConst.sensorMaxLength) sLstatus = false;
    	else sLstatus = true;
    	if(rightSensorOutput > GrabberConst.sensorMaxLength) sRstatus = false;
    	else sRstatus = true;
    	if(centerSensorOutput > GrabberConst.sensorMaxLength) sCstatus = false;
    	else sCstatus = true;
    	if(!sLstatus && !sRstatus && !sCstatus) return 0; 		// no sensors are receiving input
    	if(sLstatus || sRstatus || sCstatus) {					// any of the sensors are receiving input
    		if(sLstatus && !sRstatus && !sCstatus) return 1;	// one sensor (L) is receiving input
    		if(!sLstatus && sRstatus && !sCstatus) return 1;	// one sensor (R) is receiving input
    		if(!sLstatus && !sRstatus && sCstatus) return 1;	// one sensor (C) is receiving input
    		if(sLstatus && sRstatus && !sCstatus) return 2;		// two sensors (L & R) are receiving input
    		if(sLstatus && !sRstatus && sCstatus) return 2;		// two sensors (L & C) are receiving input
    		if(!sLstatus && sRstatus && sCstatus) return 2;		// two sensors (R & C) are receiving input
        	if(sLstatus && sRstatus && sCstatus) return 3; 		// all sensors are receiving input
    	} 
    	return 0; //if none of above conditions are met, return 0
    }
    
    private double getAngleBetweenSensors(int sideOutput, int centerOutput) {
    	if(sideOutput == GrabberConst.sensorMaxLength || centerOutput == GrabberConst.sensorMaxLength) return -361.0;
    	double y = centerOutput - sideOutput;
		double x = GrabberConst.distanceBetweenSensors;
		return Math.atan2(y, x);
	}
}

