package org.team2168.commands.auto.RightSide;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RetractPivotWithPiston;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToScale2CubeFromRightSide extends CommandGroup {

    public DriveToScale2CubeFromRightSide() {
    	//drive stright to null territory
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addSequential(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	
    	
    	
    	addParallel(new DriveLiftPIDZZZ(80.0, 0.5, 0.1,1.0,true));
    	
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic7, Robot.rightVelPathQuintic7, Robot.headingQuintic7));
    	addParallel(new DriveIntakeWheelsWithConstant(-0.36), 0.4);
    	addSequential(new OpenIntake());
    	
    	//drive lift down and get second cube
    	
    	addSequential(new DrivePIDPath(2.8,true)); //drive back 2.2
    	addSequential(new CloseIntake());
    	addSequential(new DriveLiftPIDZZZ(45, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(340, 210, 2500, 3000, 30000));//rotate A to B
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(210,0.5,0.15,0.1,true),0.2);
    	
    	//get 2nd cube
    	addSequential(new RetractPivotWithPiston());
    	addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(3.2));
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	//Score second cube
    	//addParallel(new ExtendPivotWithPiston());
    	addSequential(new DrivePIDPath(1.5,true)); //drive back 2.5
    	
    	addSequential(new  DrivePIDPathQuintic(210, 340, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RotateXDistancePIDZZZ(340.0,0.9,0.2,0.1,true));
    	addSequential(new DriveLiftPIDZZZ(80.0, 1.0, 0.1,1.0,true)); //70
    	addSequential(new DrivePIDPath(1.5)); //drive back 2.2
    	addSequential(new DriveIntakeWheelsWithConstant(-0.3), 0.3);
    	addParallel(new OpenIntake());
    	
    	//drive lift down and get third cube
    	addSequential(new DrivePIDPath(2.8,true)); //drive back 2.2
    	addParallel(new CloseIntake());
    	addSequential(new DriveLiftPIDZZZ(45, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(340, 120, 2500, 3000, 30000));//rotate A to B
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(240,0.5,0.15,0.1,true),0.2);
    	
    	
    	
    	
    	

//    	

    	//get 3rd cube
    	addSequential(new RetractPivotWithPiston());
    	addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addSequential(new DrivePIDPath(4.9));
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	
    	//Score 3rd cube
    	//addParallel(new ExtendPivotWithPiston());
    	addSequential(new DrivePIDPath(4.5,true)); //drive back5
 
    }
}
