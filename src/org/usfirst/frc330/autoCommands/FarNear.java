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
public class FarNear extends BBCommandGroup {
	
	Waypoint wp1 = new Waypoint(0, -182, 0);
	//Waypoint wp2 = new Waypoint(29, -256-18, 0); //Dropoff at scale
	Waypoint wp2 = new Waypoint(29, -256-18-4, 0);
	//Waypoint wp3 = new Waypoint(38, -212-8, 0); //Drive to cube
	Waypoint wp3 = new Waypoint(38, -212-8-1, 0);
	Waypoint wp4 = new Waypoint(29-4, -256-7, 0); //Drive back to scale
	Waypoint wp5 = new Waypoint(29-1, -256-20, 0); // Second drop off

    public FarNear(StartingPosition pos) {
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addParallel(new Defense());
    	
    	//Drive away from wall
    	BBCommand parallelCommand = new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.5));
    	//addSequential(new DropoffPositionRear());
    	addSequential(new CheckDone(parallelCommand));
    	
    	BBCommandGroup parallelGroup = new DropoffPositionRear();
    	addParallel(parallelGroup);
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Taller());
    	
    	//Drive to Scale and Dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new CheckDone(parallelGroup));
    	addSequential(new SetHandAngle(190));
    	//PIDGains tempDrive  = new PIDGains(0.050,0,0.70,0,0.7,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
    	PIDGains tempDrive  = new PIDGains(0.050,0,0.70,0,0.6,ChassisConst.defaultMaxOutputStep, "DriveHigh");
    	addSequential(new DriveWaypointBackward(wp2, invertX, ChassisConst.defaultTolerance, 5, false, tempDrive, ChassisConst.GyroDriveHigh));
    	//addSequential(new OpenClaw());
    	addParallel(new DeployCube());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new OpenClaw());
    	
    	addSequential(new WaitCommand(0.5));
    	addParallel(new IntakeCube()); //Doesn't return until is has a cube
    	addSequential(new WaitCommand(0.5)); //Can we shorten this?
    	
    	//Go to pickup a cube
    	addSequential(new Log("Before cube pickup"));
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp3, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp3, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new Log("Cube picked up"));
    	
    	//Return to scale and dropoff
    	addSequential(new WaitCommand(1.0)); //Can we shorten this?
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp4, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.6));
    	addSequential(new DropoffPositionRear());
    	addSequential(new Taller());
    	addSequential(new CheckDone(parallelCommand));
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp5, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new ShiftHigh());
    	tempDrive  = new PIDGains(0.050,0,0.70,0,0.7,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
    	addSequential(new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, tempDrive, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
    	
    	//addSequential(new SetHandAngle(190));
    	//addParallel(new DeployCube());
    	//addSequential(new WaitCommand(0.1));
    	addSequential(new OpenClaw());
       
    }
}
