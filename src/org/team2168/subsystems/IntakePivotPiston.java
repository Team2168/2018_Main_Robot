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
	private static DoubleSolenoid CubeIntakePivotPiston;
	
	public boolean isPivotDown = false;
	
	
	public IntakePivotPiston() {
		CubeIntakePivotPiston = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.CUBE_INTAKE_PIVOT_PISTON_EXTEND, RobotMap.CUBE_INTAKE_PIVOT_PISTON_RETRACT);;
		ConsolePrinter.putBoolean("isIntakePivotDown", () -> {return getPivotStatus();}, true, false);
	
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
    	CubeIntakePivotPiston.set(DoubleSolenoid.Value.kForward);
    	this.isPivotDown = true;
    }
    
	/**
	 * Retracts cube intake pivot piston
	 */
    public void retractPivotPiston() {
    	CubeIntakePivotPiston.set(DoubleSolenoid.Value.kReverse);
    	this.isPivotDown = false;
    }
    
    
    public boolean getPivotStatus() {
    	return isPivotDown;
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

