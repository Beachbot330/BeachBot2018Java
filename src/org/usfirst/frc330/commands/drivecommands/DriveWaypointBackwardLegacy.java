/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
/*
 * This will drive the robot forwards to a waypoint on the field based on its 
 * original starting position.
 */
import org.usfirst.frc330.wpilibj.PIDGains;

public class DriveWaypointBackwardLegacy extends DriveWaypointBackward {
//	double leftDistance, rightDistance;
	
    public DriveWaypointBackwardLegacy(double x, double y, double tolerance, double timeout, boolean stopAtEnd, DrivePIDGains driveGains, DrivePIDGains gyroGains) {
        super(x, y, tolerance, timeout, stopAtEnd, driveGains, gyroGains);
    }

    protected boolean isFinished() {
        if ((Robot.chassis.leftDrivePID.onTarget() || Robot.chassis.rightDrivePID.onTarget()) || isTimedOut())
        {
                return true;            
        }
        return false;
    }
}
