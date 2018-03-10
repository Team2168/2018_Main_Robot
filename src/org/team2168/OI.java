package org.team2168;

import org.team2168.commands.auto.DriveToLeftSwitch;
import org.team2168.commands.auto.DriveToRightSwitch;
import org.team2168.commands.auto.massComp.DriveToLeftScaleAndLeftSwitchFromLeftSide;
import org.team2168.commands.auto.massComp.DriveToRightScaleAndRightSwitchFromLeftSide;
import org.team2168.commands.drivetrain.DriveWithJoystick;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.ShiftLow;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZNoBattery;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPause;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.EnableRotatePID;
import org.team2168.commands.drivetrain.PIDCommands.EnableRotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotatePIDPath;
import org.team2168.commands.guidingArm.CloseDownGuidingArm;
import org.team2168.commands.guidingArm.OpenGuidingArm;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intakePivotPiston.ExtendPivotPiston;
import org.team2168.commands.intakePivotPiston.RetractPivotPiston;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RotatePivotDownAndSpit;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.DriveLiftWithJoysticks;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.LiftShiftHigh;
import org.team2168.commands.lift.LiftShiftLow;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;
import org.team2168.commands.lift.PIDCommands.DriveLiftPathPIDZZZ;
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
		operatorJoystick.ButtonStart().whenPressed(new LiftShiftLow());
		//operatorJoystick.ButtonStart().whenPressed(new EnableRachet());
		
		////////////////Lower Platform///////////////////////////////////////
		//operatorJoystick.ButtonBack().whenPressed(new LowerPlatform());
		
		operatorJoystick.ButtonRightDPad().whenPressed(new ExtendPivotPiston());
		operatorJoystick.ButtonLeftDPad().whenPressed(new RetractPivotPiston());
		
		////////////////Intake Cube and lift to exchange////////////////////////////////////////////////////
		//operatorJoystick.ButtonRightTrigger().whileHeld(new RotatePivotDownAutomatically(-RobotMap.CUBE_PIVOT_DOWN_CONSTANT));
		operatorJoystick.ButtonRightTrigger().whenPressed(new ExtendPivotPiston());
		//operatorJoystick.ButtonRightTrigger().whileHeld(new IntakeUntilCube());]
		operatorJoystick.ButtonRightTrigger().whileHeld(new DriveIntakeWheelsWithConstant(-1.0));
		operatorJoystick.ButtonRightTrigger().whenPressed(new CloseIntake()); //open on comp bot
		//operatorJoystick.ButtonRightTrigger().whenReleased(new OperationKeepCube());
		operatorJoystick.ButtonRightTrigger().whenReleased(new CloseIntake());
		//operatorJoystick.ButtonRightTrigger().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
		//operatorJoystick.ButtonLeftTrigger().whenReleased(new DriveLiftPIDZZZ(10.0, 0.5, 0.16,1.0,true));
		
		///////////////Intake and pivot up afterwards/////////////////////////////////////////////////////////////////////////
//		operatorJoystick.ButtonLeftTrigger().whileHeld(new IntakeUntilCube());
//		operatorJoystick.ButtonLeftTrigger().whileHeld(new RotatePivotDownAutomatically(-RobotMap.CUBE_PIVOT_DOWN_CONSTANT));
//	    operatorJoystick.ButtonLeftTrigger().whenPressed(new CloseIntake()); //open for comp bot
//		operatorJoystick.ButtonLeftTrigger().whenReleased(new CloseIntake());
//		operatorJoystick.ButtonLeftTrigger().whenReleased(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT_NO_CUBE));
//		operatorJoystick.ButtonLeftTrigger().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
		
		
		
		////////////////Pivot down & spit a cube  ///////////////////////
//		operatorJoystick.ButtonRightBumper().whileHeld(new RotatePivotDownAndSpit());
		operatorJoystick.ButtonRightBumper().whenPressed(new ExtendPivotPiston());
		operatorJoystick.ButtonRightBumper().whenPressed(new CloseIntake()); //open on comp bot
		operatorJoystick.ButtonRightBumper().whileHeld(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE));
//		operatorJoystick.ButtonRightBumper().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
//		operatorJoystick.ButtonRightBumper().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
		
		////////////////Low speed spit //////////////////////////////////////////////////////////////////////////////////////////
		operatorJoystick.ButtonLeftBumper().whenPressed(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE*0.5));
		operatorJoystick.ButtonLeftBumper().whenReleased(new DriveIntakeWheelsWithConstant(0.0));
		
		/////////////////Emergency Raise Intake////////////////////////////////////////
		operatorJoystick.ButtonDownDPad().whileHeld(new ExtendPivotPiston());
		operatorJoystick.ButtonDownDPad().whenReleased(new RotatePivotUpAutomatically(0.0));
		
		/////////////////Emergency Lower Intake/////////////////////////////////////////
		operatorJoystick.ButtonUpDPad().whileHeld(new ExtendPivotPiston());
		operatorJoystick.ButtonUpDPad().whenReleased(new RotatePivotUpAutomatically(0.0));
		
		operatorJoystick.ButtonStart().whenPressed(new LiftShiftHigh());
		operatorJoystick.ButtonBack().whenPressed(new LiftShiftLow());
		
		////////////////Prepare to climb/////////////////////////////
		//operatorJoystick.ButtonBack().whenPressed(new EnableRachet());
		//operatorJoystick.ButtonBack().whenPressed(new LiftShiftLow());
		
		////////////////Lift Pid commands////////////////////////////////////////////////////
		operatorJoystick.ButtonY().whenPressed(new DriveLiftPIDZZZ(80.0, 0.5, 0.16,1.0,true));
		operatorJoystick.ButtonA().whenPressed(new DriveLiftPIDZZZ(7.0, 0.5, 0.16,1.0,true));
		operatorJoystick.ButtonX().whenPressed(new DriveLiftPIDZZZ(1.5, 0.7, 0.16,0.5,true));
		operatorJoystick.ButtonB().whenPressed(new DriveLiftPIDZZZ(65.0, 0.5, 0.16,1.0,true));
		////////////////Raise platform/////////////////////////////
		
		//operatorJoystick.ButtonA().whenPressed(new RaisePlatform());
		
		//////////////// Open arm and shift high and disengage rachet ///
		//operatorJoystick.ButtonB().whenPressed(new OpenGuidingArm());
		//operatorJoystick.ButtonB().whenPressed(new LiftShiftHigh());
		//operatorJoystick.ButtonB().whenPressed(new DisableRachet());
		
		////////////////For testing purposes//////////////////////
		//testJoystick.ButtonY().whenPressed(new LiftShiftHigh());
		//testJoystick.ButtonX().whenPressed(new LiftShiftLow());
		//testJoystick.ButtonA().whenPressed(new EnableRachet());
		//testJoystick.ButtonB().whenPressed(new DisableRachet());
		//testJoystick.ButtonLeftDPad().whenPressed(new EnableBrake());
		//testJoystick.ButtonRightDPad().whenPressed(new DisableBrake());

//		testJoystick.ButtonA().whenPressed(new RotateXDistancePIDZZZ(45,0.5,0.2));
//		testJoystick.ButtonB().whenPressed(new RotateXDistancePIDZZZ(-45,0.5,0.2));
//		testJoystick.ButtonX().whenPressed(new RotateXDistancePIDZZZ(90,0.5,0.2));
//		
//		testJoystick.ButtonY().whenPressed(new RotateXDistancePIDZZZ(0,0.5,0.2));
//		testJoystick.ButtonUpDPad().whenPressed(new EnableRotateXDistancePIDZZZ(0));
//		testJoystick.ButtonDownDPad().whenPressed(new DrivePIDPause());
//
		//testJoystick.ButtonRightTrigger().whenPressed(new DrivePIDPathQuintic(Robot.leftVelPathQuintic, Robot.rightVelPathQuintic));
//		testJoystick.ButtonRightBumper().whenPressed(new DriveIntakeWheelsWithConstant(-1));
//		
//		testJoystick.ButtonDownDPad().whenPressed(new RotatePivotUpAutomatically(-RobotMap.CUBE_PIVOT_DOWN_CONSTANT));
//		testJoystick.ButtonUpDPad().whenPressed(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_DOWN_CONSTANT));
//		
//		testJoystick.ButtonLeftBumper().whenPressed(new OpenIntake());
//		testJoystick.ButtonLeftTrigger().whenPressed(new OpenIntake());
//		///////////////PID testing//////////////////////////////////////////////////////

		
		//pidTestJoystick.ButtonB().whenPressed(new RotateXDistancePIDZZZ(-45,0.5,0.2));
		//pidTestJoystick.ButtonX().whenPressed(new DriveToRightScaleFromLeftSide());
		//pidTestJoystick.ButtonY().whenPressed(new DriveToLeftScaleFromLeftSide());
		pidTestJoystick.ButtonA().whenPressed(new DriveToLeftSwitch());
		pidTestJoystick.ButtonB().whenPressed(new DriveToRightSwitch());
		
		
		
		pidTestJoystick.ButtonY().whenPressed(new DriveLiftPIDZZZ(33.0, 0.5, 0.16,1.0,true));
		pidTestJoystick.ButtonX().whenPressed((new DriveLiftPIDZZZ(10.0, 0.5, 0.16,1.0,true)));
		
		
		pidTestJoystick.ButtonUpDPad().whenPressed(new DriveLiftPIDZZZ(2.0, 0.5, 0.16,1.0,true));
		
		
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
		return operatorJoystick.getLeftStickRaw_Y() + pidTestJoystick.getLeftStickRaw_Y();
	}

	public static double getDriveIntakeWheelsJoystickValue() {
		return operatorJoystick.getRightStickRaw_Y();
	}

	public static double getDriveIntakePivotJoystickValue() {
		return testJoystick.getRightStickRaw_Y();
	}

	public static double getGunStyleXValue() {
		// return
		// gunStyleInterpolator.interpolate(Robot.oi.driverJoystick.getLeftStickRaw_X());
		return -Robot.oi.driverJoystick.getLeftStickRaw_X();
	}

	public static double getGunStyleYValue() {
		// return
		// gunStyleInterpolator.interpolate(Robot.oi.driverJoystick.getLeftStickRaw_X());
		return Robot.oi.driverJoystick.getLeftStickRaw_Y();
	}
	
}
