package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * subsystem file for cube intake pivot
 */
public class CubeIntakePivot extends Subsystem {

	private static CubeIntakePivot instance = null;
	private static VictorSP intakePivotMotor;
	
	private static DigitalInput fullyLowered;
	private static DigitalInput fullyRaised;
	
	/**
	 * Default constructors
	 */
	public CubeIntakePivot(){
		
		
		fullyLowered = new DigitalInput(RobotMap.CUBE_INTAKE_EXTEND_LIMIT);
		fullyRaised = new DigitalInput(RobotMap.CUBE_INTAKE_RETRACT_LIMIT);
		intakePivotMotor = new VictorSP(RobotMap.CUBE_INTAKE_PIVOT_MOTOR);
		
		ConsolePrinter.putBoolean("Is Intake Fully Up", () -> {return isRaised();}, true, false);
		ConsolePrinter.putBoolean("Is Lift Fully Down", () -> {return isLowered();}, true, false);
			
	}
	
		public static CubeIntakePivot getInstance(){
		if (instance == null)
			instance = new CubeIntakePivot();
		return instance;
	}

	/**
	 * Checks to see if arm down
	 * @return true if pressed, false if not
	 */
	public boolean isLowered() {
		return !fullyLowered.get();
	}
	
	/**
	 * Checks to see if arm is up
	 * @return true if pressed, false if not
	 */
	public boolean isRaised() {
		return !fullyRaised.get();
	}
	
	/**
	 * Rotates the pivot motor
	 * 1 is rotate 1 and -1 is rotate down
	 */
	public void drivePivot(double speed)
	{
		if (RobotMap.INTAKE_PIVOT_REVERSE)
			speed = -speed;
		if ((speed > RobotMap.CUBE_INTAKE_PIVOT_MIN_SPEED && !isRaised()))  {
			intakePivotMotor.set(speed);
		}
			else if ((speed < RobotMap.CUBE_INTAKE_PIVOT_MIN_SPEED) && isLowered())
			intakePivotMotor.set(speed);
		else {
			intakePivotMotor.set(0);
		}
	}

	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

