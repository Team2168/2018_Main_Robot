package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.ShiftHigh;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.DriveXUntilCube;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.PivotIntakeDown;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to left switch boiii
 */
public class DriveToLeftSwitch extends CommandGroup {
	
	double backupSecondCube = 5.0;
	double driveToCube = 4.5;
	double rotateSecondCube = -40;

    public DriveToLeftSwitch() {
    	//this is da monnay for the the intake to go down
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new PivotIntakeDown()); 
    	
    	//move lift up and drive path to left switch
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic4, Robot.rightVelPathQuintic4, Robot.headingQuintic4)); 	
    	
    	//spit cube after path
    	addSequential(new DriveIntakeWheelsWithConstant(-0.45),0.25);
   	 	
   	 	
   	 	//second cube
   	 	addSequential(new DrivePIDPath(3.5,5.0,true)); //drive back 3
   	 	addParallel(new DriveLiftPIDZZZ(0.0, 0.7, 0.1,1.0,true));
   	  //  addSequential(new  DrivePIDPathQuintic(0, 40, 2500, 3000, 30000));//rotate A to B
   	 	
   	    //addSequential(new RotateXDistancePIDZZZ(-rotateSecondCube,0.6,0.4,0.5,true));
   	    addSequential(new RotateXDistancePIDZZZ(38,0.5,0.24,0.5,true));
   	 	addParallel(new OpenIntake());
   	    addParallel(new IntakeUntilCube()); 
   	 	addSequential(new DrivePIDPath(2.5,5.0));  //6
   	 	addParallel(new OperationKeepCube());
   	 	addParallel(new CloseIntake());
   	 	addSequential(new DrivePIDPath(2, 5.0,true));
        addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
        addSequential(new  DrivePIDPathQuintic(40, -10, 2500, 3000, 30000));//rotate A to B
   	 	addSequential(new RotateXDistancePIDZZZ(0,0.6,0.2,0.5,true), 0.3);
   	    
   	 	
   	 	
   	 	addSequential(new DrivePIDPath(3.0, 5.0));
   	    addSequential(new DriveIntakeWheelsWithConstant(-0.4 ),0.2);
	 	
	 	
	 	//3rd cube
	 	addSequential(new DrivePIDPath(backupSecondCube,5.0,true)); //drive back 3
   	 	addParallel(new DriveLiftPIDZZZ(10.0, 0.7, 0.1,1.0,true));
   	    //addSequential(new  DrivePIDPathQuintic(0, 35, 2500, 3000, 30000));//rotate A to B
   	    addSequential(new RotateXDistancePIDZZZ(35,0.5,0.24,0.5,true));
   	    addParallel(new OpenIntake());
	    addParallel(new IntakeUntilCube()); 
	 	addSequential(new DrivePIDPath(4.8,5.0));  //6
	 	addParallel(new OperationKeepCube());
	 	addParallel(new CloseIntake());
	 	addSequential(new DrivePIDPath(3,5.0,true));
        addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
        addSequential(new  DrivePIDPathQuintic(40, -20, 2500, 3000, 30000));//rotate A to B
	 	addSequential(new RotateXDistancePIDZZZ(0,0.6,0.2,0.5,true), 0.3);
	 	
	 	addSequential(new DrivePIDPath(4.0,5.0));
	 	addSequential(new RotateXDistancePIDZZZ(0,0.5,0.24,0.5,true));
	 	addSequential(new DriveIntakeWheelsWithConstant(-0.45 ),0.25);
	 	addSequential(new StopWheels());
 	 
    }
}
