package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterStart_Switch extends BBCommandGroup {
	
	// Drive to (0,16)
	// Drive to (55, 55)
	// Drive to (56, 96)

	Waypoint wp1 = new Waypoint(0,17,0);
	Waypoint wp2 = new Waypoint(58,58,0);
	Waypoint wp3 = new Waypoint(58,99,0);
	
	
	public enum SwitchPosition{
		LEFT, RIGHT
	}

    public CenterStart_Switch(SwitchPosition switchPosition) {
    	
    	boolean invertX = (switchPosition == SwitchPosition.LEFT);
    	
    	addSequential(new CloseClaw());
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	addSequential(new Defense());
        addSequential(new DriveWaypoint(wp1, false, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        addSequential(new ShiftLow());
        
        addSequential(new TurnGyroWaypoint(wp2, invertX, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(wp2, invertX, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));

        
        addSequential(new DropoffPositionSwitch());
        addSequential(new ShiftLow());
        
        addSequential(new TurnGyroWaypoint(wp3, invertX, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(-wp3.getX(), wp3.getY(), ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        addSequential(new OpenClaw());
    	
       
    }
}
