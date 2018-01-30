package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.lift.DriveLiftWithJoysticks;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subystem class for the lift
 * 
 * @author DM
 */
public class Lift extends Subsystem {
	private static Lift instance = null;
	private static Victor liftMotor1;
	private static Victor liftMotor2;
	private static Victor liftMotor3;
	private DoubleSolenoid liftBrake;
	private static AnalogInput potentiometer;
	private static DigitalInput liftFullyUp;
	private static DigitalInput liftFullyDown;
	

	public volatile double liftMotor1Voltage;
	public volatile double liftMotor2Voltage;
	public volatile double liftMotor3Voltage;

	/**
	 * Default constructor for the lift
	 */
	private Lift() {
		liftMotor1 = new Victor(RobotMap.LIFT_MOTOR_1);
		liftMotor2 = new Victor(RobotMap.LIFT_MOTOR_2);
		liftMotor3 = new Victor(RobotMap.LIFT_MOTOR_3);
		liftBrake = new DoubleSolenoid(RobotMap.LIFT_BRAKE_APPLY, RobotMap.LIFT_BRAKE_RELEASE);
		potentiometer = new AnalogInput(RobotMap.LIFT_POSITION_POT);
		liftFullyUp = new DigitalInput(RobotMap.LIFT_FULLY_UP);
		liftFullyDown = new DigitalInput(RobotMap.LIFT_FULLY_DOWN);
	}

	/**
	 * Singleton constructor of the lift
	 * 
	 */

	public static Lift GetInstance() {
		if (instance == null)
			instance = new Lift();
		return instance;
	}

	public double getRawPot() {
		return potentiometer.getVoltage();
	}
	
	/**
	 * Checks to see if arm is fully up
	 * @return true if pressed, false if not
	 */
	public boolean isLiftFullyUp() {
		return !liftFullyUp.get();
	}
	
	/**
	 * Checks to see if arm is fully down
	 * @return true if pressed, false if not
	 */
	public boolean isLiftFullyDown() {
		return !liftFullyDown.get();
	}
	
	
	
	

	/**
	 * Drives the first Lift motor at a speed from -1 to 1 where 1 is forward
	 * and negative 1 is backwards
	 * 
	 * @param speed
	 */
	private void driveLiftMotor1(double speed) {
		if (RobotMap.LIFT_MOTOR1_REVERSE)
			speed = -speed;
		liftMotor1.set(speed);
		liftMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Drives the second Lift motor at a speed from -1 to 1 where 1 is forward
	 * and negative 1 is backwards
	 * 
	 * @param speed
	 */
	private void driveLiftMotor2(double speed) {
		if (RobotMap.LIFT_MOTOR2_REVERSE)
			speed = -speed;
		liftMotor2.set(speed);
		liftMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Drives the third Lift motor at a speed from -1 to 1 where 1 is forward
	 * and negative 1 is backwards
	 * 
	 * @param speed
	 */
	private void driveLiftMotor3(double speed) {
		if (RobotMap.LIFT_MOTOR3_REVERSE)
			speed = -speed;
		liftMotor3.set(speed);
		liftMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Drives all lift Motors at a speed from -1 to 1 where 1 is forward and
	 * negative 1 is backwards
	 * 
	 * @param speed
	 */
	public void driveAllMotors(double speed) {
		if ((speed > 0.2 && isLiftFullyDown()) || ((speed < 0.2) && isLiftFullyUp())) {
			disableBrake();
			driveLiftMotor1(speed);
			driveLiftMotor2(speed);
			driveLiftMotor3(speed);
		} else {
			enableBrake();
			driveLiftMotor1(0);
			driveLiftMotor2(0);
			driveLiftMotor3(0);
		}
	}

	/**
	 * Enables the pneumatic brake
	 */
	public void enableBrake() {
		liftBrake.set(Value.kForward);
	}

	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is enabled
	 */
	public boolean isBrakeEnabled() {
		return liftBrake.get() == Value.kForward;
	}

	/**
	 * Disables the pneumatic brake
	 */
	public void disableBrake() {
		liftBrake.set(Value.kReverse);
	}

	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is disabled
	 */
	public boolean isBrakeDisabled() {
		return liftBrake.get() == Value.kReverse;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveLiftWithJoysticks(OI.getInstance().operatorJoystick));
	}
}
