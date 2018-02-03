package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**  
 * Subsystem class for the fork lift
 * @author Aidan
 */
public class Platform extends Subsystem 
{
	private static Platform instance = null;
	
	private static VictorSP platformMotor;
	private Solenoid platformPiston;
	private static DigitalInput raisedLimitSwitch;
	private static DigitalInput loweredLimitSwitch;
	
	/**
	 * Default constructors
	 */
	public Platform(){
		platformMotor = new VictorSP(RobotMap.PLATFORM_MOTOR);
		platformPiston = new Solenoid(RobotMap.PLATFORM_PISTON);
		loweredLimitSwitch = new DigitalInput(RobotMap.PLATFORM_LOWERED_LIMIT);
		raisedLimitSwitch = new DigitalInput(RobotMap.PLATFORM_RAISED_LIMIT);
			
	}
	
	/**
	 * Calls instance object and makes it singleton object of class fork lift
	 * @return singleton object instance
	 */
	public static Platform getInstance(){
		if (instance == null)
			instance = new Platform();
		return instance;
	}
	
	/**
	 * Takes in a double speed and sets it to fork lift motor 
	 * @param speed is a double between -1 and 1
	 */
	public void driveMotor(double speed){
		if (RobotMap.FORK_LIFT_REVERSE)
			speed = -speed;
		platformMotor.set(speed);
	}
	

	/**
	 * Retracts fork lift piston
	 */
	public void retract(){
		platformPiston.set(RobotMap.SOLENOID_OFF);
	}

	/**
	 * Extends fork lift piston
	 */
	public void extend(){
		platformPiston.set(RobotMap.SOLENOID_ON);
	}
	
	/**
	 * takes in value of raised limit switch
	 * @return true if Fork lift is retracted, false if lift is extended
	 */
	public boolean isRetracted(){
		return raisedLimitSwitch.get();
	}
	
	/**
	 * takes in value of lowered limit switch
	 * @return true if fork lift is extended, false if lift is retracted
	 */
	public boolean isExtended(){
		return loweredLimitSwitch.get();

	}
	
	/**
	 * reads last commanded value of fork lift to determine its status
	 * @return true if fork lift is extended, false if lift is raised
	 */
	public boolean isCommandedExtend(){
		//if no sensor is installed or sensor is malfunctioning use last commanded value
				return platformPiston.get() == RobotMap.SOLENOID_ON;
			}
	
	/**
	 * reads last commanded value of fork lift to determine its status
	 * @return true if fork lift is raised, false if lift is extended
	 */
	public boolean isCommandedRetract(){
		//if no sensor is installed or sensor is malfunctioning use last commanded value
		return platformPiston.get() == RobotMap.SOLENOID_OFF;
		
	}
	
	public void initDefaultCommand(){
	}
	
}
