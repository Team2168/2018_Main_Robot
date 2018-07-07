package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.winch.driveWinchWithJoystick;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
	public static Winch instance = null;
	private static Talon winchMotor1;
	private static Talon winchMotor2;
	public volatile double winchMotor1Voltage;
	public volatile double winchMotor2Voltage;
	
	
	/**
	 * Default constructor for the lift
	 */
	private Winch() {
		winchMotor1 = new Talon(RobotMap.WINCH_MOTOR_1);
		winchMotor2 = new Talon(RobotMap.WINCH_MOTOR_2);
	}
	
	public static Winch GetInstance() {
		if (instance == null)
			instance = new Winch();
		return instance;
	}
	
	private void driveWinchMotor1(double speed){
		if (RobotMap.WINCH_MOTOR1_REVERSE)
			speed = -speed;
		winchMotor1.set(speed);
		winchMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
		
	}
	
	private void driveWinchMotor2(double speed){
		if (RobotMap.WINCH_MOTOR2_REVERSE)
			speed = -speed;
		winchMotor2.set(speed);
		winchMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	public void driveWinch(double speed) {
		driveWinchMotor1(speed);
		driveWinchMotor2(speed);
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new driveWinchWithJoystick());
    }
}

