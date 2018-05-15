package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.PivotIntakeDown;
import org.team2168.commands.intake.PivotIntakeUp;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToLeftScaleAndLeftSwitchV2 extends CommandGroup {

    public DriveToLeftScaleAndLeftSwitchV2() {
    	//drive stright to null territory
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new PivotIntakeDown()); 
    	
    	
    	
    	addParallel(new DrivePIDPath(5.0, 5.0));
    	addParallel(new DriveLiftPIDZZZ(75.0, 0.5, 0.1,1.0,true));
    	addParallel(new DrivePIDPath(3.5, 5.0)); //stuff might go down
    	
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic6, Robot.rightVelPathQuintic6, Robot.headingQuintic6));
    	//addParallel(new OpenIntake());
    	addParallel(new DriveIntakeWheelsWithConstant(-0.40), 0.3); //spit
    	
    	addParallel(new EngageIntakePivotHardStop());
    	//addSequential(new OpenIntake());
    	
    	//drive lift down and get second cube
    	addParallel(new PivotIntakeUp());
    	
    	addParallel(new DrivePIDPath(2.8,true)); //drive back 2.2 //stuff will go down
    	addSequential(new Sleep(), .3);
    	addSequential(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(20, 150, 2500, 3000, 30000));//rotate A to B
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveLiftPIDZZZ(0.8, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(150,0.5,0.15,0.1,true));
    	
    	//get 2nd cube
    	addSequential(new PivotIntakeDown());
    	addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(2.2, 5.0)); //stuff might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	//Score second cube	
    	addSequential(new DrivePIDPath(1.8, 5.0,true)); //drive back originally 2.3 //stuff will go down
    	addSequential(new PivotIntakeUp());
    	addParallel(new PivotIntakeUp());
    	addParallel(new DriveLiftPIDZZZ(30, 1.0, 0.2,1.0,true)); //drive lift down slowly
       	//addParallel(new EngageIntakePivotHardStop());8
    	addSequential(new DrivePIDPathQuintic(150, 15, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RotateXDistancePIDZZZ(15.0,0.9,0.2,0.1,true));
    	addParallel(new PivotIntakeDown());
    	addParallel(new DriveLiftPIDZZZ(80.0, 1.0, 0.1,1.0,true)); //70
    	addSequential(new DrivePIDPath(1.6, 5.0)); //drive back 2.2 //stuff might go down
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveIntakeWheelsWithConstant(-0.36), 0.3);
    	//addParallel(new OpenIntake());
    	
    	//drive lift down and get third cube
    	addParallel(new PivotIntakeUp());
    	addParallel(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slCSXowly
    	addSequential(new DrivePIDPath(2.5, 5.0, true)); //drive back 2.2 //stuff will go down 2.0
    	
    	addParallel(new CloseIntake());
    	addParallel(new  DrivePIDPathQuintic(20, 130, 2500, 3000, 30000));//rotate A to B
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(130,0.5,0.15,0.1,true));
    	
    	
    	
    	
    	

//    	

    	//get 3rd cube
    	
    	//addParallel(new OpenIntakSequentiale());
    	//addParallel(new OpenIntake());
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(3.4, 4.0)); //stuff might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	//addParallel(new EngageIntakePivotHardStop());

    	
    	//score 3rd cube
    	addParallel(new DriveLiftPIDZZZ(40.0, 1.0, 0.1,1.0,true)); //70
    	addSequential(new DrivePIDPath(2.4, 5.0, true)); //drive back 2.2 //stuff will go down 2.0
    	addSequential(new RotateXDistancePIDZZZ(170,0.5,0.15,0.1,true), 0.8);
    	addSequential(new DrivePIDPath(2.8, 5.0)); //drive back 2.2 //stuff will go down 2.0
    	addSequential(new OpenIntake());
    	
    }
}
