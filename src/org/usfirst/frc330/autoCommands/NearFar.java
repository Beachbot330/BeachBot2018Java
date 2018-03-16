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
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class NearFar extends BBCommandGroup {

	
	Waypoint wp1 = new Waypoint(0,   -230, 0); //Turn to go down path
	Waypoint wp2 = new Waypoint(212, -230, 0); //Drive to scale
	Waypoint wp3 = new Waypoint(200, -256-18, 0); //Dropoff at scale
	Waypoint wp4 = new Waypoint(186, -212-7, 0); //Drive to cube
	Waypoint wp5 = new Waypoint(200+4, -256-7, 0); //Drive back to scale
	Waypoint wp6 = new Waypoint(200+4, -256-20, 0); //Second scale dropoff
	

    public NearFar(StartingPosition pos) {
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addParallel(new Defense());
    	
    	addSequential(new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));

    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp3, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	addSequential(new DropoffPositionRear());
    	addSequential(new Taller());
    	
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp3, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new OpenClaw());
    	
    	addSequential(new WaitCommand(0.5));
    	addParallel(new IntakeCube()); //Doesn't return until is has a cube
    	addSequential(new WaitCommand(0.5));
    	
    	addSequential(new Log("Before cube pickup"));
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp4, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new Log("Cube picked up"));
    	
    	//Return to scale and dropoff
    	addSequential(new WaitCommand(1.0));
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp5, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	addSequential(new ShiftHigh());
    	BBCommand parallelCommand = new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.6));
    	addSequential(new DropoffPositionRear());
    	addSequential(new Taller());
    	addSequential(new CheckDone(parallelCommand));
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp6, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new ShiftHigh());
    	PIDGains tempDrive  = new PIDGains(0.050,0,0.70,0,0.7,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
    	addSequential(new DriveWaypointBackward(wp6, invertX, ChassisConst.defaultTolerance, 5, false, tempDrive, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
    	
    	addSequential(new OpenClaw());
       
    }
}
