/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
/*
 * This will drive the robot forwards to a waypoint on the field based on its 
 * original starting position.
 */
import org.usfirst.frc330.wpilibj.PIDGains;

public class DriveWaypointBackward extends DriveWaypoint {
//	double leftDistance, rightDistance;
	
	
	public DriveWaypointBackward(double x, double y, double tolerance, double timeout, boolean stopAtEnd, DrivePIDGains driveGains, DrivePIDGains gyroGains){
        super(x, y, tolerance, timeout, stopAtEnd, driveGains, gyroGains);
    }
	
    public DriveWaypointBackward(Waypoint wp, boolean invertX, double tolerance, double timeout, boolean stopAtEnd, DrivePIDGains driveGains, DrivePIDGains gyroGains) {
    	super(wp, invertX, tolerance, timeout, stopAtEnd, driveGains, gyroGains);

    }

    protected void calcXY(double x, double y) {
        double gyroAngle;
        
        super.calcXY(x, y);

        leftDistance = -leftDistance;
        rightDistance = -rightDistance;
        
        gyroAngle = Robot.chassis.getAngle();
        if (gyroAngle < angle)
            angle = angle-180;
        else
            angle = angle+180;
        Logger.getInstance().println("Backward Angle: " + angle, Severity.INFO);
    }
}
