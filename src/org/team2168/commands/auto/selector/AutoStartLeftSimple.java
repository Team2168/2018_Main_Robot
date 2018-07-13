package org.team2168.commands.auto.selector;

import org.team2168.Robot;
import org.team2168.commands.auto.RealOnes.DriveToLeftScale3CubeFromLeftSide;
import org.team2168.commands.auto.RealOnes.DriveToLeftScaleAndLeftSwitchFromLeftSide;
import org.team2168.commands.auto.RealOnes.DriveToRightScaleFromLeftSafe;
import org.team2168.commands.auto.RealOnes.LeftSwitchOnlyFromLeftSide;
import org.team2168.commands.auto.RealOnes.SimpleOneCube;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutoStartLeftSimple extends Command {

    public AutoStartLeftSimple() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.gameData.equals("LLL"))
    	{
    		if (Robot.getAutoPriorityInt() == 0) //switch priority
    			Scheduler.getInstance().add(new DriveToLeftScaleAndLeftSwitchFromLeftSide());
    		else
    			Scheduler.getInstance().add(new SimpleOneCube());
    	}
    	else if (Robot.gameData.equals("LRL"))
    		Scheduler.getInstance().add(new LeftSwitchOnlyFromLeftSide());
    	else if (Robot.gameData.equals("RRR"))
//    		Scheduler.getInstance().add(new RightScaleOnlyFromLeftSide());
    		Scheduler.getInstance().add(new DriveToRightScaleFromLeftSafe());
    	else if (Robot.gameData.equals("RLR"))
      		Scheduler.getInstance().add(new SimpleOneCube());
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
