package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.constants.LiftConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterStartSwitchMulti extends BBCommandGroup {
	
	Waypoint wp1;
	Waypoint wp2;
	Waypoint wp3;
	Waypoint wp4;
	Waypoint wp5;
	Waypoint wp6;
	
	public enum SwitchPosition{
		LEFT, RIGHT
	}

    public CenterStartSwitchMulti(SwitchPosition switchPosition) {
    	
    	//boolean invertX = (switchPosition == SwitchPosition.LEFT);
    	
    	if((switchPosition == SwitchPosition.LEFT)) {
    		wp1 = new Waypoint( -16,  17, 0); //Away from wall
    		wp2 = new Waypoint( -65,  58, 0); //Jog
    		wp3 = new Waypoint( -55, 111, 0); //Switch
    		wp4 = new Waypoint(  -17,  25, 0); //Back
    		wp5 = new Waypoint(  -17, 75, 0); //Cube
    		wp6 = new Waypoint(  -17, 65, 0); //Back
    	}
    	else {
    		wp1 = new Waypoint(  0,  17, 0);
    		wp2 = new Waypoint( 46,  58, 0);
    		wp3 = new Waypoint( 46, 111, 0);
    		wp4 = new Waypoint(  3,  25, 0);
    		wp5 = new Waypoint(  3, 75, 0);
    		wp6 = new Waypoint(  -17, 65, 0);
    	}
    	
    	//Drive away from the wall
    	addSequential(new ShiftHigh());
    	Command parallelCommand = new DriveWaypoint(wp2, false, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addParallel(new SetLiftPosition(LiftConst.switchDropoff + 5, 8));
        addSequential(new CheckDone(parallelCommand));
        
        //Get into dropoff position
    	addParallel(new CoordinatedMove(ArmConst.switchArm, HandConst.switchDropoff - 10));

        //Drive to switch
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(wp3, false, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        parallelCommand = new DriveWaypoint(wp3, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
        addParallel(parallelCommand);
        
        //Deploy Cube
        addSequential(new WaitForPosition(wp3, false, 6));
        addSequential(new RollerReverse());
        
        //Dropoff first cube
        //addSequential(new WaitCommand(0.5));
        //addSequential(new OpenClaw());
        
        //Drive backwards
        addSequential(new DriveWaypointBackward(wp4, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        addSequential(new OpenClaw());
    	
        //Get into pickup position
        addParallel(new SetLiftPosition(LiftConst.intakePosition));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-10));
    	addSequential(new RollerOn());

    	//Drive to cube
    	addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(wp5, false, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(wp5, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        //Grab Cube
        addParallel(new PredictiveCloseClaw(wp5, false, 5)); //Start closing 5 inches early
    	addSequential(new WaitCommand(0.4));
    	
    	//Drive back 10 inches
    	parallelCommand = new DriveWaypointBackward(wp6, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	
    	//Turn off grabber
    	addSequential(new WaitCommand(0.5));
    	addSequential(new RollerOff());
    	
    	//Finish backing up
    	addSequential(new CheckDone(parallelCommand));
    	
    	//Get into dropoff position
        addParallel(new DropoffPositionSwitch());

        //Drive to switch
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(wp3, false, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(wp3, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        //Dropoff first cube
        addSequential(new RollerReverse());
        addSequential(new WaitCommand(0.2));
        addSequential(new OpenClaw());
        addSequential(new WaitCommand(0.5));
       
    }
}
