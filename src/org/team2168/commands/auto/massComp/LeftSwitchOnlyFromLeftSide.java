package org.team2168.commands.auto.massComp;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intakePivotPiston.ExtendPivotPiston;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**1
 *
 */
public class LeftSwitchOnlyFromLeftSide extends CommandGroup {

    public LeftSwitchOnlyFromLeftSide() {
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new ExtendPivotPiston());
    	addParallel(new IntakeUntilCube());
    	addSequential(new Sleep(), 0.5);
    	addParallel(new OperationKeepCube());
    	addSequential(new Sleep(), 1.5);
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	//addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic, Robot.rightVelPathQuintic));
    	addSequential(new DrivePIDPath(10.5));
    	addSequential(new RotateXDistancePIDZZZ(60,0.7,0.2, 0.5, true),2.0);
    	addSequential(new RotateXDistancePIDZZZ(60,0.7,0.2 ,0.5, true ),1.0);
    	addSequential(new DrivePIDPath(10.5));
    	
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
    }
}
