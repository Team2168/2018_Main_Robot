package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.StopWheels;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to left switch and left scale from center
 */
public class DriveToLeftSwitchAndLeftScale extends CommandGroup {

    public DriveToLeftSwitchAndLeftScale() {
    	addSequential(new DriveXDistance(3.0,1.0,0.05));
   	 	addSequential(new RotateXDistancePIDZZZ(-45,1.0,0.22));
   	 	addSequential(new DriveXDistance(6.0,1.0,0.05));
   	 	addSequential(new RotateXDistancePIDZZZ(45,1.0,0.22));
   	 	addSequential(new DriveXDistance(2.5,1.0,0.1));
   	 	
   	 
   	 
   	    //score on switch (Spit Intake)
   	 	
   	 	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	addParallel(new StopWheels());
	    
	    //Go Around Switch
	    addSequential(new RotateXDistancePIDZZZ(-90,1.0,.22));
	    addSequential(new DriveXDistance(4.0,1.0,0.1));
	    addSequential(new RotateXDistancePIDZZZ(90,1.0,.22));
	    addSequential(new DriveXDistance(8.0,1.0,0.1));
	    addSequential(new RotateXDistancePIDZZZ(105,1.0,.22));
	    addSequential(new DriveXDistance(2.5, 1.0,0.1));
	    
	     //pick up second cube
 	    addParallel(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_INTAKE));
 	    addSequential(new DriveXDistance(5.0,0.6,0.1));
 	    addSequential(new DriveXDistance(-4.5,1.0,0.1)); 
	 	
	 	//on the path to scale
	 	addSequential(new RotateXDistancePIDZZZ(-105,1.0,0.22));	 	
	 	addSequential(new DriveXDistance(9.0,1.0,0.1));
	 	addSequential(new RotateXDistancePIDZZZ(90,1.0,0.22));
	 	

	 	//score on switch (Spit Intake) 
   	 	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE),0.4);
   	 	addParallel(new StopWheels());
   	  
	 	
    }
}
