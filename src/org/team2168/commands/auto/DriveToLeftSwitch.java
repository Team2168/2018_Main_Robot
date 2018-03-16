package org.team2168.commands.auto;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.DriveXUntilCube;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intakePivotPiston.ExtendPivotPiston;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to left switch boiii
 */
public class DriveToLeftSwitch extends CommandGroup {

    public DriveToLeftSwitch() {
    	addParallel(new ExtendPivotPiston());
    	addParallel(new IntakeUntilCube());
    	addParallel(new OperationKeepCube());
    	addSequential(new Sleep(), 1.0);
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic4, Robot.rightVelPathQuintic4)); 	
    	
    	addSequential(new DrivePIDPath(1.0));
    	addSequential(new RotateXDistancePIDZZZ(0,0.7,0.2, 0.5,true), 2.0 );
    	addSequential(new RotateXDistancePIDZZZ(0,0.7,0.2, 0.5, true), 2.0);
    	addSequential(new DrivePIDPath(2.5));
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE * 0.4),0.4);
   	 	addSequential(new StopWheels());
 	 
    }
}
