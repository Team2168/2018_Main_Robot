package org.team2168.commands.intake;

import org.team2168.commands.auto.Sleep;
import org.team2168.commands.flippyFloopy.EngageIntakePivotHardStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class test extends CommandGroup {

    public test() {
      addParallel(new RetractPivotWithPiston());
      addParallel(new EngageIntakePivotHardStop());
      addSequential(new Sleep(),0.5);
      addSequential(new ExtendPivotWithPiston());
      
    }
}
