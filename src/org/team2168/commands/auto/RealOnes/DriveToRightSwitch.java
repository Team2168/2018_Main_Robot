package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.flippyFloopy.EngageIntakePivotHardStop;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intake.RobotPrep;
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
    	//this is da monnay for the the intake to go down
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addSequential(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addSequential(new Sleep(), 0.4);
    	//move lift up and drive path to right switch
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic3, Robot.rightVelPathQuintic3, Robot.headingQuintic3));
    	//spit cube after path    	
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE *.4 ),0.4);
   	 	addSequential(new StopWheels());
   	 	
    	//second cube
   	    addSequential(new DrivePIDPath(backupSecondCube,true)); //drive back 3
   	    addParallel(new DriveLiftPIDZZZ(0.0, 0.7, 0.1,1.0,true));	 	
   	 	addSequential(new RotateXDistancePIDZZZ(-rotateSecondCube,0.6,0.4,0.5,true));
   	    addSequential(new RotateXDistancePIDZZZ(-rotateSecondCube,0.6,0.4,0.5,true));
   	    
   	    addSequential(new DriveLiftPIDZZZ(0.0, 0.7, 0.1,1.0,true));
	 	addParallel(new IntakeUntilCube()); 
   	    addSequential(new DrivePIDPath(driveToCube));  //6
   	 	addParallel(new OperationKeepCube());
   	 	addSequential(new DrivePIDPath(backupSecondCube,true));
   	 	
   	 	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
   	 	addSequential(new RotateXDistancePIDZZZ(0.0,0.6,0.2,0.5,true));
   	 	addSequential(new RotateXDistancePIDZZZ(0.0,0.6,0.2,0.5,true));
   	    
   	    addSequential(new DrivePIDPath(6.0));
   	    addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE *.4 ),0.4);
	 	addSequential(new StopWheels());
   	 	
   	 	
    	
    	 
    }
}