package org.team2168.commands.auto;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.DriveXUntilCube;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to right switch boiii
 */
public class DriveToLeftSwitch extends CommandGroup {

    public DriveToLeftSwitch() {
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_INTAKE),0.4);
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic4, Robot.rightVelPathQuintic4)); 	
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	addSequential(new StopWheels());
 	 
    }
}
