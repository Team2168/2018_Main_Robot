package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intake.StopWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to right swtich from right side
 */
public class DriveToRightSwitchFromRightSide extends CommandGroup {

    public DriveToRightSwitchFromRightSide() {
    	addSequential(new DriveXDistance(12.0,0.7,0.05));
    	addSequential(new RotateXDistancePIDZZZ(-90,0.4,0.2));
    	addSequential(new DriveXDistance(1.3,0.7,0.05));
    	
    	//score on switch (Spit Intake)
   	 	addSequential(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT)); 
   	 	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	addParallel(new StopWheels());
   	    addParallel(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_CONSTANT));
 
    }
}
