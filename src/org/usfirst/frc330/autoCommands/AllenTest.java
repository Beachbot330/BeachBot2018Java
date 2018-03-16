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
    	
    	addSequential(new ShiftHigh());
    	addSequential(new DriveTime(3.0, 1.0, 1.0)); //Timeout, left out, right out
       
    }
}
