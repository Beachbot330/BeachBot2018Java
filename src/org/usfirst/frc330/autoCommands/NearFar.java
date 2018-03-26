package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.autoCommands.Chooser_RightLeftStart.StartingPosition;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.constants.LiftConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class NearFar extends BBCommandGroup {

	
	Waypoint wp1 = new Waypoint(0,   -230, 0); //Turn to go down path
	Waypoint wp2 = new Waypoint(212, -230, 0); //Drive to scale
	Waypoint wp3 = new Waypoint(200, -279, 0); //Dropoff at scale
	Waypoint wp4 = new Waypoint(186, -223, 0); //Drive to cube
	Waypoint wp5 = new Waypoint(204, -263, 0); //Drive back to scale
	Waypoint wp6 = new Waypoint(204, -276, 0); //Second scale dropoff
	Waypoint wp7 = new Waypoint(186+15, -223, 0); //Pickup third cube

    public NearFar(StartingPosition pos) {
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addParallel(new Defense());
    	
    	//Drive away from wall
    	addSequential(new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));

    	//Turn down aisle
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Lift arm (low CG)
    	//addParallel(new CoordinatedMove(ArmConst.safeAngle, HandConst.rearLevel));
    	
    	//Drive down aisle
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Lift arm to scale dropoff location
    	addParallel(new SetLiftPosition(LiftConst.scaleDropoffMid + 5));
    	Command parallelCommand = new CoordinatedMove(ArmConst.vertical, HandConst.rearLevel);
    	addParallel(parallelCommand);
    	
    	//Drive To Scale
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp3, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	PIDGains driveMid     = new PIDGains(0.050,0,0.70,0,0.6,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
    	addSequential(new DriveWaypointBackward(wp3, invertX, ChassisConst.defaultTolerance, 5, false, driveMid, ChassisConst.GyroDriveHigh));
    	
    	//Aim low and shoot cube
    	addSequential(new SetHandAngle(190));
    	addParallel(new DeployCube());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new OpenClaw());
    	
    	//Get into pickup position
    	addSequential(new WaitCommand(0.5));
    	addParallel(new IntakeCube()); //Doesn't return until is has a cube
    	addSequential(new WaitCommand(0.5)); //Can we shorten this?
    	
    	//Go to pickup a cube
    	addSequential(new Log("Before cube pickup"));
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp4, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(1.0)); //Can we shorten this?
    	addSequential(new Log("Cube picked up"));
    	
    	//Return to scale and dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp5, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Move lift while driving to prep location
    	addParallel(new SetLiftPosition(LiftConst.scaleDropoffMid + 5));
    	
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.6));
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Get into dropoff position
    	parallelCommand = new CoordinatedMove(ArmConst.vertical, HandConst.rearLevel);
    	addParallel(parallelCommand);
    	
    	//Drive to scale for second dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp6, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new ShiftHigh());
    	PIDGains tempDrive  = new PIDGains(0.050,0,0.70,0,0.7,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
    	addSequential(new DriveWaypointBackward(wp6, invertX, ChassisConst.defaultTolerance, 5, false, tempDrive, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
    	
    	//Aim low and shoot second cube
    	addSequential(new CheckDone(parallelCommand)); //Check that arm is in final location before changing hand angle
    	addSequential(new SetHandAngle(190));
    	addParallel(new DeployCube());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new OpenClaw());
    	
    	//Get into pickup position
    	addSequential(new WaitCommand(0.5));
    	addParallel(new IntakeCube());
    	addSequential(new WaitCommand(0.5)); //Can we shorten this?
    	
    	//Go to pickup a new cube
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp7, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp7, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(1.0)); //Can we shorten this?
    	addSequential(new Log("Cube picked up"));
    	
    	//Move lift while driving to prep location
    	addParallel(new SetLiftPosition(LiftConst.scaleDropoffMid + 5));
    	
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.6));
    	addSequential(new CoordinatedMove(ArmConst.vertical, HandConst.rearLevel));
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Get into dropoff position
    	parallelCommand = new CoordinatedMove(ArmConst.vertical, HandConst.rearLevel);
    	addParallel(parallelCommand);
    	
    	//Drive to scale for third dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp6, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new ShiftHigh());
    	tempDrive  = new PIDGains(0.050,0,0.70,0,0.7,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
    	addSequential(new DriveWaypointBackward(wp6, invertX, ChassisConst.defaultTolerance, 5, false, tempDrive, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
    	
    	//Aim low and shoot third cube
    	addSequential(new CheckDone(parallelCommand)); //Check that arm is in final location before changing hand angle
    	addSequential(new SetHandAngle(190));
    	addParallel(new DeployCube());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new OpenClaw());
       
    }
}
