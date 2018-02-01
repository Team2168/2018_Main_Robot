package org.team2168;

<<<<<<< HEAD
=======
import org.team2168.subsystems.*;
import org.team2168.subsystems.drivetrain.*;
import org.team2168.commands.auto.*;
import org.team2168.commands.pneumatics.*;
import org.team2168.utils.Debouncer;
import org.team2168.utils.PowerDistribution;
import org.team2168.utils.TX1TurnON;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
>>>>>>> refs/remotes/origin/Lift_DM
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.team2168.commands.ExampleCommand;
import org.team2168.subsystems.drivetrain.Drivetrain;

<<<<<<< HEAD
=======
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
>>>>>>> refs/remotes/origin/Lift_DM

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

<<<<<<< HEAD
<<<<<<< HEAD
=======
	//Subsystems instance variables
	//  If you need to access a subsystem from anywhere in the code,
	//  it's done through one of these variables.
	public static Drivetrain drivetrain;   
=======
	// Subsystems instance variables
	// If you need to access a subsystem from anywhere in the code,
	// it's done through one of these variables.
	public static Drivetrain drivetrain;
>>>>>>> refs/remotes/origin/Lift_DM
	public static Pneumatics pneumatics;
<<<<<<< HEAD
<<<<<<< HEAD
	public static CubeIntake cubeIntake;
>>>>>>> refs/remotes/origin/_CubeIntake_AS
=======
	public static ForkLift forkLift;
	public static ScissorLift scissorLift;
>>>>>>> refs/remotes/origin/_Lifts_AS

public class Robot extends IterativeRobot
{
	
=======
	public static Lift lift;
	public static LiftShifter liftShifter;
	public static PowerDistribution pdp;
	public static Compressor comp;
	public static TX1TurnON tx1;
	public static DrivetrainIMUGlobalPosition dtIMU;

	// Variables for initializing and calibrating the Gyro
	private static boolean matchStarted = false;
	public static int gyroReinits;
	private double lastAngle;
	private Debouncer gyroDriftDetector = new Debouncer(1.0);
	public static boolean gyroCalibrating = false;
	private boolean lastGyroCalibrating = false;
	private double curAngle = 0.0;

>>>>>>> refs/remotes/origin/Lift_DM
	public static OI oi;
<<<<<<< HEAD
	public static Drivetrain drivetrain;
	
	Command autonomousCommand;
	
	
	

	 //This command is run when the robot is first started up and should be
	 //used for any initialization code

=======

	public static DriverStation driverstation;
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private static boolean autoMode;
	static Command autonomousCommand;
	public static SendableChooser<Command> autoChooser;

	private static int controlStyle;
	public static SendableChooser<Number> controlStyleChooser;

	private static boolean blueAlliance = false;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
>>>>>>> refs/remotes/origin/Lift_DM
	public void robotInit() {
<<<<<<< HEAD
		
		oi = OI.getInstance();
		drivetrain = Drivetrain.getInstance();
		// instantiate the command used for the autonomous period
		autonomousCommand = new ExampleCommand();
		
=======
		this.setPeriod(RobotMap.MAIN_PERIOD_S);

		ConsolePrinter.init();
		ConsolePrinter.setRate(RobotMap.CONSOLE_PRINTER_LOG_RATE_MS);

		practiceBot = new DigitalInput(RobotMap.PRACTICE_BOT_JUMPER);

		// Instantiate the subsystems
		drivetrain = Drivetrain.getInstance();
		pneumatics = Pneumatics.getInstance();

		// drivetrainShifter = DrivetrainShifter.getInstance();
		// flashlight = Flashlight.getInstance();
		// gearIntakeArm = GearIntakeArm.getInstance();
		// gearIntakeRoller = GearIntakeRoller.getInstance();
		// shooterHood = ShooterHood.getInstance();
		// shooterIndexer = ShooterIndexer.getInstance();
		// shooterWheel = ShooterWheel.getInstance();
		// turret = Turret.getInstance();

		oi = OI.getInstance();

		// run compressor
		new StartCompressor();

		autoSelectInit();
		controlStyleSelectInit();

		pdp = new PowerDistribution(RobotMap.PDPThreadPeriod);
		pdp.startThread();

		tx1 = new TX1TurnON(RobotMap.PDPThreadPeriod);
		tx1.startThread();

		dtIMU = new DrivetrainIMUGlobalPosition(RobotMap.PDPThreadPeriod);
		dtIMU.startThread();

		drivetrain.calibrateGyro();
		driverstation = DriverStation.getInstance();

		SmartDashboard.putData("Autonomous Mode Chooser", Robot.autoChooser);
		SmartDashboard.putData("Control Style Chooser", Robot.controlStyleChooser);
		// ConsolePrinter.putData("Autonomous Mode Chooser", () -> {return
		// Robot.autoChooser;}, true, false);
		ConsolePrinter.putString("AutoName", () -> {
			return Robot.getAutoName();
		}, true, false);
		ConsolePrinter.putString("Control Style Name", () -> {
			return Robot.getControlStyleName();
		}, true, false);
		// ConsolePrinter.putBoolean("isPracticeBot", Robot.isPracticeRobot());
		ConsolePrinter.putNumber("gameClock", () -> {
			return driverstation.getMatchTime();
		}, true, false);
		ConsolePrinter.putNumber("Robot Pressure", () -> {
			return Robot.pneumatics.getPSI();
		}, true, false);

		ConsolePrinter.putBoolean("Is Practice Bot", () -> {
			return isPracticeRobot();
		}, true, false);

		ConsolePrinter.startThread();
		System.out.println("Robot Done Loading");

		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
>>>>>>> refs/remotes/origin/Lift_DM
	}
<<<<<<< HEAD
	
	
	
=======

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// m_autoSelected = SmartDashboard.getString("Auto Selector",
		// kDefaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
		case kCustomAuto:
			// Put custom auto code here
			break;
		case kDefaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This method is called once each time the robot enters Disabled mode. You can
	 * use it to reset any subsystem information you want to clear when the robot is
	 * disabled.
	 */
	public void disabledInit() {
		autoMode = false;
		matchStarted = false;
	}

>>>>>>> refs/remotes/origin/Lift_DM
	public void disabledPeriodic() {
<<<<<<< HEAD
		
=======

		SmartDashboard.putNumber("GunStyleYValueMakingThisLongSoWeCanFindIt",
				Robot.oi.driverJoystick.getLeftStickRaw_Y());
		SmartDashboard.putNumber("GunStyleXValueMakingThisLongSoWeCanFindIt",
				Robot.oi.driverJoystick.getLeftStickRaw_X());
		SmartDashboard.putNumber("GunStyleXInterpolatedValueMakingThisLongSoWeCanFindIt",
				Robot.drivetrain.getGunStyleXValue());

		getControlStyleInt();
		controlStyle = (int) controlStyleChooser.getSelected();

		autonomousCommand = (Command) autoChooser.getSelected();

		// Kill all active commands
>>>>>>> refs/remotes/origin/Lift_DM
		Scheduler.getInstance().run();
<<<<<<< HEAD
		
	}
	
	
	
	public void autonomousInit() {
		
		// Schedule the autonomous command
		if (autonomousCommand != null) autonomousCommand.start();
		
=======

		autoMode = false;

		// Check to see if the gyro is drifting, if it is re-initialize it.
		gyroReinit();
	}

	/**
	 * Get the name of an autonomous mode command.
	 * 
	 * @return the name of the auto command.
	 */
	public static String getAutoName() {
		if (autonomousCommand != null) {
			return autonomousCommand.getName();
		} else {
			return "None";
		}
	}

	/**
	 * Get the name of an control style.
	 * 
	 * @return the name of the control style.
	 */
	public static String getControlStyleName() {
		String retVal = "";

		switch (controlStyle) {
		case 0:
			retVal = "Tank Drive";
			break;
		case 1:
			retVal = "Gun Style";
			break;
		case 2:
			retVal = "Arcade Drive";
			break;
		case 3:
			retVal = "GTA Drive";
			break;
		default:
			retVal = "Invalid Control Style";
		}

		return retVal;
	}

	/**
	 * Adds control styles to the selector
	 */
	public void controlStyleSelectInit() {
		controlStyleChooser = new SendableChooser<>();
		controlStyleChooser.addObject("Tank Drive", 0);
		controlStyleChooser.addDefault("Gun Style Controller", 1);
		controlStyleChooser.addObject("Arcade Drive", 2);
		controlStyleChooser.addObject("GTA Drive", 3);
	}

	public static int getControlStyleInt() {
		return (int) controlStyleChooser.getSelected();
	}

	/**
	 * Adds the autos to the selector
	 */
	public void autoSelectInit() {
		autoChooser = new SendableChooser<Command>();
		autoChooser.addObject("Do Nothing", new DoNothing());
		// autoChooser.addObject("Do Something", new DoSomething());
	}

	/**
	 *
	 * @return true if the robot is in auto mode
	 */
	public static boolean isAutoMode() {
		return autoMode;
>>>>>>> refs/remotes/origin/Lift_DM
	}
<<<<<<< HEAD
	
	
	
	// This function is called periodically during autonomous
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
		
=======

	/**
	 * Method which checks to see if gyro drifts and resets the gyro. Call this in a
	 * loop.
	 */
	private void gyroReinit() {
		// Check to see if the gyro is drifting, if it is re-initialize it.
		// Thanks FRC254 for orig. idea.
		curAngle = drivetrain.getHeading();
		gyroCalibrating = drivetrain.isGyroCalibrating();

		if (lastGyroCalibrating && !gyroCalibrating) {
			// if we've just finished calibrating the gyro, reset
			gyroDriftDetector.reset();
			curAngle = drivetrain.getHeading();
			System.out.println("Finished auto-reinit gyro");
		} else if (gyroDriftDetector.update(Math.abs(curAngle - lastAngle) > (0.75 / 50.0)) && !matchStarted
				&& !gyroCalibrating) {
			// && gyroReinits < 3) {
			gyroReinits++;
			System.out.println("!!! Sensed drift, about to auto-reinit gyro (" + gyroReinits + ")");
			drivetrain.calibrateGyro();
		}

		lastAngle = curAngle;
		lastGyroCalibrating = gyroCalibrating;
>>>>>>> refs/remotes/origin/Lift_DM
	}
<<<<<<< HEAD
	
	
	
	public void teleopInit() {
		
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
=======

	/**
	 * Returns the status of DIO pin 24
	 *
	 * @return true if this is the practice robot
	 */
	public static boolean isPracticeRobot() {
		return !practiceBot.get();
>>>>>>> refs/remotes/origin/Lift_DM
	}
<<<<<<< HEAD
	
	
	
=======

>>>>>>> refs/remotes/origin/Lift_DM
	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
<<<<<<< HEAD
	public void disabledInit() {
		
=======
	public static boolean onBlueAlliance() {
		return driverstation.getAlliance() == DriverStation.Alliance.Blue;
>>>>>>> refs/remotes/origin/Lift_DM
	}
<<<<<<< HEAD
	
	
	
	// This function is called periodically during operator control
=======

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
>>>>>>> refs/remotes/origin/Lift_DM
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		
	}
	
	
	
	// This function is called periodically during test mode
	public void testPeriodic() {
		
		LiveWindow.run();
		
	}
	
}