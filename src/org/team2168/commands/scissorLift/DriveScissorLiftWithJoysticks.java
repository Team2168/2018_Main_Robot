package org.team2168.commands.scissorLift;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveScissorLiftWithJoysticks extends Command {

	private F310 joystick;
	private double speed;
	
	
    public DriveScissorLiftWithJoysticks(F310 joystick) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.scissorLift);
        this.joystick = joystick;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.scissorLift.driveMotor(OI.operatorJoystick.getLeftStickRaw_Y());
    	
    	
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
