package org.team2168;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.team2168.commands.ExampleCommand;
import org.team2168.subsystems.drivetrain.Drivetrain;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

<<<<<<< HEAD
=======
	//Subsystems instance variables
	//  If you need to access a subsystem from anywhere in the code,
	//  it's done through one of these variables.
	public static Drivetrain drivetrain;
	public static Pneumatics pneumatics;
	public static CubeIntake cubeIntake;
>>>>>>> refs/remotes/origin/_CubeIntake_AS

public class Robot extends IterativeRobot
{
	
	public static OI oi;
	public static Drivetrain drivetrain;
	
	Command autonomousCommand;
	
	
	

	 //This command is run when the robot is first started up and should be
	 //used for any initialization code

	public void robotInit() {
		
		oi = OI.getInstance();
		drivetrain = Drivetrain.getInstance();
		// instantiate the command used for the autonomous period
		autonomousCommand = new ExampleCommand();
		
	}
	
	
	
	public void disabledPeriodic() {
		
		Scheduler.getInstance().run();
		
	}
	
	
	
	public void autonomousInit() {
		
		// Schedule the autonomous command
		if (autonomousCommand != null) autonomousCommand.start();
		
	}
	
	
	
	// This function is called periodically during autonomous
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
		
	}
	
	
	
	public void teleopInit() {
		
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
	}
	
	
	
	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		
	}
	
	
	
	// This function is called periodically during operator control
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		
	}
	
	
	
	// This function is called periodically during test mode
	public void testPeriodic() {
		
		LiveWindow.run();
		
	}
	
}