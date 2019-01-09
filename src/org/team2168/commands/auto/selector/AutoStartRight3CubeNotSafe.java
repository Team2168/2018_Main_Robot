package org.team2168.commands.auto.selector;

import org.team2168.Robot;
import org.team2168.commands.auto.RealOnes.AidansRightyTighty3CubeAuto;
import org.team2168.commands.auto.RightSide.DriveToLeftScaleFromRightSide;
import org.team2168.commands.auto.RightSide.DriveToLeftScaleFromRightSideSafe;
import org.team2168.commands.auto.RightSide.DriveToRightScaleAndRightSwitchRightSide;
import org.team2168.commands.auto.RightSide.RightSwitchOnlyFromRightSide;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutoStartRight3CubeNotSafe extends Command {

    public AutoStartRight3CubeNotSafe() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.gameData.equals("RRR"))
    	{
    		if (Robot.getAutoPriorityInt() == 0) //switch priority
    			Scheduler.getInstance().add(new DriveToRightScaleAndRightSwitchRightSide());
    		else
    			Scheduler.getInstance().add(new AidansRightyTighty3CubeAuto());
    	}
    	else if (Robot.gameData.equals("RLR"))
    		Scheduler.getInstance().add(new DriveToLeftScaleFromRightSide());
    	else if (Robot.gameData.equals("LLL"))
    		Scheduler.getInstance().add(new DriveToLeftScaleFromRightSide());
    	else if (Robot.gameData.equals("LRL"))
      		Scheduler.getInstance().add(new AidansRightyTighty3CubeAuto());
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
