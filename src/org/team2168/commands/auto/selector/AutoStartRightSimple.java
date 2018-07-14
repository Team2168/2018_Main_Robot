package org.team2168.commands.auto.selector;

import org.team2168.Robot;
import org.team2168.commands.auto.RealOnes.AidansRightyTighty3CubeAuto;
import org.team2168.commands.auto.RealOnes.DriveStraight;
import org.team2168.commands.auto.RightSide.DriveToRightScaleAndRightSwitchRightSide;
import org.team2168.commands.auto.RightSide.RightSwitchOnlyFromRightSide;
import org.team2168.commands.auto.RightSide.oneCube;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutoStartRightSimple extends Command {

    public AutoStartRightSimple() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.gameData.equals("RRR"))
    	{
    		if (Robot.getAutoPriorityInt() == 0) //switch priority
    			Scheduler.getInstance().add(new oneCube());
    		else
    			Scheduler.getInstance().add(new oneCube());
    	}
    	else if (Robot.gameData.equals("RLR"))
    		Scheduler.getInstance().add(new RightSwitchOnlyFromRightSide());
    	else if (Robot.gameData.equals("LLL"))
    		Scheduler.getInstance().add(new DriveStraight());
    	else if (Robot.gameData.equals("LRL"))
      		Scheduler.getInstance().add(new oneCube());
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
