package org.usfirst.frc330.commands.drivecommands;

import java.io.File;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

import edu.wpi.first.wpilibj.command.BBCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import jaci.pathfinder.Waypoint;

public class PathfinderDrive extends BBCommand {
	
	Trajectory leftTraj, rightTraj;
	DistanceFollower leftFollow, rightFollow;
	double gyroP;

	public PathfinderDrive(String path, double kP, double kI, double kD, double kV, double kA, double gyroP) {
		requires(Robot.chassis);
		this.gyroP = gyroP;
		
		Waypoint[] points = new Waypoint[] {
			    new Waypoint(2, 4, 0),
			    new Waypoint(14, 4, 0),
			    new Waypoint(19.75, 10, 90),
			    new Waypoint(19.75, 19, 90),
			    new Waypoint(24, 23, 0)
			};

		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 10.0, 20.0, 25.9);
		Trajectory trajectory = Pathfinder.generate(points, config);
		TankModifier modifier = new TankModifier(trajectory);
		modifier.modify(2.16);
		leftTraj = modifier.getLeftTrajectory();
		rightTraj = modifier.getRightTrajectory();
		
		//File myFile = new File(path + "_left_detailed.csv");
		//leftTraj = Pathfinder.readFromCSV(myFile);
		//myFile = new File(path + "_right_detailed.csv");
		//rightTraj = Pathfinder.readFromCSV(myFile);
		
		leftFollow = new DistanceFollower(leftTraj);
		rightFollow = new DistanceFollower(rightTraj);
		
		leftFollow.configurePIDVA(kP, kI, kD, kV, kA);
		rightFollow.configurePIDVA(kP, kI, kD, kV, kA);
		
		//leftFollow.configureEncoder(Robot.chassis.getLeftEncoderRaw(), (int)ChassisConst.pulsePerRevolution*4, ChassisConst.wheelDiameter/12.0);
		//rightFollow.configureEncoder(Robot.chassis.getRightEncoderRaw(), (int)ChassisConst.pulsePerRevolution*4, ChassisConst.wheelDiameter/12.0);
		
		CSVLoggable temp = new CSVLoggable(true) {
			public double get() { 
				if (!leftFollow.isFinished())
					return leftFollow.getSegment().position*12;
				else
					return 0.0;
			}
    	};
    	CSVLogger.getInstance().add("PFLeftPosition", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				if (!leftFollow.isFinished())
					return leftFollow.getSegment().velocity*12;
				else
					return 0.0;
			}
    	};
    	CSVLogger.getInstance().add("PFLeftVelocity", temp);
	
	}
	
	@Override
	protected void execute() {
		double left, right, curAngle;
		left = leftFollow.calculate(Robot.chassis.getLeftDistance()/12);
		right = rightFollow.calculate(Robot.chassis.getRightDistance()/12);
		curAngle = Robot.chassis.getAngle();
		
		double desired_heading = Pathfinder.r2d(leftFollow.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - curAngle);
        double turn = gyroP * angleDifference;
		
		Robot.chassis.tankDrive(left+turn, right-turn);

	}
	
	
	@Override
	protected boolean isFinished() {
		return leftFollow.isFinished() && rightFollow.isFinished();
	}
	
	@Override
	protected void end() {
		Robot.chassis.stopDrive();
		Logger.getInstance().println("DriveWaypoint Final Location   X: " + Robot.chassis.getX() + "  Y: " + Robot.chassis.getY(), Severity.INFO);
    	Logger.getInstance().println("DriveWaypoint Final DriveTrain Distances   Left: " + Robot.chassis.getLeftDistance() + "  Right: " + Robot.chassis.getRightDistance(), Severity.INFO);
	}

}
