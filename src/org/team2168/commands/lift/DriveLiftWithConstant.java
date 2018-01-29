package org.team2168.commands.lift;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives lift with constant
 */
public class DriveLiftWithConstant extends Command {

	double speed;

	public DriveLiftWithConstant(double liftSpeed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
		speed = liftSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.lift.driveAllMotors(speed);
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
	}
}
