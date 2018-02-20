package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

import edu.wpi.first.wpilibj.command.BBCommand;

/**
 *  Waits until the robot has started driving past a waypoint number in the path.
 *  Useful for triggering commands for other subsystem based on driver 
 */
public class WaitForWaypoint extends BBCommand {
	int waypoint;

    public WaitForWaypoint(int waypoint) {
    	this.waypoint = waypoint;
    }
    
    @Override
    protected void initialize() {
    	if (waypoint > Robot.chassis.getLastWaypointNumber())
    		Logger.getInstance().println("WaitForWaypoint: Given Waypoint, " + waypoint + 
    				" exceeds last waypoint in path, " + Robot.chassis.getLastWaypointNumber(), Severity.ERROR);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassis.getCurrentWaypointNumber() >= waypoint;
    }
}
