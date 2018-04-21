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

/**
 *
 */
public class Flinger extends BBCommandGroup {
	
	double flingDistance = 60+12+18;
	
	Waypoint wp1 = new Waypoint(0, -182+5, 0);
	Waypoint wp2 = new Waypoint(29, -274+3, 0); //Dropoff at scale
	Waypoint wp3 = new Waypoint(36, -221+5+3, 0); //Drive to second cube
	Waypoint wp4 = new Waypoint(25, -263+5+2, 0); //Drive back to scale
	Waypoint wp5 = new Waypoint(32, -274, 0); // Second drop off
	Waypoint wp6 = new Waypoint(56+5, -216+5-1, 0); // Pickup third cube

    public Flinger(StartingPosition pos) {
    	
    	if (pos == StartingPosition.LEFT) {
    		wp3.setX(wp3.getX() + 3);
    		wp6.setX(wp6.getX() + 3);
    	}
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	
    	//Drive away from wall
    	addSequential(new ShiftHigh());
    	Command parallelCommand = new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	addSequential(new Calibrate());
    	//addParallel(new Defense());
    	
    	//Finish driving away from wall
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Start Drive to Scale
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	//PIDGains tempDrive  = new PIDGains(0.10, 0, 0.80, 0, 0.8, ChassisConst.defaultMaxOutputStep, "DriveHigh");
    	parallelCommand = new DriveWaypointBackward(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	//Fling while driving
    	addSequential(new WaitForPosition(wp2, invertX, flingDistance));
    	addSequential(new ThrowCubeArm(10, 65, 1.0, 5));
    	addSequential(new CheckDone(parallelCommand)); //Check that we are done driving
    	   	
    	//Get into pickup position
    	addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Go to pickup a cube
    	addSequential(new Log("Pickup Second Cube"));
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp3, invertX, ChassisConst.defaultTurnTolerance, 1, ChassisConst.GyroTurnLow));
    	addSequential(new RollerOn());
    	addSequential(new ShiftHigh());
    	addSequential(new WaitCommand(0.2));
    	addSequential(new DriveWaypoint(wp3, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Grab the cube
    	addParallel(new PredictiveCloseClaw(wp3, invertX, 5)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	
    	//Return to prep location
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp4, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.4));
    	addSequential(new RollerOff());
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Drive to scale for second dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp5, invertX, ChassisConst.defaultTurnTolerance, 1, ChassisConst.GyroTurnLow));
    	
    	//Start Flinging
    	parallelCommand = new ThrowCubeArm();
    	addParallel(parallelCommand);
    	
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
    	
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Get into pickup position
    	addSequential(new OpenClaw());
    	addSequential(new RollerOn());
    	addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Go to pickup a new cube
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp6, invertX, ChassisConst.defaultTurnTolerance, 1, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp6, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));

    	//Grab the cube
    	addParallel(new PredictiveCloseClaw(wp6, invertX, 5)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	addSequential(new Log("Third cube picked up"));
    	
    	//Return to prep location
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp4, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.6));
    	addSequential(new CheckDone(parallelCommand));
    	addSequential(new RollerOff());
    	
    	
    	//Drive to scale for third dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp5, invertX, ChassisConst.defaultTurnTolerance, 1, ChassisConst.GyroTurnLow));
    	
    	//Fling Cube
    	parallelCommand = new ThrowCubeArm();
    	addParallel(parallelCommand);
    	
    	//Drive back
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
    	
       
    }
}
