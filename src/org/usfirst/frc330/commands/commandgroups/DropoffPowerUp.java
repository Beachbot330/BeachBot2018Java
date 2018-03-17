// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands.commandgroups;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.*;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.Robot;

/**
 *
 */
public class DropoffPowerUp extends BBCommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public DropoffPowerUp() {
    	
    	addSequential(new SetHandAngleRelArm(HandConst.encFrameSafe));
    	//lower lift and arm to intake position
    	addParallel(new SetLiftPosition(LiftConst.powerupDropoff));
    	addSequential(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp));
    	
    } 
}