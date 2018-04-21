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
public class NearSwitch extends BBCommandGroup {
	
	double flingDistance = 60+12;
	
	Waypoint wp1 = new Waypoint(0, -15*12, 0); //Drive away from wall to center switch
	Waypoint wp2 = new Waypoint(12, -14.5*12, 0); //Dropoff at switch
	Waypoint wp3 = new Waypoint(25, -218+10, 0); //Pickup second cube
	Waypoint wp7 = new Waypoint(36, -221+18, 0); //Deploy second cube
	Waypoint wp4 = new Waypoint(14, -240, 0); //Drive to prep location 1
	Waypoint wp8 = new Waypoint(55, -230, 0); //Drive to prep location 2
	Waypoint wp5 = new Waypoint(32, -276+5+2, 0); // Second drop off
	Waypoint wp6 = new Waypoint(47, -213, 0); // Pickup third cube

    public NearSwitch(StartingPosition pos) {
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	
    	//Drive away from wall
    	addSequential(new ShiftHigh());
    	Command parallelCommand = new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	addSequential(new Calibrate());
    	//addParallel(new Defense());
    	
    	//addParallel(new SetLiftPosition(LiftConst.switchDropoff - 5, 8));
    	
    	//Finish driving away from wall
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Turn towards switch
    	addSequential(new ShiftLow());
    	addParallel(new SetLiftPosition(LiftConst.switchDropoff + 10, 8));
    	addSequential(new TurnGyroWaypoint(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Set dropoff position
    	addParallel(new SetArmAngle(ArmConst.intakePosition));
    	addParallel(new SetHandAngle( HandConst.pickUp-7));
    	addSequential(new WaitCommand(0.3));
    	
    	//Drive to switch
    	addSequential(new ShiftHigh());
    	addSequential (new DriveWaypoint(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addParallel(new RollerReverse());
    	addSequential(new WaitCommand(0.2));
    	
    	//Drive away from switch
    	addSequential(new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new RollerOff());
    	addSequential(new OpenClaw());
    	//addParallel(new Defense());
    	//addSequential(new WaitCommand(3.30));
    	
    	//Get into pickup position
    	addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Drive to prep location
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	//PIDGains tempDrive  = new PIDGains(0.10, 0, 0.80, 0, 0.8, ChassisConst.defaultMaxOutputStep, "DriveHigh");
    	addSequential(new DriveWaypointBackward(wp4, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Go to pickup second cube
    	addSequential(new Log("Pickup Second Cube"));
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp3, invertX, ChassisConst.defaultTurnTolerance, 1, ChassisConst.GyroTurnLow));
    	addSequential(new RollerOn(1.0, 0.5));
    	addSequential(new ShiftHigh());
    	addSequential(new WaitCommand(0.2));
    	addSequential(new DriveWaypoint(wp3, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Grab the cube
    	addParallel(new PredictiveCloseClaw(wp3, invertX, 5)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	
    	//Get into switch dropoff position
    	parallelCommand = new SetLiftPosition(LiftConst.switchDropoff+5, 8);
    	addParallel(parallelCommand);
    	addSequential(new RollerOff());
    	addSequential(new CoordinatedMove(ArmConst.switchArm, HandConst.switchDropoff-10));
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Deploy Second Cube
    	addSequential(new DriveWaypoint(wp7, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new RollerReverse());
    	
    	//Return to prep location
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp8, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.4));
    	addSequential(new RollerOff());
    	addSequential(new CloseClaw());
    	
    	//Get into pickup position
    	addParallel(new SetLiftPosition(LiftConst.intakePosition+3));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-15));
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Get into pickup position
    	addSequential(new OpenClaw());
    	addSequential(new RollerOn());
    	addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Go to pickup a third cube
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp6, invertX, ChassisConst.defaultTurnTolerance, 1, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp6, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));

    	//Grab the cube
    	addSequential(new PredictiveCloseClaw(wp6, invertX, 5, 2.0)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	addSequential(new Log("Third cube picked up"));
    	
    	//Get into switch dropoff position
    	parallelCommand = new SetLiftPosition(LiftConst.switchDropoff+8, 8);
    	addParallel(parallelCommand);
    	addSequential(new CoordinatedMove(ArmConst.switchArm, HandConst.switchDropoff-10));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new RollerOff());
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Deploy Third Cube
    	addSequential(new DriveWaypoint(wp7, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new RollerReverse());
    	
    	//Get into defense
    	addParallel(new DriveWaypointBackward(wp8, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new Defense());
    	addSequential(new RollerOff());
    }
}
