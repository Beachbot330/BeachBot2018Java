/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;
/*
 * This will drive the robot forwards to a waypoint on the field based on its 
 * original starting position.
 */
public class DriveWaypoint extends DriveDistanceAtAbsAngle_NoTurn {
    double x,y;

    
    public DriveWaypoint(double x, double y, double tolerance, double timeout, boolean stopAtEnd, DrivePIDGains driveGains, DrivePIDGains gyroGains) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(0,tolerance,0,timeout, stopAtEnd, driveGains, gyroGains);
        this.x=x;
        this.y=y;
    }
    
    public DriveWaypoint(Waypoint wp, boolean invertX, double tolerance, double timeout, boolean stopAtEnd, DrivePIDGains driveGains, DrivePIDGains gyroGains) {
    	super(0,tolerance,0,timeout, stopAtEnd, driveGains, gyroGains);
    	if (invertX)
    		this.x = -wp.getX();
    	else
    		this.x = wp.getX();
    	this.y = wp.getY();
    }

    protected void initialize() {
        calcXY(x,y);
        super.initialize();
    }
    
    protected void calcXY(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, calcDistance, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcDistance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        calcAngle = Math.toDegrees(Math.atan2(deltaX, deltaY));
        
        if (Double.isNaN(calcAngle) || Double.isInfinite(calcAngle))
        {
        	Logger.getInstance().println("Infinite calcAngle in DriveWaypoint", Severity.ERROR);
            calcAngle = 0;
        }
        
        robotAngle = Robot.chassis.getAngle();
        
        if (Double.isNaN(robotAngle) || Double.isInfinite(robotAngle))
        {
        	Logger.getInstance().println("Infinite robotAngle in DriveWaypoint", Severity.ERROR);
            robotAngle = 0;
        }
        if (Math.abs(robotAngle-calcAngle)<180)
        {
            //do nothing
        }
        else if (robotAngle-calcAngle > 180)
        {
            while (robotAngle-calcAngle > 180)
                calcAngle += 360;
        }
        else 
        {
            while (robotAngle-calcAngle < -180)
                calcAngle -= 360;
        }
        Logger.getInstance().println("DriveWaypoint x: " + x + " y: " + y + " curX: " + curX + " curY: " + curY + " curAngle: " + robotAngle, Severity.INFO);
        Logger.getInstance().println("DriveWaypoint distance: " + calcDistance + " angle: " + calcAngle, Severity.INFO);
        
        leftDistance = calcDistance;
        rightDistance = calcDistance;
        angle = calcAngle;
        
    }
    protected void end() {
    	Severity severity = Severity.INFO;
    	if (isTimedOut()) {
    		severity = Severity.WARNING;
    	}
    	Logger.getInstance().println("DriveWaypoint Final Location   X: " + Robot.chassis.getX() + "  Y: " + Robot.chassis.getY(), severity);
    	Logger.getInstance().println("DriveWaypoint Final DriveTrain Distances   Left: " + Robot.chassis.getLeftDistance() + "  Right: " + Robot.chassis.getRightDistance(), severity);
        super.end();
    }
    
    protected boolean isFinished() {
    	boolean first = Robot.chassis.leftDrivePID.onTarget();
    	boolean second = Robot.chassis.rightDrivePID.onTarget();
    	boolean third = isTimedOut();
        if (first || second || third)
        {
            return true;            
        }
        return false;
    }

}
