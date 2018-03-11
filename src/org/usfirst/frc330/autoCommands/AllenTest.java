package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AllenTest extends BBCommandGroup {


    public AllenTest() {
    	
    	addSequential(new Calibrate());
    	addSequential(new ShiftLow());
    	addSequential(new Defense());
    	//addSequential(new dropoffPositionSwitch());
        //addSequential(new DriveDistance(95, ChassisConst.DriveHigh));
        //addSequential(new OpenClaw());
    	//addSequential(new DriveWaypointBackward(0, -120, ChassisConst.defaultTolerance, 5, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	addSequential(new TurnGyroRel(90, ChassisConst.GyroTurnLow));
       
    }
}
