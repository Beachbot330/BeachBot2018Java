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


import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.WristConst;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Hand extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX wrist;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Hand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        wrist = new WPI_TalonSRX(2);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		wrist.setInverted(false);
		wrist.setSensorPhase(false);
		setPIDConstants(WristConst.proportional,WristConst.integral,WristConst.derivative,true);
		/*
        setLowerSoftLimit(ArmConst.limitLowerAngle);
		setUpperSoftLimit(ArmConst.limitUpperAngle);
		*/
		wrist.configForwardSoftLimitEnable(true, 10);
		wrist.configReverseSoftLimitEnable(false, 10);
		wrist.setNeutralMode(NeutralMode.Brake);
		//create constants for these two in ArmConst
		wrist.configOpenloopRamp(WristConst.VoltageRampRate, 10);
		wrist.configNominalOutputForward(WristConst.MaxOutputVoltage, 10);
		
		
    }
  
    
    
  //VERIFY Implement setWristOutput -JB
    
    public void setWrist(double output) {
    //	changeControlMode(ControlMode.PercentOutput);
    	wrist.set(ControlMode.PercentOutput, output);
    	wrist.set(output);
    }
    public void stopWrist() {
		wrist.disable();
		Logger.getInstance().println("Wrist disabled", Logger.Severity.INFO);
	}
    
    //VERIFY Implement setWristAngle -JB
    public void setAngle(double position) {
    	wrist.set(ControlMode.Position, 0);
    	wrist.set(convertDegreesToRotations(position));
    //	if (SCtable != null)
    //		SCtable.putNumber("setpoint", position);
    }
    public void setPIDConstants (double P, double I, double D, boolean timeout)
   	{
       	if(timeout) {
       		//assume using main PID loop (index 0)
       		wrist.config_kP(0, P, 10);
       		wrist.config_kI(0, I, 10);
       		wrist.config_kD(0, D, 10);
       	}
       	else {
   	    	//assume using main PID loop (index 0)
   			wrist.config_kP(0, P, 0);
   			wrist.config_kI(0, I, 0);
   			wrist.config_kD(0, D, 0);
       	}
       	
           Logger.getInstance().println("Wrist PID set to: " + P + ", " + I + ", " + D, Severity.INFO);
   	}
    double tolerance = 0;
  //VERIFY Implement getHandOnTarget - MF
  	public boolean getHandOnTarget() {
  		double error = wrist.getClosedLoopError(0);
      	return (Math.abs(error) < tolerance);
  	}
  //VERIFY IMplement setMaxWristOutput -JB
    public void setMaxWristOutput(double percentOut){
    	wrist.configNominalOutputForward(percentOut,10);
    	Logger.getInstance().println("Max wrist output set to: " + percentOut, Severity.INFO);
    }
  //VERIFY implement getWristOUtput - JB
    public double getWristOutput() {
  		return wrist.getMotorOutputVoltage()/wrist.getBusVoltage();
  	}
    
    //VERIFY Implement getWristLowerLimit, getWristUpperLimit -JB
    public double getWristLowerLimit()
	{
		return (convertTicksToDegrees((int)wrist.configGetParameter(ParamEnum.eForwardSoftLimitThreshold, 0, 0)));

	}
    public double getWristUpperLimit()
	{
		return (convertTicksToDegrees((int)wrist.configGetParameter(ParamEnum.eReverseSoftLimitThreshold, 0, 0)));

	}
	
	//VERIFY IMplement getLowerWristLimitTripped, getUpperWristLimitTripped -ejo
    // Double check usage of getFaults(Faults); Don't need to pass a reference?
    public boolean getLowerLimitTripped(){
    	Faults allFaults = new Faults();	//define a faults variable with a new Faults() object (creates a list of faults w/out booleans)
    	wrist.getFaults(allFaults);			//passes empty list of faults to be updated. 
		return allFaults.ForwardSoftLimit; 	//return boolean of ForwardSoftLimit fault
	}
    
    public boolean getUpperLimitTripped(){
    	Faults allFaults = new Faults();
    	wrist.getFaults(allFaults);			 
		return allFaults.ReverseSoftLimit;
	}
    
  //VERIFY implement getWristAngle -JB
    public double getWristAngle()
	{
		return (-convertRotationsToDegrees(wrist.getSelectedSensorPosition(0)));
	} 
    
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new HandLevel());

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
    //VERIFY change below code to be wrist, not arm -ejo
    private int convertDegreesToTicks(double degrees) {
    	return (int)(degrees * WristConst.maxEncoderCounts / WristConst.maxAngleDegrees + 0.5);
    }
    
    private double convertTicksToDegrees(int ticks) {
    	return (ticks * WristConst.maxAngleDegrees / WristConst.maxEncoderCounts);
    }
    
    private double convertDegreesToRotations(double degrees) {
    	return (-degrees / WristConst.maxAngleDegrees);
    }
    
    private double convertRotationsToDegrees(double rotations) {
    	return (rotations * WristConst.maxAngleDegrees);
    }

}

