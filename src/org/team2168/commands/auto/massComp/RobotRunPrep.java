package org.team2168.commands.auto.massComp;

import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intakePivotPiston.ExtendPivotPiston;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RobotRunPrep extends CommandGroup {

    public RobotRunPrep() {
    	addSequential(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new ExtendPivotPiston());
    	addParallel(new IntakeUntilCube());
    	addSequential(new Sleep(), 0.5);
    	addParallel(new OperationKeepCube());
    	addSequential(new Sleep(), 0.75);}
}