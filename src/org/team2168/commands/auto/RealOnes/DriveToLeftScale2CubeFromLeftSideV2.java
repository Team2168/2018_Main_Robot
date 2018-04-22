
package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.DriveXUntilCube;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.flippyFloopy.EngageIntakePivotHardStop;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.ExtendPivotWithPiston;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RetractPivotWithPiston;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive To swtich from Left side
 */
public class DriveToLeftScale2CubeFromLeftSideV2 extends CommandGroup {

    public DriveToLeftScale2CubeFromLeftSideV2() {
    	
    	//drive stright to null territory
    	
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new RetractPivotWithPiston()); 
    	
    	
    	
    	
    	addParallel(new DriveLiftPIDZZZ(78.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic6, Robot.rightVelPathQuintic6, Robot.headingQuintic6));
    	
    	addParallel(new DriveIntakeWheelsWithConstant(-0.3), 0.2);
    	//addSequential(new OpenIntake());
    	
    	//drive lift down and get second cube
    	addParallel(new ExtendPivotWithPiston());
    	addParallel(new DrivePIDPath(2.8,true)); //drive back 2.2
    	addSequential(new Sleep(), .3);
    	addSequential(new CloseIntake());
    	addSequential(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(20, 150, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RetractPivotWithPiston());
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(150,0.5,0.15,0.1,true),0.2);
    	
    	//get 2nd cube
    	addSequential(new RetractPivotWithPiston());
    	addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(2.2));
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	//Score second cube
    	//addParallel(new ExtendPivotWithPiston());
    	
    	addSequential(new DrivePIDPath(2.1,true)); //drive back 2.5
       	addParallel(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new DrivePIDPathQuintic(150, 20, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RotateXDistancePIDZZZ(20.0,0.9,0.2,0.1,true));
    	addSequential(new DriveLiftPIDZZZ(80.0, 1.0, 0.1,1.0,true)); //70
    	addSequential(new DrivePIDPath(2.0)); //drive back 2.2
    	addSequential(new DriveIntakeWheelsWithConstant(-0.2), 0.3);
    	//addParallel(new OpenIntake());
    	
    	//drive lift down and get third cube
    	addParallel(new DrivePIDPath(2.8,true)); //drive back 2.2
    	addSequential(new Sleep(), .3);
    	addParallel(new CloseIntake());
    	addSequential(new DriveLiftPIDZZZ(45, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(20, 120, 2500, 3000, 30000));//rotate A to B
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(120,0.5,0.15,0.1,true),0.2);
    	
    	
    	
    	
    	

//    	

    	//get 3rd cube
    	addSequential(new RetractPivotWithPiston());
    	//addParallel(new OpenIntake());
    	addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addSequential(new DrivePIDPath(3.5));
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	
    	//Score 3rd cube
    	//addParallel(new ExtendPivotWithPiston());
    	addParallel(new DriveLiftPIDZZZ(40.0, 1.0, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(4.5,true)); //drive back5
    	addSequential(new  DrivePIDPathQuintic(120, 35, 2500, 3000, 30000));//rotate A to B
    	//addParallel(new RotateXDistancePIDZZZ(20,0.5,0.15,0.1,true),0.2);
    	addSequential(new DriveLiftPIDZZZ(80.0, 1.0, 0.1,1.0,true)); //70
    	addSequential(new DrivePIDPath(3.0));
    	addSequential(new DriveIntakeWheelsWithConstant(-0.2), 0.3);
    	
    }
}
 																									