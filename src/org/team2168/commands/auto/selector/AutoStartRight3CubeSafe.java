package org.team2168.commands.auto.selector;

import org.team2168.Robot;
import org.team2168.commands.auto.RealOnes.DriveToRightScaleFromLeft;
import org.team2168.commands.auto.RealOnes.AidansRightyTighty3CubeAuto;
import org.team2168.commands.auto.RealOnes.DriveStraight;
import org.team2168.commands.auto.RealOnes.DriveToLeftScale2CubeFromLeftSide;
import org.team2168.commands.auto.RealOnes.DriveToLeftScale3CubeFromLeftSide;
import org.team2168.commands.auto.RealOnes.DriveToLeftScaleAndLeftSwitchFromLeftSide;
import org.team2168.commands.auto.RealOnes.DriveToLeftSwitchFromLeftSide2;
import org.team2168.commands.auto.RightSide.DriveToLeftScaleFromRightSide;
import org.team2168.commands.auto.RightSide.DriveToLeftScaleFromRightSideSafe;
import org.team2168.commands.auto.RightSide.DriveToRightScaleAndRightSwitchRightSide;
import org.team2168.commands.auto.RightSide.DriveToRightSwitchFromRightSide;
import org.team2168.commands.auto.RightSide.DriveToScale2CubeFromRightSide;
import org.team2168.commands.auto.RightSide.RightSwitchOnlyFromRightSide;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutoStartRight3CubeSafe extends Command {

    public AutoStartRight3CubeSafe() {
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
    		Scheduler.getInstance().add(new RightSwitchOnlyFromRightSide());
    	else if (Robot.gameData.equals("LLL"))
    		Scheduler.getInstance().add(new DriveStraight());
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
