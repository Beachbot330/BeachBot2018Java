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

import java.util.ArrayList;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.Waypoint;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.ChassisConst.Devices;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc330.wpilibj.DummyPIDOutput;
import org.usfirst.frc330.wpilibj.MultiPIDController;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */

public class Chassis extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	//VERIFY make drive motors Sparks -ejo
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private AHRS navX;
    private BBDoubleSolenoid shifters;
    private Encoder driveEncoderRight;
    private Encoder driveEncoderLeft;
    private Spark leftDrive1;
    private Spark leftDrive2;
    private Spark leftDrive3;
    private SpeedControllerGroup leftDrive;
    private Spark rightDrive1;
    private Spark rightDrive2;
    private Spark rightDrive3;
    private SpeedControllerGroup rightDrive;
    private AnalogInput pressureSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    double  gyro_prevVal = 0.0;
    int     ctrRollOver  = 0;
    boolean fFirstUse    = true;
    double  left, right;
    
    public MultiPIDController gyroPID, leftDrivePID, rightDrivePID;
    private DummyPIDOutput gyroOutput, leftDriveOutput, rightDriveOutput;

    public Chassis() {
    	
    	super();
    	
    	PIDSource gyroSource = new PIDSource()
        {

			@Override
			public double pidGet()
			{
				return getAngle();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource)
			{
			}

			@Override
			public PIDSourceType getPIDSourceType()
			{
				return PIDSourceType.kDisplacement;
			}
        	
        };
        
        gyroOutput = new DummyPIDOutput();
        leftDriveOutput = new DummyPIDOutput();
        rightDriveOutput = new DummyPIDOutput();	
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        navX = new AHRS(Port.kMXP, (byte)50);
        addChild(navX);
        
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
        leftDrive1 = new Spark(3);
        addChild(leftDrive1);
        leftDrive1.setInverted(true);
        leftDrive2 = new Spark(4);
        addChild(leftDrive2);
        leftDrive2.setInverted(true);
        leftDrive3 = new Spark(5);
        addChild(leftDrive3);
        leftDrive3.setInverted(true);
        leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2 , leftDrive3 );
        addChild(leftDrive);
        
        rightDrive1 = new Spark(0);
        addChild(rightDrive1);
        rightDrive1.setInverted(true);
        rightDrive2 = new Spark(1);
        addChild(rightDrive2);
        rightDrive2.setInverted(true);
        rightDrive3 = new Spark(2);
        addChild(rightDrive3);
        rightDrive3.setInverted(true);
        rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2 , rightDrive3 );
        addChild(rightDrive);
        
        pressureSensor = new AnalogInput(0);
        addChild(pressureSensor);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        //MultiPIDController(PIDGains gains, PIDSource source, PIDOutput output, double period, String name)
        //PIDController(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output, double period)
        gyroPID = new MultiPIDController(ChassisConst.GyroTurnLow, gyroSource,gyroOutput, 0.02,"Gyro");
        leftDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveEncoderLeft,leftDriveOutput, 0.02,"LeftDrive");
        rightDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveEncoderRight,rightDriveOutput, 0.02, "RightDrive");
        
        SmartDashboard.putData("gyroPID", gyroPID);
        SmartDashboard.putData("leftDrivePID", leftDrivePID);
        SmartDashboard.putData("rightDrivePID", rightDrivePID);
        
        //VERIFY Create ChassisConst file and put in default values to make this code valid - JB
        //VERIFY make the period of the PIDControllers 0.02 seconds (50 hz) -JB
        
        //VERIFY Find a non-deprecated function to use for this -JB
        LinearDigitalFilter.movingAverage(gyroSource, ChassisConst.gyroTolerancebuffer);
        
        double pulsePerRevolutionLeft, pulsePerRevolutionRight;
    	pulsePerRevolutionRight = ChassisConst.pulsePerRevolution;
    	pulsePerRevolutionLeft = ChassisConst.pulsePerRevolution;
        
        double distanceperpulse = Math.PI*ChassisConst.wheelDiameter/pulsePerRevolutionLeft /
        		ChassisConst.encoderGearRatio/ChassisConst.gearRatio * ChassisConst.Fudgefactor;

        //VERIFY Fix these lines of code -JB
        driveEncoderRight.setReverseDirection(true);
        driveEncoderLeft.setDistancePerPulse(distanceperpulse);
        
        distanceperpulse = Math.PI*ChassisConst.wheelDiameter/pulsePerRevolutionRight /
        		ChassisConst.encoderGearRatio/ChassisConst.gearRatio * ChassisConst.Fudgefactor;
        
        driveEncoderRight.setDistancePerPulse(distanceperpulse);
        
        //VERIFY update to the latest RobotBuilderExtensions so that the deprecated addSensor code below is no longer used. -JB
        
        //-----------------------------------------------------------------------
        // Logging
        //-----------------------------------------------------------------------
        
        //TODO Finish logging. See 2017 for examples
        //Joe: instead of logging left&right Drive1,2,3, log leftDrive and rightDrive. Don't need to log pitch & roll.
        //VERIFY above (ldrive, rdrive, remove pitch & roll)
        CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return driveEncoderLeft.getDistance(); }
    	};
    	CSVLogger.getInstance().add("DriveTrainDistanceL", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveEncoderRight.getDistance(); }
    	};
    	CSVLogger.getInstance().add("DriveTrainDistanceR", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveEncoderLeft.getRate(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRateL", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveEncoderRight.getRate(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRateR", temp);    	
    	
    	temp = new CSVLoggable() {
			public double get() { return leftDrive.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainLeft", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return rightDrive.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRight", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getAngle(); }  		
    	};    	
    	CSVLogger.getInstance().add("ChassisAngle", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return gyroPID.getSetpoint(); }  		
    	};    	
    	CSVLogger.getInstance().add("GyroSetpoint", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getGyroComp(); }  		
    	};    	
    	CSVLogger.getInstance().add("GyroCompensation", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return navX.isConnected() ? 1: 0; }  		
    	};    	
    	CSVLogger.getInstance().add("GyroIsConnected", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return navX.isCalibrating() ? 1: 0; }  		
    	};    	
    	CSVLogger.getInstance().add("GyroIsCalibrating", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getX(); }  		
    	};     	
    	CSVLogger.getInstance().add("ChassisX", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getY(); }  		
    	};      	
    	CSVLogger.getInstance().add("ChassisY", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getPressure(); }  		
    	};  
    	CSVLogger.getInstance().add("Pressure", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				DoubleSolenoid.Value state = shifters.get();
				double state_int;
				if (state == DoubleSolenoid.Value.kForward)
					state_int = 1.0;
				else
					state_int = 0.0;
				return state_int;}  		
    	};  
    	CSVLogger.getInstance().add("Shifter", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				if (path == null) 
					return -1;
				return getNextWaypointNumber(); }  		
    	};  
    	CSVLogger.getInstance().add("NextWaypointNumber", temp);
    }

    @Override
    public void initDefaultCommand() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    private double x = 0, y = 0;
    private double prevLeftEncoderDistance  = 0, prevRightEncoderDistance = 0;

	public void calcXY()
	{
		 double distance, 
		 	    leftEncoderDistance, 
		 	    rightEncoderDistance, 
		 	    gyroAngle;
		 
		 leftEncoderDistance  = driveEncoderLeft.getDistance();
		 rightEncoderDistance = driveEncoderRight.getDistance();
		 gyroAngle = getAngle();
		 distance =  ((leftEncoderDistance - prevLeftEncoderDistance) + (rightEncoderDistance - prevRightEncoderDistance))/2;
		 x = x + distance * Math.sin(Math.toRadians(gyroAngle));
		 y = y + distance * Math.cos(Math.toRadians(gyroAngle));
		 prevLeftEncoderDistance  = leftEncoderDistance;
		 prevRightEncoderDistance = rightEncoderDistance;
	}
	
	/////////////////////////////////////////////////////////////
	//GET methods
	/////////////////////////////////////////////////////////////   
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getPressure() {
		return (50*pressureSensor.getAverageVoltage() -25);
	}
	
    public double getAngle() {  	
    	return navX.getAngle();
    }
    
    public String getNavXFirmware() {
    	return navX.getFirmwareVersion();
    }  
    
    public void pidDriveAuto()
    {
        double left, right, gyroValue, gyroMin;
        
        if (DriverStation.getInstance().isDisabled()) {
            stopDrive();
        }
        else {      	
        	if (Math.abs(gyroOutput.getOutput()) > 0 && gyroPID.isEnabled() && !leftDrivePID.isEnabled() && !rightDrivePID.isEnabled()) 
        		gyroMin = ChassisConst.gyroTurnMin * Math.signum(gyroOutput.getOutput());
        	else
        		gyroMin = 0;
        	
        	gyroValue = Math.signum(gyroOutput.getOutput()) * Math.min(Math.abs(gyroOutput.getOutput()+gyroMin) , 1.0);
        	left = this.left+leftDriveOutput.getOutput() + gyroValue;
            right = this.right+rightDriveOutput.getOutput() - gyroValue;
            drive(left, right);
            this.left = 0;
            this.right = 0;
        }
    } /* End pidDriveAuto() */
    
    double gyroComp = 0;
    
    public void setGyroComp(double compensation) {
    	navX.setAngleAdjustment(compensation);    	
    	//gyroComp = compensation;
    }
       
    public double getGyroComp() {
    	return gyroComp;
    }    

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void shiftHigh() {
    	shifters.set(DoubleSolenoid.Value.kForward);
    }
    
    public void shiftLow() {
    	shifters.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick) {
       left = -leftJoystick.getY();
       right = -rightJoystick.getY();
    }
    
    public void tankDrive(double left, double right) {
        this.left = left;
        this.right = right;
    }
    
    public void stopDrive() {
        if (gyroPID.isEnabled())
            gyroPID.reset();
        if (leftDrivePID.isEnabled())
            leftDrivePID.reset();
        if (rightDrivePID.isEnabled())
            rightDrivePID.reset();        
       
        tankDrive(0, 0);  
    }
	
	//Other 
	public boolean isGyroCalibrating() {
		return navX.isCalibrating();
	}
	
	public void pidDrive() {
	    double left, right;
	    if (DriverStation.getInstance().isDisabled()) {
	        stopDrive();
	    }
	    else {
	        left = this.left+leftDriveOutput.getOutput() + gyroOutput.getOutput();
	        right = this.right+rightDriveOutput.getOutput() - gyroOutput.getOutput();
	        drive(left, right);
	        this.left = 0;
	        this.right = 0;
	    }
	}
	
    private void drive(double left, double right) {
        leftDrive1.set(-left);
        leftDrive2.set(-left);
        leftDrive3.set(-left);
        
        rightDrive1.set(right);
        rightDrive2.set(right);
        rightDrive3.set(right);
    }
    
    public void resetPosition() {
    	driveEncoderLeft.reset();
    	driveEncoderRight.reset();
        navX.zeroYaw();
        fFirstUse = true;
        ctrRollOver = 0;
        setXY(0,0);
        this.prevLeftEncoderDistance = 0;
        this.prevRightEncoderDistance = 0;
    } /* End resetPosition */
    
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    } /* End setXY */

	public double getLeftDistance() 
	{
		return driveEncoderLeft.getDistance();
	}

	public double getRightDistance() {
		return driveEncoderRight.getDistance();
	}
	
    //Path methods
	ArrayList<Waypoint> path;
	int currentWaypoint = 0;
	
	public void setPath(ArrayList<Waypoint> path) {
		if ((path == null)) {
			Logger.getInstance().println("Null path in setPath", Severity.ERROR);
			Logger.getInstance().printStackTrace(new NullPointerException());
		}
		this.path = path;
		currentWaypoint = 0;
	}

	public int getNextWaypointNumber() {
		return currentWaypoint;
	}
	
	public Waypoint getNextWaypoint() {
		return path.get(getNextWaypointNumber());
	}
	
	public void incrementWaypoint() {
		if (currentWaypoint + 1 < path.size()) {
			currentWaypoint++;
		}
		else {
			Logger.getInstance().println("Attempt to increment waypoint past path", Severity.ERROR);
			Logger.getInstance().printStackTrace(new IndexOutOfBoundsException());
		}
	}
	
	public double getDistanceToEnd() {
		return getDistanceToWaypoint(path.get(path.size()-1));
	}
	
	public double getAngleToWaypoint(Waypoint waypt) {
		double deltaX = waypt.getX() - getX();
        double deltaY = waypt.getY() - getY();
        
		return Math.toDegrees(Math.atan2(deltaX, deltaY));
	}

	public double getAngleToNextWaypoint() {
		return getAngleToWaypoint(getNextWaypoint());
	}
	
	public double getDistanceBetweenWaypoints(Waypoint cur, Waypoint to) {
		double deltaX = to.getX() - cur.getX();
        double deltaY = to.getY() - cur.getY();
        
        return Math.sqrt(deltaX*deltaX+deltaY*deltaY);
	}
	
	public double getDistanceToWaypoint(Waypoint waypt) {
		Waypoint currentLocation = new Waypoint(Robot.chassis.getX(), Robot.chassis.getY(), Robot.chassis.getAngle());
        return getDistanceBetweenWaypoints(currentLocation, waypt);
	}
	
	public double getDistanceToNextWaypoint() {
		return getDistanceToWaypoint(getNextWaypoint());
	}
	
	public int getCurrentWaypointNumber() {
		return currentWaypoint;
	}
	
	public Waypoint getCurrentWaypoint() {
		return path.get(currentWaypoint);
	}
	
	public int getPreviousWaypointNumber() {
		if (currentWaypoint - 1 >= 0) {
			return currentWaypoint - 1;
		}
		else {
			Logger.getInstance().println("Attempt to get negative previous waypoint", Severity.ERROR);
			return currentWaypoint;
		}
	}
	
	public Waypoint getPreviousWaypoint() {
		if (currentWaypoint - 1 >= 0) {
			return path.get(currentWaypoint - 1);
		}
		else {
			Logger.getInstance().println("Attempt to get negative previous waypoint", Severity.ERROR);
			return path.get(currentWaypoint);
		}
	}
	
	public int getLastWaypointNumber() {
		return path.size()-1;
	}
}
