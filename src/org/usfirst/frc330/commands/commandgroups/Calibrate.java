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

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.constants.*;

/**
 *
 */
public class Calibrate extends BBCommandGroup {


    public Calibrate() {

    	addSequential(new CalibrateWrist()); //Calibrate Wrist First
    	addSequential(new CalibrateArm()); //Calibrate the other two at the same time
    	addSequential(new CalibrateLift());
    	addSequential(new Defense());
    	
    } 
}
