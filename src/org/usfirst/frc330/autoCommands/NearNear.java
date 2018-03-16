package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.autoCommands.Chooser_RightLeftStart.StartingPosition;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class NearNear extends BBCommandGroup {
	
	Waypoint wp1 = new Waypoint(0, -182, 0);
	Waypoint wp2 = new Waypoint(29, -256-18, 0); //Dropoff at scale
	Waypoint wp3 = new Waypoint(38, -212-8, 0); //Drive to cube
	Waypoint wp4 = new Waypoint(40, -196-12, 0); //Dropoff at switch
	
	//Switch 296 Red right
	//198, 196, 196, 195
	

    public NearNear(StartingPosition pos) {
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addParallel(new Defense());
    	
    	BBCommand parallelCommand = new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new CheckDone(parallelCommand));
    	
    	BBCommandGroup parallelGroup = new DropoffPositionRear();
    	addParallel(parallelGroup);
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Taller());
    	
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new CheckDone(parallelGroup));
    	PIDGains DriveMid     = new PIDGains(0.050,0,0.70,0,0.6,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
    	addSequential(new DriveWaypointBackward(wp2, invertX, ChassisConst.defaultTolerance, 5, false, DriveMid, ChassisConst.GyroDriveHigh));
    	addSequential(new OpenClaw());
    	
    	addSequential(new WaitCommand(0.5));
    	addParallel(new IntakeCube()); //Doesn't return until is has a cube
    	addSequential(new WaitCommand(2.0));
    	
    	addSequential(new Log("Before cube pickup"));
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp3, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp3, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new Log("Cube picked up"));
    	
    	addSequential(new WaitCommand(1.0));
    	addSequential(new DropoffPositionSwitch());
    	addSequential(new Taller());
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp4, invertX, ChassisConst.defaultTolerance, 2, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new OpenClaw());
       
    }
}
