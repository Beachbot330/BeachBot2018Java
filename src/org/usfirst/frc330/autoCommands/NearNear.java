package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.autoCommands.Chooser_RightLeftStart.StartingPosition;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class NearNear extends BBCommandGroup {


    public NearNear(StartingPosition pos) {
    	
    	addSequential(new Calibrate());
    	addSequential(new ShiftHigh());
    	if (pos == StartingPosition.LEFT) {
    		
    	}
    	else
    	{
    		
    	}
    	//addSequential(new dropoffPositionSwitch());
        //addSequential(new DriveDistance(95, ChassisConst.DriveHigh));
        //addSequential(new OpenClaw());
    	//addSequential(new DriveWaypoint(0, 240, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
       
    }
}
