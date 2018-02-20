package org.team2168;

import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.ShiftLow;
import org.team2168.commands.guidingArm.CloseDownGuidingArm;
import org.team2168.commands.guidingArm.OpenGuidingArm;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.DriveLiftWithJoysticks;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.LiftShiftHigh;
import org.team2168.commands.lift.LiftShiftLow;
import org.team2168.commands.liftRatchetShifter.DisableRachet;
import org.team2168.commands.liftRatchetShifter.EnableRachet;
import org.team2168.utils.F310;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	private static OI instance = null;

	public static F310 driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
	public static F310 operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);

	public static F310 driverOperatorEJoystick = new F310(RobotMap.DRIVER_OPERATOR_E_BACKUP);

	public static F310 testJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);
	public static F310 pidTestJoystick = new F310(RobotMap.PID_TEST_JOYSTICK);

	/**
	 * Private constructor for singleton class which instantiates the OI object
	 */
	private OI() {

		/*************************************************************************
		 *                         Driver Joystick			                       *
		 *************************************************************************/
		driverJoystick.ButtonStart().whenPressed(new ShiftLow());  //add drivetrainshifter
		driverJoystick.ButtonA().whenPressed(new ShiftHigh());

		////////////// Operator Joystick//////////////
		
		
		/*************************************************************************
		 *                         Operator Joystick         		              *
		 *************************************************************************/
		
		//Start will be climb
		//operatorJoystick.ButtonStart().whenPressed(new CloseDownGuidingArm());
		//operatorJoystick.ButtonStart().whenPressed(new LiftShiftLow());
		//operatorJoystick.ButtonStart().whenPressed(new EnableRachet());
		
		////////////////Lower Platform///////////////////////////////////////
		//operatorJoystick.ButtonBack().whenPressed(new LowerPlatform());
		
		
		////////////////Intake Cube and Rotate up when done////////////////////////////////////////////////////
		operatorJoystick.ButtonLeftTrigger().whileHeld(new IntakeUntilCube());
		operatorJoystick.ButtonLeftTrigger().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
		operatorJoystick.ButtonLeftTrigger().whenPressed(new OpenIntake());
		operatorJoystick.ButtonLeftTrigger().whenReleased(new CloseIntake());
		operatorJoystick.ButtonLeftTrigger().whileHeld(new RotatePivotDownAutomatically(-RobotMap.CUBE_PIVOT_DOWN_CONSTANT));
		
		
		operatorJoystick.ButtonRightBumper().whenPressed(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE));
		operatorJoystick.ButtonRightBumper().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
		//operatorJoystick.ButtonRightTrigger().whenReleased(new DrivePivotBackWithConstant(0.1));
		
		////////////////Spit Cube With wheels////////////////////////////////////////////////////
		operatorJoystick.ButtonRightTrigger().whileHeld(new IntakeUntilCube());
		operatorJoystick.ButtonRightTrigger().whileHeld(new RotatePivotDownAutomatically(-RobotMap.CUBE_PIVOT_DOWN_CONSTANT));
		//operatorJoystick.ButtonRightTrigger().whenPressed(new OpenIntake());
		
		//operatorJoystick.ButtonRightTrigger().whenReleased(new CloseIntake());
		operatorJoystick.ButtonRightTrigger().whenReleased(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT_NO_CUBE));
		operatorJoystick.ButtonRightTrigger().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
		
		
		////////////////Emergency Open/Close Intake///////////////////////
		operatorJoystick.ButtonLeftBumper().whenPressed(new OpenIntake());
		operatorJoystick.ButtonLeftBumper().whenReleased(new CloseIntake());
		
		/////////////////Emergency Raise Intake////////////////////////////////////////
		operatorJoystick.ButtonDownDPad().whileHeld(new RotatePivotDownAutomatically(-RobotMap.CUBE_PIVOT_DOWN_CONSTANT));
		operatorJoystick.ButtonDownDPad().whenReleased(new RotatePivotUpAutomatically(0.0));
		
		/////////////////Emergency Lower Intake/////////////////////////////////////////
		operatorJoystick.ButtonUpDPad().whileHeld(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT_NO_CUBE));
		operatorJoystick.ButtonUpDPad().whenReleased(new RotatePivotUpAutomatically(0.0));
		
		////////////////Raise platform/////////////////////////////
		//operatorJoystick.ButtonA().whenPressed(new RaisePlatform());
		
		//////////////// Open arm and shift high and disengage rachet ///
		//operatorJoystick.ButtonB().whenPressed(new OpenGuidingArm());
		//operatorJoystick.ButtonB().whenPressed(new LiftShiftHigh());
		//operatorJoystick.ButtonB().whenPressed(new DisableRachet());
		
		////////////////For testing purposes//////////////////////
		operatorJoystick.ButtonY().whenPressed(new LiftShiftHigh());
		operatorJoystick.ButtonX().whenPressed(new LiftShiftLow());
		operatorJoystick.ButtonA().whenPressed(new EnableRachet());
		operatorJoystick.ButtonB().whenPressed(new DisableRachet());
		operatorJoystick.ButtonLeftDPad().whenPressed(new EnableBrake());
		operatorJoystick.ButtonRightDPad().whenPressed(new DisableBrake());
		//ConsolePrinter.putNumber("Lift speed value", () -> {return ();}, true, false);
		
	}

	/**
	 * Returns an instance of the Operator Interface.
	 * 
	 * @return is the current OI object
	 */
	public static OI getInstance() {
		if (instance == null)
			instance = new OI();

		return instance;
	}

	/**
	 * Method that sets that Left side of the drive train so that it drives with
	 * LeftStick Y
	 * 
	 * @author Krystina
	 */
	public static double getDriveTrainLeftJoystick() {
		return driverJoystick.getLeftStickRaw_Y();
	}

	/**
	 * Method that sets that Right side of the drive train so that it drives with
	 * RightStick Y
	 * 
	 * @author Krystina
	 */
	public static double getDriveTrainRightJoystick() {
		return driverJoystick.getRightStickRaw_Y();
	}

	/**
	 * Method that sets that Left side of the drive train so that it drives with
	 * Operator RightStick Y
	 * 
	 * @author Krystina
	 */
	public static double getDriveLiftJoystickValue() {
		return operatorJoystick.getLeftStickRaw_Y();
	}

	public static double getDriveIntakeWheelsJoystickValue() {
		return operatorJoystick.getRightStickRaw_Y();
	}

	public static double getDriveIntakePivotJoystickValue() {
		return testJoystick.getRightStickRaw_Y();
	}

}
