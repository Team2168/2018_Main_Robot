package org.team2168.commands.intake;

import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.flippyFloopy.ExtendFlippy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Pivot arm down and spit cube
 */
public class RotatePivotDownAndSpit extends CommandGroup {

    public RotatePivotDownAndSpit() {
        addSequential(new ExtendFlippy(), 0.6);
        addSequential(new DriveIntakeWheelsWithConstant(RobotMap.CUBE_INTAKE_MAX_OUTAKE));
    }
}
