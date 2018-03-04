package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intake.StopWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScoreOnSwitch extends CommandGroup {

    public ScoreOnSwitch() {
    	addSequential(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT)); 
   	 	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	addParallel(new StopWheels());
   	    addParallel(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_CONSTANT));
    }
}
