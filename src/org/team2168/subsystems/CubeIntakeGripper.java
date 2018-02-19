package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subystem file for cube intake gripper
 */
public class CubeIntakeGripper extends Subsystem {


	private static CubeIntakeGripper instance = null;
	private DoubleSolenoid intakeOpenPiston;
	
	public CubeIntakeGripper(){
		intakeOpenPiston = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.CUBE_INTAKE_GRIPPER_LOWERED, RobotMap.CUBE_INTAKE_GRIPPER_RAISED);
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
	
	
	public void extendIntake(){
		intakeOpenPiston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractIntake(){
		intakeOpenPiston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isIntakeExtended() {
		return intakeOpenPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	public boolean isIntakeRetracted() {
		return intakeOpenPiston.get() == DoubleSolenoid.Value.kReverse;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

