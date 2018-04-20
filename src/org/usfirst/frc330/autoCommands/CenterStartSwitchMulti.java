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
	Waypoint wp7, wp8;
	
	public enum SwitchPosition{
		LEFT, RIGHT
	}

    public CenterStartSwitchMulti(SwitchPosition switchPosition) {
    	
    	//boolean invertX = (switchPosition == SwitchPosition.LEFT);
    	
    	DrivePIDGains DriveHighReduced = new DrivePIDGains(0.100,0,0.80,0,ChassisConst.defaultMaxOutput-0.1,ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput,"DriveHigh"); //AP 4-19-18
    	DrivePIDGains DriveHighSlowAccel = new DrivePIDGains(0.100,0,0.80,0,ChassisConst.defaultMaxOutput,ChassisConst.defaultMaxOutputStep*0.6, ChassisConst.defaultMinStartOutput,"DriveHigh"); //AP 4-19-18
    	
    	if((switchPosition == SwitchPosition.LEFT)) {
    		wp1 = new Waypoint( -16,  17, 0);  // Depricated
    		wp2 = new Waypoint( -65,  58, 0);  // Drive away from the wall with a jog
    		wp3 = new Waypoint( -53, 107, 0);  // First deploy at the switch
    		wp8 = new Waypoint( -40, 111, 0);  // Second Deploy at Switch
    		wp4 = new Waypoint(  -17,  25, 0); //First and second drive back
    		wp5 = new Waypoint(  -11, 75-5, 0);  //Pickup Second Cube
    		wp6 = new Waypoint(  -25, 45, 0);  //Pull back from pyramid
    		wp7 = new Waypoint(  -1, 75+20, 0); //Pickup Third cube
    	}
    	else {
    		wp1 = new Waypoint(  0,  17, 0);   // Depricated
    		wp2 = new Waypoint( 46,  58, 0);   // Jog
    		wp3 = new Waypoint( 46, 107, 0);   // Switch (was 111)
    		wp8 = new Waypoint( 30, 111, 0);   // Second deploy at switch
    		wp4 = new Waypoint(  3,  25, 0);   //First and second drive back
    		wp5 = new Waypoint(  2, 75-5, 0);    //Second Cube
    		wp6 = new Waypoint(  20, 45, 0);   //Pull back from pyramid
    		wp7 = new Waypoint(  -5, 75+20, 0); //Third cube
    	}
    	
    	//Drive away from the wall
    	addSequential(new ShiftHigh());
    	Command parallelCommand = new DriveWaypoint(wp2, false, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addParallel(parallelCommand);
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addParallel(new SetLiftPosition(LiftConst.switchDropoff+2, 8));
        addSequential(new CheckDone(parallelCommand));
        
        //Get into dropoff position
    	addParallel(new CoordinatedMove(ArmConst.switchArm, HandConst.switchDropoff - 10));

        //Drive to switch
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(wp3, false, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        parallelCommand = new DriveWaypoint(wp3, false, ChassisConst.defaultTolerance, 2.0, true, DriveHighReduced, ChassisConst.GyroDriveHigh);
        addParallel(parallelCommand);
        
        //Deploy Cube
        addSequential(new WaitForPosition(wp3, false, 4, 2.0));
        addSequential(new RollerReverse());
        addSequential(new CheckDone(parallelCommand));
        
        //Dropoff first cube
        //addSequential(new WaitCommand(0.5));
        //addSequential(new OpenClaw());
        
        //Drive backwards
        addSequential(new DriveWaypointBackward(wp4, false, ChassisConst.defaultTolerance, 2.0, true, DriveHighSlowAccel, ChassisConst.GyroDriveHigh));
        
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
    	
    	//Drive back from the pyramid
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
        addSequential(new TurnGyroWaypoint(wp8, false, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(wp8, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        //Dropoff second cube
        addSequential(new RollerReverse());
        addSequential(new WaitCommand(0.2));
        addSequential(new OpenClaw());
        addSequential(new WaitCommand(0.5));
       
        //Drive backwards
        addSequential(new DriveWaypointBackward(wp4, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        addSequential(new OpenClaw());
    	
        //Get into pickup position
        addParallel(new SetLiftPosition(LiftConst.intakePosition + 11));
    	addParallel(new CoordinatedMove(ArmConst.intakePosition, HandConst.pickUp-5));
    	addSequential(new RollerOn());

    	//Drive to third cube
    	addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(wp7, false, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(wp7, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        //Grab Cube
        addSequential(new CloseClaw());
    	addSequential(new WaitCommand(0.4));
    	
    	//Drive back 10 inches
    	parallelCommand = new DriveWaypointBackward(wp6, false, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
    	addSequential(parallelCommand);
    	addSequential(new Defense());
    	
    }
}
