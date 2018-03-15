package org.team2168.commands.auto.massComp;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleOnlyFromLeftSide extends CommandGroup {

    public LeftScaleOnlyFromLeftSide() {
    	//drive stright to null territory
    	addParallel(new DriveLiftPIDZZZ(33.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(18.0));
    	addSequential(new RotateXDistancePIDZZZ(45,0.7,0.2));
    	
    	
    	//drive lift to score height
    	addSequential(new DriveLiftPIDZZZ(80.0, 0.5, 0.1,1.0,true));
    	
    	
    	
    	//addSequential(new DrivePIDPath(2.0));
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE), 0.4 );
    }
}
