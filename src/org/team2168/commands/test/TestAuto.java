package org.team2168.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuto extends CommandGroup {

    public TestAuto() {
        addSequential(new TestCommand());
    }
}
