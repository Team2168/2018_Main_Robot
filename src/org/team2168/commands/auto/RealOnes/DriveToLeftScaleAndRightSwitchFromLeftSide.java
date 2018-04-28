
package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.DriveXUntilCube;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive To swtich from Left side
 */
public class DriveToLeftScaleAndRightSwitchFromLeftSide extends CommandGroup {

    public DriveToLeftScaleAndRightSwitchFromLeftSide() {
    	
    	//drive stright to null territory
    	
    	addSequential(new RobotRunPrep());
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(18.0));
    	addSequential(new RotateXDistancePIDZZZ(45,0.7,0.2));
    	
    	
    	//drive lift to score height
    	addSequential(new DriveLiftPIDZZZ(80.0, 0.5, 0.1,1.0,true));
    	
    	
    	
    	//addSequential(new DrivePIDPath(2.0));
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE), 0.4 );
    	
//    	//get second cube
//    	addSequential(new DriveLiftPIDZZZ(1.5, 0.7, 0.1,1.0,true));
//    	addSequential(new RotateXDistancePIDZZZ(95,0.63,0.2));
//    	addParallel(new DrivePIDPath(4.0));
//    	addSequential(new IntakeUntilCube());
//    	
//    	
//    	//score second cube
//    	//drive lift to score height
//    	addSequential(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));
//    	addSequential(new DrivePIDPath(2.0));
//    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE), 0.4 );
    	
    	

    	
    	
    	
    	
    	
    	
    	//addSequential(new DriveXDistance(26.0,0.7,0.05));
    	//addSequential(new RotateXDistancePIDZZZ(90,0.7,0.2));
    	//addSequential(new DriveXDistance(1.8,0.7,0.05));
    	
    	//score on switch (Spit Intake)
   	 	//addSequential(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT)); 
   	 	//addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	//addParallel(new StopWheels());
   	    //addParallel(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_CONSTANT));
    }
}
