package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
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
public class TestAuto extends CommandGroup {

    public TestAuto() {
    	//drive stright to null territory
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new PivotIntakeDown()); 
    	
    	
    	
    	//addParallel(new DrivePIDPath(5.0, 5.0));
    	//addParallel(new DrivePIDPath(3.5, 5.0)); //stuff might go down
    	addParallel(new DriveLiftPIDZZZ(30.0, 0.5, 0.1,1.0,true));
    	addSequential(new DriveXDistance(188.0/12.0, 1.0));
    	addSequential(new RotateXDistancePIDZZZ(90,0.5,0.24,0.5,true));
    	addSequential(new DriveXDistance(15.0, 1.0));
    	
    	addSequential(new DriveLiftPIDZZZ(80.0, 0.5, 0.1,1.0,true));
    	addSequential(new RotateXDistancePIDZZZ(-20,0.5,0.24,0.5,true)); //Rotate to scale
    	addSequential(new DriveXDistance(1.9, 1.0));
    	addParallel(new DriveIntakeWheelsWithConstant(-0.45)); //spit
    	//addParallel(new DrivePIDPath(1.8,true)); //drive back 2.2 //stuff will go down
    	addParallel(new EngageIntakePivotHardStop());
    	addSequential(new DrivePIDPath(2.2,true,false)); //drive back 2.2 //stuff will go down
    	//addSequential(new OpenIntake());
    	
    	//drive lift down and get second cube
    	addParallel(new PivotIntakeUp());
    	addParallel(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly;
    	addSequential(new RotateXDistancePIDZZZ(-160,0.5,0.24,0.5,true));
   	
    	
    	addSequential(new PivotIntakeDown());
    	addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(2.8, 5.0)); //stuff might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	    }
}
