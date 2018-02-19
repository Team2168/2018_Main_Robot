package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbGuideArm extends Subsystem {

	private static DoubleSolenoid guidingArm;
	private static ClimbGuideArm instance = null;
	
	
	
	/**
	 * Default constructors for Climb guide arm
	 */
	private ClimbGuideArm() {
		guidingArm = new DoubleSolenoid(RobotMap.PCM_CAN_ID,RobotMap.CLIMB_GUIDE_ARM_RAISE, RobotMap.CLIMB_GUIDE_ARM_LOWER);
			}
	
	/**
	 * Calls instance object and makes it a singleton object of type Climb guide arm
	 * @returns Climb guide arm object "instance"
	 */
	public static ClimbGuideArm getInstance() {
		if(instance == null)
			instance = new ClimbGuideArm();
		
		return instance;
	}

	/**
	 * Close down guide arm
	 */
    public void retractArm() {
    	guidingArm.set(DoubleSolenoid.Value.kForward);
    }
    
	/**
	 * Open guide arm
	 */
    public void extendArm() {
    	guidingArm.set(DoubleSolenoid.Value.kReverse);
    }
	
    /**
	 * Returns true if arm is closed
	 */
    public boolean isArmRetracted() {
    	return guidingArm.get() == DoubleSolenoid.Value.kForward;
    }

	/**
	 * Returns true if arm is open
	 */
    public boolean isArmExtended() {
    	return guidingArm.get() == DoubleSolenoid.Value.kReverse;
    }

	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

