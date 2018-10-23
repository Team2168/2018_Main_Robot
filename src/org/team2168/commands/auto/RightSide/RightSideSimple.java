package org.team2168.commands.auto.RightSide;

import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.PivotIntakeDown;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSideSimple extends CommandGroup {

    public RightSideSimple() {
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new PivotIntakeDown()); 
    	
    	
    	
    	//addParallel(new DrivePIDPath(5.0, 5.0));
    	addParallel(new DriveLiftPIDZZZ(81.0, 0.5, 0.1,1.0,true));
    	//addParallel(new DrivePIDPath(3.5, 5.0)); //stuff might go down
    	addSequential(new DrivePIDPath(18.0, 1.0));
    	addSequential(new Sleep(), 0.5);
    	addSequential(new RotateXDistancePIDZZZ(-90,0.45,0.24,0.5,true),2.0);
    	
    	
    	
    	addParallel(new DriveIntakeWheelsWithConstant(-0.53), 0.35); //spit
    	addSequential(new DrivePIDPath(2.0, true));
}}
