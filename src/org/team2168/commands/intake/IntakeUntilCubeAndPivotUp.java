package org.team2168.commands.intake;

import org.team2168.commands.intakePivotPiston.ExtendPivotPiston;
import org.team2168.commands.intakePivotPiston.RetractPivotPiston;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeUntilCubeAndPivotUp extends CommandGroup {

    public IntakeUntilCubeAndPivotUp() {
    	addParallel(new ExtendPivotPiston());
    	addParallel(new IntakeUntilCube());
    	addSequential(new OperationKeepCube());
    	
    }
}
