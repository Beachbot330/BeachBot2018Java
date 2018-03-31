package org.usfirst.frc330.autoCommands;

import java.util.ArrayList;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AllenTest extends BBCommandGroup {


    public AllenTest() {
    	
    	addSequential(new ShiftHigh());
    	//addSequential(new ShiftLow());
    	addSequential(new Defense());
    	//addSequential(new DriveTime(3.0, 1.0, 1.0)); //Timeout, left out, right out
    	addSequential(new DriveWaypointBackward(0, -12*5, ChassisConst.defaultTolerance, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
       
//    	addSequential(new TurnGyroRel(90, 2, 3, false, ChassisConst.GyroTurnLow));
//       addSequential(new WaitCommand(1.0));
//       addSequential(new TurnGyroRel(90, 2, 3, false, ChassisConst.GyroTurnLow));
//       addSequential(new WaitCommand(1.0));
//       addSequential(new TurnGyroRel(90, 2, 3, false, ChassisConst.GyroTurnLow));
//       addSequential(new WaitCommand(1.0));
//       addSequential(new TurnGyroRel(90, 2, 3, false, ChassisConst.GyroTurnLow));
       
    }
}
