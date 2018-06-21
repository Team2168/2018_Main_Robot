package org.team2168.commands.auto.RealOnes;

import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.PivotIntakeDown;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NeverRunMe extends CommandGroup {

    public NeverRunMe() {
    	addParallel(new DriveLiftPIDZZZ(35.0, 0.5, 0.1,1.0,true));
    	addSequential(new DriveXDistance(183/12.0, 1.0));
    	
    	addSequential(new RotateXDistancePIDZZZ(89,0.5,0.24,0.45,true));
    	
    	addSequential(new DriveXDistance(8.3, 1.0));
    	addSequential(new RotateXDistancePIDZZZ(0,0.5,0.24,0.45,true));
    	addSequential(new DriveXDistance(3.1, 1.0));
    	addParallel(new PivotIntakeDown()); 
    	addSequential(new RotateXDistancePIDZZZ(90,0.5,0.24,0.45,true));
    	addSequential(new DriveLiftPIDZZZ(82.0, 0.5, 0.1,1.0,true));
    	addSequential(new DrivePIDPath(3.1,true));
    }
}
