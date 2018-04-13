package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.flippyFloopy.EngageIntakePivotHardStop;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToLeftSwitchFromLeftSide2 extends CommandGroup {

    public DriveToLeftSwitchFromLeftSide2() {
        
    	
    	addSequential(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new DriveLiftPIDZZZ(50, 0.7, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic, Robot.rightVelPathQuintic, Robot.headingQuintic));
    	
    
    	
    	
    	
    	//spit
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE *0.5),0.4);
    	addSequential(new StopWheels());
    	
    	//Drop it low and get dat cube
    	addParallel(new DriveLiftPIDZZZ(1.5, 0.7, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic2, Robot.rightVelPathQuintic2, Robot.headingQuintic2, true));
    	addSequential(new RotateXDistancePIDZZZ(140,0.6,0.2,0.5,true));
    	addSequential(new RotateXDistancePIDZZZ(140,0.6,0.2,0.5,true));
    	addParallel(new DrivePIDPath(5.5));
    	addSequential(new IntakeUntilCube());
    	//Score Second Cube
    	addSequential(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(1.0));
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE), 0.4 );
    	
    	
    	
    	
           }
}
