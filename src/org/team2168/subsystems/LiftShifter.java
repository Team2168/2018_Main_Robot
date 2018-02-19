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
	private DoubleSolenoid gearShifter;


	/**
	 * Default constructor for the lift shifter
	 */
	private LiftShifter() {
	gearShifter = new DoubleSolenoid(RobotMap.PCM_CAN_ID_2, RobotMap.LIFT_LOW_GEAR_PCM, RobotMap.LIFT_HIGH_GEAR_PCM);
	//gearShifter = new DoubleSolenoid(RobotMap.LIFT_HIGH_GEAR, RobotMap.LIFT_LOW_GEAR);
	ConsolePrinter.putBoolean("Lift High Gear", () -> {return Robot.liftShifter.isHighGear();}, true, false);
	ConsolePrinter.putBoolean("Lift Low Gear", () -> {return Robot.liftShifter.isLowGear();}, true, false);
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
    public boolean isLowGear() {
    	return gearShifter.get() == DoubleSolenoid.Value.kForward;
    }

	/**
	 * Returns true if last commanded shift was High Gear
	 */
    public boolean isHighGear() {
    	return gearShifter.get() == DoubleSolenoid.Value.kReverse;
    }
    
	
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

