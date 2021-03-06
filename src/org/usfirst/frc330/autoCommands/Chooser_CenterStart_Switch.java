// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.autoCommands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.autoCommands.CenterStartSwitchMulti.SwitchPosition;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;

/**
 *
 */
public class Chooser_CenterStart_Switch extends BBCommand {
	
	String gameData;
	Command leftSwitch, rightSwitch;
	
    public Chooser_CenterStart_Switch() {
    	this.setRunWhenDisabled(false);
    	//leftSwitch = new CenterStart_Switch(SwitchPosition.LEFT);
    	//rightSwitch = new CenterStart_Switch(SwitchPosition.RIGHT);
    	leftSwitch = new CenterStartSwitchMulti(SwitchPosition.LEFT);
    	rightSwitch = new CenterStartSwitchMulti(SwitchPosition.RIGHT);
    }

    protected void initialize() {
		
    }


    protected void execute() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0) {
			if(gameData.charAt(0) == 'L') {
				leftSwitch.start();
				Logger.getInstance().println("Our Switch is on the left", Severity.INFO);
			}
			else if (gameData.charAt(0) == 'R') {
				rightSwitch.start();
				Logger.getInstance().println("Our Switch is on the right", Severity.INFO);
			}
			else {
				//new DriveDistance(ChassisConst.driveStraightAuto, ChassisConst.defaultTolerance, 5.0, true, ChassisConst.DriveHigh).start();
				Logger.getInstance().println("Unknown gameData: " + gameData, Severity.ERROR);
				//Logger.getInstance().println("Driving forward due to invalid data", Severity.ERROR);
			}
		}
    }

    protected boolean isFinished() {
        return gameData.length() > 0 || isTimedOut();
    }

    protected void end() {
    	if (isTimedOut()) {
    		Logger.getInstance().println("No game data (or invalid data) received.", Severity.ERROR);
    		//new DriveDistance(ChassisConst.driveStraightAuto, ChassisConst.defaultTolerance, 5.0, true, ChassisConst.DriveHigh).start(); 
    			//double distance, double tolerance, double timeout, boolean stopAtEnd, PIDGains gains
    	}
    }


    protected void interrupted() {
    }
}
