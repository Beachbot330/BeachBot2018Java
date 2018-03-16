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
	
	Waypoint wp1;
	Waypoint wp2;
	Waypoint wp3;
	
	
	public enum SwitchPosition{
		LEFT, RIGHT
	}

    public CenterStart_Switch(SwitchPosition switchPosition) {
    	
    	boolean invertX = (switchPosition == SwitchPosition.LEFT);
    	
    	if((switchPosition == SwitchPosition.LEFT)) {
    		wp1 = new Waypoint(0-16,17,0);
    		wp2 = new Waypoint(58+12,58,0);
    		wp3 = new Waypoint(58+12,99+12+18,0);
    	}
    	else {
    		wp1 = new Waypoint(0,17,0);
    		wp2 = new Waypoint(58-12,58,0);
    		wp3 = new Waypoint(58-12,99+12+18,0);
    	}
    	
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
        addSequential(new DriveWaypoint(wp3, invertX, ChassisConst.defaultTolerance, 2.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        addSequential(new OpenClaw());
    	
       
    }
}
