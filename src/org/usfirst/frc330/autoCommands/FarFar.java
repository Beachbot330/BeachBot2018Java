package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.autoCommands.Chooser_RightLeftStart.StartingPosition;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FarFar extends BBCommandGroup {

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
	
	Waypoint wp1 = new Waypoint(0,   -216, 0); //Turn to go down path
	Waypoint wp2 = new Waypoint(212, -220, 0); //Drive to scale
	Waypoint wp3 = new Waypoint(200, -256, 0); //Dropoff at scale
	Waypoint wp4 = new Waypoint(186, -208, 0); //Drive to cube
	Waypoint wp5 = new Waypoint(186, -193, 0); //Dropoff at switch
	

    public FarFar(StartingPosition pos) {
    	
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
    	addSequential(new WaitCommand(2.0));
    	
    	addSequential(new Log("Before cube pickup"));
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp4, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new Log("Cube picked up"));
    	
    	addSequential(new WaitCommand(1.0));
    	addSequential(new DropoffPositionSwitch());
    	addSequential(new Taller());
    	
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp5, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	addSequential(new OpenClaw());
       
    }
}
