package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnCamera extends TurnGyroRel {
	String cameraName;
	int toleranceStableCount;
	
	
	/**
	 * Turn the base until it aligns with the vision target.
	 * 
	 * @param cameraName name of the camera Distance/Detected (airship or target)
	 * @param tolerance degrees
	 * @param toleranceStableCount number of iterations to be within tolerance before finishing. Should be 10 or more to account for camera latency.
	 * @param timeout seconds
	 * @param stopAtEnd
	 * @param gains
	 */
	public TurnCamera(String cameraName, double tolerance, int toleranceStableCount, double timeout, boolean stopAtEnd, DrivePIDGains gains) {
		super(0, tolerance, timeout, stopAtEnd, gains);
		this.cameraName = cameraName;
		this.toleranceStableCount = toleranceStableCount;
	}

	/**
	 * Turn the base until it aligns with the vision target.
	 * 
	 * @param cameraName name of the camera Distance/Detected (airship or target)
	 * @param tolerance degrees
	 * @param toleranceStableCount number of iterations to be within tolerance before finishing. Should be 10 or more to account for camera latency.
	 * @param gains
	 */
	public TurnCamera(String cameraName, double tolerance, int toleranceStableCount, DrivePIDGains gains) {
		super(0, tolerance, gains);
		this.cameraName = cameraName;
		this.toleranceStableCount = toleranceStableCount;
	}
	
	@Override
	public void initialize() {
		if (!SmartDashboard.containsKey(cameraName+"Detected"))
			Logger.getInstance().println("Camera Name: " + cameraName + " Not detected", Severity.ERROR);
		toleranceCount = 0;
		angle = getCameraAngle();
		super.initialize();
	}
	
	@Override
	public void execute() {
		if (Math.abs(getCameraAngle() - angle) > tolerance) {
			angle = getCameraAngle();
			Robot.chassis.gyroPID.setSetpoint(angle+Robot.chassis.getAngle());
			toleranceCount = 0;
		}
			
		super.execute();
	}
	
	int toleranceCount = 0;
	
	@Override
	public boolean isFinished() {
		if (super.isFinished())
			toleranceCount++;
		else
			toleranceCount = 0;
		return (getCameraDetected() && toleranceCount > toleranceStableCount) || isTimedOut();   //
	}
	
	public double getCameraAngle() {
		if (getCameraDetected())
			return SmartDashboard.getNumber(cameraName+"Angle", 0);
		else
			return 0;
	}
	
	public boolean getCameraDetected() {
		return SmartDashboard.getBoolean(cameraName+"Detected", false);
	}
}
