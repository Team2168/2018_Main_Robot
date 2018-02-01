package org.team2168.commands.drivetrain;

import org.team2168.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class DriveWithJoysticks extends Command {

	public DriveWithJoysticks() {
		
		requires(Robot.drivetrain);
		
	}
	
	
	
	// Called just before this command runs for the first time
	protected void initialize() {
	}
	
	
	
	// Called repeatedly when this command is scheduled to run
	protected void execute() {
		
		Robot.drivetrain.tankDrive(Robot.oi.driverJoystick.getLeftStickRaw_Y(), Robot.oi.driverJoystick.getRightStickRaw_Y());
		
	}
	
	
	
	// Make this return true when the command no longer needs to run execute()
	protected boolean isFinished() {
		
		return false;
		
	}
	
	
	
	// Called once isFinished() returns true
	protected void end() {
	}
	
	
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
	
}
