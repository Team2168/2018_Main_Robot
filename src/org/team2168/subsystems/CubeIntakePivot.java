package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.intake.DriveIntakePivotWithJoystick;
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
	public static volatile double intakeVoltage;
	
	private static DigitalInput fullyLowered;
	private static DigitalInput fullyRaised;
	public static volatile double pivotMotorVoltage;
	private int isPivotFullyUpInt;
	private int isPivotFullyDownInt;
	/**
	 * Default constructors
	 */
	public CubeIntakePivot(){

		fullyLowered = new DigitalInput(RobotMap.CUBE_INTAKE_ROTATE_UP_LIMIT);
		fullyRaised = new DigitalInput(RobotMap.CUBE_INTAKE_ROTATE_DOWN_LIMIT);
		intakePivotMotor = new VictorSP(RobotMap.CUBE_INTAKE_PIVOT_MOTOR);

		isPivotFullyUpInt = 0;
		isPivotFullyDownInt = 0;
		
		ConsolePrinter.putBoolean("Is Intake Pivot Fully Up", () -> {return isRaised();}, true, false);
		ConsolePrinter.putBoolean("Is Intake Pivot Fully Down", () -> {return isLowered();}, true, false);
		ConsolePrinter.putNumber("Pivot motor voltage", () -> {return getMotorVoltage();}, true, true);
		ConsolePrinter.putNumber("Is fully up int", () -> {return (double)getIsFullyRaisedInt();}, true, false);
		ConsolePrinter.putNumber("Is fully down int", () -> {return (double)getIsFullyLoweredInt();}, true, false);
		ConsolePrinter.putNumber("Pivot Motor current ", () -> {return Robot.pdp.getChannelCurrent(RobotMap.INTAKE_PIVOT_MOTOR_PDP);}, true, false);	

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
		if(!fullyLowered.get())
			this.isPivotFullyDownInt = 1;
		else 
			this.isPivotFullyDownInt = 0;
		return !fullyLowered.get();
		
	}
	
	/**
	 * Checks to see if arm is up
	 * @return true if pressed, false if not
	 */
	public boolean isRaised() {
		if(!fullyRaised.get())
			this.isPivotFullyUpInt = 1;
		else
			this.isPivotFullyUpInt = 0;
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
		
		
		if ((speed > RobotMap.CUBE_INTAKE_PIVOT_MIN_SPEED && !isRaised()) ||
			((speed < -RobotMap.CUBE_INTAKE_PIVOT_MIN_SPEED) && !isLowered()))
		{
			System.out.println(speed);
			intakePivotMotor.set(speed);
		}
		else 
		{
			intakePivotMotor.set(0.0);
		}

		pivotMotorVoltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	public int getIsFullyLoweredInt() {
		return this.isPivotFullyDownInt;
	}
	public int getIsFullyRaisedInt() {
		return this.isPivotFullyUpInt;
	}
	
	public double getMotorVoltage() {
		return  pivotMotorVoltage;      
  }

	public double getPivotVoltage() 
  {
		return intakeVoltage;
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveIntakePivotWithJoystick());
    }
}

