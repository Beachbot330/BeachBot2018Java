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
public class CoopFarFar extends BBCommandGroup {

	
	Waypoint wp1 = new Waypoint(0,   -224, 0); //Turn to go down path
	Waypoint wp2 = new Waypoint(96, -223, 0); //Drive to scale
	Waypoint wp3 = new Waypoint(109+4, -209+5, 0); //Pickup center cube
	Waypoint wp4 = new Waypoint(122, -199, 0); //Drive to shooting location
	
	Waypoint wp5 = new Waypoint(42, -225, 0); //Prep for second cube
	Waypoint wp6 = new Waypoint(53, -211, 0); //Pickup second cube

    public CoopFarFar(StartingPosition pos) {
    	
//    	if (pos == StartingPosition.LEFT) {
//    		wp4.setX(wp4.getX() + 3);
//    		wp7.setX(wp7.getX() + 3);
//    	}
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	//Drive away from wall
    	addSequential(new ShiftHigh());
    	Command parallelCommand = new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	//Calibrate while driving
    	addSequential(new Calibrate());
    	
    	//Drop Cube
    	//addSequential(new WaitCommand(0.2));
    	addSequential(new OpenClaw());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new SetHandAngle(HandConst.leveledWrist));
    	
    	//Finish driving away from wall
    	addSequential(new CheckDone(parallelCommand));

    	//Turn down aisle
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Drive to center of aisle
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Turn Towards Cube
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp3, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Get in pickup position
    	addSequential(new OpenClaw());
    	addSequential(new RollerOn());
    	addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Drive to cube
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypoint(wp3, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	//Grab the cube
    	addSequential(new PredictiveCloseClaw(wp3, invertX, 5, 2.0)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	addSequential(new Log("Cube picked up"));
    	addSequential(new CheckDone(parallelCommand)); //Check that we are done driving
    	
    	addSequential(new WaitCommand(0.2));
    	addSequential(new RollerOff());
    	addSequential(new Defense());
    	
    	//Position to shoot
    	parallelCommand = new SetLiftPosition(LiftConst.upperLimit);
    	addParallel(parallelCommand);
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, 45));
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Drive to shooting location
    	addSequential(new DriveWaypoint(wp4, invertX, ChassisConst.defaultTolerance, 3, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Shoot Cube
    	addSequential(new RollerReverse());
    	
    	//Backup
    	addSequential(new DriveWaypointBackward(wp3, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Get in pickup position
    	addSequential(new OpenClaw());
    	addSequential(new RollerOn());
    	parallelCommand = new SetLiftPosition(LiftConst.intakePosition);
    	addParallel(parallelCommand);
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Prep for second cube
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp5, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Check in position
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Drive to cube
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp6, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypoint(wp6, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	//Grab the cube
    	addSequential(new PredictiveCloseClaw(wp6, invertX, 5, 2.0)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	addSequential(new Log("Cube picked up"));
    	addSequential(new CheckDone(parallelCommand)); //Check that we are done driving
    	
    	addSequential(new WaitCommand(0.5));
    	addSequential(new RollerOff());
    	addSequential(new Defense());
    }
}
