package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Subsystem class for lift shifter
 */
public class LiftShifter extends Subsystem {
	
	private static LiftShifter instance = null;
	private DoubleSolenoid gearShifter;


	/**
	 * Default constructor for the lift shifter
	 */
	private LiftShifter() {
	gearShifter = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.LIFT_HIGH_GEAR, RobotMap.LIFT_LOW_GEAR);
	ConsolePrinter.putBoolean("In High Gear", () -> {return Robot.liftShifter.isInHighGear();}, true, false);
	ConsolePrinter.putBoolean("In Low Gear", () -> {return Robot.liftShifter.isInLowGear();}, true, false);

	}

	
	/**
	 * Singleton constructor of the lift
	 * 
	 */

	public static LiftShifter GetInstance() {
		if (instance == null)
			instance = new LiftShifter();
		return instance;
	}
	/**
	 * Shifts the Lift from High to Low Gear
	 */
    public void shiftToLow() {
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    }
    
	/**
	 * Shifts the Lift from Low to High Gear
	 */
    public void shiftToHigh() {
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
    
	/**
	 * Returns true if last commanded shift was Low Gear
	 */
    public boolean inLowGear() {
    	return gearShifter.get() == DoubleSolenoid.Value.kForward;
    }

	/**
	 * Returns true if last commanded shift was High Gear
	 */
    public boolean inHighGear() {
    	return gearShifter.get() == DoubleSolenoid.Value.kReverse;
    }
    
    public boolean isInLowGear() {
    	return inLowGear();
    }
    
    public boolean isInHighGear() {
    	return inHighGear();
    }

	
	
	
	
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

