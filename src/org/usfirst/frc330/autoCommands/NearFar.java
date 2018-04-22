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

	
	Waypoint wp1 = new Waypoint(0,   -230+5, 0); //Turn to go down path
	Waypoint wp2 = new Waypoint(212, -230+5, 0); //Drive to scale
	Waypoint wp3 = new Waypoint(195, -279+10+2, 0); //Dropoff at scale
	Waypoint wp4 = new Waypoint(182+2+1, -223+5, 0); //Drive to cube
	Waypoint wp5 = new Waypoint(204, -263+5, 0); //Drive back to scale
	Waypoint wp6 = new Waypoint(192, -276+6+2, 0); //Second scale dropoff
	Waypoint wp7 = new Waypoint(160, -220+5, 0); //Pickup third cube

    public NearFar(StartingPosition pos) {
    	
    	if (pos == StartingPosition.LEFT) {
    		wp4.setX(wp4.getX() + 3);
    		wp7.setX(wp7.getX() + 3);
    	}
    	
    	boolean invertX = (pos == StartingPosition.LEFT);
    	
    	//Close Claw Just in Case
    	addSequential(new CloseClaw());
    	
    	//Drive away from wall
    	addSequential(new ShiftHigh());
    	Command parallelCommand = new DriveWaypointBackward(wp1, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	//Calibrate while driving
    	addSequential(new Calibrate());
    	
    	//Finish driving away from wall
    	addSequential(new CheckDone(parallelCommand));

    	//Turn down aisle
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Drive down aisle
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Drive to Scale
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp3, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp3, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	//Fling while driving
    	addSequential(new ThrowCubeArm());
    	addSequential(new CheckDone(parallelCommand)); //Check that we are done driving
    	
    	//Get into pickup position
    	addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Go to pickup a cube
    	addSequential(new Log("Pickup Second Cube"));
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp4, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new RollerOn());
    	addSequential(new ShiftHigh());
    	addSequential(new WaitCommand(0.2));
    	addSequential(new DriveWaypoint(wp4, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	
    	//Grab the cube
    	addParallel(new PredictiveCloseClaw(wp4, invertX, 5)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	
    	//Return to prep location
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.4));
    	addSequential(new RollerOff());
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Drive to scale for second dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp6, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Start Flinging
    	parallelCommand = new ThrowCubeArm();
    	addParallel(parallelCommand);
    	
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp6, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
    	
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Get into pickup position
    	addSequential(new OpenClaw());
    	addSequential(new RollerOn());
    	addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	
    	//Go to pickup a new cube
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypoint(wp7, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(wp7, invertX, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));

    	//Grab the cube
    	addParallel(new PredictiveCloseClaw(wp7, invertX, 5)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	addSequential(new Log("Third cube picked up"));
    	
    	//Return to prep location
    	addSequential(new ShiftHigh());
    	parallelCommand = new DriveWaypointBackward(wp5, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new WaitCommand(0.6));
    	addSequential(new CheckDone(parallelCommand));
    	addSequential(new RollerOff());
    	
    	
    	//Drive to scale for third dropoff
    	addSequential(new ShiftLow());
    	addSequential(new TurnGyroWaypointBackward(wp6, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
    	
    	//Fling Cube
    	parallelCommand = new ThrowCubeArm();
    	addParallel(parallelCommand);
    	
    	//Drive back
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypointBackward(wp6, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new WaitCommand(0.1));
       
    }
}
