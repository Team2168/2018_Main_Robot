package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subystem file for cube intake gripper
 */
public class CubeIntakeGripper extends Subsystem {


	private static CubeIntakeGripper instance = null;
	private Solenoid intakeOpenPiston;
	
	public CubeIntakeGripper(){
		intakeOpenPiston = new Solenoid(RobotMap.CUBE_INTAKE_OPEN_PISTON_CLOSED);
	}
	
	/**
	 * Calls instance object and makes it singleton object of class fork lift
	 * @return 
	 * @return singleton object instance
	 */
	public static CubeIntakeGripper getInstance(){
		if (instance == null)
			instance = new CubeIntakeGripper();
		return instance;
	}
	
	
	public void openOpen(){
		intakeOpenPiston.set(false);
	}
	public void closeOpen(){
		intakeOpenPiston.set(true);
	}
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

