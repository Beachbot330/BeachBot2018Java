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
import org.usfirst.frc330.autoCommands.CenterStart_Switch.SwitchPosition;
import org.usfirst.frc330.autoCommands.Chooser_RightLeftStart.StartingPosition;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveWaypointBackward;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;



/**
 *
 */
public class Chooser_Coop extends BBCommand {
	
	String gameData;
	Command nearNear, nearFar, farFar, farNear, driveForward;
	StartingPosition startingPosition;
	
    public Chooser_Coop(StartingPosition pos) {
    	this.setRunWhenDisabled(false);
    	driveForward = new DriveWaypointBackward(0, -ChassisConst.driveStraightAuto, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	//nearNear = new NearNear(pos);
    	nearNear = new Flinger(pos);
    	//nearNear = new FarNear(pos);
    	nearFar = new NearFar(pos);
    	//farFar = new FarFar(pos);
    	farFar = new NearFar(pos);
    	//farNear = new FarNear(pos);
    	farNear = new Flinger(pos);
    	this.startingPosition = pos;
    	this.setName(this.getName() + ": " + pos.toString());
    }

    protected void initialize() {
		
    }


    protected void execute() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	String positionCombo;
    	if(gameData.length() > 0) {
	    	if (startingPosition == StartingPosition.LEFT)
	    		positionCombo = "L" + gameData.substring(0, 2);
	    	else
	    		positionCombo = "R" + gameData.substring(0, 2) ;
	    	switch(positionCombo) {
		    	case "LLL":
		    		nearNear.start();
		    		break;
		    	case "LLR":
		    		nearFar.start();
		    		break;
		    	case "LRL":
		    		farNear.start();
		    		break;
		    	case "LRR":
		    		farFar.start();
		    		break;
		    	case "RLL":
		    		farFar.start();
		    		break;
		    	case "RLR":
		    		farNear.start();
		    		break;
		    	case "RRL":
		    		nearFar.start();
		    		break;
		    	case "RRR":
		    		nearNear.start();
		    		break;
	    		default:
	    			Logger.getInstance().println("Driving forward due to invalid data", Severity.ERROR);
	    			Logger.getInstance().println("positionCombo: " + positionCombo, Severity.DEBUG);
	    			driveForward.start();
	    			break;
	    	}
    	}    	
    }

    protected boolean isFinished() {
        return gameData.length() > 0 || isTimedOut();
    }

    protected void end() {
    	if (isTimedOut()) {
    		Logger.getInstance().println("No game data (or invalid data) received. Out of time, driving forward.", Severity.ERROR);
    		new DriveWaypointBackward(0, -ChassisConst.driveStraightAuto, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh).start();
    	}
    	else
    		Logger.getInstance().println("The game data we received: " + gameData, Severity.INFO);
    }


    protected void interrupted() {
    }
}
