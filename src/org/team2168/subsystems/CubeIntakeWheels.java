package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem file for cube intake wheels
 */
public class CubeIntakeWheels extends Subsystem {

private static CubeIntakeWheels instance = null;
	
	private static VictorSP intakeMotorLeft;
	private static VictorSP intakeMotorRight;
	private static AnalogInput intakeIRSensor;
	
	public CubeIntakeWheels(){
		intakeMotorLeft = new VictorSP(RobotMap.CUBE_INTAKE_MOTOR_LEFT);
		intakeMotorRight = new VictorSP(RobotMap.CUBE_INTAKE_MOTOR_RIGHT);
		intakeIRSensor = new AnalogInput(RobotMap.CUBE_INTAKE_IR_SENSOR1);
		
		ConsolePrinter.putNumber("Gripper IR", () -> {return getRawIRVoltage();}, true, false);
	}
	
	/**
	 * Calls instance object and makes it singleton object of class intake wheels
	 * @return 
	 * @return singleton object instance
	 */
	public static CubeIntakeWheels getInstance(){
		if (instance == null)
			instance = new CubeIntakeWheels();
		return instance;
	}
	/**
	 * Takes in a double speed and sets it to intake lift motor 
	 * @param speed is a double between -1 and 1
	 */
	private void driveLeft(double speed){
		if (RobotMap.INTAKE_LEFT_REVERSE)
			speed = -speed;
		intakeMotorLeft.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to intake lift motor 
	 * @param speed is a double between -1 and 1 where -1 is in and 1 is out
	 */
	private void driveRight(double speed){
		if (RobotMap.INTAKE_RIGHT_REVERSE)
			speed = -speed;
		intakeMotorRight.set(speed);
	}
	

	/**
	 * drives all cube intake motors where 1 is out and -1 is in
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
	 * 
	 * @return true when a cube is present
	 */
	public boolean isCubePresent() {
		return (getRawIRVoltage() >= RobotMap.CUBE_INTAKE_IR_THRESHOLD);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ());
    }
}

