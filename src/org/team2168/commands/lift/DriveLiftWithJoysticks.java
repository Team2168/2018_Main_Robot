package org.team2168.commands.lift;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the lift with joysticks
 */
public class DriveLiftWithJoysticks extends Command {
	

	public DriveLiftWithJoysticks() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.lift.driveAllMotors(OI.getDriveLiftJoystickValue() * RobotMap.LIFT_MAX_JOYSTICK_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.driveAllMotors(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.lift.driveAllMotors(0);
	}
}
