package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the left wing
 * @author Aidan
 */
public class LeftWing extends Subsystem 
{
	private static LeftWing instance = null;
	
	private static VictorSP leftWingMotor1;
	private static VictorSP leftWingMotor2;
	
	private DoubleSolenoid leftWingPiston;
	
	/**
	 * Default constructors
	 */
	public LeftWing(){
		leftWingMotor1 = new VictorSP(RobotMap.LEFT_WING_MOTOR_1);
		leftWingMotor2 = new VictorSP(RobotMap.LEFT_WING_MOTOR_2);
		
		leftWingPiston = new DoubleSolenoid(RobotMap.LEFT_WING_PISTON_EXTEND, 
				RobotMap.LEFT_WING_PISTON_RETRACT);
	}
	
	/**
	 * Calls instance object and makes it singleton object of class Left Wing
	 * @return singleton object instance
	 */
	public static LeftWing getInstance(){
		if (instance == null)
			instance = new LeftWing();
		return instance;
	}
	
	/**
	 * Takes in a double speed and sets it to Left Wing Motor 1
	 * @param speed is a double between -1 and 1
	 */
	private void driveMotor1(double speed){
		if (RobotMap.LW_REVERSE1)
			speed = -speed;
		leftWingMotor1.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to Left Wing Motor 2
	 * @param speed is a double between -1 and 1
	 */
	private void driveMotor2(double speed){
		if (RobotMap.LW_REVERSE2)
			speed = -speed;
		leftWingMotor2.set(speed);
	}
	
	/**
	 * takes in a double as speed and assigns it to both left wing motors
	 * @param speed is a double between -1 and 1
	 */
	public void driveWing(double speed){
		driveMotor1(speed);
		driveMotor2(speed);
	}
	
	
	/**
	 * Retracts left wing piston
	 */
	public void retract(){
		leftWingPiston.set(Value.kReverse);
	}

	/**
	 * Extends left wing piston
	 */
	public void extend(){
		leftWingPiston.set(Value.kForward);
	}
	
	/**
	 * reads last commanded value of left wing to determine its status
	 * @return true if wing is raised, false if wing is extended
	 */
	public boolean isRetracted(){
		//if no sensor is installed use last commanded value
		return leftWingPiston.get() == DoubleSolenoid.Value.kReverse;
	}
	
	/**
	 * reads last commanded value of left wing to determine its status
	 * @return true if wing is extended, false if wing is raised
	 */
	public boolean isExtended(){
		//if no sensor is installed use last commanded value
		return leftWingPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	public void initDefaultCommand(){
	}
	
}
