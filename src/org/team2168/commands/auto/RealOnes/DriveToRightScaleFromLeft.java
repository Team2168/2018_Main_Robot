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
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.ExtendPivotWithPiston;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.RetractPivotWithPiston;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to right switch boiii
 */
public class DriveToRightScaleFromLeft extends CommandGroup {

    public DriveToRightScaleFromLeft() {
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new RetractPivotWithPiston()); 
    	
    	
    	
    	addParallel(new DrivePIDPath(5.0, 5.0));
    	addParallel(new DriveLiftPIDZZZ(35.0, 0.5, 0.1,1.0,true));
    	addParallel(new DrivePIDPath(3.5, 5.0)); //shit might go down
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic5, Robot.rightVelPathQuintic5, Robot.headingQuintic5));
    	addSequential(new DriveLiftPIDZZZ(74.0, 0.8, 0.1,1.0,true));
    	addSequential(new  DrivePIDPathQuintic(0, -30, 2500, 3000, 30000));//rotate A to B
    	addParallel(new DriveIntakeWheelsWithConstant(-0.45), 0.3);
   
    	addParallel(new ExtendPivotWithPiston());
    	addParallel(new DrivePIDPath(1.5,true)); //drive back 2.2 //shit will go down
    	
    	addSequential(new Sleep(), .3);
    	addSequential(new CloseIntake());
    	addSequential(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(-30, -155, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RetractPivotWithPiston());
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(-155,0.5,0.15,0.1,true),0.2);
    	
    	//get 2nd cube
    	//addSequential(new RetractPivotWithPiston());
    	//addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(3.5, 5.0)); //shit might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	addSequential(new DrivePIDPath(2.3, 5.0,true)); //drive back 2.5 //shit will go down
       	addParallel(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new DrivePIDPathQuintic(-155 , -20, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RotateXDistancePIDZZZ(-20.0,0.9,0.2,0.1,true));
    	addSequential(new DriveLiftPIDZZZ(80.0, 7.0, 0.1,1.0,true)); //70
    	addSequential(new DrivePIDPath(1.4, 5.0)); //drive back 2.2 //shit might go down
    	addSequential(new DriveIntakeWheelsWithConstant(-0.4), 0.4);
    	
    	
    	 
    }
}
