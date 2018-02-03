package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem file for cube intake wheels
 */
public class CubeIntakeWheels extends Subsystem {

private static CubeIntakeWheels instance = null;
	
	private static VictorSP intakeMotorLeft;
	private static VictorSP intakeMotorRight;
	
	public CubeIntakeWheels(){
		intakeMotorLeft = new VictorSP(RobotMap.CUBE_INTAKE_MOTOR_LEFT);
		intakeMotorRight = new VictorSP(RobotMap.CUBE_INTAKE_MOTOR_RIGHT);
	}
	
	/**
	 * Calls instance object and makes it singleton object of class fork lift
	 * @return 
	 * @return singleton object instance
	 */
	public static CubeIntakeWheels getInstance(){
		if (instance == null)
			instance = new CubeIntakeWheels();
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

	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ());
    }
}

