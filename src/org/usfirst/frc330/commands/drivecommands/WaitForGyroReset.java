package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForGyroReset extends BBCommand {

    public WaitForGyroReset() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.chassis.getAngle()) < 0.2;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
