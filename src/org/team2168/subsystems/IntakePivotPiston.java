package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem Class for cube intake pivot piston
 */
public class IntakePivotPiston extends Subsystem {

	private static IntakePivotPiston instance = null;
	private static DoubleSolenoid CubeIntakePivotPiston;
	
	public IntakePivotPiston() {
		CubeIntakePivotPiston = new DoubleSolenoid(RobotMap.CUBE_INTAKE_PIVOT_PISTON_EXTEND, RobotMap.CUBE_INTAKE_PIVOT_PISTON_RETRACT);;
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
    }
    
	/**
	 * Retracts cube intake pivot piston
	 */
    public void retractPivotPiston() {
    	CubeIntakePivotPiston.set(DoubleSolenoid.Value.kReverse);
    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

