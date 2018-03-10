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

	public enum SwitchPosition{
		LEFT, RIGHT
	}

    public CenterStart_Switch(SwitchPosition switchPosition) {
    	
    	addSequential(new Calibrate());
    	addSequential(new ShiftLow());
    	addSequential(new dropoffPositionSwitch());
        addSequential(new DriveDistance(15, ChassisConst.DriveHigh));
        if(switchPosition == SwitchPosition.LEFT) {
        	addSequential(new TurnGyroWaypoint(-50, 50, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        	addSequential(new DriveWaypoint(-50, 50, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        }
        else if (switchPosition == SwitchPosition.RIGHT) {
        	addSequential(new TurnGyroWaypoint(50, 50, ChassisConst.defaultTolerance, 5, ChassisConst.GyroTurnLow)); //(double x, double y, double tolerance, double timeout, PIDGains gains
        	addSequential(new DriveWaypoint(50, 50, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        }
        addSequential(new OpenClaw());
    	addSequential(new DriveWaypoint(0, 240, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
       
    }
}
