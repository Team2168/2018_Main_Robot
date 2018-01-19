package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Subsystem class for the Right Wing
 * @author Aidan
 */
public class RightWing extends Subsystem 
{
	private static RightWing instance = null;
	
	private static VictorSP rightWingMotor1;
	private static VictorSP rightWingMotor2;
	
	private DoubleSolenoid rightWingPiston;
	
	/**
	 * Default Constructors
	 */
	public RightWing(){
		rightWingMotor1 = new VictorSP(RobotMap.RIGHT_WING_MOTOR_1);
		rightWingMotor2 = new VictorSP(RobotMap.RIGHT_WING_MOTOR_2);
		
		rightWingPiston = new DoubleSolenoid(RobotMap.RIGHT_WING_PISTON_EXTEND, 
				RobotMap.RIGHT_WING_PISTON_RETRACT);
	}
	
	/**
	 * Calls instance object and makes it singleton object of class Right Wing
	 * @return singleton object instance
	 */
	public static RightWing getInstance(){
		if (instance == null)
			instance = new RightWing();
		return instance;
	}
	
	/**
	 * Takes in a double speed and sets it to Right Wing Motor 1
	 * @param speed is a double between -1 and 1
	 */
	public void driveRightWingMotor1(double speed){
		if (RobotMap.RW_REVERSE1)
			speed = -speed;
		rightWingMotor1.set(speed);
	}
	/**
	 * takes in a double as speed and sets it to Right wing motor 2
	 * @param speed is a double between -1 and 1
	 */
	public void driveRightWingMotor2(double speed){
		if (RobotMap.RW_REVERSE2)
			speed = -speed;
		rightWingMotor2.set(speed);
	}
	
	/**
	 *  takes in a double as speed and assigns it to both right wing motors
	 * @param speed is a double between -1 and 1
	 */
	public void driveRightWing(double speed){
		driveRightWingMotor1(speed);
		driveRightWingMotor2(speed);
	}
	
	/**
	 * retracts right wing piston
	 */
	public void Retract(){
		rightWingPiston.set(Value.kReverse);
	}
	
	/**
	 * extends right wing piston
	 */
	public void Extend(){
		rightWingPiston.set(Value.kForward);
	}
	
	/**
	 * reads last commanded value of right wing to determine its status
	 * @return true if wing is raised, false if wing is extended
	 */
	public boolean isWingRaised(){
		//if no sensor is installed use last commanded value
		return rightWingPiston.get() == DoubleSolenoid.Value.kReverse;
	}
	
	/**
	 * reads last commanded value of right wing to determine its status
	 * @return true if wing is lowered, false if wing is raised
	 */
	public boolean isWingLowered(){
		//if no sensor is installed use last commanded value
		return rightWingPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	public void initDefaultCommand(){
	}
	
}
