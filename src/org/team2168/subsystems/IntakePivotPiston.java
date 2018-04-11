package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem Class for cube intake pivot piston
 */
public class IntakePivotPiston extends Subsystem {

	private static IntakePivotPiston instance = null;
	private static DoubleSolenoid pivotPiston;
	
	public boolean isPivotPistonExtended = false;
	
	
	public IntakePivotPiston() {
		pivotPiston = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.CUBE_INATKE_PIVOT_PISTON_EXTEND, RobotMap.CUBE_INTAKE_PIVOT_PISTON_RETRACT);;
		ConsolePrinter.putBoolean("isPivotPistonExtended", () -> {return getPistonStatus();}, true, false);
	
	}
	
	public static IntakePivotPiston getInstance(){
		if (instance == null)
			instance = new IntakePivotPiston();
		return instance;
	}
	
	
	
	/**
	 * Extends the cube intake pivot piston
	 */
    public void extendPivotPiston() {
    	pivotPiston.set(DoubleSolenoid.Value.kForward);
    	this.isPivotPistonExtended = true;
    }
    
	/**
	 * Retracts cube intake pivot piston
	 */
    public void retracPivotPiston() {
    	pivotPiston.set(DoubleSolenoid.Value.kReverse);
    	this.isPivotPistonExtended = false;
    }
    
    
    public boolean getPistonStatus() {
    	return isPivotPistonExtended;
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand()); //Fluppy floop flip flap
    }
}

