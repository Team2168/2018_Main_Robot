
package org.team2168.commands.auto.selector;

import org.team2168.Robot;
import org.team2168.commands.auto.DriveToRightScaleFromLeft;
import org.team2168.commands.auto.massComp.DriveStraight;
import org.team2168.commands.auto.massComp.DriveToLeftScale2CubeFromLeftSide;
import org.team2168.commands.auto.massComp.DriveToLeftScaleAndLeftSwitchFromLeftSide;
import org.team2168.commands.auto.massComp.DriveToLeftScaleOnlyV2;
import org.team2168.commands.auto.massComp.DriveToLeftSwitch;
import org.team2168.commands.auto.massComp.DriveToRightSwitch;
import org.team2168.commands.auto.massComp.LeftScaleOnlyFromLeftSide;
import org.team2168.commands.auto.massComp.LeftSwitchOnlyFromLeftSide;
import org.team2168.commands.auto.massComp.RightScaleOnlyFromLeftSide;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutoStartLeft1Cube extends Command {

    public AutoStartLeft1Cube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.gameData.equals("LLL"))
    	{
    			Scheduler.getInstance().add(new DriveToLeftScaleOnlyV2());}
    	else if (Robot.gameData.equals("RRR"))  		
    		Scheduler.getInstance().add(new DriveToRightScaleFromLeft());
    	else if (Robot.gameData.equals("LRL"))
    		Scheduler.getInstance().add(new LeftSwitchOnlyFromLeftSide());
    	else if (Robot.gameData.equals("RLR"))
      		Scheduler.getInstance().add(new DriveToLeftScaleOnlyV2());
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
