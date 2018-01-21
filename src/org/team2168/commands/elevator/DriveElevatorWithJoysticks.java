package org.team2168.commands.elevator;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the elevator with joysticks
 */
public class DriveElevatorWithJoysticks extends Command {
	private F310 joystick;
	private double speed;
    public DriveElevatorWithJoysticks(F310 Joysticks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
        this.joystick = joystick; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double value = joystick.getRightStickRaw_X();
    	Robot.elevator.driveAllMotors(value * RobotMap.ELEVATOR_MAX_JOYSTICK_SPEED  );
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.driveAllMotors(0);
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
