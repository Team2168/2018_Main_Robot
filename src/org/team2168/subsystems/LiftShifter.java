package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Subsystem class for lift shifter
 */
public class LiftShifter extends Subsystem {
	
	private static LiftShifter instance = null;
	private Solenoid gearShifter;


	/**
	 * Default constructor for the lift shifter
	 */
	private LiftShifter() {
	gearShifter = new Solenoid(RobotMap.LIFT_SHIFT_HIGH_LOW);
	ConsolePrinter.putBoolean("Lift in High Gear", () -> {return Robot.liftShifter.isInHighGear();}, true, false);
	ConsolePrinter.putBoolean("Lift in Low Gear", () -> {return Robot.liftShifter.isInLowGear();}, true, false);

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
    	gearShifter.set(true);
    }
    
	/**
	 * Shifts the Lift from Low to High Gear
	 */
    public void shiftToHigh() {
    	gearShifter.set(false);
    }
    
	/**
	 * Returns true if last commanded shift was Low Gear
	 */
    public boolean inLowGear() {
    	return gearShifter.get() == true;
    }

	/**
	 * Returns true if last commanded shift was High Gear
	 */
    public boolean inHighGear() {
    	return gearShifter.get() == false;
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

