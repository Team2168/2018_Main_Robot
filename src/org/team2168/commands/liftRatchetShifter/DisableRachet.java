package org.team2168.commands.liftRatchetShifter;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DisableRachet extends Command {

    public DisableRachet() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.liftRatchetShifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    Robot.liftRatchetShifter.disengageRatchet();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.liftRatchetShifter.isRatchetDisEngaged();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
