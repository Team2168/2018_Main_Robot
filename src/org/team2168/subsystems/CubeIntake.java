package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
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
public class CubeIntake extends Subsystem 
{
	private static CubeIntake instance = null;
	
	private static VictorSP intakeMotorLeft;
	private static VictorSP intakeMotorRight;
	private Solenoid intakeOpenPiston;
	private DoubleSolenoid intakePivotPiston;
	private static AnalogInput intakeIRSensor;
	
	/**
	 * Default constructors
	 */
	public CubeIntake(){
		intakeMotorLeft = new VictorSP(RobotMap.CUBE_INTAKE_MOTOR_LEFT);
		intakeMotorRight = new VictorSP(RobotMap.CUBE_INTAKE_MOTOR_RIGHT);
		//intakeOpenPiston = new Solenoid(RobotMap.CUBE_INTAKE_OPEN_PISTON_CLOSED);
		
		intakePivotPiston = new DoubleSolenoid(RobotMap.CUBE_INTAKE_PIVOT_EXTEND, 
				RobotMap.CUBE_INTAKE_PIVOT_RETRACT);
		intakeIRSensor = new AnalogInput(RobotMap.CUBE_INTAKE_IR_SENSOR1);
			
	}
	
	/**
	 * Calls instance object and makes it singleton object of class fork lift
	 * @return 
	 * @return singleton object instance
	 */
	public static CubeIntake getInstance(){
		if (instance == null)
			instance = new CubeIntake();
		return instance;
	}
	
	/**
	 * Takes in a double speed and sets it to fork lift motor 
	 * @param speed is a double between -1 and 1
	 */
	private void driveLeft(double speed){
		if (RobotMap.INTAKE_LEFT_REVERSE)
			speed = -speed;
		intakeMotorLeft.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to fork lift motor 
	 * @param speed is a double between -1 and 1
	 */
	private void driveRight(double speed){
		if (RobotMap.INTAKE_RIGHT_REVERSE)
			speed = -speed;
		intakeMotorRight.set(speed);
	}
	

	/**
	 * drives all cube intake motors
	 * @param speed
	 */
	
	public void driveAllMotors(double speed) {
		driveRight(speed);
		driveLeft(speed);
	}
	
	/**
	 * Gets the voltage given by the Sharp IR sensor on the Lift Intake.
	 * @return the raw voltage from the cube presence sensor
	 */
	public double getRawIRVoltage(){
		return intakeIRSensor.getVoltage();
	}
	
	/**
	 * Automatically determines if the cube is present by contrasting the IR Sensor voltage to a threshold set in the RobotMap.java 
	 * @return true if a cube is present within the cube intake
	 */
	public boolean isCubePresent(){
		return (getRawIRVoltage() >= RobotMap.CUBE_INTAKE_IR_THRESHOLD);
	}
	

	/**
	 * Retracts fork lift piston
	 */
	public void retractPivot(){
		intakePivotPiston.set(Value.kReverse);
	}

	/**
	 * Extends fork lift piston
	 */
	public void extendPivot(){
		intakePivotPiston.set(Value.kForward);
	}
	
	public void openOpen(){
		intakeOpenPiston.set(false);
	}
	public void closeOpen(){
		intakeOpenPiston.set(true);
	}
	
		
	/**
	 * takes in value of raised limit switch
	 * @return true if Fork lift is retracted, false if lift is extended
	 */
	public boolean isRetracted(){
		return intakePivotPiston.get() == DoubleSolenoid.Value.kReverse;
	}
	
	/**
	 * takes in value of lowered limit switch
	 * @return true if fork lift is extended, false if lift is retracted
	 */
	public boolean isExtended(){
		return intakePivotPiston.get() == DoubleSolenoid.Value.kForward;

	}
	
	public void initDefaultCommand(){
	}
	
}
