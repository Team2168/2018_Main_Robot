package org.team2168.commands.lights;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftLow extends Command {

    public LiftLow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.i2c.write(8, 1);
    	System.out.println("sent it 3");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
