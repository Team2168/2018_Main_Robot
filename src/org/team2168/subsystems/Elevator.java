package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.elevator.DriveElevatorWithJoysticks;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subystem class for the elevator
 * @author DM
 */
public class Elevator extends Subsystem {
	private static Elevator instance = null;
	private static Victor elevatorMotor1;
	private static Victor elevatorMotor2;
	private static Victor elevatorMotor3;
	private DoubleSolenoid elevatorBrake;
	private static AnalogInput potentiometer;
	
	public volatile double elevatorMotor1Voltage;
	public volatile double elevatorMotor2Voltage;
	public volatile double elevatorMotor3Voltage;
	/**
	 * Default constructor for the elevator
	 */
	private Elevator() {
		elevatorMotor1 = new Victor(RobotMap.ELEVATOR_MOTOR_1);
		elevatorMotor2 = new Victor(RobotMap.ELEVATOR_MOTOR_2);
		elevatorMotor3 = new Victor(RobotMap.ELEVATOR_MOTOR_3);
		elevatorBrake = new DoubleSolenoid(RobotMap.ELEVATOR_BRAKE_FORWARD, RobotMap.ELEVATOR_BRAKE_REVERSE);	
		potentiometer = new AnalogInput(RobotMap.ELEVATOR_POSITION_POT);
	}
/**
 *  Singleton constructor of the elevator
 *  
 */
	
	public static Elevator GetInstance() {
		if (instance ==null)
			instance = new Elevator();
		return instance;
}
	public double getRawPot() {
		return potentiometer.getVoltage();
	}	
	
	/**
	 * Drives the first Elevator motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void driveElevatorMotor1(double speed) {
		if(RobotMap.ELEVATOR_MOTOR1_REVERSE)
			speed = -speed;
		elevatorMotor1.set(speed);
		elevatorMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
}
	/**
	 * Drives the second Elevator motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void driveElevatorMotor2(double speed) {
		if(RobotMap.ELEVATOR_MOTOR2_REVERSE)
			speed = -speed;
		elevatorMotor2.set(speed);
		elevatorMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
}
	/**
	 * Drives the third Elevator motor at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	private void driveElevatorMotor3(double speed) {
		if(RobotMap.ELEVATOR_MOTOR3_REVERSE)
			speed = -speed;
		elevatorMotor3.set(speed);
		elevatorMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;
}	
	/**
	 * Drives all elevator Motors at a speed from -1 to 1 where 1 is forward and negative 1 is backwards
	 * @param speed
	 */
	public void driveAllMotors(double speed) {
		if((speed > 0)||(speed < 0)){
			
			enableBrake();
			driveElevatorMotor1(0);
			driveElevatorMotor2(0);
			driveElevatorMotor3(0);
		}
		else if (Math.abs(speed) > 0.2)
		{	
			disableBrake();
			driveElevatorMotor1(speed);
			driveElevatorMotor2(speed);
			driveElevatorMotor3(speed);
		}
	else
	{
		enableBrake();
		elevatorMotor1.set(0);
		elevatorMotor2.set(0);
		elevatorMotor3.set(0);
	}
}
	
	/**
	 * Enables the pneumatic brake
	 */
	public void enableBrake() {
		elevatorBrake.set(Value.kForward);
}
	
	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is enabled
	 */
	public boolean isBrakeEnabled() {
		return elevatorBrake.get() == Value.kForward;
	}

	/**
	 * Disables the pneumatic brake
	 */
	public void disableBrake() {
		elevatorBrake.set(Value.kReverse);
	}
	
	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is disabled
	 */
	public boolean isBrakeDisabled() {
		return elevatorBrake.get() == Value.kReverse;
}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveElevatorWithJoysticks(OI.getInstance().operatorJoystick));
    }
}

