package org.team2168.subsystems;

import org.team2168.RobotMap;

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
	private static Spark intakePivotMotor;
	private static AnalogInput intakeIRSensor;
	private static DigitalInput fullyExtend;
	private static DigitalInput fullyRetract;
	
	/**
	 * Default constructors
	 */
	public CubeIntakePivot(){
		
		intakeIRSensor = new AnalogInput(RobotMap.CUBE_INTAKE_IR_SENSOR1);
		fullyExtend = new DigitalInput(RobotMap.CUBE_INTAKE_EXTEND_LIMIT);
		fullyRetract = new DigitalInput(RobotMap.CUBE_INTAKE_RETRACT_LIMIT);
		intakePivotMotor = new Spark(RobotMap.CUBE_INTAKE_PIVOT_MOTOR);
			
	}
	
		public static CubeIntakePivot getInstance(){
		if (instance == null)
			instance = new CubeIntakePivot();
		return instance;
	}

	/**
	 * Checks to see if arm is fully up
	 * @return true if pressed, false if not
	 */
	public boolean isIntakeFullyExtend() {
		return !fullyExtend.get();
	}
	
	/**
	 * Checks to see if arm is fully down
	 * @return true if pressed, false if not
	 */
	public boolean isIntakeFullyRetract() {
		return !fullyRetract.get();
	}
	
	/**
	 * Calls instance object and makes it singleton object of class fork lift
	 * @return 
	 * @return singleton object instance
	 */
	public void drivePivot(double speed)
	{
		if (RobotMap.INTAKE_PIVOT_REVERSE)
			speed = -speed;
		if ((speed > 0.2 && isIntakeFullyRetract()) || ((speed < 0.2) && isIntakeFullyExtend())) {
			intakePivotMotor.set(speed);
		} else {
			intakePivotMotor.set(0);

		}
}

	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

