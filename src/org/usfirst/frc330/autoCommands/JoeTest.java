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

    public JoeTest() {

    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addParallel(new Defense());
    	
    	addSequential(new PathfinderDrive("Tune_15ft", ChassisConst.DriveHigh.getP(),0,0,1/13.5,0.04,ChassisConst.GyroDriveHigh.getP()));
   	
//    	addSequential(new ShiftLow());
//    	addSequential(new TurnGyroWaypointBackward(wp2, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
//    	addSequential(new ShiftHigh());
//    	addSequential(new DriveWaypointBackward(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
//    	
//    	addSequential(new ShiftLow());
//    	addSequential(new TurnGyroWaypointBackward(wp3, invertX, ChassisConst.defaultTurnTolerance, 2, ChassisConst.GyroTurnLow));
//    	
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
//       
    }
}
