package org.team2168.commands.auto.massComp;

import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.flippyFloopy.ExtendFlippy;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleOnlyFromLeftSide extends CommandGroup {

    public RightScaleOnlyFromLeftSide() {
    	addSequential(new RobotRunPrep());
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(15.0));
    	addSequential(new RotateXDistancePIDZZZ(89.0,0.63,0.1,0.5,true));
    	addSequential(new DrivePIDPath(12.7));
    	addSequential(new RotateXDistancePIDZZZ(-10.0,0.7,0.1,0.5,true));
    
    	//drive lift to score height
    	addParallel(new DriveLiftPIDZZZ(80.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(0.6));
    	
    	
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE), 0.4 );
    } 
    
}
