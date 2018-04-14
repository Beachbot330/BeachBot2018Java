package org.usfirst.frc330.commands.drivecommands;

import java.util.ArrayList;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;

public class DrivePath extends DriveWaypoint {
	
	double lookahead;
	Waypoint currentLocation = new Waypoint(0,0,0);
	ArrayList<Waypoint> path;
	
	public DrivePath(ArrayList<Waypoint> path, double lookahead, double tolerance, double timeout, boolean stopAtEnd, 
			DrivePIDGains driveGains, DrivePIDGains gyroGains) {
		super(0,0,tolerance,timeout,stopAtEnd, driveGains, gyroGains);
		this.path = path;
		this.lookahead = lookahead;
	}
	
	
	Waypoint tempWaypoint;
	@Override
	protected void initialize() {
		Robot.chassis.setPath(path);
		updatePosition();
		tempWaypoint = calcInterpWaypoint();
		Logger.getInstance().println(tempWaypoint.toString(), Severity.INFO);
		x = tempWaypoint.x;
		y = tempWaypoint.y;
		super.initialize();
	}



	@Override
	protected void execute() {
		updatePosition();
		tempWaypoint = calcInterpWaypoint();
		x = tempWaypoint.x;
		y = tempWaypoint.y;
		Logger.getInstance().println("CalcInterpWaypoint: " + tempWaypoint.toString(), Severity.INFO);
		calcXY(x,y);
        leftSetpoint = leftDistance+Robot.chassis.getLeftDistance();
        rightSetpoint = rightDistance+Robot.chassis.getRightDistance();
        Robot.chassis.leftDrivePID.setSetpoint(leftSetpoint);
        Robot.chassis.rightDrivePID.setSetpoint(rightSetpoint);
        Robot.chassis.gyroPID.setSetpoint(angle);
		super.execute();
	}
	

	public void updatePosition() {
		currentLocation.setX(Robot.chassis.getX());
		currentLocation.setY(Robot.chassis.getY());
		currentLocation.setHeading(Robot.chassis.getAngle());
	}

	public Waypoint calcInterpWaypoint() {
		if (Robot.chassis.getCurrentWaypointNumber() == Robot.chassis.getLastWaypointNumber() && Robot.chassis.getDistanceToNextWaypoint() < lookahead)  {
			return (Robot.chassis.getCurrentWaypoint());
		} else if (Robot.chassis.getDistanceToNextWaypoint() > lookahead && Robot.chassis.getCurrentWaypointNumber() == 0) {
			return (Robot.chassis.getCurrentWaypoint());
		} else {
			while (Robot.chassis.getCurrentWaypointNumber() != Robot.chassis.getLastWaypointNumber() && Robot.chassis.getDistanceToNextWaypoint() < lookahead)
				Robot.chassis.incrementWaypoint();
			if (Robot.chassis.getCurrentWaypointNumber() == Robot.chassis.getLastWaypointNumber() && Robot.chassis.getDistanceToNextWaypoint() < lookahead) {
				return (Robot.chassis.getCurrentWaypoint());
			}
		}

		double A = Robot.chassis.getDistanceBetweenWaypoints(currentLocation, Robot.chassis.getPreviousWaypoint());
		double B = Robot.chassis.getDistanceBetweenWaypoints(Robot.chassis.getPreviousWaypoint(), Robot.chassis.getCurrentWaypoint());
		double C = Robot.chassis.getDistanceBetweenWaypoints(currentLocation, Robot.chassis.getCurrentWaypoint());
		double alpha = Math.acos((A*A - B*B + C*C)/(2*A*C));
		if (Math.abs(alpha) > Math.PI - 0.02)
			return (Robot.chassis.getCurrentWaypoint());
		double gamma = Math.acos((A*A + B*B - C*C)/(2*A*B));
		double delta = Math.PI-alpha-gamma;
		double beta = Math.asin(A/lookahead*Math.sin(gamma));
		double theta = Math.PI-beta - gamma;
		double d = lookahead*Math.sin(theta)/Math.sin(gamma);
		double wayX = d/B*(Robot.chassis.getCurrentWaypoint().getX()-Robot.chassis.getPreviousWaypoint().getX()) + Robot.chassis.getPreviousWaypoint().getX();
		double wayY = d/B*(Robot.chassis.getCurrentWaypoint().getY()-Robot.chassis.getPreviousWaypoint().getY()) + Robot.chassis.getPreviousWaypoint().getY();
		Logger.getInstance().println("A: " + A + " B: " + B + " C: " + C + " alpha: " + Math.toDegrees(alpha) + " gamma: " +  Math.toDegrees(gamma)
				+ " beta: " +  Math.toDegrees(beta) + " theta: " +  Math.toDegrees(theta) + " d: " + d + " wayX: " + wayX + " wayY: " + wayY, Severity.INFO);
		return new Waypoint(
				wayX,
				wayY,
				Math.toDegrees(alpha));
	}

}
