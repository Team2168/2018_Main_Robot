package org.team2168.commands.intake;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveIntakeWheelsWithConstant extends Command {
	double speed;
    
	public DriveIntakeWheelsWithConstant(double inputspeed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.cubeIntakeWheels);
        speed = inputspeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    Robot.cubeIntakeWheels.driveAllMotors(speed);
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntakeWheels.driveAllMotors(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
