package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.RotatePivotDownAutomatically;
import org.team2168.commands.intake.RotatePivotUpAutomatically;
import org.team2168.commands.intake.StopWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to right switch from and right scale from center
 */
public class DriveToRightSwitchAndRightScale extends CommandGroup {

    public DriveToRightSwitchAndRightScale() {
        
    	addSequential(new DriveXDistance(2.41,0.7,0.05));
      	addSequential(new RotateXDistancePIDZZZ(45,0.7,0.2));
      	addSequential(new DriveXDistance(5.5,0.9,0.05));
      	addSequential(new RotateXDistancePIDZZZ(-45,0.7,0.2));
      	addSequential(new DriveXDistance(2.0 ,0.6,0.1));
      	 
      //score on switch (Spit Intake)
   	 	addSequential(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT)); 
   	 	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	addParallel(new StopWheels());
   	    addParallel(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_CONSTANT));
   	    
   	  //Go Around Switch
   	    addSequential(new RotateXDistancePIDZZZ(90,1.0,.22));
   	    addSequential(new DriveXDistance(4.1,1.0,0.1));
   	    addSequential(new RotateXDistancePIDZZZ(-90,1.0,.22));
   	    addSequential(new DriveXDistance(9.3,1.0,0.1));
   	    addSequential(new RotateXDistancePIDZZZ(-99,1.0,.22));
   	    addSequential(new DriveXDistance(1.0, 1.0,0.1));
      	 
   	//pick up second cube
 	    addSequential(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_CONSTANT)); 
 	    addParallel(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_INTAKE));
 	    addSequential(new DriveXDistance(5.0,0.6,0.1));
 	    addSequential(new DriveXDistance(-4.5,1.0,0.1));
   	 	
   	 	//go to scale
   	 	addSequential(new DriveXDistance(-2.5,1.0,0.1));
   	    addSequential(new RotateXDistancePIDZZZ(99,1.0,.22));
   	    addSequential(new DriveXDistance(8.0,1.0,0.1));
   	    addSequential(new RotateXDistancePIDZZZ(-90,1.0,.22));
   	    addSequential(new DriveXDistance(1.3,1.0,0.1));
   	//score on switch (Spit Intake)
   	 	addSequential(new RotatePivotUpAutomatically(RobotMap.CUBE_PIVOT_CONSTANT)); 
   	 	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	addParallel(new StopWheels());
   	    addParallel(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_CONSTANT));
   	 
   	 	
   	 	
    }
}
