package org.team2168.commands.intake;

import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Pivot arm down and spit cube
 */
public class RotatePivotDownAndSpit extends CommandGroup {

    public RotatePivotDownAndSpit() {
        addSequential(new RotatePivotDownAutomatically(RobotMap.CUBE_PIVOT_DOWN_CONSTANT), 0.4);
        addSequential(new DriveIntakeWheelsWithConstant(-RobotMap.CUBE_INTAKE_MAX_OUTAKE));
    }
}
