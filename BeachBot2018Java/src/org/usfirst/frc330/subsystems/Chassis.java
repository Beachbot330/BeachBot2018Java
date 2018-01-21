// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.subsystems;


import org.usfirst.frc330.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;
import org.usfirst.frc330.wpilibj.BBSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Chassis extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private AHRS navX;
    private BBSolenoid pTO;
    private BBDoubleSolenoid shifters;
    private Encoder driveEncoderRight;
    private Encoder driveEncoderLeft;
    private WPI_TalonSRX leftDrive1;
    private WPI_TalonSRX leftDrive2;
    private WPI_TalonSRX leftDrive3;
    private SpeedControllerGroup leftDrive;
    private WPI_TalonSRX rightDrive1;
    private WPI_TalonSRX rightDrive2;
    private WPI_TalonSRX rightDrive3;
    private SpeedControllerGroup rightDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Chassis() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        navX = new AHRS(Port.kMXP, (byte)50);
        LiveWindow.addSensor("Chassis", "NavX", navX);
        
        pTO = new BBSolenoid(0, 0);
        addChild(pTO);
        
        shifters = new BBDoubleSolenoid(0, 1, 2);
        addChild(shifters);
        
        driveEncoderRight = new Encoder(0, 1, false, EncodingType.k4X);
        addChild(driveEncoderRight);
        driveEncoderRight.setDistancePerPulse(1.0);
        driveEncoderRight.setPIDSourceType(PIDSourceType.kRate);
        driveEncoderLeft = new Encoder(2, 3, false, EncodingType.k4X);
        addChild(driveEncoderLeft);
        driveEncoderLeft.setDistancePerPulse(1.0);
        driveEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
        leftDrive1 = new WPI_TalonSRX(4);
        
        
        leftDrive2 = new WPI_TalonSRX(5);
        
        
        leftDrive3 = new WPI_TalonSRX(6);
        
        
        leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2 , leftDrive3 );
        addChild(leftDrive);
        
        rightDrive1 = new WPI_TalonSRX(7);
        
        
        rightDrive2 = new WPI_TalonSRX(8);
        
        
        rightDrive3 = new WPI_TalonSRX(9);
        
        
        rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2 , rightDrive3 );
        addChild(rightDrive);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void ShiftHigh() {
    	shifters.set(DoubleSolenoid.Value.kForward);
    }
    public void ShiftLow() {
    	shifters.set(DoubleSolenoid.Value.kReverse);
    }
    

}

