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

	// Drive to (0, -173)
	// Move to rear drop off, lift plus 5 inches
	// Drive to (21, -248)
	// Eject cube
	// Move to pickup position
	// Drive to (26, -200)
	// Pickup cube - go to switch dropoff
	// (27.5, -187)
	// Eject cube
	// Backup to 27.8, -233
	// Pickup mode
	// Drive to 48.5, 193
	// Drive back to 35.6, -221
	// Arm to reverse dropoff, lift plus 5 inches
	// Drive back to 36.7, -245
	// Eject cube
	// Defense
	
	Waypoint wp1 = new Waypoint(0, -182, 0);
	Waypoint wp2 = new Waypoint(29, -256-9, 0); //Dropoff at scale
	Waypoint wp3 = new Waypoint(38, -212-8, 0); //Drive to cube
	Waypoint wp4 = new Waypoint(40, -196-12, 0); //Dropoff at switch
	Waypoint wp5 = new Waypoint(29, -244.7, 0); //TBD
	

    public NearNear(StartingPosition pos) {
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addParallel(new Defense());
    	
    	BBCommand parallelCommand = new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new DropoffPositionRear());
    	addSequential(new CheckDone(parallelCommand));
    	addSequential(new Taller());
    	
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	PIDGains DriveMid     = new PIDGains(0.050,0,0.70,0,0.7,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
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
