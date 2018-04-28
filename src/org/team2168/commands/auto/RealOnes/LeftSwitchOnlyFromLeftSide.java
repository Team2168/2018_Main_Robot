package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**1
 *
 */
public class LeftSwitchOnlyFromLeftSide extends CommandGroup {

	

    public LeftSwitchOnlyFromLeftSide() {
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addSequential(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addSequential(new Sleep(), 0.75);
    	addParallel(new DriveLiftPIDZZZ(40.0, 0.5, 0.1,1.0,true));

    	//drive to side of left switch
    	addSequential(new DrivePIDPath(10.0));
    	
    	
    	//rotate into switch
    	addSequential(new RotateXDistancePIDZZZ(90,0.7,0.2, 0.5, true),4.0);
    	addSequential(new RotateXDistancePIDZZZ(90,0.7,0.2 ,0.5, true ),2.0);
    
    	
    	//spit
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE *0.5),0.4);
    	addSequential(new StopWheels());
    	
    	
    	//get second cube for switch
    	

}
}
