package org.team2168.commands.auto.massComp;

import org.team2168.Robot;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToLeftSwitchFromLeftSide2 extends CommandGroup {

    public DriveToLeftSwitchFromLeftSide2() {
        
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic, Robot.rightVelPathQuintic, Robot.headingQuintic));
    	addSequential(new Sleep(), 2.0);
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic2, Robot.rightVelPathQuintic2, Robot.headingQuintic2, true));
    	addSequential(new RotateXDistancePIDZZZ(140,0.6,0.2,0.5,true));
    	addSequential(new RotateXDistancePIDZZZ(140,0.6,0.2,0.5,true));
    	addSequential(new DrivePIDPath(5.5));
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
