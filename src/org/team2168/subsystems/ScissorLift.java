package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the scissor lift
 * @author Aidan
 */
public class ScissorLift extends Subsystem 
{
	private static ScissorLift instance = null;
	
	private static VictorSP scissorLiftMotor;
	private Solenoid scissorLiftPiston;
	private static DigitalInput raisedLimitSwitch;
	private static DigitalInput loweredLimitSwitch;
	
	/**
	 * Default constructors
	 */
	public ScissorLift(){
		scissorLiftMotor = new VictorSP(RobotMap.SCISSOR_MOTOR);
		scissorLiftPiston = new Solenoid(RobotMap.SCISSOR_LIFT_PISTON); 
		loweredLimitSwitch = new DigitalInput(RobotMap.SCISSOR_LIFT_LOWERED_LIMIT);
		raisedLimitSwitch = new DigitalInput(RobotMap.SCISSOR_LIFT_RAISED_LIMIT);
			
	}
	
	/**
	 * Calls instance object and makes it singleton object of class scissor lift
	 * @return singleton object instance
	 */
	public static ScissorLift getInstance(){
		if (instance == null)
			instance = new ScissorLift();
		return instance;
	}
	
	/**
	 * Takes in a double speed and sets it to scissor lift motor 
	 * @param speed is a double between -1 and 1
	 */
	public void driveMotor(double speed){
		if (RobotMap.SCISSOR_LIFT_REVERSE)
			speed = -speed;
		scissorLiftMotor.set(speed);
	}
	
	
	

	/**
	 * Retracts scissor lift piston
	 */
	public void retract(){
		scissorLiftPiston.set(RobotMap.SOLENOID_OFF);
	}        

	/**
	 * Extends scissor lift piston
	 */
	public void extend(){
		scissorLiftPiston.set(RobotMap.SOLENOID_ON);
	}
	
	/**
	 * takes in value of raised limit switch
	 * @return true if Scissor lift is retracted, false if lift is extended
	 */
	public boolean isRetracted(){
		return raisedLimitSwitch.get();
	}
	
	/**
	 * takes in value of lowered limit switch
	 * @return true if scissor lift is extended, false if lift is retracted
	 */
	public boolean isExtended(){
		return loweredLimitSwitch.get();

	}
	
	/**
	 * reads last commanded value of scissor lift to determine its status
	 * @return true if scissor lift is extended, false if lift is raised
	 */
	public boolean isCommandedExtend(){
		//if no sensor is installed or sensor is malfunctioning use last commanded value
				return scissorLiftPiston.get() == RobotMap.SOLENOID_ON;
			}
	
	/**
	 * reads last commanded value of scissor lift to determine its status
	 * @return true if scissor lift is raised, false if lift is extended
	 */
	public boolean isCommandedRetract(){
		//if no sensor is installed or sensor is malfunctioning use last commanded value
		return scissorLiftPiston.get() == RobotMap.SOLENOID_OFF;
		
	}
	
	public void initDefaultCommand(){
	}
	
}
