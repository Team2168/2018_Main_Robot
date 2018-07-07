package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.winch.driveWinchWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
	public static Winch instance = null;
	private static WPI_TalonSRX winchMotor1;
	private static WPI_TalonSRX winchMotor2;
	public volatile double winchMotor1Voltage;
	public volatile double winchMotor2Voltage;
	
	
	/**
	 * Default constructor for the lift
	 */
	private Winch() {
		winchMotor1 = new WPI_TalonSRX(5);
		winchMotor2 = new WPI_TalonSRX(6);
		Robot.winch.winchMotor1.configPeakCurrentLimit(0, 10);
		Robot.winch.winchMotor1.configPeakCurrentDuration(200, 10);
		Robot.winch.winchMotor1.configContinuousCurrentLimit(40, 0);
		Robot.winch.winchMotor1.enableCurrentLimit(true);
		
		Robot.winch.winchMotor2.configPeakCurrentLimit(0, 10);
		Robot.winch.winchMotor2.configPeakCurrentDuration(200, 10);
		Robot.winch.winchMotor2.configContinuousCurrentLimit(40, 0);
		Robot.winch.winchMotor2.enableCurrentLimit(true);
	}
	
	public static Winch GetInstance() {
		if (instance == null)
			instance = new Winch();
		return instance;
	}
	
	private void driveWinchMotor1(double speed){
		if (RobotMap.WINCH_MOTOR1_REVERSE)
			speed = -speed;
		winchMotor1.set(ControlMode.PercentOutput,speed);
		winchMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;

		
		
	}
	
	private void driveWinchMotor2(double speed){
		if (RobotMap.WINCH_MOTOR2_REVERSE)
			speed = -speed;
		winchMotor2.set(ControlMode.PercentOutput, speed);
		winchMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	public void driveWinch(double speed) {
		Robot.winch.winchMotor1.configPeakCurrentLimit(0, 0);
		Robot.winch.winchMotor1.configPeakCurrentDuration(200, 0);
		Robot.winch.winchMotor1.configContinuousCurrentLimit(40, 0);
		Robot.winch.winchMotor1.enableCurrentLimit(true);
		
		Robot.winch.winchMotor2.configPeakCurrentLimit(0, 0);
		Robot.winch.winchMotor2.configPeakCurrentDuration(200, 0);
		Robot.winch.winchMotor2.configContinuousCurrentLimit(40, 0);
		Robot.winch.winchMotor2.enableCurrentLimit(true);
		driveWinchMotor1(speed);
		driveWinchMotor2(speed);
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new driveWinchWithJoystick());
    }
    
    
}

