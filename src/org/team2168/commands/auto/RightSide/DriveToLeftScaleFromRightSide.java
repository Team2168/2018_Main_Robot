package org.team2168.commands.auto.RightSide;

import org.team2168.Robot;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.flippyFloopy.EngageIntakePivotHardStop;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToLeftScaleFromRightSide extends CommandGroup {

    public DriveToLeftScaleFromRightSide() {
    	addParallel(new EngageIntakePivotHardStop());
    	addSequential(new RobotPrep());
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic10, Robot.rightVelPathQuintic10, Robot.headingQuintic10));
//    	addSequential(new RotateXDistancePIDZZZ(0,0.7,0.2, 0.5,true), 2.0);
//    	addSequential(new RotateXDistancePIDZZZ(0,0.7,0.2, 0.5, true), 2.0 );
//    	addSequential(new DrivePIDPath(2.5));
    	//addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE *.4 ),0.4);
   	 	//addSequential(new StopWheels());
    	    }
}
