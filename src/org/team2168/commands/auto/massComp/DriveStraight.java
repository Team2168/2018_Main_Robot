package org.team2168.commands.auto.massComp;

import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraight extends CommandGroup {

    public DriveStraight(double dist) {
        addSequential(new DrivePIDPath(12.0));
    }
    
    public DriveStraight() {
        addSequential(new DrivePIDPath(9.5));
    }
}
