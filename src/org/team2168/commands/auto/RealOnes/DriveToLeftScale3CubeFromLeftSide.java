
package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.DriveXUntilCube;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.hardStop.DisEngageIntakePivotHardStop;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.PivotIntakeUp;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.PivotIntakeDown;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive To swtich from Left side
 */
public class DriveToLeftScale3CubeFromLeftSide extends CommandGroup {

    public DriveToLeftScale3CubeFromLeftSide() {
    	
    	//drive stright to null territory
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new PivotIntakeDown()); 
    	
    	
    	
    	//addParallel(new DrivePIDPath(5.0, 5.0));
    	addParallel(new DriveLiftPIDZZZ(80.0, 0.5, 0.1,1.0,true));
    	//addParallel(new DrivePIDPath(3.5, 5.0)); //stuff might go down
    	addSequential(new DriveXDistance(185.0/12.0, 1.0));
    	addSequential(new RotateXDistancePIDZZZ(30,0.5,0.24,0.5,true));
    	addSequential(new DriveXDistance(3.2, 1.0));
    	
    	
    	addParallel(new DriveIntakeWheelsWithConstant(-0.39), 0.30); //spit
    	
    	addParallel(new EngageIntakePivotHardStop());
    	//addSequential(new OpenIntake());
    	addParallel(new DriveLiftPIDZZZ(74.0, 0.5, 0.1,1.0,true));
    	//drive lift down and get second cube
    	addParallel(new PivotIntakeUp());
    	
    	addParallel(new DrivePIDPath(2.3,true)); //drive back 2.2 //stuff will go down
    	addSequential(new Sleep(), .3);
    	addSequential(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(20, 150, 2500, 3000, 30000));//rotate A to B
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly;
    	addSequential(new RotateXDistancePIDZZZ(149,0.5,0.24,0.5,true));
    	
    	//get 2nd cube
    	addSequential(new PivotIntakeDown());
    	//addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(2.8, 5.0)); //stuff might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	//Score second cube	
    	addSequential(new DrivePIDPath(2.1, 5.0,true)); //drive back originally 2.3 //stuff will go down
    	addParallel(new PivotIntakeUp());
    	addParallel(new OperationKeepCube());
    	addParallel(new DrivePIDPathQuintic(150, 15, 2500, 3000, 30000));//rotate A to B
    	addSequential(new DriveLiftPIDZZZ(30, 1.0, 0.2,1.0,true)); //drive lift up
       	//addParallel(new EngageIntakePivotHardStop());
    	
    	addParallel(new OperationKeepCube());
    	addSequential(new RotateXDistancePIDZZZ(15.0,0.9,0.24,0.5,true));
    	addParallel(new OperationKeepCube());
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveLiftPIDZZZ(80.0, 1.0, 0.1,1.0,true)); //70
    	addSequential(new DrivePIDPath(1.6, 5.0)); //drive fwd
    	addParallel(new PivotIntakeDown());
    	addParallel(new OpenIntake());
    	addSequential(new Sleep(), 0.75);
    	//addSequential(new DriveIntakeWheelsWithConstant(-0.45), 0.3);
    	//addParallel(new OpenIntake());
    	
    	//drive lift down and get third cube
    	addParallel(new CloseIntake());
    	//addParallel(new DriveIntakeWheelsWithConstant(-0.45), 0.3); //addded
    	addParallel(new PivotIntakeUp());
    	addParallel(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slCSXowly
    	addSequential(new DrivePIDPath(2.5, 5.0, true)); //drive back 2.2 //stuff will go down 2.0
    	
    	addParallel(new CloseIntake());
    	addParallel(new  DrivePIDPathQuintic(20, 130, 2500, 3000, 30000));//rotate A to B
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(130,0.5,0.24,0.5,true));
    	
    	
    	
    	
    	

//    	

    	//get 3rd cube
    	
    	//addParallel(new OpenIntake());
    	//addParallel(new OpenIntake());
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(3.4, 4.0)); //stuff might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	//addParallel(new EngageIntakePivotHardStop());
    	
    	
    	
    	//Score 3rd cube
    	addSequential(new DrivePIDPath(3.5, 5.0, true)); //drive back5 
    	addParallel(new DriveLiftPIDZZZ(45.0, 1.0, 0.1,1.0,true));
    	addParallel(new PivotIntakeUp());
    	//addSequential(new  DrivePIDPathQuintic(130, 20, 2500, 3000, 30000));//rotate A to B
   	    addSequential(new RotateXDistancePIDZZZ(28,0.5,0.24,0.5,true));
    	//addParallel(new RotateXDistancePIDZZZ(20,0.5,0.15,0.1,true),0.2);
    	addParallel(new DriveLiftPIDZZZ(80.0, 1.0, 0.1,1.0,true)); //70
    	//addParallel(new DisEngageIntakePivotHardStop());
    	addParallel(new PivotIntakeDown());
    	addSequential(new DrivePIDPath(1.4, 3.5)); //stuff might go down
    	
    	addParallel(new OpenIntake());
    	addSequential(new DriveIntakeWheelsWithConstant(-0.3), 0.3);
    	
    }
}
 																									