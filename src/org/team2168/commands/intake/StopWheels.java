package org.team2168.commands.intake;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stop intake wheels
 */
public class StopWheels extends Command {

    public StopWheels() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.cubeIntakeWheels);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cubeIntakeWheels.driveAllMotors(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntakeWheels.driveAllMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
