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
public class ForkLift extends Subsystem 
{
	private static ForkLift instance = null;
	
	private static VictorSP forkLiftMotor;
	private DoubleSolenoid forkLiftPiston;
	private static DigitalInput raisedLimitSwitch;
	private static DigitalInput loweredLimitSwitch;
	
	/**
	 * Default constructors
	 */
	public ForkLift(){
		forkLiftMotor = new VictorSP(RobotMap.FORKLIFT_MOTOR);
		forkLiftPiston = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.FORK_LIFT_PISTON_EXTEND, RobotMap.FORK_LIFT_PISTON_RETRACT);
		loweredLimitSwitch = new DigitalInput(RobotMap.FORK_LIFT_LOWERED_LIMIT);
		raisedLimitSwitch = new DigitalInput(RobotMap.FORK_LIFT_RAISED_LIMIT);
			
	}
	
	/**
	 * Calls instance object and makes it singleton object of class fork lift
	 * @return singleton object instance
	 */
	public static ForkLift getInstance(){
		if (instance == null)
			instance = new ForkLift();
		return instance;
	}
	
	/**
	 * Takes in a double speed and sets it to fork lift motor 
	 * @param speed is a double between -1 and 1
	 */
	public void driveMotor(double speed){
		if (RobotMap.FORK_LIFT_REVERSE)
			speed = -speed;
		forkLiftMotor.set(speed);
	}
	

	/**
	 * Retracts fork lift piston
	 */
	public void retractPivot(){
		forkLiftPiston.set(Value.kReverse);
	}

	/**
	 * Extends fork lift piston
	 */
	public void extendPivot(){
		forkLiftPiston.set(Value.kForward);
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
				return forkLiftPiston.get() == DoubleSolenoid.Value.kForward;
			}
	
	/**
	 * reads last commanded value of fork lift to determine its status
	 * @return true if fork lift is raised, false if lift is extended
	 */
	public boolean isCommandedRetract(){
		//if no sensor is installed or sensor is malfunctioning use last commanded value
		return forkLiftPiston.get() == DoubleSolenoid.Value.kReverse;
		
	}
	
	public void initDefaultCommand(){
	}
	
}
