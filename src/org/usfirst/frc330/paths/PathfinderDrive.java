package org.usfirst.frc330.paths;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
		
		File leftFile = new File("/home/lvuser/" + path + "_left_detailed.csv");
		
		Logger.getInstance().println(getClass().getResource(path + "_left_detailed.csv").toString(), true);
		
		File rightFile = new File("/home/lvuser/" + path + "_right_detailed.csv");

		try {
		Files.copy(getClass().getResource(path + "_left_detailed.csv").openStream(), leftFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(getClass().getResource(path + "_right_detailed.csv").openStream(), rightFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			Logger.getInstance().printStackTrace(ex);
		}
		
		leftTraj = Pathfinder.readFromCSV(leftFile);
		rightTraj = Pathfinder.readFromCSV(rightFile);
		
		leftFollow = new DistanceFollower(leftTraj);
		rightFollow = new DistanceFollower(rightTraj);
		
		leftFollow.configurePIDVA(kP, kI, kD, kV, kA);
		rightFollow.configurePIDVA(kP, kI, kD, kV, kA);
			
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
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				if (!leftFollow.isFinished())
					return Math.toDegrees(-leftFollow.getSegment().heading);
				else
					return 0.0;
			}
    	};
    	CSVLogger.getInstance().add("PFHeading", temp);
	
	}
	
	@Override
	protected void execute() {
		double left, right, curAngle;
		left = leftFollow.calculate(Robot.chassis.getLeftDistance()/12);
		right = rightFollow.calculate(Robot.chassis.getRightDistance()/12);
		curAngle = -Robot.chassis.getAngle();
		
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
		Logger.getInstance().println("PathfinderDrive Final Location   X: " + Robot.chassis.getX() + "  Y: " + Robot.chassis.getY(), Severity.INFO);
    	Logger.getInstance().println("PathfinderDrive Final DriveTrain Distances   Left: " + Robot.chassis.getLeftDistance() + "  Right: " + Robot.chassis.getRightDistance(), Severity.INFO);
	}

}
