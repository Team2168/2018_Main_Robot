package org.team2168;

import org.team2168.subsystems.*;
import org.team2168.PID.trajectory.OneDimensionalMotionProfiling;
import org.team2168.PID.trajectory.QuinticTrajectory;
import org.team2168.commands.auto.*;
import org.team2168.commands.auto.massComp.DriveStraight;
import org.team2168.commands.auto.massComp.DriveToLeftScaleOnlyV2;
import org.team2168.commands.auto.massComp.DriveToLeftSwitchAndRightScaleFromLeft;
import org.team2168.commands.auto.selector.TestAutoCommandGroupA;
import org.team2168.commands.auto.selector.AutoStartCenter1Cube;
import org.team2168.commands.auto.selector.AutoStartLeft1Cube;
import org.team2168.commands.auto.selector.AutoStartLeft2Cube;
import org.team2168.commands.auto.selector.AutoStartLeft2CubeSuperDooperPooper;
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
	public static FlipperyFloopyFlupy flipperyFloopyFlupy;
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
    public static double[] headingQuintic;
    
    public static double[] leftVelPathQuintic2;
    public static double[] rightVelPathQuintic2;
    public static double[] headingQuintic2;
    
    public static double[] leftVelPathQuintic3;
    public static double[] rightVelPathQuintic3;
    public static double[] headingQuintic3;
    
    public static double[] leftVelPathQuintic4;
    public static double[] rightVelPathQuintic4;
    public static double[] headingQuintic4;
 
    
    public static double[] leftVelPathQuintic5;
    public static double[] rightVelPathQuintic5;
    public static double[] headingQuintic5;
    
    public static double[] leftVelPathQuintic6;
    public static double[] rightVelPathQuintic6;
    public static double[] headingQuintic6;
 
    
    
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
		flipperyFloopyFlupy = FlipperyFloopyFlupy.getInstance();

		
		
//		motion = new OneDimensionalMotionProfiling(15);
//		for(int i=0; i<motion.getVelArray().length; i++)
//			System.out.println(motion.getVelArray()[i]);
//		//Start Thread Only After Every Other Class is Loaded. 
		
		
		double[][] waypointPath = new double[][]{
			{1, 26, 0}, //For left switch & right scale from left side
			{11.5, 27.0, 0},
			{13.0, 25.5, -Math.PI/2 + 0.0001}		
			
	};

		QuinticTrajectory quinticPath= new QuinticTrajectory(waypointPath);
		quinticPath.calculate();
		
		this.leftVelPathQuintic = quinticPath.getLeftVel();
		this.rightVelPathQuintic = quinticPath.getRightVel();
		this.headingQuintic = quinticPath.getHeadingDeg();
		
		
		double[][] waypointPath2 = new double[][]{
			{14.5, 23.5, Math.PI/2},
			{21, 27.0, 0+0.0001},
			{22.5, 27.0, 0}
			
			//{27, 20, 0}	

	};
		
		QuinticTrajectory quinticPath2= new QuinticTrajectory(waypointPath2, 3.0, 20.0);
		quinticPath2.calculate();
	
		this.leftVelPathQuintic2 = quinticPath2.getLeftVel();
		this.rightVelPathQuintic2 = quinticPath2.getRightVel();
		this.headingQuintic2 = quinticPath2.getHeadingDeg();
		
	
	    double[][] waypointPath3 = new double[][]
      {
	    	{1, 15.5, 0}, //Right switch Path
			{2, 15.5, 0},
			{9.5, 11.5, 0} //need to add 1.5 to 12.6 //for 4th match
	    
	};


		QuinticTrajectory quinticPath3 = new QuinticTrajectory(waypointPath3);
		quinticPath3.calculate();
		this.leftVelPathQuintic3 = quinticPath3.getLeftVel();
		this.rightVelPathQuintic3 = quinticPath3.getRightVel();
		this.headingQuintic3 = quinticPath3.getHeadingDeg();
		
	    double[][] waypointPath4 = new double[][]{
	    	//{5, 17, Math.PI/2}, //For Right switch from center 
			//{5, 19, Math.PI/2},
			//{8.5, 23, Math.PI/2},
			//{8.5, 24, Math.PI/2}
			
	    	{1, 15.5, 0}, //Right switch Path
			{2, 15.5, 0},
			{9.5, 19.5, 0} 
		};


		QuinticTrajectory quinticPath4 = new QuinticTrajectory(waypointPath4);
		quinticPath4.calculate();
		this.leftVelPathQuintic4 = quinticPath4.getLeftVel();
		this.rightVelPathQuintic4 = quinticPath4.getRightVel();
		this.headingQuintic4 = quinticPath4.getHeadingDeg();
		
		//Start Thread Only After Every Other Class is Loaded. 
		
	    double[][] waypointPath5 = new double[][]{
	    	//{5, 17, Math.PI/2}, //For Right switch from center 
			//{5, 19, Math.PI/2},
			//{8.5, 23, Math.PI/2},
			//{8.5, 25, Math.PI/2}
			
//			{10, 24, 0},
//			{23, 24, 0},
//			{27, 20, -Math.PI/2+0.0001},
//			{27, 8, -Math.PI/2+0.0001},
//			{29,5, 0}
			
			{10, 24, 0},
			{24, 24, 0},
			{27, 20, -Math.PI/2+0.0001},
			{27, 17, -Math.PI/2+0.0001}
			//{27, 13, -Math.PI/2+0.0001},
			//{27, 10, -Math.PI/2+0.0001},
			//{29, 8, 0}
			
		};
		
		
		

		
		QuinticTrajectory quinticPath5 = new QuinticTrajectory(waypointPath5);
		quinticPath5.calculate();
		this.leftVelPathQuintic5 = quinticPath5.getLeftVel();
		this.rightVelPathQuintic5 = quinticPath5.getRightVel();
		this.headingQuintic5 = quinticPath5.getHeadingDeg();

		double[][] waypointPath6 = new double[][]{
//			{2, 26, 0},
//			{15.5, 26, 0},
//			{19.5, 25.5, -Math.PI/6}
			
//			{2, 26, 0},
//			{18.5, 27.5, 0},
//			{23.5, 26, -Math.PI/2.5}
			
			
			//{2, 26, 0},  //OG Path
			//{14.3, 27, 0},
			//{19.1, 26.5, -Math.PI/5}
			
			{2, 26, 0}, //crazy path
			{20.3, 26.5, 0},
			{22.1, 26.5, -Math.PI/3}
//			{2, 26, 0},
//			{17.5, 26, 0},
//			{21.5, 26, -Math.PI/3.5}
		};
		
		QuinticTrajectory quinticPath6 = new QuinticTrajectory(waypointPath6);
		quinticPath6.calculate();
		
		this.leftVelPathQuintic6 = quinticPath6.getLeftVel();
		this.rightVelPathQuintic6 = quinticPath6.getRightVel();
		this.headingQuintic6 = quinticPath6.getHeadingDeg();

		
		
		
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
		ConsolePrinter.putSendable("Priority Mode Chooser", () -> {return Robot.autoPriorityChooser;}, true, false);
		ConsolePrinter.putString("AutoName", () -> {return Robot.getAutoName();}, true, false);
		ConsolePrinter.putString("Control Style Name", () -> {return Robot.getControlStyleName();}, true, false);
		ConsolePrinter.putString("Auto Priority Name", () -> {return Robot.getAutoPriority();}, true, false);
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
	        updateLights();
	        
	        	
	        
	        

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
		 * Adds auto priorities to the selector
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
			autoChooser.addObject("Do Nothing", new DriveStraight(8.0));
	        autoChooser.addObject("Center Auto 1 Cube", new AutoStartCenter1Cube());
	        autoChooser.addObject("Left Auto 1 Cube", new AutoStartLeft1Cube());
	        autoChooser.addObject("Left Auto 2 Cube", new AutoStartLeft2Cube());
			autoChooser.addDefault("Drive Straight Only", new DriveStraight());
			autoChooser.addObject("Left Auto 2 Cube Super Dooper", new AutoStartLeft2CubeSuperDooperPooper());
			autoChooser.addObject("RightScaleTest", new DriveToRightScaleFromLeft());
			
			
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
	private void updateLights()  {                          
	if (cubeIntakeWheels.isCubePresent())
    	cubeIntakeWheels.setLights(1);
    else 
    	cubeIntakeWheels.setLights(-1);
	}
	
	
	
}
