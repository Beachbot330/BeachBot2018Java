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
public class JoeTest extends BBCommandGroup {

	
	Waypoint wp1 = new Waypoint(0,   230, 0); //Turn to go down path
	Waypoint wp2 = new Waypoint(-212, 230, 0); //Drive to scale
	Waypoint wp3 = new Waypoint(-200, 280, 0); //Dropoff at scale
	Waypoint wp4 = new Waypoint(-186, 223, 0); //Drive to cube
	Waypoint wp5 = new Waypoint(-204, 263, 0); //Drive back to scale
	Waypoint wp6 = new Waypoint(-204, 276, 0); //Second scale dropoff
	
	ArrayList<Waypoint> path1 = new ArrayList<Waypoint>();
	

    public JoeTest(StartingPosition pos) {
    	path1.add(wp1);
    	path1.add(wp2);
    	path1.add(wp3);
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addParallel(new Defense());
    	
    	addSequential(new DrivePath(path1, invertX, 60, ChassisConst.defaultTolerance, 5, false, ChassisConst.DrivePathHigh, ChassisConst.GyroPathHigh));

    	
//    	addSequential(new DropoffPositionRear());
//    	addSequential(new Taller());
//    	
//    	addSequential(new ShiftHigh());
//    	PIDGains driveMid     = new PIDGains(0.050,0,0.70,0,0.6,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
//    	addSequential(new DriveWaypointBackward(wp3, invertX, ChassisConst.defaultTolerance, 5, false, driveMid, ChassisConst.GyroDriveHigh));
//    	//addSequential(new DriveWaypointBackward(wp3, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
//    	
//    	addSequential(new OpenClaw());
//    	
//    	addSequential(new WaitCommand(0.5));
//    	addParallel(new IntakeCube()); //Doesn't return until is has a cube
//    	addSequential(new WaitCommand(0.5));
//    	
//    	addSequential(new Log("Before cube pickup"));
//    	
//    	addSequential(new ShiftLow());
//    	addSequential(new TurnGyroWaypoint(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
//    	addSequential(new ShiftHigh());
//    	addSequential(new DriveWaypoint(wp4, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
//    	
//    	addSequential(new Log("Cube picked up"));
//    	
//    	//Return to scale and dropoff
//    	addSequential(new WaitCommand(1.0));
//    	addSequential(new ShiftLow());
//    	addSequential(new TurnGyroWaypointBackward(wp5, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
//    	
//    	addSequential(new ShiftHigh());
//    	BBCommand parallelCommand = new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
//    	addParallel(parallelCommand);
//    	addSequential(new WaitCommand(0.6));
//    	addSequential(new DropoffPositionRear());
//    	addSequential(new Taller());
//    	addSequential(new CheckDone(parallelCommand));
//    	
//    	addSequential(new ShiftLow());
//    	addSequential(new TurnGyroWaypointBackward(wp6, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
//    	addSequential(new WaitCommand(0.1));
//    	addSequential(new ShiftHigh());
//    	PIDGains tempDrive  = new PIDGains(0.050,0,0.70,0,0.7,ChassisConst.defaultMaxOutputStep, "DriveHigh"); //AP 3-12-18
//    	addSequential(new DriveWaypointBackward(wp6, invertX, ChassisConst.defaultTolerance, 5, false, tempDrive, ChassisConst.GyroDriveHigh));
//    	addSequential(new WaitCommand(0.1));
//    	
//    	addSequential(new OpenClaw());
       
    }
}
