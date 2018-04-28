package org.team2168.commands.intake;

import org.team2168.commands.auto.Sleep;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RobotPrep extends CommandGroup {

    public RobotPrep() {
      addSequential(new DriveLiftPIDZZZ(20.0, 0.5, 0.1,1.0,true));
      //addSequential(new EngageIntakePivotHardStop());
      addSequential(new PivotIntakeDown()); 
     // addSequential(new Sleep(),0.3);
      //addSequential(new ExtendPivotWithPiston());
      
    }
}
