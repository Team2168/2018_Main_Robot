package org.team2168.commands.auto.massComp;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intakePivotPiston.ExtendPivotPiston;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to right switch boiii
 */
public class DriveToRightSwitch extends CommandGroup {
	
	double backupSecondCube = 6.0;
	double driveToCube = 5.0;
	double rotateSecondCube = 50;

    public DriveToRightSwitch() {
    	addParallel(new ExtendPivotPiston());
    	addParallel(new IntakeUntilCube());
    	addParallel(new OperationKeepCube());
    	addSequential(new Sleep(), 1.0);
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic3, Robot.rightVelPathQuintic3, Robot.headingQuintic3));
//    	addSequential(new RotateXDistancePIDZZZ(0,0.7,0.2, 0.5,true), 2.0);
//    	addSequential(new RotateXDistancePIDZZZ(0,0.7,0.2, 0.5, true), 2.0 );
//    	addSequential(new DrivePIDPath(2.5));
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE *.4 ),0.4);
   	 	addSequential(new StopWheels());
   	 	
   	 	
   	    addSequential(new DrivePIDPath(backupSecondCube,true)); //drive back 3
   	    addParallel(new DriveLiftPIDZZZ(1.5, 0.7, 0.1,1.0,true));
   	 	
   	 	addSequential(new RotateXDistancePIDZZZ(-rotateSecondCube,0.7,0.2,0.5,true));
   	    addSequential(new RotateXDistancePIDZZZ(-rotateSecondCube,0.7,0.2,0.5,true));
   	    
   	 
   	    addParallel(new DrivePIDPath(driveToCube));  //6
   	 	addSequential(new IntakeUntilCube());
   	 	addParallel(new OperationKeepCube());
   	 	addSequential(new DrivePIDPath(backupSecondCube,true));
   	 	
   	 	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
   	 	addSequential(new RotateXDistancePIDZZZ(0.0,0.7,0.2,0.5,true));
   	 	addSequential(new RotateXDistancePIDZZZ(0.0,0.7,0.2,0.5,true));
   	    
   	    addSequential(new DrivePIDPath(6.0));
   	    addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE *.4 ),0.4);
	 	addSequential(new StopWheels());
   	 	
   	 	
    	
    	 
    }
}
