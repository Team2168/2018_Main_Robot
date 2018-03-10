package org.team2168.commands.intake;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Keepin the cube
 */
public class OperationKeepCube extends Command {

    public OperationKeepCube() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.cubeIntakeWheels);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.cubeIntakeWheels.isCubePresent())
    		Robot.cubeIntakeWheels.driveAllMotors(0.25);
    	else if(Robot.cubeIntakeWheels.getRawIRVoltage() < 2.3 && Robot.cubeIntakeWheels.getRawIRVoltage() > 1.5)

    		Robot.cubeIntakeWheels.driveAllMotors(RobotMap.CUBE_INTAKE_MAX_INTAKE * .5);
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
    	end();
    }
}