// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
//import org.usfirst.frc330.commands.commandgroups.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shiftLow_1;
    public JoystickButton openClaw_2;
    public Joystick driverL;
    public JoystickButton shiftHigh_1;
    public JoystickButton deployCube__2;
    public JoystickButton twoButtonPlatformDeploy_45;
    public JoystickButton alignReady_release_10;
    public JoystickButton climbReady_held_10;
    public Joystick driverR;
    public JoystickButton intakeCube_whileHeld_1;
    public JoystickButton defense_release_1;
    public JoystickButton intakeCube_whileHeld_2;
    public JoystickButton switchShot_release_2;
    public JoystickButton switchDropoff_3;
    public JoystickButton scaleMidDropoff_6;
    public JoystickButton scaleDropoffRear_5;
    public JoystickButton scaleTallDropoff_4;
    public JoystickButton sensorLessPickupDown_7;
    public JoystickButton sensorlessPickupUp_7;
    public JoystickButton killAll_8;
    public JoystickButton powerUpDropOff_9;
    public JoystickButton lowerHand_10;
    public Joystick armGamePad;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    //TODO Allen, reassign climb buttons to driver
    
    //public POVButton powerupDropoff1;
    //public POVButton powerupDropoff2;
    public POVButton taller;
    public POVButton shorter;
    public JoystickButton limelightDrive_3;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        armGamePad = new Joystick(2);
        
        lowerHand_10 = new JoystickButton(armGamePad, 10);
        lowerHand_10.whenPressed(new LowerHand());
        powerUpDropOff_9 = new JoystickButton(armGamePad, 9);
        powerUpDropOff_9.whenPressed(new DropoffPowerUp());
        killAll_8 = new JoystickButton(armGamePad, 8);
        killAll_8.whenPressed(new KillAll());
        sensorlessPickupUp_7 = new JoystickButton(armGamePad, 7);
        sensorlessPickupUp_7.whenReleased(new SensorlessPickupUp());
        sensorLessPickupDown_7 = new JoystickButton(armGamePad, 7);
        sensorLessPickupDown_7.whileHeld(new SensorlessPickupDown());
        scaleTallDropoff_4 = new JoystickButton(armGamePad, 4);
        scaleTallDropoff_4.whenPressed(new dropoffPositionMax());
        scaleDropoffRear_5 = new JoystickButton(armGamePad, 5);
        scaleDropoffRear_5.whenPressed(new DropoffPositionRear());
        scaleMidDropoff_6 = new JoystickButton(armGamePad, 6);
        scaleMidDropoff_6.whenPressed(new dropoffPositionMed());
        switchDropoff_3 = new JoystickButton(armGamePad, 3);
        switchDropoff_3.whenPressed(new DropoffPositionSwitch());
        switchShot_release_2 = new JoystickButton(armGamePad, 2);
        switchShot_release_2.whenReleased(new SwitchPopShotPosition());
        intakeCube_whileHeld_2 = new JoystickButton(armGamePad, 2);
        intakeCube_whileHeld_2.whileHeld(new IntakeCube());
        defense_release_1 = new JoystickButton(armGamePad, 1);
        defense_release_1.whenReleased(new Defense());
        intakeCube_whileHeld_1 = new JoystickButton(armGamePad, 1);
        intakeCube_whileHeld_1.whileHeld(new IntakeCube());
        driverR = new Joystick(1);
        
        climbReady_held_10 = new JoystickButton(driverR, 10);
        climbReady_held_10.whileHeld(new ClimbReady());
        alignReady_release_10 = new JoystickButton(driverR, 10);
        alignReady_release_10.whenReleased(new AlignReady());
        twoButtonPlatformDeploy_45 = new JoystickButton(driverR, 4);
        twoButtonPlatformDeploy_45.whileHeld(new TwoButtonPlatformDeploy());
        deployCube__2 = new JoystickButton(driverR, 2);
        deployCube__2.whenPressed(new DeployCube());
        shiftHigh_1 = new JoystickButton(driverR, 1);
        shiftHigh_1.whenPressed(new ShiftHigh());
        driverL = new Joystick(0);
        
        openClaw_2 = new JoystickButton(driverL, 2);
        openClaw_2.whenPressed(new OpenClaw());
        shiftLow_1 = new JoystickButton(driverL, 1);
        shiftLow_1.whenPressed(new ShiftLow());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("ShiftLow", new ShiftLow());
        SmartDashboard.putData("ShiftHigh", new ShiftHigh());
        SmartDashboard.putData("OpenClaw", new OpenClaw());
        SmartDashboard.putData("CloseClaw", new CloseClaw());
        SmartDashboard.putData("SensorCloseClaw", new SensorCloseClaw());
        SmartDashboard.putData("ManualLift", new ManualLift());
        SmartDashboard.putData("UnlockPlatforms", new UnlockPlatforms());
        SmartDashboard.putData("ManualArm", new ManualArm());
        SmartDashboard.putData("ManualWrist", new ManualWrist());
        SmartDashboard.putData("RollerOn", new RollerOn());
        SmartDashboard.putData("RollerOff", new RollerOff());
        SmartDashboard.putData("RollerReverse", new RollerReverse());
        SmartDashboard.putData("DeployCube", new DeployCube());
        SmartDashboard.putData("IntakeCube", new IntakeCube());
        SmartDashboard.putData("DropoffPositionSwitch", new DropoffPositionSwitch());
        SmartDashboard.putData("dropoffPositionLow", new dropoffPositionLow());
        SmartDashboard.putData("dropoffPositionMed", new dropoffPositionMed());
        SmartDashboard.putData("dropoffPositionMax", new dropoffPositionMax());
        SmartDashboard.putData("Defense", new Defense());
        SmartDashboard.putData("ClimbReady", new ClimbReady());
        SmartDashboard.putData("FlipWrist", new FlipWrist());
        SmartDashboard.putData("HandLevel", new HandLevel());
        SmartDashboard.putData("IntakePortal", new IntakePortal());
        SmartDashboard.putData("UnlockRatchet", new UnlockRatchet());
        SmartDashboard.putData("LockRatchet", new LockRatchet());
        SmartDashboard.putData("LiftStepUp", new LiftStepUp());
        SmartDashboard.putData("LiftStepDown", new LiftStepDown());
        SmartDashboard.putData("DeployRollerCoaster", new DeployRollerCoaster());
        SmartDashboard.putData("RetractRollerCoaster", new RetractRollerCoaster());
        SmartDashboard.putData("Taller", new Taller());
        SmartDashboard.putData("Shorter", new Shorter());
        SmartDashboard.putData("DropoffPositionRear", new DropoffPositionRear());
        SmartDashboard.putData("KillAll", new KillAll());
        SmartDashboard.putData("ClimbDeploy", new ClimbDeploy());
        SmartDashboard.putData("AlignReady", new AlignReady());
        SmartDashboard.putData("DropoffPowerUp", new DropoffPowerUp());
        SmartDashboard.putData("DropoffPositionMed90", new DropoffPositionMed90());
        SmartDashboard.putData("TwoButtonPlatformDeploy", new TwoButtonPlatformDeploy());
        SmartDashboard.putData("e_calibrate", new e_calibrate());
        SmartDashboard.putData("SensorlessPickupDown", new SensorlessPickupDown());
        SmartDashboard.putData("SensorlessPickupUp", new SensorlessPickupUp());
        SmartDashboard.putData("LowerHand", new LowerHand());
        SmartDashboard.putData("SwitchPopShotPosition", new SwitchPopShotPosition());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        SmartDashboard.putData("PushMe, I dare you", new Buzz());
        SmartDashboard.putData("ResetPosition", new ResetPosition());
        SmartDashboard.putData("DashboardArmMove", new DashboardArmMove());
        SmartDashboard.putNumber("DashboardArmMoveSetpoint", 0.0);
        SmartDashboard.putData("DashboardHandMove", new DashboardHandMove());
        SmartDashboard.putNumber("DashboardHandMoveSetpoint", 0.0);
        SmartDashboard.putData("DashboardLiftMove", new DashboardLiftMove());
        SmartDashboard.putNumber("DashboardLiftMoveSetpoint", 0.0);
        SmartDashboard.putData("LockPlatforms", new LockPlatforms());
        SmartDashboard.putData("dropoffPositionHigh", new dropoffPositionHigh());
        SmartDashboard.putData("RollerUntilCube", new RollerUntilCube());
        SmartDashboard.putData("ThrowCubeArm", new ThrowCubeArm());
        SmartDashboard.putData("TankDrive", new TankDrive());
        SmartDashboard.putData("CheesyDrive", new CheesyDrive());
        SmartDashboard.putData("limelightAim", new LimelightTurn());
        
        //powerupDropoff1 = new POVButton(armGamePad, 0, 90); //right
        //powerupDropoff2 = new POVButton(armGamePad, 0, 270); //left
        
        taller = new POVButton(armGamePad, 0, 0);  //Up
        shorter = new POVButton(armGamePad, 0, 180); //Down
        
        taller.whenPressed(new Taller());
        shorter.whenPressed(new Shorter());
        
        limelightDrive_3 = new JoystickButton(driverL, 3);
        limelightDrive_3.whileHeld(new LimelightTurn());
        
        //powerupDropoff1.whenPressed(new DropoffPowerUp());
        //powerupDropoff2.whenPressed(new DropoffPowerUp());
        
        //increaseSpeed = new POVButton(operator, 0, 90);  //right
        //decreaseSpeed = new POVButton(operator, 0, 270);  // left
        //increaseHood = new POVButton(operator, 0, 0);  //Up
        //decreaseHood = new POVButton(operator, 0, 180); //Down
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverL() {
        return driverL;
    }

    public Joystick getDriverR() {
        return driverR;
    }

    public Joystick getarmGamePad() {
        return armGamePad;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

