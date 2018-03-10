package org.team2168.commands.auto.selector;

import org.team2168.Robot;
import org.team2168.commands.auto.DriveToLeftSwitch;
import org.team2168.commands.auto.DriveToRightSwitch;
import org.team2168.commands.auto.massComp.DriveToLeftScaleAndLeftSwitchFromLeftSide;
import org.team2168.commands.auto.massComp.DriveToLeftScaleAndRightSwitchFromLeftSide;
import org.team2168.commands.auto.massComp.DriveToLeftSwitchAndRightScaleFromLeft;
import org.team2168.commands.auto.massComp.DriveToRightScaleAndRightSwitchFromLeftSide;
import org.team2168.commands.auto.massComp.RightScaleOnlyFromLeftSide;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutoStartCenter1Cube extends Command {

    public AutoStartCenter1Cube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.gameData.equals("LLL"))
    		Scheduler.getInstance().add(new DriveToLeftSwitch());
    	else if (Robot.gameData.equals("LRL"))
    		Scheduler.getInstance().add(new DriveToLeftSwitch());
    	else if (Robot.gameData.equals("RRR"))
    		Scheduler.getInstance().add(new DriveToRightSwitch());
    	else if (Robot.gameData.equals("RLR"))
      		Scheduler.getInstance().add(new DriveToRightSwitch());
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