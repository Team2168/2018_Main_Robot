package org.team2168.commands.intake;

import org.team2168.commands.flippyFloopy.ExtendFlippy;
import org.team2168.commands.flippyFloopy.RetractFloopy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeUntilCubeAndPivotUp extends CommandGroup {

    public IntakeUntilCubeAndPivotUp() {
    	addParallel(new ExtendFlippy());
    	addParallel(new IntakeUntilCube());
    	addSequential(new OperationKeepCube());
    	
    }
}
