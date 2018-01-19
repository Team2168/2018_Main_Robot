package org.team2168.commands.elevator;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives elevator with constant
 */
public class DriveElevatorWithConstant extends Command {

	
	double speed;
    public DriveElevatorWithConstant(double elevatorSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
        speed = elevatorSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.driveAllMotors(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.driveAllMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
