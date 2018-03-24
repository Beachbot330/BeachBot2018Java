package org.usfirst.frc330.commands.drivecommands;

import java.util.ArrayList;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;

public class DrivePathBackward extends DrivePath {

	public DrivePathBackward(ArrayList<Waypoint> path, boolean invertX, double lookahead, double tolerance,
			double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains) {
		super(path, invertX, lookahead, tolerance, timeout, stopAtEnd, driveGains, gyroGains);
		// TODO Auto-generated constructor stub
	}

	@Override
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
