package org.team2168.commands.intake;

import org.team2168.commands.hardStop.DisEngageIntakePivotHardStop;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeUntilCubeAndPivotUp extends CommandGroup {

    public IntakeUntilCubeAndPivotUp() {
    	addParallel(new EngageIntakePivotHardStop());
    	addParallel(new IntakeUntilCube());
    	addSequential(new OperationKeepCube());
    	
    }
}
