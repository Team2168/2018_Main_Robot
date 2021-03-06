package org.team2168.commands.auto.RightSide;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToRightScaleAndRightSwitchRightSide extends CommandGroup {

    public DriveToRightScaleAndRightSwitchRightSide() {
    	//drive stright to null territory
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
        addSequential(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	//addSequential(new Sleep(), 0.75); 
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(18.0));
    	//drive lift to score height
    	addSequential(new DriveLiftPIDZZZ(80.0, 0.9, 0.1,1.0,true));
    	addSequential(new RotateXDistancePIDZZZ(300,0.9,0.4,0.5,true), 0.6);
    	
    	

    	
    	//addSequential(new DrivePIDPath(2.0));
    	addSequential(new DriveIntakeWheelsWithConstant(-0.8), 0.4 );
    	addSequential(new RotateXDistancePIDZZZ(215,1.0,0.5,0.5,true));
    	//get second cube
    	addSequential(new DriveLiftPIDZZZ(1.5, 0.7, 0.1,1.0,true));
    	
    	addParallel(new DrivePIDPath(5.0));
    	addSequential(new IntakeUntilCube());
    	
    	
    	//score second cube
    	//drive lift to score height
    	addSequential(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(2.0));
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE), 0.4 );

    }
}
