package org.team2168;

import org.team2168.subsystems.*;
import org.team2168.PID.trajectory.OneDimensionalMotionProfiling;
import org.team2168.PID.trajectory.QuinticTrajectory;
import org.team2168.commands.auto.*;
import org.team2168.commands.auto.massComp.DriveStraight;
import org.team2168.commands.auto.massComp.DriveToLeftSwitch;
import org.team2168.commands.auto.massComp.DriveToLeftSwitchAndRightScaleFromLeft;
import org.team2168.commands.auto.massComp.DriveToRightSwitch;
import org.team2168.commands.auto.selector.TestAutoCommandGroupA;
import org.team2168.commands.auto.selector.AutoStartCenter1Cube;
import org.team2168.commands.auto.selector.AutoStartLeft2Cube;
import org.team2168.commands.pneumatics.*;
import org.team2168.utils.Debouncer;
import org.team2168.utils.PowerDistribution;

import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot
{
	//Digital Jumper to Identify if this is practice bot or comp bot
	private static DigitalInput practiceBot;

	//Operator Interface
	public static OI oi;
	
	//Subsystems
	public static ClimbGuideArm climbGuideArm;
	public static CubeIntakeWheels cubeIntakeWheels;
	public static CubeIntakeGripper cubeIntakeGripper;
	public static CubeIntakePivot cubeIntakePivot;
	public static Drivetrain drivetrain;
	public static DrivetrainShifter drivetrainShifter;
	public static Lift lift;
	public static LiftRatchetShifter liftRatchetShifter;
	public static LiftShifter liftShifter;
	public static IntakePivotPiston intakePivotPiston;
	//public static Platform platform;
	public static Pneumatics pneumatics;
	

	// Variables for initializing and calibrating the Gyro
	static boolean autoMode;
	private static boolean matchStarted = false;
	public static int gyroReinits;
	private double lastAngle;
	private Debouncer gyroDriftDetector = new Debouncer(1.0);
	public static boolean gyroCalibrating = false;
	private boolean lastGyroCalibrating = false;
	private double curAngle = 0.0;

	public static String test = "Bye";
	public static boolean variable = true;
	
	//Driverstation Instance
	public static DriverStation driverstation;
	
	//PDP Instance
	public static PowerDistribution pdp;
	
	//Autonomous Chooser
    static Command autonomousCommand;
    public static SendableChooser<Command> autoChooser;
    
    //Driver Joystick Chooser
    static int controlStyle;
    public static SendableChooser<Number> controlStyleChooser;
    
  //Driver Joystick Chooser
    static int autoPriority;
    public static SendableChooser<Number> autoPriorityChooser;
    

	double runTime = Timer.getFPGATimestamp();
    
    //Turn on TX1
    //TX1TurnON tx1;
    
    //Global Position Tracking Class
   // public static DrivetrainIMUGlobalPosition dtIMU;
	
    //Variable to track blue alliance vs red alliance
    private static boolean blueAlliance = false;
    
    public static OneDimensionalMotionProfiling motion;
    
    
    public static double[] leftVelPathQuintic;
    public static double[] rightVelPathQuintic;
    
    public static double[] leftVelPathQuintic2;
    public static double[] rightVelPathQuintic2;
    
    public static double[] leftVelPathQuintic3;
    public static double[] rightVelPathQuintic3;
    
    public static double[] leftVelPathQuintic4;
    public static double[] rightVelPathQuintic4;
 
    
    
    public static String gameData = "N A";

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		try
		{
		this.setPeriod(RobotMap.MAIN_PERIOD_S);

		//Stop all WPILib 2018 Telementry
		LiveWindow.disableAllTelemetry();
		
		ConsolePrinter.init();
		ConsolePrinter.setRate(RobotMap.CONSOLE_PRINTER_LOG_RATE_MS);

		practiceBot = new DigitalInput(RobotMap.PRACTICE_BOT_JUMPER);

				
		// Instantiate the subsystems
		climbGuideArm = ClimbGuideArm.getInstance();
		cubeIntakeGripper = CubeIntakeGripper.getInstance();
		cubeIntakePivot = CubeIntakePivot.getInstance();
		cubeIntakeWheels =  CubeIntakeWheels.getInstance();
		drivetrain = Drivetrain.getInstance();
		drivetrainShifter = DrivetrainShifter.getInstance();
		lift = Lift.GetInstance();
		liftShifter = LiftShifter.GetInstance();
		liftRatchetShifter = LiftRatchetShifter.GetInstance();
		//platform = platform.getInstance();
		pneumatics = Pneumatics.getInstance();
		//scissorLift = ScissorLift.getInstance();
		intakePivotPiston = IntakePivotPiston.getInstance();

		
		
//		motion = new OneDimensionalMotionProfiling(15);
//		for(int i=0; i<motion.getVelArray().length; i++)
//			System.out.println(motion.getVelArray()[i]);
//		//Start Thread Only After Every Other Class is Loaded. 
		
		//S Path
//		double[][] waypointPath = new double[][]{
//				{5, 15, Math.PI/2},
//				{5, 18, Math.PI/2},
//				{7, 23, Math.PI/2},
//				{7, 25, Math.PI/2},
//		};
		
		double[][] waypointPath = new double[][]{
			{5, 15, Math.PI/2}, //For left switch & right scale from left side
			{5, 18, Math.PI/2},
			{9, 22, Math.PI/4}
			
			
	};
	
	double[][] waypointPath3 = new double[][]{
		{5, 17, Math.PI/2}, //For Right switch from center 
		{5, 19, Math.PI/2},
		{8.5, 23, Math.PI/2},
		{8.5, 24, Math.PI/2}
	};
	
	double[][] waypointPath4 = new double[][]{
		{10, 18, Math.PI/2}, //For l switch from center 
		{10, 19, Math.PI/2},
		{5, 24,Math.PI/2}
	};
	
	
	

		QuinticTrajectory quinticPath= new QuinticTrajectory(waypointPath);
		quinticPath.calculate();
		quinticPath.calculate();
		
		this.leftVelPathQuintic = quinticPath.getLeftVel();
		this.rightVelPathQuintic = quinticPath.getRightVel();
		
		
		double[][] waypointPath2 = new double[][]{
			{6, 26, 2.36},
			{5, 28, 1.79},
			{5, 34.9, Math.PI/2}
	};
		
		QuinticTrajectory quinticPath2= new QuinticTrajectory(waypointPath2);
		QuinticTrajectory quinticPath3= new QuinticTrajectory(waypointPath3);
		QuinticTrajectory quinticPath4= new QuinticTrajectory(waypointPath4);
		quinticPath2.calculate();
		quinticPath3.calculate();
		quinticPath4.calculate();
		this.leftVelPathQuintic3 = quinticPath3.getLeftVel();
		this.rightVelPathQuintic3 = quinticPath3.getRightVel();
		
		this.leftVelPathQuintic4 = quinticPath4.getLeftVel();
		this.rightVelPathQuintic4 = quinticPath4.getRightVel();
		
		this.leftVelPathQuintic2 = quinticPath2.getLeftVel();
		this.rightVelPathQuintic2 = quinticPath2.getRightVel();
		
		//Start Thread Only After Every Other Class is Loaded. 
		
		
		//Start Operator Interface
		oi = OI.getInstance();

		// enable compressor
		new StartCompressor();

		//Initialize Autonomous Selector Choices
		autoSelectInit();
		
		//Initialize Control Selector Choices
		controlStyleSelectInit();
		
		//Initialize Control Selector Choices
		AutoPrioritySelectInit();
				

		pdp = new PowerDistribution(RobotMap.PDPThreadPeriod);
		pdp.startThread();

		//tx1 = new TX1TurnON(RobotMap.PDPThreadPeriod);
		//tx1.startThread();

		//dtIMU = new DrivetrainIMUGlobalPosition(RobotMap.PDPThreadPeriod);
		//dtIMU.startThread();

		drivetrain.calibrateGyro();
		driverstation = DriverStation.getInstance();


		
		

		
		
		
		
		
		
		
		ConsolePrinter.putSendable("Control Style Chooser", () -> {return Robot.controlStyleChooser;}, true, false);
		ConsolePrinter.putSendable("Autonomous Mode Chooser", () -> {return Robot.autoChooser;}, true, false);
		ConsolePrinter.putString("AutoName", () -> {return Robot.getAutoName();}, true, false);
		ConsolePrinter.putString("Control Style Name", () -> {return Robot.getControlStyleName();}, true, false);
		ConsolePrinter.putNumber("gameClock", () -> {return driverstation.getMatchTime();}, true, false);
		ConsolePrinter.putNumber("Robot Pressure", () -> {return Robot.pneumatics.getPSI();}, true, false);
		ConsolePrinter.putBoolean("Is Practice Bot", () -> {return isPracticeRobot();}, true, false);
		ConsolePrinter.putString("Switch_Scale_Switch orientation", () -> {return driverstation.getGameSpecificMessage();}, true, false); //Ill show you de wei

		
		
		
		

		ConsolePrinter.startThread();
		System.out.println("************Robot Done Loading Successfully**********");
		}
		catch (Throwable throwable) 
		{
		      Throwable cause = throwable.getCause();
		      if (cause != null) 
		        throwable = cause;
		      
		      System.err.println("Bad things occured, testing using our own stach trace catch");
		      System.err.println("Implement Logging function here");
		      System.err.flush();
		      
		      //Show Stack Trace on Driverstration like before
		      DriverStation.reportError("Unhandled exception instantiating robot" 
		              + throwable.toString(), throwable.getStackTrace());
		          DriverStation.reportWarning("Robots should not quit, but yours did!", false);
		          DriverStation.reportError("Could not instantiate robot!", false);
		          System.exit(1);
		      return;
		}
		
		
		LiveWindow.disableAllTelemetry();
		
	}
	
    /************************************************************
    *
    * 				Robot State Machine
    * 
    ************************************************************/
	
	
	//
	/**
	 * This method is called once each time the robot enters Disabled mode. You can
	 * use it to reset any subsystem information you want to clear when the robot is
	 * disabled.
	 */
	public void disabledInit() 
	{
		autoMode = false;
		matchStarted = false;
		
		//If we are not in a match allow Gyro to be recalibrated in Disabled even if a previous 
		//calibration was performed, we disable this in a match so that if we ever die in a match,
		//we don't try to recalibrate a moving robot. 
		if(driverstation.isFMSAttached())
			drivetrain.startGyroCalibrating();
		
		drivetrain.calibrateGyro();
	}

	public void disabledPeriodic() 
	{

		//Keep track of Gunstyle Controller Variables
		
		
		getControlStyleInt();
		controlStyle = (int) controlStyleChooser.getSelected();
		autoPriority = (int) autoPriorityChooser.getSelected();
		autonomousCommand = (Command) autoChooser.getSelected();

		//Continuously get field data
		getFieldData();
		
		// Kill all active commands
		Scheduler.getInstance().run();

	}
	

    public void autonomousInit() 
    {
    	autoMode = true;
    	
    	//get field data one last time
    	getFieldData();	
		matchStarted = true;
		drivetrain.stopGyroCalibrating();
		drivetrain.resetGyro();
		
		
		autonomousCommand = (Command) autoChooser.getSelected();
    	
        // schedule the autonomous command
        if (autonomousCommand != null) 
        	autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	autoMode = true;
        Scheduler.getInstance().run();
        
    }	
	
    /**
     * This function called prior to robot entering Teleop Mode
     */
	public void teleopInit() 
	{
		
	    	autoMode = false;
	    	
			matchStarted = true;
			drivetrain.stopGyroCalibrating();
	    	
	    	// This makes sure that the autonomous stops running when
	        // teleop starts running. If you want the autonomous to 
	        // continue until interrupted by another command, remove
	        // this line or comment it out.
	        if (autonomousCommand != null) autonomousCommand.cancel();
	
	    	// Select the control style
	        controlStyle = (int) controlStyleChooser.getSelected();
	        
	        runTime = Timer.getFPGATimestamp();
	        
	  }
	    

	    /**
	     * This function is called periodically during operator control
	     */
	    public void teleopPeriodic() {
	    	
	        SmartDashboard.putNumber("TeleopLoopTime", Timer.getFPGATimestamp()-runTime);
	        runTime = Timer.getFPGATimestamp();
	    
	    	autoMode = false;
	        Scheduler.getInstance().run();
	        
	        controlStyle = (int) controlStyleChooser.getSelected();
	        if (cubeIntakeWheels.isCubePresent())
	        	cubeIntakeWheels.setLights(1);
	        else 
	        	cubeIntakeWheels.setLights(-1);
	        
	        	
	        
	        

	    }

	    
	    /************************************************************
	     *
	     * 			HELPER FUNCTIONS FOR ENTIRE ROBOT
	     * 
	     ************************************************************/
	
	    

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
		 * Get the name of auto priority
		 * 
		 * @return the name of auto priority
		 */
		public static String getAutoPriority() {
			String retVal = "";

			switch (autoPriority) {
			case 0:
				retVal = "Switch";
				break;
			case 1:
				retVal = "Scale";
				break;
			default:
				retVal = "Invalid Auto Priority";
			}

			return retVal;
		}

		/**
		 * Adds control styles to the selector
		 */
		public void AutoPrioritySelectInit() {
			autoPriorityChooser = new SendableChooser<>();
			autoPriorityChooser.addObject("Switch", 0);
			autoPriorityChooser.addDefault("Scale", 1);
		}

		public static int getAutoPriorityInt() {
			return (int) autoPriorityChooser.getSelected();
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
			autoChooser.addDefault("Do Nothing", new DoNothing());
//			autoChooser.addObject("2018 Right Switch From Center", new DriveToRightSwitch());
//	        autoChooser.addObject("2018 Left Switch From Center", new DriveToLeftSwitch());
//	        autoChooser.addObject("2018 Left Switch From Center and left scale", new DriveToLeftSwitchAndLeftScale());
//	        autoChooser.addObject("2018 Right Switch From Center and right scale", new DriveToRightSwitchAndRightScale());
//	        autoChooser.addObject("2018 Left Switch From Center and right scale ", new DriveToLeftSwitchAndRightScale());
//	        autoChooser.addObject("2018 Right Switch From Center and left scale", new DriveToRightSwitchAndLeftScale());
//	        autoChooser.addObject("2018 Left Scale From Left Side", new DriveToLeftScaleFromLeftSide());
//	        autoChooser.addObject("2018 Right Scale From Right Side", new DriveToRightScaleFromRightSide());
//	        autoChooser.addObject("2018 Right Scale from Left side", new DriveToRightScaleFromLeftSide());
//	        autoChooser.addObject("2018 Left Scale from Right side", new DriveToLeftScaleFromRightSide());
//	        autoChooser.addObject("2018 Left Switch from Left side", new DriveToLeftSwitchFromLeftSide());
//	        autoChooser.addObject("2018 Right Switch from Right side", new DriveToRightSwitchFromRightSide());
//	        autoChooser.addObject("2018 Left Switch from Right side", new DriveToLeftSwitchFromRightSide());
//	        autoChooser.addObject("2018 Right Switch from Left side", new DriveToRightSwitchFromLeftSide());
	        autoChooser.addObject("2018 Boss Shit Left", new DriveToLeftSwitchAndRightScaleFromLeft());
	        autoChooser.addObject("2018 Center right", new DriveToRightSwitch());
	        autoChooser.addObject("2018 Center left", new DriveToLeftSwitch());
	        autoChooser.addObject("Center Auto", new AutoStartCenter1Cube());
			autoChooser.addObject("Straight", new DriveStraight());
		}

		/**
		 *
		 * @return true if the robot is in auto mode
		 */
		public static boolean isAutoMode() {
			return autoMode;

		}

		
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

		}
	    


	/**
	 * Returns the status of DIO pin 24
	 *
	 * @return true if this is the practice robot
	 */
	public static boolean isPracticeRobot() {
		return !practiceBot.get();

	}


	public static boolean onBlueAlliance() {
		return driverstation.getAlliance() == DriverStation.Alliance.Blue;

	}
	
	
	// This function is called periodically during test mode
	public void testPeriodic() {
		
		LiveWindow.run();
		
	}
	
	public String getFieldData()
	{
		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameMessage.length()==3 && this.gameData != null)
		{
			gameData = gameMessage;
		}
		else
			gameData = "N A";
		return gameData;
	}
	
	
}