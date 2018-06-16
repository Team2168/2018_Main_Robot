package org.team2168.commands.intake;

import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FadeAway extends CommandGroup {

    public FadeAway() {
        addParallel(new DriveIntakeWheelsWithConstant(-.40), 1.0);
        addSequential(new DrivePIDPath(2.2,true)); //drive back 2.2 //stuff will go down
        //addSequential(new DrivePIDPath(3.5, 4.0, true, true));
    }
}
	