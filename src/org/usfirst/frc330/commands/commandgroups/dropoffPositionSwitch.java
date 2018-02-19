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

import org.usfirst.frc330.commands.SetArmAngle;
import org.usfirst.frc330.commands.SetHandAngle;
import org.usfirst.frc330.commands.SetLiftPosition;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.LiftConst;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.subsystems.*;

/**
 *
 */
public class dropoffPositionSwitch extends BBCommandGroup {

	//VERIFY -mf
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public dropoffPositionSwitch() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    	addParallel(new SetArmAngle(ArmConst.switchArm));
    	addParallel(new SetHandAngle(HandConst.leveledWrist));
    	//set lift position to switch position
    	addSequential(new SetLiftPosition(LiftConst.switchDropoff));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
 
    } 
}
