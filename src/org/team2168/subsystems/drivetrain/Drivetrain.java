package org.team2168.subsystems.drivetrain;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.DriveWithJoysticks;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */

public class Drivetrain extends Subsystem
{

	private static Drivetrain instance = null;
	
	
	
	//Drivetrain motor controllers
	private static VictorSP leftMotor1;
	private static VictorSP leftMotor2;
	private static VictorSP leftMotor3;
	
	private static VictorSP rightMotor1;
	private static VictorSP rightMotor2;
	private static VictorSP rightMotor3;
	
	
	
	//initializes member variables
	private Drivetrain()
	{
		leftMotor1 = new VictorSP(RobotMap.LEFT_MOTOR_1_PWM);
		leftMotor2 = new VictorSP(RobotMap.LEFT_MOTOR_2_PWM);
		leftMotor3 = new VictorSP(RobotMap.LEFT_MOTOR_3_PWM);
		
		rightMotor1 = new VictorSP(RobotMap.RIGHT_MOTOR_1_PWM);
		rightMotor2 = new VictorSP(RobotMap.RIGHT_MOTOR_2_PWM);
		rightMotor3 = new VictorSP(RobotMap.RIGHT_MOTOR_3_PWM);
		
	}
	
	
	
	public static Drivetrain getInstance()
	{
		
		if(instance==null) {

			instance = new Drivetrain();
		}
		
		return instance;
		
	}
	
	
	
	public void driveLeft1(double speed)
	{
		
		if(RobotMap.REVERSE_LEFT_1_MOTOR_DIRECTION)
			speed=-speed;
		
		leftMotor1.set(speed);
		
	}
	
	
	public void driveLeft2(double speed)
	{
		
		if(RobotMap.REVERSE_LEFT_2_MOTOR_DIRECTION)
			speed=-speed;
		
		leftMotor2.set(speed);
		
	}
	
	
	public void driveLeft3(double speed)
	{
		
		if(RobotMap.REVERSE_LEFT_3_MOTOR_DIRECTION)
			speed=-speed;
		
		leftMotor3.set(speed);
		
	}
	
	
	public void driveLeft(double speed)
	{
		
		driveLeft1(speed);
		driveLeft2(speed);
		driveLeft3(speed);
		
	}
	
	
	
	public void driveRight1(double speed)
	{
		
		if(RobotMap.REVERSE_RIGHT_1_MOTOR_DIRECTION)
			speed=-speed;
		rightMotor1.set(speed);
	
	}
	
	
	public void driveRight2(double speed)
	{
		
		if(RobotMap.REVERSE_RIGHT_2_MOTOR_DIRECTION)
			speed=-speed;
		
		rightMotor2.set(speed);
		
	}
	
	
	public void driveRight3(double speed)
	{
		
		if(RobotMap.REVERSE_RIGHT_3_MOTOR_DIRECTION)
			speed=-speed;
		
		rightMotor3.set(speed);
		
	}
	
	
	public void driveRight(double speed)
	{
		
		driveRight1(speed);
		driveRight2(speed);
		driveRight3(speed);
		
	}
	
	
	
	public void tankDrive(double leftSpeed, double rightSpeed)
	{
		
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
		
	}
	
	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
		
}
