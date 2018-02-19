package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Subsystem class for lift shifter
 */
public class LiftRatchetShifter extends Subsystem {
	
	private static LiftRatchetShifter instance = null;
	private DoubleSolenoid ratchetShifter;
	private static DigitalInput ratchetPosition;


	/**
	 * Default constructor for the lift shifter
	 */
	private LiftRatchetShifter() {
	ratchetShifter = new DoubleSolenoid(RobotMap.PCM_CAN_ID_2, RobotMap.LIFT_RACHET_DISENGAGE_PCM, RobotMap.LIFT_RACHET_ENGAGE_PCM);
	ratchetPosition = new DigitalInput(RobotMap.LIFT_RATCHET_ENGAGE_LIMIT);

	ConsolePrinter.putBoolean("Is Lift Ratchet Engaged", () -> {return Robot.liftRatchetShifter.isRatchetEngaged();}, true, false);
	ConsolePrinter.putBoolean("Is Lift Ratchet Disengaged", () -> {return Robot.liftRatchetShifter.isRatchetDisEngaged();}, true, false);
	}

	
	/**
	 * Singleton constructor of the lift
	 * 
	 */
	public static LiftRatchetShifter GetInstance() {
		if (instance == null)
			instance = new LiftRatchetShifter();
		return instance;
	}
	/**
	 * Shifts the Lift Ratchet to Engage
	 */
    public void engageRatchet() {
    	ratchetShifter.set(DoubleSolenoid.Value.kForward);
    }
    
	/**
	 * Shifts the Lift Ratchet to Disengage
	 */
    public void disengageRatchet() {
    	ratchetShifter.set(DoubleSolenoid.Value.kReverse);
    }
    
//	/**
//	 * Returns true if last commanded shift was disengage ratchet
//	 */
//    public boolean isDisengaged() {
//    	return ratchetShifter.get() == DoubleSolenoid.Value.kForward;
//    }
//
//	/**
//	 * Returns true if last commanded shift was engage ratchet
//	 */
//    public boolean isEngaged() {
//    	return ratchetShifter.get() == DoubleSolenoid.Value.kReverse;
//    }
//    
	
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	/**
	 * Hall Effect Status of Ratchet Position, true if ratchet is engaged
	 */
	public boolean isRatchetEngaged() {
		return !ratchetPosition.get();
	}
	
	
	/**
	 * Hall Effect Status of Ratchet Position, true if ratchet is disengaged
	 */
	public boolean isRatchetDisEngaged() {
		return !ratchetPosition.get();
	}
}

