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

import org.usfirst.frc330.commands.RelativeWristPosition;
import org.usfirst.frc330.commands.SetArmAngle;
import org.usfirst.frc330.commands.SetHandAngle;
import org.usfirst.frc330.commands.SetLiftPosition;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.LiftConst;
import org.usfirst.frc330.constants.WristConst;


public class dropoffPositionLow extends BBCommandGroup {

	//VERIFY -mf
    public dropoffPositionLow() {
    	
    	addParallel(new SetArmAngle(ArmConst.minArm));
    	//VERIFY MAKENA create a second function for setting the wrist angle relative to the ground
    	addParallel(new RelativeWristPosition(WristConst.leveledWrist)); //keeps hard level in relation to the floor
    	addSequential(new SetLiftPosition(LiftConst.scaleDropoffMin));
 
    } 
}
