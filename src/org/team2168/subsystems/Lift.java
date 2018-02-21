package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.controllers.PIDPosition;
import org.team2168.PID.sensors.AveragePotentiometer;
import org.team2168.commands.lift.DriveLiftWithJoysticks;
import org.team2168.utils.LinearInterpolator;
import org.team2168.utils.TCPSocketSender;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subystem class for the lift
 * 
 * @author DM
 */
public class Lift extends Subsystem {
	private static Lift instance = null;
	private static VictorSP liftMotor1;
	private static VictorSP liftMotor2;
	private static VictorSP liftMotor3;
	private DoubleSolenoid liftBrake;
	private static AveragePotentiometer liftPot;
	private static DigitalInput liftFullyUp; //hall effect sensors
	private static DigitalInput liftFullyDown; // ^^^^^^^^^^^
	
    double liftPotMax;
    double liftPotMin;
    
	public PIDPosition liftPOTController;	
	TCPSocketSender TCPLiftPOTController;
	
	public volatile double liftMotor1Voltage;
	public volatile double liftMotor2Voltage;
	public volatile double liftMotor3Voltage;
	
    private static LinearInterpolator liftPotInterpolator;
    //TODO get these values plez format for points: (volts, inches)
    private double[][] liftPotRange = {{RobotMap.LIFT_POT_VOLTAGE_0,RobotMap.LIFT_POT_0_HEIGHT_INCHES},
    		                          {RobotMap.LIFT_POT_VOLTAGE_MAX,RobotMap.LIFT_POT_MAX_HEIGHT_INCHES}};

	/**
	 * Default constructor for the lift
	 */
	private Lift() {
		liftMotor1 = new VictorSP(RobotMap.LIFT_MOTOR_1);
		liftMotor2 = new VictorSP(RobotMap.LIFT_MOTOR_2);
		liftMotor3 = new VictorSP(RobotMap.LIFT_MOTOR_3);
		liftBrake = new DoubleSolenoid(RobotMap.PCM_CAN_ID_2,RobotMap.LIFT_BRAKE_ENGAGE_PCM, RobotMap.LIFT_BRAKE_DISENGAGE_PCM);
		liftFullyUp = new DigitalInput(RobotMap.LIFT_FULLY_UP_LIMIT);
		liftFullyDown = new DigitalInput(RobotMap.LIFT_FULLY_DOWN_LIMIT);
		
		if(Robot.isPracticeRobot()){
			liftPot = new AveragePotentiometer(RobotMap.LIFT_POSITION_POT_PBOT,
					 RobotMap.LIFT_POT_VOLTAGE_0_PBOT, RobotMap.LIFT_POT_0_HEIGHT_INCHES_PBOT,
					 RobotMap.LIFT_POT_VOLTAGE_MAX_PBOT, RobotMap.LIFT_POT_MAX_HEIGHT_INCHES_PBOT,
					 RobotMap.LIFT_AVG_ENCODER_VAL);
			liftPotMax = RobotMap.LIFT_POT_VOLTAGE_MAX_PBOT;
			liftPotMin = RobotMap.LIFT_POT_VOLTAGE_0_PBOT;
		}
		else {
			liftPot = new AveragePotentiometer(RobotMap.LIFT_POSITION_POT,
					 RobotMap.LIFT_POT_VOLTAGE_0, RobotMap.LIFT_POT_0_HEIGHT_INCHES,
					 RobotMap.LIFT_POT_VOLTAGE_MAX, RobotMap.LIFT_POT_MAX_HEIGHT_INCHES,
					 RobotMap.LIFT_AVG_ENCODER_VAL);
			liftPotMax = RobotMap.LIFT_POT_VOLTAGE_MAX;
			liftPotMin = RobotMap.LIFT_POT_VOLTAGE_0;

		}
    		
		liftPOTController = new PIDPosition(
				"LiftPosController",
				RobotMap.LIFT_P,
				RobotMap.LIFT_I,
				RobotMap.LIFT_D,
				RobotMap.LIFT_N,
				liftPot,
				RobotMap.LIFT_PID_PERIOD);
    	
		liftPOTController.setSIZE(RobotMap.LIFT_PID_ARRAY_SIZE);

		liftPOTController.startThread();
		
		TCPLiftPOTController = new TCPSocketSender(RobotMap.TCP_SERVER_LIFT_POT_CONTROLLER, liftPOTController);
		TCPLiftPOTController.start();
		
		ConsolePrinter.putBoolean("Is Lift Fully Up", () -> {return Robot.lift.isLiftFullyUp();}, true, false);
		ConsolePrinter.putBoolean("Is Lift Fully Down", () -> {return Robot.lift.isLiftFullyDown();}, true, false);
		ConsolePrinter.putNumber("Lift Raw Pot", () -> {return getRawPot();}, true, false);
		ConsolePrinter.putNumber("Lift Pot Inches", () -> {return getPotPos();}, true, false);

		ConsolePrinter.putNumber("Lift motor 1 voltage", () -> {return liftMotor1Voltage;}, true, false);
		ConsolePrinter.putNumber("Lift motor 2 voltage", () -> {return liftMotor2Voltage;}, true, false);
		ConsolePrinter.putNumber("Lift motor 3 voltage", () -> {return liftMotor3Voltage;}, true, false);

		ConsolePrinter.putNumber("Lift Motor 1 Current ", () -> {return Robot.pdp.getChannelCurrent(RobotMap.LIFT_MOTOR_1_PDP);}, true, true);
		ConsolePrinter.putNumber("Lift Motor 2 Current ", () -> {return Robot.pdp.getChannelCurrent(RobotMap.LIFT_MOTOR_2_PDP);}, true, true);
		ConsolePrinter.putNumber("Lift Motor 3 Current ", () -> {return Robot.pdp.getChannelCurrent(RobotMap.LIFT_MOTOR_3_PDP);}, true, true);
		
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

	/**
	 * 
	 * @return pot position in volts
	 */
	public double getRawPot() {
		return liftPot.getRawPos();
	}
	
	/**
	 * 
	 * @return pot position in inches
	 */
	public double getPotPos() {
		return liftPot.getPos();
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
	 * Drives all lift Motors at a speed from -1 to 1 where 1 is up and
	 * negative 1 is down, and ratchet is not engaged
	 * 
	 * @param speed is +1 up and -1 down
	 */
	public void driveAllMotors(double speed) {
		if ((speed > RobotMap.LIFT_MIN_SPEED && !isLiftFullyUp() && Robot.liftRatchetShifter.isRatchetDisEngaged()) ||
				((speed < -RobotMap.LIFT_MIN_SPEED) && !isLiftFullyDown() && Robot.liftRatchetShifter.isRatchetDisEngaged()))
		{
			disableBrake();
			driveLiftMotor1(speed);
			driveLiftMotor2(speed);
			driveLiftMotor3(speed); 
		}
		else 
		{
			enableBrake();
			driveLiftMotor1(0.0);
			driveLiftMotor2(0.0);
			driveLiftMotor3(0.0);
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
		setDefaultCommand(new DriveLiftWithJoysticks());
	}
}
