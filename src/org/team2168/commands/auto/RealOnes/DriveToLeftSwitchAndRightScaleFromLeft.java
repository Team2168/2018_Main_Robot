package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZNoBattery;
import org.team2168.commands.flippyFloopy.EngageIntakePivotHardStop;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to left switch and right scale
 */
public class DriveToLeftSwitchAndRightScaleFromLeft extends CommandGroup {

    public DriveToLeftSwitchAndRightScaleFromLeft() {
    	
    	//addParallel(new DriveLiftPIDZZZ(33.0, 0.5, 0.1,1.0,true));
    	addSequential(new RobotRunPrep());
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic, Robot.rightVelPathQuintic));
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
    	
    	addSequential(new DrivePIDPath(2.5, true)); //drive backwards
    	addSequential(new RotateXDistancePIDZZZNoBattery(-25.0,0.53,0.1,0.5,true));
    	addSequential(new RotateXDistancePIDZZZ(-25.0,0.53,0.1,0.5,true));
    	
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic2, Robot.rightVelPathQuintic2));
    	//addSequential(new DrivePIDPath(1.0));
    	
    	addSequential(new RotateXDistancePIDZZZ(89.0,0.53,0.1,0.5,true));
    	addSequential(new RotateXDistancePIDZZZ(89.0,0.53,0.1,0.5,true));
    	//addSequential(new DrivePIDPath(1.0));
    	//addParallel(new DriveLiftPIDZZZ(1.5, 0.7, 0.16,0.5,true));
    	addSequential(new DrivePIDPath(11.0));
    	addSequential(new RotateXDistancePIDZZZ(150.0,0.53,0.1,0.5,true));

    	addParallel(new DrivePIDPath(3));
    	addSequential(new IntakeUntilCube());
//    	
//    	//score second cube
//    	//drive lift to score height
//    	addParallel(new DrivePIDPath(1.0, true)); //drive backwards
//    	addSequential(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
//    	addSequential(new DrivePIDPath(2.0)); //put bumpers against wall
//    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE), 0.4 );
    	
    	
//    	addSequential(new DriveXDistance(19.8,0.7,0.05));
//    	addSequential(new RotateXDistancePIDZZZ(90,0.7,0.2));
//    	addSequential(new DriveXDistance(21.0,0.7,0.05));
//    	addSequential(new RotateXDistancePIDZZZ(-90,0.7,0.2));S
//    	addSequential(new DriveXDistance(7.0,0.7,0.05));
//    	addSequential(new RotateXDistancePIDZZZ(-90,0.7,0.2));
//    	addSequential(new DriveXDistance(2.0,0.7,0.05));
//    	
//    	
//    	//score on switch (Spit Intake)
//   	 	addSequential(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT)); 
//   	 	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
//   	 	addParallel(new StopWheels());
//   	    addParallel(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_CONSTANT));
    }
}
